package me.will.sb.controller;

import me.will.sb.annotation.TimeLog;
import me.will.sb.model.req.QueryReq;
import me.will.sb.model.resp.App;
import me.will.sb.service.SBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("sb")
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);

    private final SBService service;

    public Controller(SBService service) {
        this.service = service;
    }

    @SaCheckLogin
    @TimeLog(name = "query")
    @PostMapping("query")
    public ResponseEntity<List<App>> query(@RequestBody @Valid QueryReq req) {
        var app = service.query(req);
        return ResponseEntity.ok(app);
    }

    @GetMapping("app/{id}")
    public ResponseEntity<App> get(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/login")
    public ResponseEntity<Map<String,String>> login(){
        var id = "adaga";
        StpUtil.setLoginId(id);
        var token = StpUtil.getTokenInfo();
        log.info("{} login token is {}",id,token);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/async")
    public ResponseEntity<String> async() throws ExecutionException, InterruptedException {
        service.asyncA();
        Future<String> future = service.asyncB();
        String str = "";
        while (true){
            if (future.isDone()){
                str = future.get();
                break;
            }else {
                Thread.sleep(100);
                log.info("async :{}",System.currentTimeMillis());
            }
        }
        return ResponseEntity.ok(str);
    }
}
