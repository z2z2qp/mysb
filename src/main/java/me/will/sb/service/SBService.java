package me.will.sb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.will.sb.annotation.My;
import me.will.sb.annotation.ServiceLog;
import me.will.sb.mapper.SBMapper;
import me.will.sb.mapper.SBRepository;
import me.will.sb.model.req.QueryReq;
import me.will.sb.model.resp.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.Future;

/**
 * The type Sb service.
 */
@My
@Service
@Transactional
public class SBService {

    private static final Logger log = LoggerFactory.getLogger(SBService.class);

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

    @Async
    public void asyncA() throws InterruptedException {
        Thread.sleep(1000);
        log.info("method A:{}",System.currentTimeMillis());
    }

    @Async
    public Future<String> asyncB() throws InterruptedException {
        log.info("method B:{}",System.currentTimeMillis());
        return new AsyncResult<>("finish");
    }
}
