package sportshop.servlet;

import sportshop.pojo.Cart.Cart;
import sportshop.pojo.Order;
import sportshop.pojo.Orderitem;
import sportshop.pojo.User;
import sportshop.services.OrderService;
import sportshop.services.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/myOrder")
public class OrderServlet extends ViewBaseServlet {
    private final OrderService orderService = new OrderServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Order> orders = orderService.queryOrderByUserId(user.getUser_id());
        req.setAttribute("orders",orders);
        processTemplate("order/myOrder",req,resp);
    }

    public void confirmOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        processTemplate("order/orderConfirm",req,resp);
    }

    public void submitOrder(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        user.setUser_address(req.getParameter("user_address"));
        String order_pay = req.getParameter("order_pay");
        try {
            Order order= orderService.submitOrder(
                    order_pay,
                    user,
                    (Cart) session.getAttribute("cart"));
            session.setAttribute("cart",new Cart());
            req.setAttribute("order",order);
        } catch (SQLException e) {
            /*使用order的存在判断是否提交成功,不成功渲染失败页面*/
            req.setAttribute("order",null);
            throw new RuntimeException(e);
        }
        processTemplate("order/orderSubmit",req,resp);
    }

    public void queryOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String order_id = req.getParameter("order_id");
        List<Orderitem> orderitems = orderService.queryOrderitemByOrderId(order_id);
        req.setAttribute("orderitemList",orderitems);
        processTemplate("order/orderDetail",req,resp);
    }

}
