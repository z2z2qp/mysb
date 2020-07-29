package me.will.sb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.will.sb.model.resp.App;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SBMapper extends BaseMapper<App> {
}
