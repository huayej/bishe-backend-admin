package com.example.bishe.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bishe.common.QueryPageParam;
import com.example.bishe.common.Result;
import com.example.bishe.entity.Painting;
import com.example.bishe.service.PaintingService;
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
@RequestMapping("/painting")
public class PaintingController {
    @Autowired
    private PaintingService paintingService;
    @GetMapping("/list")
    public List<Painting> list(){
        return paintingService.list();
    }
    @GetMapping("/findByName")
    public Result findByName(@RequestParam String paintingName){
        List list = paintingService.lambdaQuery().eq(Painting::getPaintingName,paintingName).list();
        return list.size()>0?Result.suc(list):Result.fail();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Painting painting){
        return paintingService.save(painting)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Painting painting){
        return paintingService.updateById(painting)?Result.suc():Result.fail();
    }
    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Painting painting){
        return paintingService.updateById(painting);
    }
    //新增或修改
    @PostMapping("/saveOrMod")
    public boolean saveOrMod(@RequestBody Painting painting){
        return paintingService.saveOrUpdate(painting);
    }
    //删除
    @GetMapping("/delete")
    public Result delete(String id){
        return paintingService.removeById(id)?Result.suc():Result.fail();
    }
    //查询（模糊、匹配）
    @PostMapping("/listP")
    public Result listP(@RequestBody Painting painting){
        LambdaQueryWrapper<Painting> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(painting.getPaintingName())){
            lambdaQueryWrapper.like(Painting::getPaintingName,painting.getPaintingName());
        }
//        lambdaQueryWrapper.eq(User::getName,user.getName());
        return  Result.suc(paintingService.list(lambdaQueryWrapper));
    }

    @PostMapping("/listPage")
    public List<Painting> listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name=(String)param.get("name");

        Page<Painting> page=new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Painting> lambdaQueryWrapper=new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Painting::getPaintingName,name);
        IPage result=paintingService.page(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());
        return result.getRecords();
    }
    @PostMapping("/listPageC")
    public Result listPageC(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name=(String)param.get("name");
        Page<Painting> page=new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Painting> lambdaQueryWrapper=new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Painting::getPaintingName,name);
        }
        IPage result=paintingService.page(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());
        return Result.suc(result.getRecords(),result.getTotal());
    }
}
