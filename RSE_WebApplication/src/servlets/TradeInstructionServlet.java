package servlets;

import classes.Stock;
import classes.StockExchange;
import classes.User;
import constants.Constants;
import utils.ServletUtils;
import utils.SessionUtils;
import webUsers.UserManager;
import javax.servlet.ServletException;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.InputMismatchException;


public class TradeInstructionServlet extends HttpServlet {

    /**URLs paths for the usage of LoginUserServlet.**/
    private final static String STOCK_PAGE_URL = "/pages/singleStockPage/SingleStockPage.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Get string username from session.
        String usernameFromSession = SessionUtils.getUsername(request);
        String company_symbol = request.getParameter(Constants.SYMBOL);


        //StockExchange: Object holding the "Rizpa Stock trading" + users data structures.
        //Get stockExchange from servletContext
        StockExchange market = ServletUtils.getStockExchange(getServletContext());


        /***************** Shawn *******************/

        //choose what kind of action.
        int actionType = new Integer(request.getParameter("typeAction")).intValue();
        int sellOrBuy = new Integer(request.getParameter("buyOrSell")).intValue();
        int sumOfStocks = 0;
        int limit = 0;
        int killAllIfNotFull = 0;
        int killTheRest = 0;
        try{
            sumOfStocks = new Integer(request.getParameter("sumOfStocks")).intValue();
            if(sumOfStocks == 0) { throw new Exception("You can't put 0 as quantity for number of stocks."); }
            else if(sumOfStocks < 0) { throw new Exception("You can't put negative number as quantity for number of stocks."); }  // exception: for SumOfStocks < 0:
            limit = new Integer(request.getParameter("priceOfStock")).intValue();
            if(limit == 0) { throw new Exception("You can't put 0 as price foreach stock."); }
            else if(limit < 0) { throw new Exception("You can't put negative number as price foreach stock."); }  // exception: for SumOfStocks < 0:

        }catch (NumberFormatException e) {

        }catch (NullPointerException e) {
            ///////handle exception
        }catch (Exception e) {

        }

        if (actionType == 2) { limit = -1; } //If it's an MKT action.
        else if (actionType == 3) { killAllIfNotFull = 1; }
        else if (actionType == 4) { killTheRest = 1; }

        Stock.Bid newBid = new Stock.Bid(sellOrBuy, actionType, company_symbol, sumOfStocks, limit,
                market.getUser(usernameFromSession), killTheRest, killAllIfNotFull);
        if (company_symbol != null && newBid != null){
            market.addBid(company_symbol, newBid);
        }
        request.setAttribute(Constants.SYMBOL, company_symbol);
        getServletContext().getRequestDispatcher(STOCK_PAGE_URL).forward(request, response);
    }
}