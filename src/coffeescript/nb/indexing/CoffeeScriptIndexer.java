/*
 * Copyright(c) Zdenek Tronicek, FIT CTU in Prague. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (CDDL). You may not use this file except in
 * compliance with the CDDL. You can obtain a copy of the CDDL at
 * http://www.netbeans.org/cddl.html.
 *
 */
package coffeescript.nb.indexing;

import coffeescript.nb.antlr.parser.definitions.CoffeeScriptFileDefinition;
import coffeescript.nb.core.context.ICoffeeScriptMainContext;
import coffeescript.nb.core.Constants;
import coffeescript.nb.parser.ParseAction;
import coffeescript.nb.parser.ParsingResult;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.modules.parsing.api.Snapshot;
import org.netbeans.modules.parsing.spi.Parser.Result;
import org.netbeans.modules.parsing.spi.indexing.Context;
import org.netbeans.modules.parsing.spi.indexing.EmbeddingIndexer;
import org.netbeans.modules.parsing.spi.indexing.EmbeddingIndexerFactory;
import org.netbeans.modules.parsing.spi.indexing.Indexable;
import org.netbeans.modules.parsing.spi.indexing.support.IndexDocument;
import org.netbeans.modules.parsing.spi.indexing.support.IndexingSupport;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

/**
 *
 * @author Denis Stepanov
 */
public class CoffeeScriptIndexer extends EmbeddingIndexer {

    private static final Logger LOGGER = Logger.getLogger(CoffeeScriptIndexer.class.getSimpleName());
    private static final boolean LOG = LOGGER.isLoggable(Level.FINE);

    @Override
    protected void index(Indexable indexable, Result parserResult, Context context) {
        try {
            ICoffeeScriptMainContext coffeeScriptFilesContext = Lookup.getDefault().lookup(ICoffeeScriptMainContext.class);
            IndexingSupport support = IndexingSupport.getInstance(context);
            IndexDocument document = support.createDocument(indexable);
            ParsingResult result = (ParsingResult) parserResult;
            if(result != null) {
                if(!result.isLegacy()) {
                    FileObject fo = parserResult.getSnapshot().getSource().getFileObject();
                    coffeeScriptFilesContext.addDefinition(fo, result.getGd());
                }
            }
            document.addPair(CoffeeScriptIndex.CONTENT_KEY, Boolean.TRUE.toString(), true, true);
            support.addDocument(document);
            
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    public static class Factory extends EmbeddingIndexerFactory {

        static final String NAME = "CoffeeScript"; //NOI18N
        static final int VERSION = 1;

        @Override
        public EmbeddingIndexer createIndexer(Indexable indexable, Snapshot snapshot) {
            if (isIndexable(snapshot)) {
                return new CoffeeScriptIndexer();
            } else {
                return null;
            }
        }

        @Override
        public void filesDeleted(Iterable<? extends Indexable> deleted, Context context) {
            try {
                IndexingSupport is = IndexingSupport.getInstance(context);
                for (Indexable i : deleted) {
                    is.removeDocuments(i);
                }
            } catch (IOException ioe) {
                LOGGER.log(Level.WARNING, null, ioe);
            }
        }

        @Override
        public void filesDirty(Iterable<? extends Indexable> dirty, Context context) {
            try {
                IndexingSupport is = IndexingSupport.getInstance(context);
                for (Indexable i : dirty) {
                    is.markDirtyDocuments(i);
                }
            } catch (IOException ioe) {
                LOGGER.log(Level.WARNING, null, ioe);
            }
        }

        @Override
        public String getIndexerName() {
            return NAME;
        }

        @Override
        public int getIndexVersion() {
            return VERSION;
        }

        private boolean isIndexable(Snapshot snapshot) {
            return Constants.MIME_TYPE.equals(snapshot.getMimeType());
        }
    }
}
