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
package coffeescript.nb.core;

import coffeescript.nb.indexing.CoffeeScriptIndexer;
import coffeescript.nb.indexing.classpath.CoffeeScriptClassPathProvider;
import coffeescript.nb.options.CoffeeScriptSettings;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import javax.swing.JEditorPane;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;
import org.netbeans.api.java.classpath.ClassPath;
import org.netbeans.api.java.classpath.GlobalPathRegistry;
import org.netbeans.jellytools.JellyTestCase;
import org.netbeans.modules.csl.spi.DefaultLanguageConfig;
import org.netbeans.modules.csl.spi.ParserResult;
import org.netbeans.modules.parsing.api.ParserManager;
import org.netbeans.modules.parsing.api.ResultIterator;
import org.netbeans.modules.parsing.api.Source;
import org.netbeans.modules.parsing.api.UserTask;
import org.netbeans.modules.parsing.impl.indexing.CacheFolder;
import org.netbeans.modules.parsing.impl.indexing.FileObjectIndexable;
import org.netbeans.modules.parsing.impl.indexing.RepositoryUpdater;
import org.netbeans.modules.parsing.impl.indexing.SPIAccessor;
import org.netbeans.modules.parsing.impl.indexing.SuspendSupport;
import org.netbeans.modules.parsing.lucene.support.DocumentIndex;
import org.netbeans.modules.parsing.spi.Parser;
import org.netbeans.modules.parsing.spi.indexing.Context;
import org.netbeans.modules.parsing.spi.indexing.EmbeddingIndexer;
import org.netbeans.modules.parsing.spi.indexing.EmbeddingIndexerFactory;
import org.netbeans.modules.parsing.spi.indexing.Indexable;
import org.netbeans.modules.parsing.spi.indexing.PathRecognizer;
import org.netbeans.spi.java.classpath.ClassPathProvider;
import org.netbeans.spi.java.classpath.support.ClassPathSupport;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileSystem;
import org.openide.filesystems.FileUtil;
import org.openide.filesystems.MultiFileSystem;
import org.openide.filesystems.Repository;
import org.openide.filesystems.XMLFileSystem;
import org.openide.util.test.MockLookup;

/**
 *
 * @author Miloš Pensimus
 */
public class CoffeeScriptTestBase extends JellyTestCase {

    private static Object[] extraLookupContent = null;
    private Map<String, ClassPath> classPathsForTest;
    protected String testProjectSourcePath = "/testProject/src/";

    public CoffeeScriptTestBase(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        CoffeeScriptSettings.get().setLegacy(false);
        System.setProperty("netbeans.user", getWorkDirPath());
        final FileObject wd = FileUtil.toFileObject(getWorkDir());
        assert wd != null;
        FileObject cache = FileUtil.createFolder(wd, "var/cache");
        assert cache != null;
        CacheFolder.setCacheFolder(cache);
        List<URL> layers = new LinkedList<URL>();
        String[] additionalLayers = new String[]{"META-INF/generated-layer.xml"};
        Object[] additionalLookupContent = new Object[0];
        for (String additionalLayer : additionalLayers) {
            boolean found = false;
            for (Enumeration<URL> en = Thread.currentThread().getContextClassLoader().getResources(additionalLayer); en.hasMoreElements();) {
                found = true;
                layers.add(en.nextElement());
            }
            assertTrue(additionalLayer, found);
        }

        XMLFileSystem xmlFS = new XMLFileSystem();
        xmlFS.setXmlUrls(layers.toArray(new URL[0]));
        FileSystem system = new MultiFileSystem(new FileSystem[]{FileUtil.createMemoryFileSystem(), xmlFS});
        Repository repository = new Repository(system);

        RepositoryUpdater.getDefault().start(true);
        Logger logger = Logger.getLogger(RepositoryUpdater.class.getName() + ".tests");
        logger.setLevel(Level.FINEST);
        Waiter w = new Waiter(false);
        logger.addHandler(w);

        extraLookupContent = new Object[additionalLookupContent.length + 1];
        System.arraycopy(additionalLookupContent, 0, extraLookupContent, 1, additionalLookupContent.length);
        extraLookupContent[0] = repository;
        MockLookup.setInstances(extraLookupContent, new TestClassPathProvider(), new TestPathRecognizer());

        classPathsForTest = createClassPathsForTest();
        for (String cpId : classPathsForTest.keySet()) {
            ClassPath cp = classPathsForTest.get(cpId);
            GlobalPathRegistry.getDefault().register(cpId, new ClassPath[]{cp});
        }
        w.waitForScanToFinish();
        logger.removeHandler(w);
    }

