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
package coffeescript.nb.completion;

import coffeescript.nb.core.CoffeeScriptTestBase;
import coffeescript.nb.completion.items.FieldCompletionItem;
import coffeescript.nb.core.CoffeeScriptUtils;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JEditorPane;
import org.netbeans.modules.editor.completion.CompletionImpl;
import org.netbeans.modules.editor.completion.CompletionResultSetImpl;
import org.netbeans.modules.parsing.api.Source;
import org.netbeans.spi.editor.completion.CompletionItem;
import org.netbeans.spi.editor.completion.CompletionProvider;
import org.netbeans.spi.editor.completion.CompletionTask;
import org.openide.filesystems.FileObject;
import org.openide.util.test.MockLookup;

/**
 *
 * @author milos
 */
public class CompletionTest extends CoffeeScriptTestBase {

    private final String testFileName = "completion.coffee";
    private final String testContextFileName = "completioncontext.coffee";

    public CompletionTest(String name) throws Exception {
        super(name);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        indexFile(getFileObjectInDataDir(testProjectSourcePath + testFileName));
        indexFile(getFileObjectInDataDir(testProjectSourcePath + "class1.coffee"));
        indexFile(getFileObjectInDataDir(testProjectSourcePath + "class2.coffee"));
        indexFile(getFileObjectInDataDir(testProjectSourcePath + "methods1.coffee"));
        indexFile(getFileObjectInDataDir(testProjectSourcePath + "fields1.coffee"));
    }

    public void testCompletionContextClassOnlyNoFilter1() throws Exception {
        CompletionQuery cq = new CompletionQuery(true);
        FileObject fileObject = getFileObjectInDataDir(testProjectSourcePath + testContextFileName);
        int offset = 3;
        CompletionContext context = cq.getCompletionContext(getDocument(fileObject), offset, fileObject, CoffeeScriptUtils.getIndex());
        assertEquals(fileObject, context.getFile());
        assertEquals("", context.getFilter());
        assertEquals(CompletionContext.ContextType.CLASS_ONLY_ACCESSOR, context.getType());
        assertEquals("A", context.getClassName());
        assertTrue(context.hasClass());
    }

    public void testCompletionContextClassOnlyNoFilter2() throws Exception {
        CompletionQuery cq = new CompletionQuery(true);
        FileObject fileObject = getFileObjectInDataDir(testProjectSourcePath + testContextFileName);
        int offset = 8;
        CompletionContext context = cq.getCompletionContext(getDocument(fileObject), offset, fileObject, CoffeeScriptUtils.getIndex());
        assertEquals(fileObject, context.getFile());
        assertEquals("", context.getFilter());
        assertEquals(CompletionContext.ContextType.CLASS_ONLY_ACCESSOR, context.getType());
        assertEquals("A", context.getClassName());
        assertTrue(context.hasClass());
    }

    public void testCompletionContextClassOnlyFilter1() throws Exception {
        CompletionQuery cq = new CompletionQuery(true);
        FileObject fileObject = getFileObjectInDataDir(testProjectSourcePath + testContextFileName);
        int offset = 38;
        CompletionContext context = cq.getCompletionContext(getDocument(fileObject), offset, fileObject, CoffeeScriptUtils.getIndex());
        assertEquals(fileObject, context.getFile());
        assertEquals("fil", context.getFilter());
        assertEquals(CompletionContext.ContextType.CLASS_ONLY_ACCESSOR, context.getType());
        assertEquals("A", context.getClassName());
        assertTrue(context.hasClass());
    }

    public void testCompletionContextClassOnlyFilter2() throws Exception {
        CompletionQuery cq = new CompletionQuery(true);
        FileObject fileObject = getFileObjectInDataDir(testProjectSourcePath + testContextFileName);
        int offset = 46;
        CompletionContext context = cq.getCompletionContext(getDocument(fileObject), offset, fileObject, CoffeeScriptUtils.getIndex());
        assertEquals(fileObject, context.getFile());
        assertEquals("fil", context.getFilter());
        assertEquals(CompletionContext.ContextType.CLASS_ONLY_ACCESSOR, context.getType());
        assertEquals("A", context.getClassName());
        assertTrue(context.hasClass());
    }

