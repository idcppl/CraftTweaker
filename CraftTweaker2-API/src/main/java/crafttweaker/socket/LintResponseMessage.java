package crafttweaker.socket;

import java.util.List;

public class LintResponseMessage extends SocketMessage {
    
    public static final String MESSAGE_TYPE = "LintResponse";
    public List<SingleError> errors;
    public boolean loadSuccessful;
    
    public LintResponseMessage(List<SingleError> errors, boolean loadSuccessful) {
        super(MESSAGE_TYPE);
        this.errors = errors;
        this.loadSuccessful = loadSuccessful;
    }
    
    @Override
    public String toString() {
        return "LintResponseMessage{" + "errors=" + errors + '}';
    }
    
    
}
