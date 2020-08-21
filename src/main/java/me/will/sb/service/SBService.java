package me.will.sb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.will.sb.annotation.My;
import me.will.sb.annotation.ServiceLog;
import me.will.sb.mapper.SBMapper;
import me.will.sb.mapper.SBRepository;
import me.will.sb.model.req.QueryReq;
import me.will.sb.model.resp.App;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The type Sb service.
 */
@My
@Service
@Transactional
public class SBService {

    private final SBMapper mapper;
    private final SBRepository repository;

    public SBService(SBMapper mapper,SBRepository repository){
        this.mapper = mapper;
        this.repository = repository;
    }

    @ServiceLog(name = "query")
    public List<App> query(QueryReq req) {
        return mapper.selectList(new QueryWrapper<>());
    }

    @ServiceLog(name = "findById")
    public App findById(Integer id) {
        return repository.findById(id).orElse(new App());
    }
}
