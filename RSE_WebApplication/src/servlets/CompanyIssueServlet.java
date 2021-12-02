package servlets;

import classes.Stock;
import classes.StockExchange;
import utils.ServletUtils;
import utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;


public class CompanyIssueServlet extends HttpServlet {

    private final String USERS_AND_STOCKS_URL = "./MainScreen.html";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Get string username from session.
        String usernameFromSession = SessionUtils.getUsername(request);

        //StockExchange: Object holding the "Rizpa Stock trading" + users data structures.
        //Get stockExchange from servletContext
        StockExchange market = ServletUtils.getStockExchange(getServletContext());


        /***************** Shawn *******************/

        /******************************** Updated 18th Jul ***********************************/
        try{

            String companyName = request.getParameter("CmpName");
            for(String st : market.getBoorsa().keySet())
            {
                if(market.getStock(st).getCompanyName().equals(companyName))
                {
                    throw new Exception("Company Name Akreaady Exist");
                }

            }

            String symbol = request.getParameter("symbol").toUpperCase(Locale.ROOT);
            if(market.getBoorsa().containsKey(symbol))
            {
                throw new Exception("Symbol is already exist");
            }
            String sumOfStocksStr = request.getParameter("sumOfStocks");
            int sumOfStocks = new Integer(sumOfStocksStr).intValue();
            if(sumOfStocks==0)
            {
                throw new Exception("Sum Of Stocks Can't Be 0");
            }else if (sumOfStocks<0)
            {
                throw new Exception("Sum Of Stocks Can't Be Negativ");
            }
            String worthStr = request.getParameter("worth");
            int worth = new Integer(worthStr).intValue();
            if(worth==0)
            {
                throw new Exception("Worth Can't Be 0");
            }else if (worth<0)
            {
                throw new Exception("Worth Can't Be Negativ");
            }
            market.getUser(usernameFromSession).companyIssue(market, companyName, symbol, sumOfStocks, worth);
        }catch (NumberFormatException e) {

        }catch (NullPointerException e) {
        ///////handle exception
        }catch (Exception e) {
            response.getOutputStream().println(e.getMessage());
        }
        /******************************** Updated 18th Jul ***********************************/

        response.sendRedirect(USERS_AND_STOCKS_URL);

        /************** shawn **********/

    }
}