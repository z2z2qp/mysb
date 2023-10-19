package me.will.sb.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import me.will.sb.Version;
import me.will.sb.annotation.OpenApi;
import me.will.sb.annotation.TimeLog;
import me.will.sb.entity.App;
import me.will.sb.model.req.QueryReq;
import me.will.sb.model.resp.VersionInfo;
import me.will.sb.service.SBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Tag(name = "示例接口")
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
    @Operation(summary = "查询", description = "有登录校验")
    public ResponseEntity<List<App>> query(@RequestBody @Valid QueryReq req) {
        var app = service.query(req);
        return ResponseEntity.ok(app);
    }

    @GetMapping("app/{id}")
    @Operation(summary = "获取", description = "无登录校验")
    public ResponseEntity<App> get(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/login")
    @Operation(summary = "登录")
    public ResponseEntity<SaTokenInfo> login() {
        var id = "adaga";
        StpUtil.login(id);
        var token = StpUtil.getTokenInfo();
        log.info("{} login token is {}", id, token);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/async")
    @Operation(summary = "一个异步方法示例")
    public ResponseEntity<String> async() throws ExecutionException, InterruptedException {
        service.asyncA();
        Future<String> future = service.asyncB();
        var str = future.get();
//        while (true) {
//            if (future.isDone()) {
//                str = future.get();
//                break;
//            } else {
//                Thread.sleep(100);
//                log.info("async :{}", System.currentTimeMillis());
//            }
//        }
        return ResponseEntity.ok(str);
    }

    @OpenApi
    @TimeLog(name = "version")
    @GetMapping("/version")
    @Operation(summary = "获取版本号")
    public ResponseEntity<VersionInfo> version() {
        return ResponseEntity.ok(new VersionInfo(Version.VERSION, Version.DATE));
    }
}
