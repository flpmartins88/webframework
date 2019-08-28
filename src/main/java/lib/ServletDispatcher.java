package lib;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="Controller", urlPatterns = {"/*"}, loadOnStartup = 2)
public class ServletDispatcher extends HttpServlet {

    private Logger log = Logger.getLogger(ServletDispatcher.class.getSimpleName());

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Processing " + request.getMethod() + " to uri " + request.getRequestURI());

        PrintWriter writer = response.getWriter();
        writer.println("Hello from servlet");
        writer.flush();
        writer.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
}
