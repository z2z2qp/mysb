package me.will.sb.exception;

public class SBException extends RuntimeException{

    private final Code code;

    public SBException(Code code) {
        this.code = code;
    }

    public SBException(String message, Code code) {
        super(message);
        this.code = code;
    }

    public SBException(String message, Throwable cause, Code code) {
        super(message, cause);
        this.code = code;
    }

    public SBException(Throwable cause, Code code) {
        super(cause);
        this.code = code;
    }

    public SBException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Code code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
    }

    @Override
    public String toString() {
        return "SBException{" +
                "code=" + code +
                '}';
    }
}
