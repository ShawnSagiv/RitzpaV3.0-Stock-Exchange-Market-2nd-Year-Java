package servlets;

import constants.Constants;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RedirectToStockPageServlet extends HttpServlet {
    private final static String STOCK_PAGE_URL = "/pages/singleStockPage/SingleStockPage.jsp";
    private final String USERS_AND_STOCKS_URL = "/Rizpa_v3/pages/mainScreenPage/MainScreen.html";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String symbolOfCompany = request.getParameter("stocks");
        ////handeling null input exception
        if(symbolOfCompany == null) response.sendRedirect(USERS_AND_STOCKS_URL);
        else {
            request.setAttribute(Constants.SYMBOL, symbolOfCompany);
            getServletContext().getRequestDispatcher(STOCK_PAGE_URL).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
