// Copyright 2011 Denis Stepanov
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

import coffeescript.nb.parser.ErrorDefinition;

/**
 *
 * @author Denis Stepanov & Miloš Pensimus
 */
public interface CoffeeScriptCompiler {
    
    public CompilerResult compile(String code, boolean bare);
    
    public static class CompilerResult {
        
        private String js;
        private Error error;
        
        public CompilerResult(String js) {
            this.js = js;
        }
        
        public CompilerResult(Error error) {
            this.error = error;
        }
        
        public String getJs() {
            return js;
        }
        
        public Error getError() {
            return error;
        }
        
        public boolean hasError() {
            return error != null;
        }
    }
    
    public static class Error {
        
        private final int line, column;
        private final String errorName, message;
        
        public Error(int line, String errorName, String message) {            
            this(line, 0, errorName, message);
        }
        
        public Error(int line, int column, String errorName, String message) {
            this.line = line;
            this.column = column;
            this.errorName = errorName;
            this.message = message;
        }
        
        public int getLine() {
            return line;
        }

        public int getColumn() {
            return column;
        }

        public String getErrorName() {
            return errorName;
        }

        public String getMessage() {
            return message;
        }
        
        public ErrorDefinition getErrorDefinition() {
            int errorLine = (line == -1) ? 0 : line;
            String errorMessage = (line == -1) ? message : errorName;
            return new ErrorDefinition (errorLine, column, errorMessage);
        }

    }
}
