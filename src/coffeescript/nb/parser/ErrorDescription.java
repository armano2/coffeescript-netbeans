package coffeescript.nb.parser;

public class ErrorDescription {
    private int line;
    private int charPosition;
    private String msg;

    public ErrorDescription(int line, int charPosition, String msg) {
        this.line = line;
        this.charPosition = charPosition;
        this.msg = msg;
    }

    public int getLine() {
        return line;
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
