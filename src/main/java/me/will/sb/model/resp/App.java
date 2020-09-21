package me.will.sb.model.resp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class App implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5618682104903644348L;
    @Id
    @TableId(type = IdType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
