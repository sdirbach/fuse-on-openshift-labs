package io.example.openapi;

import javax.annotation.Generated;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Forward requests to the Camel servlet so it can service REST requests.
 */
@Generated("org.apache.camel.generator.openapi.SpringBootProjectSourceCodeGenerator")
@RestController
public final class CamelRestController {
    @RequestMapping("/**")
    public void camelServlet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String path = request.getRequestURI();
            String camelPrefix = (path != null && path.startsWith("/")) ? "/camel" : "/camel/";
            request.getServletContext().getRequestDispatcher(camelPrefix + path).forward(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
