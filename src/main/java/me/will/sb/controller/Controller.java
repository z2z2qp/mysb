package me.will.sb.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.will.sb.Version;
import me.will.sb.annotation.OpenApi;
import me.will.sb.annotation.TimeLog;
import me.will.sb.model.req.QueryReq;
import me.will.sb.model.resp.App;
import me.will.sb.model.resp.VersionInfo;
import me.will.sb.service.SBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Api(tags = "示例接口")
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
    @ApiOperation(value = "查询", notes = "有登录校验")
    public ResponseEntity<List<App>> query(@RequestBody @Valid QueryReq req) {
        var app = service.query(req);
        return ResponseEntity.ok(app);
    }

    @GetMapping("app/{id}")
    @ApiOperation(value = "获取", notes = "无登录校验")
    public ResponseEntity<App> get(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/login")
    @ApiOperation(value = "登录")
    public ResponseEntity<SaTokenInfo> login() {
        var id = "adaga";
        StpUtil.setLoginId(id);
        var token = StpUtil.getTokenInfo();
        log.info("{} login token is {}", id, token);
        return ResponseEntity.ok(token);
    }

    @GetMapping("/async")
    @ApiOperation(value = "一个异步方法示例")
    public ResponseEntity<String> async() throws ExecutionException, InterruptedException {
        service.asyncA();
        Future<String> future = service.asyncB();
        var str = "";
        while (true) {
            if (future.isDone()) {
                str = future.get();
                break;
            } else {
                Thread.sleep(100);
                log.info("async :{}", System.currentTimeMillis());
            }
        }
        return ResponseEntity.ok(str);
    }

    @OpenApi
    @TimeLog(name = "version")
    @GetMapping("/version")
    @ApiOperation(value = "获取版本号")
    public ResponseEntity<VersionInfo> version() {
        return ResponseEntity.ok(new VersionInfo(Version.VERSION, Version.DATE));
    }
}
