package me.will.sb.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.util.List;


@Schema(description = "请求对象")
public class QueryReq {

    @Schema(name = "name", description = "名称")
    private String name;
    private List<String> phone;
    private int i;

    private String date;

    @NotBlank(message = "名称不能为空")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = "phone not empty")
    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    @Max(value = 10, message = "must lesser then 10")
    @Min(value = 0, message = "must bigger then 0")
    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Pattern(message = "date format must be yyyy-MM-dd", regexp = "^\\d{4}-[0,1][0-9]-[0-3][0-9]$")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
