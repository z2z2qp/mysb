package me.will.sb.controller;

import me.will.sb.model.req.QueryReq;
import me.will.sb.model.resp.App;
import me.will.sb.service.SBService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("sb")
public class Controller {

    private final SBService service;

    public Controller(SBService service) {
        this.service = service;
    }

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
