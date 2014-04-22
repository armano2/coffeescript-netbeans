/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package coffeescript.nb.antlr.parser;

import coffeescript.nb.core.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import org.openide.util.Exceptions;

/**
 *
 * @author James Reid
 */
public class AntlrTokenReaderParser {

    public Map<TokenEnumParser, Integer> getTypeMap() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inp = classLoader.getResourceAsStream(Constants.TOKENS_ANTLR_PARSER);
        BufferedReader input = new BufferedReader(new InputStreamReader(inp));
        return readTokenFile(input);
    }

    /**
     * Reads in the token file.
     *
     * @param buff
     */
    private Map<TokenEnumParser, Integer> readTokenFile(BufferedReader buff) {
        Map<TokenEnumParser, Integer> typeMap = new EnumMap<TokenEnumParser, Integer>(TokenEnumParser.class);
        String line;
        try {
            while ((line = buff.readLine()) != null) {
                String[] splLine = line.split("=");
                String name = splLine[0];
                int tokenOrdinal = Integer.parseInt(splLine[1].trim());
                //add it into the vector of tokens
                typeMap.put(TokenEnumParser.tokenValue(name), tokenOrdinal);
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return typeMap;
    }     
}
