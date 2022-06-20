package sportshop.servlet;

import sportshop.pojo.Goods;
import sportshop.services.GoodsService;
import sportshop.services.impl.GoodsServiceImpl;
import sportshop.util.PageBean;
import sportshop.util.WebRequestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "IndexServlet", value = "/goods.do")
public class GoodsServlet extends ViewBaseServlet {

    private final GoodsService goodsService = new GoodsServiceImpl();

    protected void queryByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNo = WebRequestUtil.getParamValue(request, "pageNo", PageBean.DEFAULT_PAGENO);
        Integer pageSize = WebRequestUtil.getParamValue(request, "pageSize", PageBean.DEAULT_PAGESIZE);
        String word = request.getParameter("goodsName");
        PageBean<Goods> pageBean = goodsService.getByPage(word,pageNo,pageSize);
        request.setAttribute("pb",pageBean);
        request.setAttribute("goodsName",word);
        super.processTemplate("goods/list",request,response);
    }

    protected void queryDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goods_id = request.getParameter("goods_id");
        Goods goodsDetail = goodsService.getGoodsDetail(Integer.valueOf(goods_id));
        request.setAttribute("gd",goodsDetail);
        processTemplate("goods/goodsDetail",request,response);
    }
}
