package sportshop.servlet;

import sportshop.pojo.Goods;
import sportshop.services.GoodsService;
import sportshop.services.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index.do")
public class IndexServlet extends ViewBaseServlet {

    private final GoodsService goodsService = new GoodsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Goods> topFiveList = goodsService.getTopFiveList();
        request.setAttribute("fiveList", topFiveList);
        super.processTemplate("new", request, response);
    }

    public void toContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processTemplate("contact",request,response);
    }
}
