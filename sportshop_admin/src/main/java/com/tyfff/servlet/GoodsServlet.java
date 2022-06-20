package com.tyfff.servlet;

import com.tyfff.entity.Catelog;
import com.tyfff.entity.Goods;
import com.tyfff.service.GoodsService;
import com.tyfff.service.serviceImpl.GoodsServiceImpl;
import com.tyfff.util.PageBean;
import com.tyfff.util.WebRequestUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/goods")
@MultipartConfig
public class GoodsServlet extends ViewBaseServlet{
    private final GoodsService goodsService = new GoodsServiceImpl();

    public void getPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer pageNo = WebRequestUtil.getParamValue(request, "pageNo", PageBean.DEFAULT_PAGENO);
        Integer pageSize = WebRequestUtil.getParamValue(request, "pageSize", PageBean.DEAULT_PAGESIZE);
        String word = request.getParameter("queryWord");
        PageBean<Goods> pageBean = goodsService.getPage(word,pageNo,pageSize);
        request.setAttribute("page",pageBean);
        request.setAttribute("queryWord",word!=null?word:"");
        processTemplate("goods/list",request,response);
    }

    public void toAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Catelog> catelogList = goodsService.listCatelogAll();
        req.setAttribute("catelogList",catelogList);
        processTemplate("goods/add",req,resp);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, ParseException {
        //处理上传文件
        Part file = req.getPart("goods_pic");
        String headerInfo = file.getHeader("content-disposition");
        String fileName = headerInfo.substring(headerInfo.lastIndexOf("=") + 2, headerInfo.length() - 1);
        //获得存储上传文件的文件夹路径
        String fileSavingFolder = this.getServletContext().getRealPath("upload");
        String fileSavingPath = fileSavingFolder + File.separator + fileName;

        //如果存储上传文件的文件夹不存在，则创建文件夹
        File f = new File(fileSavingFolder + File.separator);
        if(!f.exists()){
            f.mkdirs();
        }
        //将上传的文件内容写入服务器文件中
        file.write(fileSavingPath);

        //处理普通参数,封装goods
        Goods goods = new Goods();
        goods.setGoods_name(req.getParameter("goods_name"));
        goods.setGoods_miaoshu(req.getParameter("goods_miaoshu"));
        goods.setGoods_pic("upload/"+fileName);
        goods.setMarket_price(Integer.valueOf(req.getParameter("market_price")));
        goods.setMall_price(Integer.valueOf(req.getParameter("mall_price")));
        goods.setCatelog(new Catelog(Integer.parseInt(req.getParameter("catelog_id"))));
        goods.setStock_num(Integer.valueOf(req.getParameter("stock_num")));
        goods.setGoods_address(req.getParameter("goods_address"));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        goods.setEnter_date(sdf.parse(req.getParameter("enter_date")));


        //添加到数据库
        if (goodsService.insertGoods(goods)){
            resp.sendRedirect("goods?action=getPage");
        }else {
            req.setAttribute("errorMsg","添加错误");
            processTemplate("errorPage",req,resp);
        }
    }

    public void toUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String goods_id = req.getParameter("goods_id");
        Goods goods = goodsService.getGoodsById(Integer.valueOf(goods_id));
        List<Catelog> catelogList = goodsService.listCatelogAll();
        req.setAttribute("goods",goods);
        req.setAttribute("catelogList",catelogList);
        processTemplate("goods/update",req,resp);
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Goods goods = new Goods();
        Integer goods_id = Integer.valueOf(req.getParameter("goods_id"));
        goods.setGoods_id(goods_id);
        try {
            goods.setGoods_name(req.getParameter("goods_name"));
            goods.setMarket_price(Integer.valueOf(req.getParameter("market_price")));
            goods.setMall_price(Integer.valueOf(req.getParameter("mall_price")));
            goods.setCatelog(new Catelog(Integer.parseInt(req.getParameter("catelog_id"))));
            if (goodsService.updateByGoods(goods)){
                resp.sendRedirect("goods?action=getPage");
            }else {
                req.setAttribute("errorMsg","保存错误");
                processTemplate("errorPage",req,resp);
            }
        }catch (Exception e){
            req.setAttribute("errorMsg","保存错误");
            processTemplate("errorPage",req,resp);
        }
    }

    public void downloadPic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String goods_pic = req.getParameter("goods_pic");
        resp.setHeader("content-disposition","attachment; filename="+goods_pic.replace("upload/",""));
        String realPath = getServletContext().getRealPath(goods_pic);
        BufferedInputStream bf = new BufferedInputStream(Files.newInputStream(Paths.get(realPath)));
        ServletOutputStream outputStream = resp.getOutputStream();
        byte[] bytes = new byte[1024];
        int read = bf.read(bytes);
        while (read!=-1){
            outputStream.write(bytes);
            read = bf.read(bytes);
        }

    }
}