    @Override
    protected void tearDown() throws Exception {
        if (classPathsForTest != null && !classPathsForTest.isEmpty()) {
            Logger logger = Logger.getLogger(RepositoryUpdater.class.getName() + ".tests");
            logger.setLevel(Level.FINEST);
            Waiter w = new Waiter(false);
            logger.addHandler(w);

            for (String cpId : classPathsForTest.keySet()) {
                ClassPath cp = classPathsForTest.get(cpId);
                GlobalPathRegistry.getDefault().unregister(cpId, new ClassPath[]{cp});
            }

            w.waitForScanToFinish();
            logger.removeHandler(w);
        }

        super.tearDown();
    }

    protected DefaultLanguageConfig getPreferredLanguage() {
        return new CoffeeScriptLanguageRegistration();
    }

    protected String getPreferredMimeType() {
        return Constants.MIME_TYPE;
    }

    protected final Map<String, ClassPath> createClassPathsForTest() throws IOException {
        FileObject[] srcFolders = createSourceClassPathsForTest();
        return srcFolders != null ? Collections.singletonMap(
                CoffeeScriptClassPathProvider.SOURCE_CS,
                ClassPathSupport.createClassPath(srcFolders)
        ) : null;
    }

    protected FileObject[] createSourceClassPathsForTest() throws IOException {
        return new FileObject[]{getFileObjectInDataDir(testProjectSourcePath)};
    }

    protected EmbeddingIndexerFactory getIndexerFactory() {
        EmbeddingIndexerFactory handler = new CoffeeScriptIndexer.Factory();
        assertNotNull("You must override getIndexerFactory, either from your GsfLanguage or your test class", handler);
        return handler;
    }

    protected FileObject getFileObjectInDataDir(String path) {
        return FileUtil.toFileObject(new File(getDataDir().getAbsolutePath() + path));
    }

    protected Document getDocument(FileObject fo) {
        return Source.create(fo).getDocument(true);
    }

    protected void indexFile(FileObject testSourceFile) throws Exception {
        Source testSource = getSource(testSourceFile);

        FileObject root = testSourceFile.getParent();
        final Indexable indexable = SPIAccessor.getInstance().create(new FileObjectIndexable(root, testSourceFile));
        final EmbeddingIndexerFactory factory = getIndexerFactory();
        assertNotNull("getIndexer must be implemented", factory);
        FileObject cacheRoot = CacheFolder.getDataFolder(root.toURL());

        final Context context = SPIAccessor.getInstance().createContext(
                cacheRoot,
                root.toURL(),
                factory.getIndexerName(),
                factory.getIndexVersion(),
                null,
                false,
                false,
                false,
                SuspendSupport.NOP,
                null,
                null
        );

        try {
            ParserManager.parse(Collections.singleton(testSource), new UserTask() {
                public @Override
                void run(ResultIterator resultIterator) throws Exception {
                    Parser.Result r = resultIterator.getParserResult();
                    assertTrue(r instanceof ParserResult);

                    EmbeddingIndexer indexer = factory.createIndexer(indexable, r.getSnapshot());
                    assertNotNull("getIndexer must be implemented", factory);

                    SPIAccessor.getInstance().index(indexer, indexable, r, context);
                }
            });
        } finally {
            DocumentIndex index = SPIAccessor.getInstance().getIndexFactory(context).getIndex(context.getIndexFolder());
            if (index != null) {
                index.removeDirtyKeys(Collections.singleton(indexable.getRelativePath()));
                index.store(true);
            }
        }

    }

