package com.tyfff.servlet;

import com.tyfff.entity.Order;
import com.tyfff.entity.Orderitem;
import com.tyfff.service.OrderService;
import com.tyfff.service.serviceImpl.OrderServiceImpl;
import com.tyfff.util.PageBean;
import com.tyfff.util.WebRequestUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends ViewBaseServlet {
    private final OrderService orderService = new OrderServiceImpl();

    public void getPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer pageNo = WebRequestUtil.getParamValue(request, "pageNo", PageBean.DEFAULT_PAGENO);
        Integer pageSize = WebRequestUtil.getParamValue(request, "pageSize", PageBean.DEAULT_PAGESIZE);
        String word = request.getParameter("queryWord");
        PageBean<Order> pageBean = orderService.getPage(word, pageNo, pageSize);
        request.setAttribute("page", pageBean);
        request.setAttribute("queryWord", word != null ? word : "");
        processTemplate("order/list", request, response);
    }

    public void deliverGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String order_id = request.getParameter("order_id");
        if (orderService.updateOrderStatus(order_id)) {
            response.sendRedirect("order?action=getPage");
        } else {
            request.setAttribute("errorMsg", "发货失败");
            processTemplate("errorPage", request, response);
        }
    }

    public void getOrderDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String order_id = request.getParameter("order_id");
        List<Orderitem> orderitemList = orderService.getOrderitemByOrderId(order_id);
        request.setAttribute("orderitemList",orderitemList);
        processTemplate("order/detail",request,response);
    }

    public void removeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String order_id = request.getParameter("order_id");
        if (orderService.removeOrderByOrderId(order_id)){
            response.sendRedirect("order?action=getPage");
        } else {
            request.setAttribute("errorMsg", "删除失败");
            processTemplate("errorPage", request, response);
        }
    }
}
