package servlets;

import classes.StockExchange;
import utils.ServletUtils;
import utils.SessionUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Collection;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class LoadXMLServlet extends HttpServlet {

    private final String USERS_AND_STOCKS_URL = "./MainScreen.html";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");

        //PrintWriter out = response.getWriter();
        String usernameFromSession = SessionUtils.getUsername(request);
        StockExchange market = ServletUtils.getStockExchange(getServletContext());
        Collection<Part> parts = request.getParts();

        for (Part part : parts) {
            try {
                market.insertXMLFromFileChooser(part.getInputStream(), usernameFromSession);
            } catch (Exception e) {
                response.getOutputStream().println(e.getMessage());
            }
        }
        response.getOutputStream().println(request.getParts().toString() + " file(s):Uploaded to Rizpa");
        market.getUser(usernameFromSession).updateWorth();
        response.sendRedirect(USERS_AND_STOCKS_URL);
    }
}