    public void testCompletionContextAccessorNoFilter1() throws Exception {
        CompletionQuery cq = new CompletionQuery(true);
        FileObject fileObject = getFileObjectInDataDir(testProjectSourcePath + testContextFileName);
        int offset = 11;
        CompletionContext context = cq.getCompletionContext(getDocument(fileObject), offset, fileObject, CoffeeScriptUtils.getIndex());
        assertEquals(fileObject, context.getFile());
        assertEquals("", context.getFilter());
        assertEquals(CompletionContext.ContextType.NORMAL_ACCESSOR, context.getType());
        assertNull(context.getClassName());
        assertFalse(context.hasClass());
    }

    public void testCompletionContextAccessorNoFilter2() throws Exception {
        CompletionQuery cq = new CompletionQuery(true);
        FileObject fileObject = getFileObjectInDataDir(testProjectSourcePath + testContextFileName);
        int offset = 15;
        CompletionContext context = cq.getCompletionContext(getDocument(fileObject), offset, fileObject, CoffeeScriptUtils.getIndex());
        assertEquals(fileObject, context.getFile());
        assertEquals("", context.getFilter());
        assertEquals(CompletionContext.ContextType.NORMAL_ACCESSOR, context.getType());
        assertNull(context.getClassName());
        assertFalse(context.hasClass());
    }

    public void testCompletionContextAccessorFilter1() throws Exception {
        CompletionQuery cq = new CompletionQuery(true);
        FileObject fileObject = getFileObjectInDataDir(testProjectSourcePath + testContextFileName);
        int offset = 52;
        CompletionContext context = cq.getCompletionContext(getDocument(fileObject), offset, fileObject, CoffeeScriptUtils.getIndex());
        assertEquals(fileObject, context.getFile());
        assertEquals("fil", context.getFilter());
        assertEquals(CompletionContext.ContextType.NORMAL_ACCESSOR, context.getType());
        assertNull(context.getClassName());
        assertFalse(context.hasClass());
    }

    public void testCompletionContextAccessorFilter2() throws Exception {
        CompletionQuery cq = new CompletionQuery(true);
        FileObject fileObject = getFileObjectInDataDir(testProjectSourcePath + testContextFileName);
        int offset = 59;
        CompletionContext context = cq.getCompletionContext(getDocument(fileObject), offset, fileObject, CoffeeScriptUtils.getIndex());
        assertEquals(fileObject, context.getFile());
        assertEquals("fil", context.getFilter());
        assertEquals(CompletionContext.ContextType.NORMAL_ACCESSOR, context.getType());
        assertNull(context.getClassName());
        assertFalse(context.hasClass());
    }

    public void testCompletionContextNewNoFilter() throws Exception {
        CompletionQuery cq = new CompletionQuery(true);
        FileObject fileObject = getFileObjectInDataDir(testProjectSourcePath + testContextFileName);
        int offset = 20;
        CompletionContext context = cq.getCompletionContext(getDocument(fileObject), offset, fileObject, CoffeeScriptUtils.getIndex());
        assertEquals(fileObject, context.getFile());
        assertEquals("", context.getFilter());
        assertEquals(CompletionContext.ContextType.NEW_INSTANCE, context.getType());
        assertNull(context.getClassName());
        assertFalse(context.hasClass());
    }

    public void testCompletionContextNewFilter() throws Exception {
        CompletionQuery cq = new CompletionQuery(true);
        FileObject fileObject = getFileObjectInDataDir(testProjectSourcePath + testContextFileName);
        int offset = 67;
        CompletionContext context = cq.getCompletionContext(getDocument(fileObject), offset, fileObject, CoffeeScriptUtils.getIndex());
        assertEquals(fileObject, context.getFile());
        assertEquals("fil", context.getFilter());
        assertEquals(CompletionContext.ContextType.NEW_INSTANCE, context.getType());
        assertNull(context.getClassName());
        assertFalse(context.hasClass());
    }

