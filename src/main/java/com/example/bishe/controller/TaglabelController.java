package com.example.bishe.controller;


import com.example.bishe.entity.Taglabel;
import com.example.bishe.service.TaglabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huaye
 * @since 2023-03-07
 */
@RestController
@RequestMapping("/taglabel")
public class TaglabelController {
    @Autowired
    private TaglabelService taglabelService;
    @GetMapping("/list")
    public List<Taglabel> list(){
        return taglabelService.list();
    }
}
