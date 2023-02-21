package com.example.bishe.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bishe.common.QueryPageParam;
import com.example.bishe.common.Result;
import com.example.bishe.entity.Bronze;
import com.example.bishe.service.BronzeService;
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
@RequestMapping("/bronze")
public class BronzeController {
    @Autowired
    private BronzeService bronzeService;
    @GetMapping("/list")
    public List<Bronze> list(){
        return bronzeService.list();
    }
    @GetMapping("/findByName")
    public Result findByName(@RequestParam String bronzeName){
        List list = bronzeService.lambdaQuery().eq(Bronze::getBronzeName,bronzeName).list();
        return list.size()>0?Result.suc(list):Result.fail();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Bronze bronze){
        return bronzeService.save(bronze)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Bronze bronze){
        return bronzeService.updateById(bronze)?Result.suc():Result.fail();
    }
    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Bronze bronze){
        return bronzeService.updateById(bronze);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Bronze bronze){
        return bronzeService.saveOrUpdate(bronze);
    }
    //删除
    @GetMapping("/delete")
    public Result delete(String id){
        return bronzeService.removeById(id)?Result.suc():Result.fail();
    }
    //查询（模糊、匹配）
    @PostMapping("/listP")
    public Result listP(@RequestBody Bronze bronze){
        LambdaQueryWrapper<Bronze> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(bronze.getBronzeName())){
            lambdaQueryWrapper.like(Bronze::getBronzeName,bronze.getBronzeName());
        }
//        lambdaQueryWrapper.eq(User::getName,user.getName());
        return  Result.suc(bronzeService.list(lambdaQueryWrapper));
    }

    @PostMapping("/listPage")
    public List<Bronze> listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name=(String)param.get("name");

        Page<Bronze> page=new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Bronze> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Bronze::getBronzeName,name);
        IPage result=bronzeService.page(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());
        return result.getRecords();
    }
    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name=(String)param.get("name");
        Page<Bronze> page=new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Bronze> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Bronze::getBronzeName,name);
        }
        IPage result=bronzeService.page(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());
        return Result.suc(result.getRecords(),result.getTotal());
    }
}
