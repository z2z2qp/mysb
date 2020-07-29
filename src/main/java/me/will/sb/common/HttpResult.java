package me.will.sb.common;

import me.will.sb.exception.Code;
import me.will.sb.exception.SBException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.Serializable;
import java.util.Objects;

public class HttpResult<T> implements Serializable {

    public static final long serialVersionUID = 0x01L;

    public static final int NO_DATA = 230;

    public static final int PARAM_ERROR = 412;

    public static final int EXCEPTION = 501;
    public static final int MY_EXCEPTION = 502;

    private final Integer code;
    private final String message;
    private T data;

    private HttpResult(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public HttpResult(T data){
        this(200,"");
        this.data = data;
    }

    public static <T> HttpResult<T> OK(T data){
        return new HttpResult<>(data);
    }

    public static <T> HttpResult<T> FAIL(Integer code,String message){
        var response = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
        assert response != null;
        if (code <= 200){
            throw new SBException("返回状态码必须大于200", Code.INVALID_PARAMETER);
        }
        response.setStatus(code);
        return new HttpResult<>(code,message);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
