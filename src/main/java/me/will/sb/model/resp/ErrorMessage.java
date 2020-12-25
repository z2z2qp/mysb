package me.will.sb.model.resp;

/**
 * me.will.sb.model.resp.ErrorMessage
 *
 * @author Zhuang Jiabin
 * @version V1.0.0
 * @since 2020/12/25 10:27
 */
public class ErrorMessage {
    private int code;
    private String message;

    public ErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
