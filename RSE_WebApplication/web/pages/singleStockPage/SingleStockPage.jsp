<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="utils.*" %>
<%@ page import="constants.Constants" %>
<%@ page import="classes.StockExchange" %>
<%@ page import="classes.Stock" %>
    <%@ page import="classes.Item" %>
    <%@ page import="classes.User" %>
    <script type="text/javascript" src="/Rizpa_v3/common/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="/Rizpa_v3/common/context-path-helper.js"></script>

    <script src="/Rizpa_v3/pages/singleStockPage/single_stock_page.js"></script>
<style>
    ul {list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: #333333;}
    table {width:100%;}
    table, th, td {border: 1px solid black;
        border-collapse: collapse;}
    th, td {padding: 15px;
        text-align: left;}
    .mainScreenTable tr:nth-child(even) {background-color: #eee;}
    .mainScreenTable tr:nth-child(odd) {background-color: #fff;}
    .mainScreenTable th {background-color: black;
        color: white;}

    li {float: left;}

    li a {
        display: block;
        color: white;
        text-align: center;
        padding: 16px;
        text-decoration: none;}

    li a:hover {background-color: #111111;}

    select {background-color: #111111;
        color: white;}

    /* for the scrolling of the table */
    #table-wrapper {position:relative;}
    #table-scroll {height:250px;
        overflow:auto;
        margin-top:20px;}
    #table-wrapper table {width:80%;}
    #table-wrapper table thead th .text {
        position:absolute;
        top:-20px;
        z-index:2;
        height:20px;
        width:35%;
        border:1px solid #ff0000;}

</style>
</head>
<body>
<div class="container">
<% StockExchange stockExchange = ServletUtils.getStockExchange(request.getServletContext());%>

<% String usernameFromSession = SessionUtils.getUsername(request);%>
<% User user = stockExchange.getUser(usernameFromSession);%>
<% String symbol = (String)request.getAttribute(Constants.SYMBOL);%>
<% Stock companyStock = stockExchange.getStock(symbol);%>
    <script>
        setSymbolOfCompany("<%=symbol%>");
    </script>
<h1> <%=stockExchange.getStock(symbol).getCompanyName()%> : <%=stockExchange.getStock(symbol).getSymbol()%> (<%=stockExchange.getStock(symbol).getStock_gate()+"$)"%></h1>
<p>Live Info!</p>
<ul>
    <%if(user.getType()!=1){%>
    <li><a href="MainScreen.html">Main Menu</a> </li>
    <li id="moneyInTheAccount"><a>Cash: </a></li>
    <%} else {%>
    <li><a href="MainScreenAdmin.html">Main Menu</a> </li>
    <%}%>
    <li><a href="logout">Logout</a></li>
</ul>
    <br>
<ul>
    <li><a>Company Symbol: <%=companyStock.getSymbol()%></a></li>
    <li><a>Company Name: <%=companyStock.getCompanyName()%></a></li>
    <li><a>Company Stock Gate: <%=companyStock.getStock_gate()%>$</a></li>
    <li><a>Company Stock Cycle: <%=companyStock.getStock_cycle()%>$</a></li>
</ul>
    <br>
    <form method="GET" action="goToStock">
        <label for="stocks">Choose a Company: </label>
        <select name="stocks" id="stocks"></select>
        <input type="submit">
    </form>
    <br>
<%if(user.getType()!=1){%>
<ul>
<% Item itemOfUser = user.getItem(symbol)!=null ? user.getItem(symbol):null;%>
<% if(itemOfUser != null) {%>
    <li><a>My stock quantity: <%=itemOfUser.getQuantity()%></a></li>
    <li><a>My worth of stock: <%=itemOfUser.getWorthOfItem()+"$"%></a></li>
<%} else {%>
    <li><a>My stock quantity: 0</a></li>
    <li><a>My worth of stock: 0$</a></li>
<%}%>
</ul>
<br>
<h2>Trade Ordinance:</h2>
<form method="GET" id="tradeInstructionForm" action="tradeInstruction" >
    <label for="<%=Constants.SYMBOL%>">Stock to trade: <%=symbol%></label>
    <input type="text" value="<%=symbol%>" id="<%=Constants.SYMBOL%>" name="<%=Constants.SYMBOL%>" hidden>
    <br>
    <label>Choose type of action: </label><br>
    <input type="radio" id="LMT" name="typeAction" value="1" checked="checked">
    <label for="LMT">LMT</label><br>
    <input type="radio" id="MKT" name="typeAction" value="2">
    <label for="MKT">MKT</label><br>
    <input type="radio" id="FOK" name="typeAction" value="3">
    <label for="FOK">FOK</label><br>
    <input type="radio" id="IOC" name="typeAction" value="4">
    <label for="IOC">IOC</label><br>
    <br>
    <label>Choose Sell or Buy: </label><br>
    <input type="radio" id="SELL" name="buyOrSell" value="0" checked="checked">
    <label for="SELL">SELL</label><br>
    <input type="radio" id="BUY" name="buyOrSell" value="1">
    <label for="BUY">BUY</label><br>
    <br>
    <label for="sumOfStocks">Quantity of stocks: </label>
    <input type="number" id="sumOfStocks" name="sumOfStocks" min="1">
    <br><br>
    <label for="priceOfStock">Price of offer for each stock:</label>
    <input type="number" id="priceOfStock" name="priceOfStock" min="1">
    <br><br><br>
    <input type="submit" value="Apply now">
</form>
<%}%>
<br><br>
<h2>List Of Transactions:</h2>
    <table class="mainScreenTable" id="TransactionTable">
        <tr>
            <th>Date</th>
            <th>Quantity</th>
            <th>Stock's Gate</th>
        </tr>
    </table>
<br><br>
<%if(user.getType()==1){%>
<h2>List Of Sell:</h2>
    <table class="mainScreenTable" id="SellTable">
        <tr>
            <th>Action Type</th>
            <th>Company Symbol</th>
            <th>Username</th>
            <th>Stocks Quantity</th>
            <th>Stock's Gate</th>
            <th>Date</th>
        </tr>
        <tr> <th> KILL HIM NOW! </th></tr>
    </table>
<br><br>
    <h2>List Of Buy:</h2>
    <table class="mainScreenTable" id="BuyTable">
        <tr>
            <th>Action Type</th>
            <th>Company Symbol</th>
            <th>Username</th>
            <th>Stocks Quantity</th>
            <th>Stock's Gate</th>
            <th>Date</th>
        </tr>
        <tr> <th> KILL HIM NOW! </th></tr>
    </table>
<%}%>

</body>
</html>
