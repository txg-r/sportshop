package com.tyfff.servlet;

import com.tyfff.entity.Admin;
import com.tyfff.service.AdminService;
import com.tyfff.service.serviceImpl.AdminServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends ViewBaseServlet{
    private final AdminService adminService = new AdminServiceImpl();

    public void toUpdateAdminPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processTemplate("admin/pwd-change",request,response);
    }

    public void updateAdminPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        String userPwd = request.getParameter("userPwd");
        response.setCharacterEncoding("UTF-8");
        //验证原始密码是否正确
        if (admin.getUserpwd().equals(userPwd)){
            String newPwd = request.getParameter("newPwd");
            Admin newAdmin = new Admin(admin.getUserid(), admin.getUsername(), newPwd);
            if (adminService.updateAdmin(newAdmin)) {
                session.invalidate();
                response.getWriter().write("true");
            }else {
                response.getWriter().write("修改失败");
            }
        }else {
            response.getWriter().write("原始密码输入错误");
        }
    }
}
