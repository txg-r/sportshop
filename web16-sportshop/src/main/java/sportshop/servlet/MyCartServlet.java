package sportshop.servlet;

import sportshop.pojo.Cart.Cart;
import sportshop.pojo.Cart.CartItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/myCart")
public class MyCartServlet extends ViewBaseServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processTemplate("myCart/myCart",req,resp);
    }

    public void addCartItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        cart.addCartItem(new CartItem(
                Integer.parseInt(req.getParameter("goods_id")),
                req.getParameter("goods_name"),
                Integer.parseInt(req.getParameter("goods_price")),
                Integer.parseInt(req.getParameter("goods_quantity"))
        ));
        req.getSession().setAttribute("cart",cart);
        resp.sendRedirect("myCart");
    }

    public void updateCartItemQuantity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        cart.updateCartItemQuantity(
                Integer.valueOf(req.getParameter("goods_id")),
                Integer.valueOf(req.getParameter("goods_quantity"))
        );
        req.getSession().setAttribute("cart",cart);
        resp.sendRedirect("myCart");
    }

    public void deleteCartItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        cart.removeCartItem(Integer.valueOf(req.getParameter("goods_id")));
        req.getSession().setAttribute("cart",cart);
        resp.sendRedirect("myCart");
    }

    public void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        cart.clear();
        req.getSession().setAttribute("cart",cart);
        resp.sendRedirect("myCart");
    }
}
