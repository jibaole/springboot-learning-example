package org.spring.springboot.controller;

import org.spring.springboot.service.GoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description：
 * @author：JBL
 * @date：2018/1/25
 */
@Controller
@RequestMapping("/")
public class GoodsInfoController extends BaseController {

    @Autowired
    private GoodsInfoService goodsService;

    @RequestMapping("/getallgoods")
    @ResponseBody
    public Object getAll() {
        return renderSuccess( goodsService.getAll() );
    }

    /**
     * 添加索引
     * @return
     */
    @RequestMapping("/addGoodsIndex/{id}")
    @ResponseBody
    public Object addGoodsIndex(@PathVariable Integer id) {
        return renderSuccess( goodsService.addGoodsIndex(id) );
    }

    /**
     * 添加所有商品索引
     * @return
     */
    @RequestMapping("/addIndex")
    @ResponseBody
    public Object addIndex() {
        return renderSuccess( goodsService.addIndex() );
    }


    @RequestMapping("/deleteAllByIds")
    @ResponseBody
    public Object deleteAllByIds() {
        return renderSuccess( goodsService.deleteAllByIds() );
    }

    @RequestMapping("/deleteAllById/{id}")
    @ResponseBody
    public Object deleteAllById(@PathVariable String id) {
        return renderSuccess( goodsService.deleteAllById(id) );
    }
}
