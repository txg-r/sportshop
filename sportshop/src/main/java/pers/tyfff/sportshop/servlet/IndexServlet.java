package pers.tyfff.sportshop.servlet;

import pers.tyfff.sportshop.entity.Goods;
import pers.tyfff.sportshop.service.GoodsService;
import pers.tyfff.sportshop.service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index.action")
public class IndexServlet extends ViewBaseServlet{
    private GoodsService goodsService = new GoodsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Goods> newGoods = goodsService.findNewGoods(5);
        req.setAttribute("goodsList",newGoods);
        processTemplate("front/index",req,resp);
    }
}
