package servlets;

import classes.StockExchange;
import utils.ServletUtils;
import utils.SessionUtils;
import webUsers.UserManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginUserServlet", urlPatterns = {"/pages/loginPage/loginUserToRizpa"})
public class LoginUserServlet extends HttpServlet {

    /**URLs paths for the usage of LoginUserServlet.**/
    private final String USERS_AND_STOCKS_URL = "./pages/mainScreenPage/MainScreen.html";
    private final String ADMIN_URL = "./pages/mainScreenPage/MainScreenAdmin.html";
    private final String LOGIN_AGAIN = "/Rizpa_v3/index.html";
    private final String ERROR_LOGIN = "/pages/loginerror/login_attempt_after_error.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);

    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Get string username from session.
        String usernameFromSession = SessionUtils.getUsername(request);
        //Get userManager from servlet context.
        UserManager userManager = ServletUtils.getUserManager(getServletContext());

        //StockExchange: Object holding the "Rizpa Stock trading" + users data structures.
        //Get stockExchange from servletContext
        StockExchange market = ServletUtils.getStockExchange(getServletContext());

        if (usernameFromSession == null) {
            //user is not logged in yet
            //Get string username from text field at form login
            String usernameFromParameter = request.getParameter("username");
            if (usernameFromParameter == null || usernameFromParameter.isEmpty()) {
                //no username in session and no username in parameter -
                //redirect back to the index page
                //this return an HTTP code back to the browser telling it to load
                response.sendRedirect(LOGIN_AGAIN);
            } else {
                //normalize the username value
                usernameFromParameter = usernameFromParameter.trim();

                /*
                One can ask why not enclose all the synchronizations inside the userManager object ?
                Well, the atomic action we need to perform here includes both the question (isUserExists) and (potentially) the insertion
                of a new user (addUser). These two actions needs to be considered atomic, and synchronizing only each one of them, solely, is not enough.
                (of course there are other more sophisticated and performable means for that (atomic objects etc) but these are not in our scope)

                The synchronized is on this instance (the servlet).
                As the servlet is singleton - it is promised that all threads will be synchronized on the very same instance (crucial here)

                A better code would be to perform only as little and as necessary things we need here inside the synchronized block and avoid
                do here other not related actions (such as request dispatcher\redirection etc. this is shown here in that manner just to stress this issue
                 */
                synchronized (this) {
                    if (userManager.isUserExists(usernameFromParameter)) {
                        String errorMessage = "Username " + usernameFromParameter + " already exists. Please enter a different username.";
                        // username already exists, forward the request back to index.jsp
                        // with a parameter that indicates that an error should be displayed
                        // the request dispatcher obtained from the servlet context is one that MUST get an absolute path (starting with'/')
                        // and is relative to the web app root
                        // see this link for more details:
                        // http://timjansen.github.io/jarfiller/guide/servlet25/requestdispatcher.xhtml
                        request.setAttribute("username_error", errorMessage);
                        getServletContext().getRequestDispatcher(ERROR_LOGIN).forward(request, response);
                    }
                    else {
                        //add the new user to the users list of userManager.
                        userManager.addUser(usernameFromParameter);
                        //add the new user to the users list of stockExchange.
                        String isAdmin = request.getParameter("adminCheckBox");
                        int typeOfUser = 0;
                        if(isAdmin != null) typeOfUser = 1;
                        market.addUser(usernameFromParameter, typeOfUser);
                        //set the username in a session so it will be available on each request
                        //the true parameter means that if a session object does not exists yet
                        //create a new one
                        request.getSession(true).setAttribute("username", usernameFromParameter);

                        //redirect the request to the Users & Stocks page - in order to actually change the URL
                        System.out.println("On login, request URI is: " + request.getRequestURI());
                        response.setHeader("username", usernameFromSession);
                        if(typeOfUser == 0) response.sendRedirect(USERS_AND_STOCKS_URL);
                        else response.sendRedirect(ADMIN_URL);
                    }
                }
            }
        } else {
            //user is already logged in
            response.setHeader("username", usernameFromSession);
            response.sendRedirect(USERS_AND_STOCKS_URL);
        }
    }
}