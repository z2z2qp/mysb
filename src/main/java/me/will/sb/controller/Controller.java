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

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("sb")
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);

    private final SBService service;

    public Controller(SBService service) {
        this.service = service;
    }

    @TimeLog(name = "query")
    @PostMapping("query")
    public ResponseEntity<List<App>> query(@RequestBody @Valid QueryReq req) {
        var app = service.query(req);
        return new ResponseEntity<>(app, HttpStatus.OK);
    }

    @GetMapping("app/{id}")
    public ResponseEntity<App> get(@PathVariable Integer id) {
        return new ResponseEntity<>(service.findById(id),HttpStatus.OK);
    }

}
