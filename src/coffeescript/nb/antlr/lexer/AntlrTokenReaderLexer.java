/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package coffeescript.nb.antlr.lexer;

import coffeescript.nb.core.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import coffeescript.nb.core.CoffeeScriptTokenId;
import java.util.Collection;
import java.util.Map;
import org.openide.util.Exceptions;

/**
 *
 * @author James Reid
 */
public class AntlrTokenReaderLexer {

    private Map<Integer,CoffeeScriptTokenId> tokens = new HashMap<Integer,CoffeeScriptTokenId>();

    public AntlrTokenReaderLexer() {
    }
    
    public Collection<CoffeeScriptTokenId> readTokenFile() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inp = classLoader.getResourceAsStream(Constants.TOKENS_ANTLR_LEXER);
        BufferedReader input = new BufferedReader(new InputStreamReader(inp));
        readTokenFile(input);
        return tokens.values();
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
                if (line.contains("'")) {
                    String trimmedLine = line.trim();
                    int equatingIndex = trimmedLine.lastIndexOf("=");
                    name = trimmedLine.substring(1,equatingIndex-1);
                    int tokenOrdinal = Integer.parseInt(trimmedLine.substring(equatingIndex+1, trimmedLine.length()));
                    CoffeeScriptTokenId tokenId = tokens.get(tokenOrdinal);
                    tokenId.setTokenString(name);          
                    continue;
                }
                int tokenOrdinal = Integer.parseInt(splLine[1].trim());
                CoffeeScriptTokenId id;
                TokenEnumLexer token = TokenEnumLexer.tokenValue(name);
                if (token != null) {
                    //if the value exists, put it in the correct category
                    id = new CoffeeScriptTokenId(token, tokenOrdinal);
                } else {
                    continue;
                }
                //add it into the vector of tokens
                tokens.put(tokenOrdinal, id);
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }    
    
    
}
