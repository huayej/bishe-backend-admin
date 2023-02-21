package com.example.bishe.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bishe.common.QueryPageParam;
import com.example.bishe.common.Result;
import com.example.bishe.entity.Jade;
import com.example.bishe.service.JadeService;
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
 * @since 2023-02-13
 */
@RestController
@RequestMapping("/jade")
public class JadeController {
    @Autowired
    private JadeService jadeService;
    @GetMapping("/list")
    public List<Jade> list(){
        return jadeService.list();
    }
    @GetMapping("/findByName")
    public Result findByName(@RequestParam String jadeName){
        List list = jadeService.lambdaQuery().eq(Jade::getJadeName,jadeName).list();
        return list.size()>0?Result.suc(list):Result.fail();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Jade jade){
        return jadeService.save(jade)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Jade jade){
        return jadeService.updateById(jade)?Result.suc():Result.fail();
    }
    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Jade jade){
        return jadeService.updateById(jade);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Jade jade){
        return jadeService.saveOrUpdate(jade);
    }
    //删除
    @GetMapping("/delete")
    public Result delete(String id){
        return jadeService.removeById(id)?Result.suc():Result.fail();
    }
    //查询（模糊、匹配）
    @PostMapping("/listP")
    public Result listP(@RequestBody Jade jade){
        LambdaQueryWrapper<Jade> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(jade.getJadeName())){
            lambdaQueryWrapper.like(Jade::getJadeName,jade.getJadeName());
        }
//        lambdaQueryWrapper.eq(User::getName,user.getName());
        return  Result.suc(jadeService.list(lambdaQueryWrapper));
    }

    @PostMapping("/listPage")
    public List<Jade> listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name=(String)param.get("name");

        Page<Jade> page=new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Jade> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Jade::getJadeName,name);
        IPage result=jadeService.page(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());
        return result.getRecords();
    }
    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name=(String)param.get("name");
        Page<Jade> page=new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Jade> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Jade::getJadeName,name);
        }
        IPage result=jadeService.page(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());
        return Result.suc(result.getRecords(),result.getTotal());
    }
}
