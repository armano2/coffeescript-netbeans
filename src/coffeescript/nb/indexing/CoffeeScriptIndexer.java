// Copyright 2014 Miloš Pensimus
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package coffeescript.nb.indexing;

import coffeescript.nb.antlr.parser.definitions.ClassDefinition;
import coffeescript.nb.antlr.parser.definitions.Definition;
import coffeescript.nb.antlr.parser.definitions.MethodDefinition;
import coffeescript.nb.antlr.parser.definitions.VariableDefinition;
import coffeescript.nb.core.CoffeeScriptUtils;
import coffeescript.nb.core.Constants;
import coffeescript.nb.options.CoffeeScriptSettings;
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
 * @author Miloš Pensimus
 */
public class CoffeeScriptIndexer extends EmbeddingIndexer {

    private static final Logger LOGGER = Logger.getLogger(CoffeeScriptIndexer.class.getSimpleName());

    @Override
    protected void index(Indexable indexable, Result parserResult, Context context) {
        if(isParserResultValid(parserResult)) storeItems(indexable, parserResult, context);
    }
    
    private void storeItems(Indexable indexable, Result parserResult, Context context) {
        
        try {
            IndexingSupport support = IndexingSupport.getInstance(context);
            IndexDocument document = support.createDocument(indexable);
            ParsingResult result = (ParsingResult) parserResult;

            for(VariableDefinition def : result.getFileDefinition().getFields())  {
                document.addPair(CoffeeScriptIndex.FIELD_KEY, def.serialize(), true, true);
            }
            
            for(VariableDefinition def : result.getFileDefinition().getRootFields())  {
                document.addPair(CoffeeScriptIndex.ROOT_FIELD_KEY, def.serialize(), true, true);
            }
            
            for(MethodDefinition def : result.getFileDefinition().getMethods())  {
                document.addPair(CoffeeScriptIndex.METHOD_KEY, def.serialize(), true, true);
                for(Definition param: def.getParams()) {
                    document.addPair(CoffeeScriptIndex.METHOD_PARAM_KEY, param.serialize(), true, true);
                }
            }            
            
            for(MethodDefinition def : result.getFileDefinition().getRootMethods()) {
                document.addPair(CoffeeScriptIndex.ROOT_METHOD_KEY, def.serialize(), true, true);
                for(Definition param: def.getParams()) {
                    document.addPair(CoffeeScriptIndex.METHOD_PARAM_KEY, param.serialize(), true, true);
                }
            }
            
            for(ClassDefinition def : result.getFileDefinition().getRootClasses()) {
                document.addPair(CoffeeScriptIndex.ROOT_CLASS_KEY, def.serialize(), true, true);
                for(MethodDefinition method: def.getMethods()) {
                    document.addPair(CoffeeScriptIndex.CLASS_METHOD_KEY, method.serialize(), true, true);
                    for(Definition param: method.getParams()) {
                        document.addPair(CoffeeScriptIndex.METHOD_PARAM_KEY, param.serialize(), true, true);
                    }
                }
                for(MethodDefinition method: def.getConstructors()) {
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
    
    private boolean isParserResultValid(Result result) {
        ParsingResult parsingResult = (ParsingResult) result;
        if(!CoffeeScriptSettings.get().isLegacy() && parsingResult.isLegacy()) {
            CoffeeScriptUtils.reindexFile(result.getSnapshot().getSource().getFileObject());
            return false;
        }
        
        return !parsingResult.hasErrors();
    }

    public static class Factory extends EmbeddingIndexerFactory {

        public static final String NAME = "CoffeeScript"; //NOI18N
        static final int VERSION = 4;

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
        public void scanFinished(Context context) {
            CoffeeScriptUtils.cleanupAntlrATNConfig();
            super.scanFinished(context);
        }   

        @Override
        public boolean scanStarted(Context context) {
            CoffeeScriptUtils.cleanupAntlrATNConfig();
            return super.scanStarted(context);
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
