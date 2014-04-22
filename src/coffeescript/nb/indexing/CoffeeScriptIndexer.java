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

import coffeescript.nb.antlr.parser.definitions.ClassDefinition;
import coffeescript.nb.antlr.parser.definitions.Definition;
import coffeescript.nb.antlr.parser.definitions.MethodDefinition;
import coffeescript.nb.antlr.parser.definitions.VariableDefinition;
import coffeescript.nb.core.Constants;
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
import org.openide.util.Exceptions;

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
            IndexingSupport support = IndexingSupport.getInstance(context);
            IndexDocument document = support.createDocument(indexable);
            ParsingResult result = (ParsingResult) parserResult;
            
            for(VariableDefinition def : result.getGd().getFields())  {
                document.addPair(CoffeeScriptIndex.FIELD_KEY, def.serialize(), true, true);
            }
            
            for(VariableDefinition def : result.getGd().getRootFields())  {
                document.addPair(CoffeeScriptIndex.ROOT_FIELD_KEY, def.serialize(), true, true);
            }
            
            for(MethodDefinition def : result.getGd().getMethods())  {
                document.addPair(CoffeeScriptIndex.METHOD_KEY, def.serialize(), true, true);
                for(Definition param: def.getParams()) {
                    document.addPair(CoffeeScriptIndex.METHOD_PARAM_KEY, param.serialize(), true, true);
                }
            }            
            
            for(MethodDefinition def : result.getGd().getRootMethods()) {
                document.addPair(CoffeeScriptIndex.ROOT_METHOD_KEY, def.serialize(), true, true);
                for(Definition param: def.getParams()) {
                    document.addPair(CoffeeScriptIndex.METHOD_PARAM_KEY, param.serialize(), true, true);
                }
            }
            
            for(ClassDefinition def : result.getGd().getRootClasses()) {
                document.addPair(CoffeeScriptIndex.ROOT_CLASS_KEY, def.serialize(), true, true);
                for(MethodDefinition method: def.getMethods()) {
                    document.addPair(CoffeeScriptIndex.CLASS_METHOD_KEY, method.serialize(), true, true);
                    for(Definition param: method.getParams()) {
                        document.addPair(CoffeeScriptIndex.METHOD_PARAM_KEY, param.serialize(), true, true);
                    }
                }
                for(VariableDefinition field : def.getFields())  {
                    document.addPair(CoffeeScriptIndex.CLASS_FIELD_KEY, field.serialize(), true, true);
                }
            }
            
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
        public boolean scanStarted(Context context) {
            return super.scanStarted(context); //To change body of generated methods, choose Tools | Templates.
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