    public void testCompletionContextExtendsNoFilter() throws Exception {
        CompletionQuery cq = new CompletionQuery(true);
        FileObject fileObject = getFileObjectInDataDir(testProjectSourcePath + testContextFileName);
        int offset = 29;
        CompletionContext context = cq.getCompletionContext(getDocument(fileObject), offset, fileObject, CoffeeScriptUtils.getIndex());
        assertEquals(fileObject, context.getFile());
        assertEquals("", context.getFilter());
        assertEquals(CompletionContext.ContextType.EXTENDS, context.getType());
        assertNull(context.getClassName());
        assertFalse(context.hasClass());
    }

    public void testCompletionContextExtendsFilter() throws Exception {
        CompletionQuery cq = new CompletionQuery(true);
        FileObject fileObject = getFileObjectInDataDir(testProjectSourcePath + testContextFileName);
        int offset = 79;
        CompletionContext context = cq.getCompletionContext(getDocument(fileObject), offset, fileObject, CoffeeScriptUtils.getIndex());
        assertEquals(fileObject, context.getFile());
        assertEquals("fil", context.getFilter());
        assertEquals(CompletionContext.ContextType.EXTENDS, context.getType());
        assertNull(context.getClassName());
        assertFalse(context.hasClass());
    }

    public void testCompletionContextDefaultNoFilter() throws Exception {
        CompletionQuery cq = new CompletionQuery(true);
        FileObject fileObject = getFileObjectInDataDir(testProjectSourcePath + testContextFileName);
        int offset = 31;
        CompletionContext context = cq.getCompletionContext(getDocument(fileObject), offset, fileObject, CoffeeScriptUtils.getIndex());
        assertEquals(fileObject, context.getFile());
        assertEquals("", context.getFilter());
        assertEquals(CompletionContext.ContextType.DEFAULT, context.getType());
        assertNull(context.getClassName());
        assertFalse(context.hasClass());
    }

    public void testCompletionContextDefaultFilter() throws Exception {
        CompletionQuery cq = new CompletionQuery(true);
        FileObject fileObject = getFileObjectInDataDir(testProjectSourcePath + testContextFileName);
        int offset = 84;
        CompletionContext context = cq.getCompletionContext(getDocument(fileObject), offset, fileObject, CoffeeScriptUtils.getIndex());
        assertEquals(fileObject, context.getFile());
        assertEquals("fil", context.getFilter());
        assertEquals(CompletionContext.ContextType.DEFAULT, context.getType());
        assertNull(context.getClassName());
        assertFalse(context.hasClass());
    }

    public void testCompletionClassNewContext() throws Exception {

        // new context
        CompletionResultSetImpl completionResultSetImpl
                = performTest(testFileName, 8, CompletionProvider.COMPLETION_QUERY_TYPE);
        assertNotNull(completionResultSetImpl);
        assertEquals(4, completionResultSetImpl.getItems().size());
    }

    public void testCompletionClassExtendsContext() throws Exception {
        //extends context
        CompletionResultSetImpl completionResultSetImpl
                = performTest(testFileName, 26, CompletionProvider.COMPLETION_QUERY_TYPE);
        assertNotNull(completionResultSetImpl);
        assertEquals(3, completionResultSetImpl.getItems().size());
    }

    //this test also checks, that definitions with same name from other files won't be visible.
    //fields1.coffee defines fields a, b and c, but there should be only c in completion proposals
    public void testCompletionDotContextLocal() throws Exception {
        int caretOffset = 35;
        insertText(testFileName, caretOffset, ".");
        CompletionResultSetImpl completionResultSetImpl
                = performTest(testFileName, caretOffset + 1, CompletionProvider.COMPLETION_QUERY_TYPE);
        removeText(testFileName, caretOffset, 1);
        assertNotNull(completionResultSetImpl);
        assertEquals(5, completionResultSetImpl.getItems().size());
    }

