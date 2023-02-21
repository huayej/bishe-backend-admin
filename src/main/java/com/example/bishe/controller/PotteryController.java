package com.example.bishe.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bishe.common.QueryPageParam;
import com.example.bishe.common.Result;
import com.example.bishe.entity.Pottery;
import com.example.bishe.service.PotteryService;
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
 * @since 2023-02-12
 */
@RestController
@RequestMapping("/pottery")
public class PotteryController {
    @Autowired
    private PotteryService potteryService;
    @GetMapping("/list")
    public List<Pottery> list(){
        return potteryService.list();
    }
    @GetMapping("/findByName")
    public Result findByName(@RequestParam String potteryName){
        List list = potteryService.lambdaQuery().eq(Pottery::getPotteryName,potteryName).list();
        return list.size()>0?Result.suc(list):Result.fail();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Pottery pottery){
        return potteryService.save(pottery)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Pottery pottery){
        return potteryService.updateById(pottery)?Result.suc():Result.fail();
    }
    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Pottery pottery){
        return potteryService.updateById(pottery);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Pottery pottery){
        return potteryService.saveOrUpdate(pottery);
    }
    //删除
    @GetMapping("/delete")
    public Result delete(String id){
        return potteryService.removeById(id)?Result.suc():Result.fail();
    }
    //查询（模糊、匹配）
    @PostMapping("/listP")
    public Result listP(@RequestBody Pottery pottery){
        LambdaQueryWrapper<Pottery> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(pottery.getPotteryName())){
            lambdaQueryWrapper.like(Pottery::getPotteryName,pottery.getPotteryName());
        }
//        lambdaQueryWrapper.eq(User::getName,user.getName());
        return  Result.suc(potteryService.list(lambdaQueryWrapper));
    }

    @PostMapping("/listPage")
    public List<Pottery> listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name=(String)param.get("name");

        Page<Pottery> page=new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Pottery> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Pottery::getPotteryName,name);
        IPage result=potteryService.page(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());
        return result.getRecords();
    }
    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name=(String)param.get("name");
        Page<Pottery> page=new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Pottery> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Pottery::getPotteryName,name);
        }
        IPage result=potteryService.page(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());
        return Result.suc(result.getRecords(),result.getTotal());
    }
}
