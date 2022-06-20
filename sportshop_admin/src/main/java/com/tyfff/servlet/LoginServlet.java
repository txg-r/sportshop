package com.tyfff.servlet;

import com.tyfff.entity.Admin;
import com.tyfff.service.AdminService;
import com.tyfff.service.serviceImpl.AdminServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends ViewBaseServlet{
    private final AdminService adminService = new AdminServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setAttribute("loginError",false);
        req.setAttribute("codeError",false);
        processTemplate("login",req,resp);
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String userpwd = req.getParameter("userpwd");
        if (req.getParameter("code").equalsIgnoreCase((String) req.getSession().getAttribute("code"))) {
            Admin admin = adminService.getAdmin(username, userpwd);
            if (admin==null){
                req.setAttribute("loginError",true);
                processTemplate("login",req,resp);
            }else {
                req.getSession().setAttribute("admin",admin);
                resp.sendRedirect("main");
            }
        }else {
            req.setAttribute("codeError",true);
            processTemplate("login",req,resp);
        }
    }

    public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect("login");
    }

    public void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(req.getSession().getAttribute("admin")!=null);
    }

}
