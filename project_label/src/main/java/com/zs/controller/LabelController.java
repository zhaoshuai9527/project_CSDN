package com.zs.controller;


import com.zs.entity.Label;
import com.zs.entity.Result;
import com.zs.entity.StatusCode;
import com.zs.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RefreshScope//获取yml中的数据注入值
@RestController  //可以return值直接打印  ===任何页面都可以打印，不管页面
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;
    //获取配置文件中注入的属性值
    @Value("${project.host}")
    private String projectHost ;


    //查询全部
    @GetMapping
    public Result findAll(){
        System.out.println( "-------------" +projectHost + "-------------");
        return new Result(true, StatusCode.OK,"查询成功",
                labelService.findAll()
                        );
    }

    //    根据id查询
    @GetMapping(value = "{id}")
    public Result findLabelById(@PathVariable("id") String id){
        return new Result(true,StatusCode.OK,"查询ID成功",labelService.findLabelById(id));
    }

    //增加label
    @PostMapping
    public Result addSaveLabel(@RequestBody Label label){
        labelService.addSaveLabel(label);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    //根据id删
    @DeleteMapping(value = "{id}")
    public Result deleteLabelById(@PathVariable("id") String id){
        labelService.deleteLabelById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    ////更新label
    @PutMapping
    public Result updateLabel(@RequestBody Label label ){//UPDATE ..WHERE ID =?
        labelService.updateLabel(label);
        return new Result(true,StatusCode.OK,"修改成功" ) ;
    }


    //根据条件查询
    @RequestMapping(method = RequestMethod.GET,value = "findLabels")
    public Result findLabels(@RequestBody Map findMap){
        return new Result(true,StatusCode.OK,"条件查询完毕",labelService.findLabels(findMap));

    }

    //条件查询并分页
    @GetMapping(value = "findLabelsAndPage/{start}/{pageSize}")
    public Result findLabelsAndPage(@RequestBody Map findMap, @PathVariable int start,@PathVariable int pageSize){
        return new Result(true,StatusCode.OK,"查询并分页完毕！",labelService.findLabelsAndPage(findMap,start,pageSize));
    }
}
