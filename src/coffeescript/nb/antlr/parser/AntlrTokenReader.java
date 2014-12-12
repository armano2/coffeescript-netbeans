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

package coffeescript.nb.antlr.parser;

import coffeescript.nb.core.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.Map;
import org.openide.util.Exceptions;

/**
 *
 * @author Miloš Pensimus
 */
public class AntlrTokenReader {

    public Map<TokenEnumParser, Integer> getTypeMap() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inp = classLoader.getResourceAsStream(Constants.TOKENS_ANTLR_PARSER);
        BufferedReader input = new BufferedReader(new InputStreamReader(inp));
        return readTokenFile(input);
    }

    private Map<TokenEnumParser, Integer> readTokenFile(BufferedReader buff) {
        Map<TokenEnumParser, Integer> typeMap = new EnumMap<TokenEnumParser, Integer>(TokenEnumParser.class);
        String line;
        try {
            while ((line = buff.readLine()) != null) {
                String[] splLine = line.split("=");
                String name = splLine[0];
                int tokenOrdinal = Integer.parseInt(splLine[1].trim());
                typeMap.put(TokenEnumParser.tokenValue(name), tokenOrdinal);
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return typeMap;
    }     
}
