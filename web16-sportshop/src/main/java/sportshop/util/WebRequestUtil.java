package sportshop.util;

import javax.servlet.http.HttpServletRequest;

public class WebRequestUtil {
        public static Integer getParamValue(HttpServletRequest request,String paramName,Integer defaultValue){
            String str = request.getParameter(paramName);
            Integer currentPage = defaultValue;
            if (null != str){
                currentPage = Integer.parseInt(str);
            }
            return currentPage;
        }
}
