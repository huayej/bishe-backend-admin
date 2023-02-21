package com.example.bishe.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bishe.common.QueryPageParam;
import com.example.bishe.common.Result;
import com.example.bishe.entity.Sundry;
import com.example.bishe.service.SundryService;
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
@RequestMapping("/sundry")
public class SundryController {
    @Autowired
    private SundryService sundryService;
    @GetMapping("/list")
    public List<Sundry> list(){
        return sundryService.list();
    }
    @GetMapping("/findByName")
    public Result findByName(@RequestParam String sundryName){
        List list = sundryService.lambdaQuery().eq(Sundry::getSundryName,sundryName).list();
        return list.size()>0?Result.suc(list):Result.fail();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Sundry sundry){
        return sundryService.save(sundry)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Sundry sundry){
        return sundryService.updateById(sundry)?Result.suc():Result.fail();
    }
    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Sundry sundry){
        return sundryService.updateById(sundry);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Sundry sundry){
        return sundryService.saveOrUpdate(sundry);
    }
    //删除
    @GetMapping("/delete")
    public Result delete(String id){
        return sundryService.removeById(id)?Result.suc():Result.fail();
    }
    //查询（模糊、匹配）
    @PostMapping("/listP")
    public Result listP(@RequestBody Sundry sundry){
        LambdaQueryWrapper<Sundry> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(sundry.getSundryName())){
            lambdaQueryWrapper.like(Sundry::getSundryName,sundry.getSundryName());
        }
//        lambdaQueryWrapper.eq(User::getName,user.getName());
        return  Result.suc(sundryService.list(lambdaQueryWrapper));
    }

    @PostMapping("/listPage")
    public List<Sundry> listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name=(String)param.get("name");

        Page<Sundry> page=new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Sundry> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Sundry::getSundryName,name);
        IPage result=sundryService.page(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());
        return result.getRecords();
    }
    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name=(String)param.get("name");
        Page<Sundry> page=new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Sundry> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Sundry::getSundryName,name);
        }
        IPage result=sundryService.page(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());
        return Result.suc(result.getRecords(),result.getTotal());
    }
}
