package coffeescript.nb.parser;

import coffeescript.nb.core.CoffeeScriptTestBase;
import coffeescript.nb.antlr.parser.definitions.ClassDefinition;
import coffeescript.nb.antlr.parser.definitions.CoffeeScriptFileDefinition;
import coffeescript.nb.antlr.parser.definitions.Definition;
import coffeescript.nb.antlr.parser.definitions.MethodDefinition;
import coffeescript.nb.options.CoffeeScriptSettings;
import java.util.ArrayList;
import java.util.List;
import org.netbeans.modules.parsing.api.Source;
import org.netbeans.modules.parsing.spi.ParseException;

/**
 *
 * @author Miloš Pensimus
 */
public class ParserTest extends CoffeeScriptTestBase {

    public ParserTest(String name) {
        super(name);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void testParserResultLegacy() throws Exception {
        assertNotNull(getLegacyResult());
    }

    public void testParserResult() throws Exception {
        assertNotNull(getResult());
    }

    public void testParserClasses() throws Exception {
        ParsingResult result = getResult();
        assertNotNull(result);
        assertEquals(2, result.getFileDefinition().getRootClasses().size());
    }

    public void testParserRootMethods() throws Exception {
        ParsingResult result = getResult();
        assertNotNull(result);
        //in list : b
        assertEquals(1, result.getFileDefinition().getRootMethods().size());
    }

    public void testParserMethods() throws Exception {
        ParsingResult result = getResult();
        assertNotNull(result);
        //in list : f, s, anonymous0
        assertEquals(3, result.getFileDefinition().getMethods().size());
    }

    public void testParserRootFields() throws Exception {
        ParsingResult result = getResult();
        assertNotNull(result);
        //in list : a
        assertEquals(1, result.getFileDefinition().getRootFields().size());
    }

    public void testParserFields() throws Exception {
        ParsingResult result = getResult();
        assertNotNull(result);
        // list empty - method params in MethodDefinition, a in rootFields
        assertEquals(0, result.getFileDefinition().getFields().size());
    }

    public void testParserMethodParams() throws Exception {
        ParsingResult result = getResult();
        assertNotNull(result);
        List<Definition> params = new ArrayList<Definition>();
        for (MethodDefinition method : result.getFileDefinition().getMethods()) {
            params.addAll(method.getParams());
        }
        for (MethodDefinition method : result.getFileDefinition().getRootMethods()) {
            params.addAll(method.getParams());
        }
        //in list : t, t, f
        assertEquals(3, params.size());
    }

    public void testParserClassFields() throws Exception {
        ParsingResult result = getResult();
        assertNotNull(result);
        List<Definition> fields = new ArrayList<Definition>();
        for (ClassDefinition classDef : result.getFileDefinition().getRootClasses()) {
            fields.addAll(classDef.getFields());
        }
        //in list : f
        assertEquals(1, fields.size());
    }

    public void testParserClassMethods() throws Exception {
        ParsingResult result = getResult();
        assertNotNull(result);
        List<Definition> methods = new ArrayList<Definition>();
        for (ClassDefinition classDef : result.getFileDefinition().getRootClasses()) {
            methods.addAll(classDef.getMethods());
        }
        //in list : e
        assertEquals(1, methods.size());
    }

    public void testParserClassConstructors() throws Exception {
        ParsingResult result = getResult();
        assertNotNull(result);
        List<Definition> constructors = new ArrayList<Definition>();
        for (ClassDefinition classDef : result.getFileDefinition().getRootClasses()) {
            constructors.addAll(classDef.getConstructors());
        }
        //in list : 1 constructor of class B
        assertEquals(1, constructors.size());
    }

    public void testParserLegacyErrors() throws Exception {
        ParsingResult result = getLegacyResult("parserError.coffee");
        assertNotNull(result);
        assertTrue(result.hasErrors());
    }

    public void testParserErrors() throws Exception {
        ParsingResult result = getResult("parserError.coffee");
        assertNotNull(result);
        assertTrue(result.hasErrors());
    }

    private ParsingResult getResult(String fileName) {
        ParsingResult result = null;
        try {
            Source source = getSource(getFileObjectInDataDir(testProjectSourcePath + fileName));
            CoffeeScriptParser parser = new CoffeeScriptParser();
            parser.parse(source.createSnapshot(), null, null);
            result = (ParsingResult) parser.getResult(null);
            checkResult(result);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    private ParsingResult getResult() {
        return getResult("parser.coffee");
    }

    private ParsingResult getLegacyResult(String fileName) {
        ParsingResult result = null;
        try {
            Source source = getSource(getFileObjectInDataDir(testProjectSourcePath + fileName));
            CoffeeScriptSettings.get().setLegacy(true);
            CoffeeScriptParser parser = new CoffeeScriptParser();
            parser.parse(source.createSnapshot(), null, null);
            result = (ParsingResult) parser.getResult(null);
            checkLegacyResult(result);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }

    private ParsingResult getLegacyResult() {
        return getLegacyResult("parser.coffee");
    }

    private void checkLegacyResult(ParsingResult result) {
        CoffeeScriptFileDefinition file = result.getFileDefinition();
        assertNotNull(result);
        assertNotNull(result.getCompilerResult());
        assertNotNull(file);
        assertNotNull(file.getFileName());
        assertTrue(file.getFields().isEmpty());
        assertTrue(file.getMethods().isEmpty());
        assertTrue(file.getRootClasses().isEmpty());
        assertTrue(file.getRootDefinitions().isEmpty());
        assertTrue(file.getRootFields().isEmpty());
        assertTrue(file.getRootMethods().isEmpty());
        assertTrue(result.isLegacy());
    }

    private void checkResult(ParsingResult result) {
        CoffeeScriptFileDefinition file = result.getFileDefinition();
        assertNotNull(result);
        assertNull(result.getCompilerResult());
        assertNotNull(file);
        assertNotNull(file.getFileName());
        assertFalse(result.isLegacy());
    }

}
