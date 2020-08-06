package me.will.sb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.will.sb.annotation.ServiceLog;
import me.will.sb.mapper.SBMapper;
import me.will.sb.model.req.QueryReq;
import me.will.sb.model.resp.App;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SBService {

    public final SBMapper mapper;

    public SBService(SBMapper mapper){
        this.mapper = mapper;
    }

    @ServiceLog(name = "query")
    public App query(QueryReq req) {
        mapper.selectMaps(new QueryWrapper<>());
        return new App();
    }
}
