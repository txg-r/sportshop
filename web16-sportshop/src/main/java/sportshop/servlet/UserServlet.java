package sportshop.servlet;

import sportshop.pojo.User;
import sportshop.services.UserService;
import sportshop.services.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/user")
public class UserServlet extends ViewBaseServlet{
    private final UserService userService = new UserServiceImpl();

    public void queryUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTemplate("user/userinfo",request,response);
    }

    public void updateUserInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User(
                Integer.parseInt(request.getParameter("user_id")),
                (String) request.getParameter("user_name"),
                (String) request.getParameter("user_pwd"),
                (String) request.getParameter("user_realname"),
                (String) request.getParameter("user_address"),
                (String) request.getParameter("user_sex"),
                (String) request.getParameter("user_tel"),
                (String) request.getParameter("user_email"),
                (String) request.getParameter("user_qq")
        );
        if (userService.updateUserInfo(user)) {
            request.getSession().setAttribute("user",user);
            response.sendRedirect("index.do");
        }else {
            response.getWriter().println("error");
        }
    }


}
