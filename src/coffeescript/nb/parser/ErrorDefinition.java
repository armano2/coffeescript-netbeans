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

package coffeescript.nb.parser;

/**
 *
 * @author Miloš Pensimus
 */
public class ErrorDefinition {
    private int line;
    private int charPosition;
    private String msg;

    public ErrorDefinition(int line, int charPosition, String msg) {
        this.line = line;
        this.charPosition = charPosition;
        this.msg = msg;
    }

    public int getLine() {
        return line + 1;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getCharPosition() {
        return charPosition;
    }

    public void setCharPosition(int charPosition) {
        this.charPosition = charPosition;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
