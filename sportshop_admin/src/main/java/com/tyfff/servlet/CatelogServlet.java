package com.tyfff.servlet;

import com.tyfff.entity.Catelog;
import com.tyfff.service.CatelogService;
import com.tyfff.service.serviceImpl.CatelogServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/catelog")
public class CatelogServlet extends ViewBaseServlet{
    private final CatelogService catelogService = new CatelogServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Catelog> catelogList = catelogService.listAll();
        req.setAttribute("catelogList",catelogList);
        processTemplate("catelog/list",req,resp);
    }

    public void toAdd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //用于回显错误信息
        req.setAttribute("addError",true);
        processTemplate("catelog/add",req,resp);
    }

    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String catelog_name = req.getParameter("catelog_name");
        String catelog_miaoshu = req.getParameter("catelog_miaoshu");
        if (catelogService.save(catelog_name,catelog_miaoshu)) {
            resp.sendRedirect("catelog");
        }else {
            req.setAttribute("addError",false);
            processTemplate("catelog/add",req,resp);
        }
    }
}
