package sportshop.servlet;

import sportshop.pojo.Cart.Cart;
import sportshop.pojo.User;
import sportshop.services.UserService;
import sportshop.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends ViewBaseServlet{
    private final UserService userService = new UserServiceImpl();


    public void toLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processTemplate("user/login",request,response);
    }


    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user_name = request.getParameter("user_name");
        String user_pwd = request.getParameter("user_pwd");
        User user = userService.login(user_name,user_pwd);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String jsonStr = "{\"name\":\"fly\",\"type\":\"虫子\"}";
        if (user==null){
            response.getWriter().write("{\"loginFlag\":\"false\"}");
        }else {
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("cart",new Cart());
            response.getWriter().write("{\"loginFlag\":\"true\"}");
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("index.do");
    }

    public void toRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processTemplate("user/register",request,response);
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException{
        User user = new User();
        user.setUser_name((String) request.getParameter("user_name"));
        user.setUser_pwd((String) request.getParameter("user_pwd"));
        user.setUser_realname((String) request.getParameter("user_realname"));
        user.setUser_address((String) request.getParameter("user_address"));
        user.setUser_sex((String) request.getParameter("user_sex"));
        user.setUser_tel((String) request.getParameter("user_tel"));
        user.setUser_email((String) request.getParameter("user_email"));
        user.setUser_qq((String) request.getParameter("user_qq"));
        if (userService.register(user)) {
            response.sendRedirect("index.do");
        }else {
            response.getWriter().println("error");
        }
    }
}
