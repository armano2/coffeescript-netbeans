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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author James Reid
 */
public class AntlrTokenReaderParser {
    private Map<TokenEnumParser, Integer> tokens = new HashMap<TokenEnumParser, Integer> ();

    public AntlrTokenReaderParser() {
    }
    
    public void readTokenFile() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inp = classLoader.getResourceAsStream(Constants.TOKENS_ANTLR_PARSER);
        BufferedReader input = new BufferedReader(new InputStreamReader(inp));
        readTokenFile(input);
    }

    /**
     * Reads in the token file.
     *
     * @param buff
     */
    private void readTokenFile(BufferedReader buff) {
        String line = null;
        try {
            while ((line = buff.readLine()) != null) {
                String[] splLine = line.split("=");
                String name = splLine[0];
                int tokenOrdinal = Integer.parseInt(splLine[1].trim());
                //add it into the vector of tokens
                tokens.put(TokenEnumParser.tokenValue(name), tokenOrdinal);
            }
        } catch (IOException ex) {
        }
    }    

    public Map<TokenEnumParser, Integer> getTokens() {
        return tokens;
    }
    
    
    
    
}
