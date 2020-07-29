package me.will.sb.exception;

public enum Code {
    DATABASE(700,"数据库错误"),
    INVALID_RESULT_STATUS(760,"返回状态码必须大于200"),
    INVALID_PARAMETER(750,"无效的参数");
    private final int code;
    private final String message;

    Code(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Code{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