    private static final class Waiter extends Handler {

        private final CountDownLatch latch;

        private final boolean binaries;

        public Waiter(boolean binaries) {
            latch = new CountDownLatch(binaries ? 2 : 1);
            this.binaries = binaries;
        }

        public void waitForScanToFinish() {
            try {
                latch.await(60000, TimeUnit.MILLISECONDS);
                if (latch.getCount() > 0) {
                    fail("Waiting for classpath scanning to finish timed out");
                }
            } catch (InterruptedException ex) {
                fail("Waiting for classpath scanning to finish was interrupted");
            }
        }

        @Override
        public void publish(LogRecord record) {
            String msg = record.getMessage();
            if ("scanSources".equals(msg) || (binaries && "scanBinary".equals(msg))) {
                latch.countDown();
            }
        }

        @Override
        public void flush() {
        }

        @Override
        public void close() throws SecurityException {
        }
    }

    protected Source getSource(FileObject fo) {
        return Source.create(fo);
    }

    protected void insertText(String file, int caretOffset, String string) throws Exception {
        Source testSource = getSource(getFileObjectInDataDir(testProjectSourcePath + file));
        Document document = testSource.getDocument(true);
        document.insertString(caretOffset, string, null);
    }

    protected void removeText(String file, int caretOffset, int length) throws Exception {
        Source testSource = getSource(getFileObjectInDataDir(testProjectSourcePath + file));
        Document document = testSource.getDocument(true);
        document.remove(caretOffset, length);
    }

    private class TestClassPathProvider implements ClassPathProvider {

        public TestClassPathProvider() {
        }

        public ClassPath findClassPath(FileObject file, String type) {
            Map<String, ClassPath> map = classPathsForTest;

            if (map != null) {
                return map.get(type);
            } else {
                return null;
            }
        }
    }

    public static boolean waitMaxMilisForValue(int maxMiliSeconds, ValueResolver resolver, Object requiredValue) {
        int time = (int) maxMiliSeconds / 100;
        while (time > 0) {
            Object resolvedValue = resolver.getValue();
            if (requiredValue == null && resolvedValue == null) {
                return true;
            }
            if (requiredValue != null && requiredValue.equals(resolvedValue)) {
                return true;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                time = 0;
            }
            time--;
        }
        return false;
    }

    protected JEditorPane getPane(Source source) throws Exception {
        if (!SwingUtilities.isEventDispatchThread()) {
            fail("You must run this test from the event dispatch thread! To do that, add @Override protected boolean runInEQ() { return true } from your testcase!");
        }
        JEditorPane pane = new JEditorPane();
        pane.setContentType(getPreferredMimeType());
        pane.setDocument(source.getDocument(true));
        return pane;
    }

    public static interface ValueResolver {

        Object getValue();
    }

    private class TestPathRecognizer extends PathRecognizer {

        @Override
        public Set<String> getSourcePathIds() {
            return CoffeeScriptTestBase.this.getPreferredLanguage().getSourcePathIds();
        }

        @Override
        public Set<String> getLibraryPathIds() {
            return CoffeeScriptTestBase.this.getPreferredLanguage().getLibraryPathIds();
        }

        @Override
        public Set<String> getBinaryLibraryPathIds() {
            return CoffeeScriptTestBase.this.getPreferredLanguage().getBinaryLibraryPathIds();
        }

        @Override
        public Set<String> getMimeTypes() {
            return Collections.singleton(CoffeeScriptTestBase.this.getPreferredMimeType());
        }

    }
}
