package me.will.sb.mapper;


import com.mybatisflex.core.BaseMapper;
import me.will.sb.entity.App;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SBMapper extends BaseMapper<App> {
}