    //local and global results are the same
    public void testCompletionDotContextGlobal() throws Exception {
        int caretOffset = 35;
        insertText(testFileName, caretOffset, ".");
        CompletionResultSetImpl completionResultSetImpl
                = performTest(testFileName, caretOffset + 1, CompletionProvider.COMPLETION_ALL_QUERY_TYPE);
        assertNotNull(completionResultSetImpl);
        removeText(testFileName, caretOffset, 1);
        assertEquals(5, completionResultSetImpl.getItems().size());
    }

    public void testCompletionDefaultContextRootLocal1() throws Exception {
        CompletionResultSetImpl completionResultSetImpl
                = performTest(testFileName, 36, CompletionProvider.COMPLETION_QUERY_TYPE);
        assertNotNull(completionResultSetImpl);
        //in default context are keyword items
        assertEquals(69, completionResultSetImpl.getItems().size());
    }

    public void testCompletionDefaultContextRootGlobal() throws Exception {
        CompletionResultSetImpl completionResultSetImpl
                = performTest(testFileName, 36, CompletionProvider.COMPLETION_ALL_QUERY_TYPE);
        assertNotNull(completionResultSetImpl);
        //in default context are keyword items
        assertEquals(75, completionResultSetImpl.getItems().size());
    }

    public void testCompletionMethodParams() throws Exception {
        CompletionResultSetImpl completionResultSetImpl
                = performTest(testFileName, 46, CompletionProvider.COMPLETION_ALL_QUERY_TYPE);
        Collections.sort(completionResultSetImpl.getItems(), new Comparator<CompletionItem>() {

            public int compare(CompletionItem o1, CompletionItem o2) {
                int result = Integer.compare(o1.getSortPriority(), o2.getSortPriority());
                Collator collator = Collator.getInstance();
                return result == 0 ? collator.compare(o1.getInsertPrefix(), o2.getInsertPrefix()) : result;
            }
        });
        assertFalse(completionResultSetImpl.getItems().isEmpty());
        assertTrue(completionResultSetImpl.getItems().get(0) instanceof FieldCompletionItem);
        assertTrue(completionResultSetImpl.getItems().get(1) instanceof FieldCompletionItem);
        assertEquals("k", completionResultSetImpl.getItems().get(0).getInsertPrefix());
        assertEquals("n", completionResultSetImpl.getItems().get(1).getInsertPrefix());
    }

    @Override
    protected boolean runInEQ() {
        return true;
    }

    private CompletionResultSetImpl performTest(String file, int caretOffset, int queryType) throws Exception {
        CoffeeScriptCompletionProvider completionProvider = new CoffeeScriptCompletionProvider();
        CompletionImpl completionImpl = CompletionImpl.get();
        Source testSource = getSource(getFileObjectInDataDir(testProjectSourcePath + file));
        JEditorPane pane = getPane(testSource);
        pane.setCaretPosition(caretOffset);
        MockLookup.setInstances(testSource.getFileObject());
        CompletionTask task = completionProvider.createTask(queryType, pane);
        CompletionResultSetImpl resultSetImpl = completionImpl.createTestResultSet(task, queryType);
        task.query(resultSetImpl.getResultSet());
        waitMaxMilisForValue(5000, new ResultReadyResolver(resultSetImpl), Boolean.TRUE);
        assertTrue(resultSetImpl.isFinished());
        assertNotNull(resultSetImpl);
        return resultSetImpl;
    }

    private class ResultReadyResolver implements ValueResolver {

        private final CompletionResultSetImpl crs;

        public ResultReadyResolver(CompletionResultSetImpl crs) {
            this.crs = crs;
        }

        public Object getValue() {
            return crs.isFinished();
        }
    }

}
