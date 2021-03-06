package marubinotto.piggydb.ui;

import static java.lang.Long.parseLong;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.StringUtils.removeStart;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DocumentViewUrlFilter implements Filter {
  
  private static Log logger = LogFactory.getLog(DocumentViewUrlFilter.class);

  public void init(FilterConfig config) throws ServletException {
  }
  
  public static final String PATH_PREFIX = "/d/";

  public void doFilter(
    ServletRequest request, 
    ServletResponse response, 
    FilterChain chain) 
  throws IOException, ServletException {
    // Get the request path
    HttpServletRequest httpRequest = (HttpServletRequest)request;
    String path = removeStart(
      httpRequest.getRequestURI(), 
      httpRequest.getContextPath() + PATH_PREFIX);
    logger.info("path: " + path);
    
    String docViewPath = "/document-view.htm";  
    try {
      Long fragmentId = isNotBlank(path) ? parseLong(path) : 0L;
      if (fragmentId > 0) docViewPath += "?id=" + fragmentId;
    }
    catch (NumberFormatException e) {
      docViewPath += "?name=" + path;
    }
    httpRequest.getRequestDispatcher(docViewPath).forward(request, response);
  }

  public void destroy() {
  }
}
