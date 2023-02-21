package com.example.bishe.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bishe.common.QueryPageParam;
import com.example.bishe.common.Result;
import com.example.bishe.entity.Relic;
import com.example.bishe.service.RelicService;
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
 * @since 2023-02-15
 */
@RestController
@RequestMapping("/relic")
public class RelicController {
    @Autowired
    private RelicService relicService;
    @GetMapping("/list")
    public List<Relic> list(){
        return relicService.list();
    }
    @GetMapping("/findByName")
    public Result findByName(@RequestParam String relicName){
        List list = relicService.lambdaQuery().eq(Relic::getRelicName,relicName).list();
        return list.size()>0?Result.suc(list):Result.fail();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Relic relic){
        return relicService.save(relic)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Relic relic){
        return relicService.updateById(relic)?Result.suc():Result.fail();
    }
    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Relic relic){
        return relicService.updateById(relic);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Relic relic){
        return relicService.saveOrUpdate(relic);
    }
    //删除
    @GetMapping("/delete")
    public Result delete(String id){
        return relicService.removeById(id)?Result.suc():Result.fail();
    }
    //查询（模糊、匹配）
    @PostMapping("/listP")
    public Result listP(@RequestBody Relic relic){
        LambdaQueryWrapper<Relic> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(relic.getRelicName())){
            lambdaQueryWrapper.like(Relic::getRelicName,relic.getRelicName());
        }
//        lambdaQueryWrapper.eq(User::getName,user.getName());
        return  Result.suc(relicService.list(lambdaQueryWrapper));
    }

    @PostMapping("/listPage")
    public List<Relic> listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name=(String)param.get("name");

        Page<Relic> page=new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Relic> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Relic::getRelicName,name);
        IPage result=relicService.page(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());
        return result.getRecords();
    }
    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name=(String)param.get("name");
        Page<Relic> page=new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Relic> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Relic::getRelicName,name);
        }
        IPage result=relicService.page(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());
        return Result.suc(result.getRecords(),result.getTotal());
    }
}
