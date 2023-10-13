package me.will.sb.model.resp;


import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;

import java.io.Serializable;

public class App implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5618682104903644348L;
    @Id(keyType = KeyType.Auto)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
