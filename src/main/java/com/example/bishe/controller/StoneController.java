package com.example.bishe.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bishe.common.QueryPageParam;
import com.example.bishe.common.Result;
import com.example.bishe.entity.Stone;
import com.example.bishe.service.StoneService;
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
@RequestMapping("/stone")
public class StoneController {
    @Autowired
    private StoneService stoneService;
    @GetMapping("/list")
    public List<Stone> list(){
        return stoneService.list();
    }
    @GetMapping("/findByName")
    public Result findByName(@RequestParam String stoneName){
        List list = stoneService.lambdaQuery().eq(Stone::getStoneName,stoneName).list();
        return list.size()>0?Result.suc(list):Result.fail();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Stone stone){
        return stoneService.save(stone)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Stone stone){
        return stoneService.updateById(stone)?Result.suc():Result.fail();
    }
    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Stone stone){
        return stoneService.updateById(stone);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Stone stone){
        return stoneService.saveOrUpdate(stone);
    }
    //删除
    @GetMapping("/delete")
    public Result delete(String id){
        return stoneService.removeById(id)?Result.suc():Result.fail();
    }
    //查询（模糊、匹配）
    @PostMapping("/listP")
    public Result listP(@RequestBody Stone stone){
        LambdaQueryWrapper<Stone> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(stone.getStoneName())){
            lambdaQueryWrapper.like(Stone::getStoneName,stone.getStoneName());
        }
//        lambdaQueryWrapper.eq(User::getName,user.getName());
        return  Result.suc(stoneService.list(lambdaQueryWrapper));
    }

    @PostMapping("/listPage")
    public List<Stone> listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name=(String)param.get("name");

        Page<Stone> page=new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Stone> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Stone::getStoneName,name);
        IPage result=stoneService.page(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());
        return result.getRecords();
    }
    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name=(String)param.get("name");
        Page<Stone> page=new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Stone> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Stone::getStoneName,name);
        }
        IPage result=stoneService.page(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());
        return Result.suc(result.getRecords(),result.getTotal());
    }
}
