/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        return readTokenFile(input);
    }

    private List<String> readTokenFile(BufferedReader input) {
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
