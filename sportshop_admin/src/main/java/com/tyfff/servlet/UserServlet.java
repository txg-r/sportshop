package com.tyfff.servlet;

import com.tyfff.entity.Goods;
import com.tyfff.entity.User;
import com.tyfff.service.UserService;
import com.tyfff.service.serviceImpl.UserServiceImpl;
import com.tyfff.util.PageBean;
import com.tyfff.util.WebRequestUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends ViewBaseServlet{
    private final UserService userService = new UserServiceImpl();

    public void getPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer pageNo = WebRequestUtil.getParamValue(request, "pageNo", PageBean.DEFAULT_PAGENO);
        Integer pageSize = WebRequestUtil.getParamValue(request, "pageSize", PageBean.DEAULT_PAGESIZE);
        String word = request.getParameter("queryWord");
        PageBean<User> pageBean = userService.getPage(word,pageNo,pageSize);
        request.setAttribute("page",pageBean);
        processTemplate("user/list",request,response);
    }


}
