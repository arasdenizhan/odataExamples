package com.denzhn.odatatraining.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/deniz")
public class EntityController {

    private final EntityServlet entityServlet;

    @Autowired
    public EntityController(EntityServlet entityServlet) {
        this.entityServlet = entityServlet;
    }

    @GetMapping(path = "/abc")
    public String createAbc(@RequestBody HttpServletRequest request){

        return "abc";
    }
}
