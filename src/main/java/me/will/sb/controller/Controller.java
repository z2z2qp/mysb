package me.will.sb.controller;

import me.will.sb.common.HttpResult;
import me.will.sb.model.req.QueryReq;
import me.will.sb.model.resp.App;
import me.will.sb.service.SBService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("sb")
public class Controller {

    private final SBService service;

    public Controller(SBService service){
        this.service = service;
    }

    @PostMapping("query")
    public HttpResult<App> query(@RequestBody @Valid QueryReq req){
        var app = service.query(req);
        return HttpResult.OK(app);
    }

}
