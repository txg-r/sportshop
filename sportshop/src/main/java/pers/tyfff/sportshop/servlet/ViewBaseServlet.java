package pers.tyfff.sportshop.servlet;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ViewBaseServlet extends HttpServlet {
    private TemplateEngine templateEngine;

    @Override
    public void init() throws ServletException {
        ServletContext servletContext = getServletContext();
        ServletContextTemplateResolver servletContextTemplateResolver = new ServletContextTemplateResolver(servletContext);
        //设置解析模式
        servletContextTemplateResolver.setTemplateMode(TemplateMode.HTML);
        //设置前后缀
        String prefix = servletContext.getInitParameter("view-prefix");
        String suffix = servletContext.getInitParameter("view-suffix");
        servletContextTemplateResolver.setPrefix(prefix);
        servletContextTemplateResolver.setSuffix(suffix);
        //设置缓存
        //servletContextTemplateResolver.setCacheTTLMs(1000L);
        servletContextTemplateResolver.setCacheable(false);
        //设置编码方式
        servletContextTemplateResolver.setCharacterEncoding("utf-8");
        //创建模板引擎
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(servletContextTemplateResolver);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.equals("POST")){
            req.setCharacterEncoding("utf-8");
        }
        String action = req.getParameter("action");
        if (action!=null){
            try {
                Method handler = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
                handler.invoke(this,req,resp);
            } catch (Exception e) {
                resp.setStatus(404);
            }
        }else{
            if (method.equals("GET")){
                doGet(req,resp);
            }else {
                doPost(req,resp);
            }
        }

    }

    public void processTemplate(String templateName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        WebContext webContext = new WebContext(request, response, getServletContext());
        templateEngine.process(templateName, webContext, response.getWriter());
    }
}
