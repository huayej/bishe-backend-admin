package com.example.bishe.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bishe.common.QueryPageParam;
import com.example.bishe.common.Result;
import com.example.bishe.entity.Creativity;
import com.example.bishe.service.CreativityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author huaye
 * @since 2023-03-11
 */
@RestController
@RequestMapping("/creativity")
public class CreativityController {
    @Autowired
    private CreativityService creativityService;
    @GetMapping("/list")
    public List<Creativity> list(){
        return creativityService.list();
    }
    @GetMapping("/findByName")
    public Result findByName(@RequestParam String Name){
        List list = creativityService.lambdaQuery().eq(Creativity::getName,Name).list();
        return list.size()>0?Result.suc(list):Result.fail();
    }
    @GetMapping("/findByUid")
    public Result findByUid(@RequestParam Integer Uid){
        List list = creativityService.lambdaQuery().eq(Creativity::getUid,Uid).list();
        return list.size()>0?Result.suc(list):Result.fail();
    }
    @GetMapping("/findById")
    public Result findById(@RequestParam Integer Id){
        List list = creativityService.lambdaQuery().eq(Creativity::getId,Id).list();
        return list.size()>0?Result.suc(list):Result.fail();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Creativity creativity){
        return creativityService.save(creativity)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Creativity creativity){
        return creativityService.updateById(creativity)?Result.suc():Result.fail();
    }
    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Creativity creativity){
        return creativityService.updateById(creativity);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Creativity creativity){
        return creativityService.saveOrUpdate(creativity);
    }
    //删除
    @GetMapping("/delete")
    public Result delete(String id){
        return creativityService.removeById(id)?Result.suc():Result.fail();
    }
    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name=(String)param.get("name");
        Page<Creativity> page=new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Creativity> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Creativity::getName,name);
        }
        IPage result=creativityService.page(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());
        return Result.suc(result.getRecords(),result.getTotal());
    }
}
