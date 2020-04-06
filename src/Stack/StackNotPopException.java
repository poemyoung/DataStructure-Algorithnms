package Stack;

public class StackNotPopException extends RuntimeException {
    public StackNotPopException() {
        super();
    }

    public StackNotPopException(String message) {
        super(message);
    }

    public StackNotPopException(String message, Throwable cause) {
        super(message, cause);
    }

    public StackNotPopException(Throwable cause) {
        super(cause);
    }

    protected StackNotPopException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
