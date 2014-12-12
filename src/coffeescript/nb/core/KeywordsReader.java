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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Exceptions;

/**
 *
 * @author Miloš Pensimus
 */
public class KeywordsReader {
    
    public List<String> getKeywords() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inp = classLoader.getResourceAsStream(Constants.KEYWORDS);
        BufferedReader input = new BufferedReader(new InputStreamReader(inp));
        return readKeywordFile(input);
    }

    private List<String> readKeywordFile(BufferedReader input) {
        List<String> keywords = new ArrayList<String>();        
        try {            
            String keyword;
            while((keyword = input.readLine()) != null) {
                keywords.add(keyword);
            }            
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        return keywords;
    }
    
    
    
}
