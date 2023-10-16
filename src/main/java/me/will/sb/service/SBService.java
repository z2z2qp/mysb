package me.will.sb.service;

import com.mybatisflex.core.query.QueryWrapper;
import me.will.sb.annotation.My;
import me.will.sb.annotation.ServiceLog;
import me.will.sb.mapper.SBMapper;
import me.will.sb.mapper.SBRepository;
import me.will.sb.model.req.QueryReq;
import me.will.sb.entity.App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
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

    public SBService(SBMapper mapper, SBRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @ServiceLog(name = "query")
    public List<App> query(QueryReq req) {
        return mapper.selectListByQuery(QueryWrapper.create());
    }

    @ServiceLog(name = "findById")
    public App findById(Integer id) {
        return repository.findById(id).orElse(new App());
    }

    @Async
    public void asyncA() {
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("method A:{}", System.currentTimeMillis());
        });
    }

    @Async
    public Future<String> asyncB() {
        log.info("method B:{}", System.currentTimeMillis());
        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("method B2:{}", System.currentTimeMillis());
            return "异步";
        });
    }
}
