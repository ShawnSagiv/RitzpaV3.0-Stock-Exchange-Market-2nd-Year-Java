var refreshRate = 500; //milli seconds
var refreshRateOption = 5000; //milli seconds
var MONEY_IN_THE_BANK_URL = buildUrlWithContextPath("/pages/mainScreenPage/cashAccount");
var STOCKS_OPTION_URL = buildUrlWithContextPath("pages/singleStockPage/stocksOption");
var TRANSACTIONS_LIST_URL = buildUrlWithContextPath("pages/singleStockPage/transactionsTable");
var SELL_LIST_URL = buildUrlWithContextPath("pages/singleStockPage/sellTable");
var BUY_LIST_URL = buildUrlWithContextPath("pages/singleStockPage/buyTable");
var symbolOfCompany = "";

function setSymbolOfCompany(symbol) {
    symbolOfCompany = symbol;
}

function refreshStocksOption(stocks) {
    //clear all current users
    $("#stocks").empty();
    for(var i=0;i<stocks.length;i++) {
        $('<option>' + stocks[i] + '</option>')
            .appendTo($("#stocks"));
    }
}
function refreshCash(cash) {
    //clear all current users
    $("#moneyInTheAccount").empty();
    $('<a>' + "Cash: " + cash + '$' +'</a>').appendTo($("#moneyInTheAccount"));
}
function refreshTransactionList(transactions) {
    //clear all current users
    $("#TransactionTable").empty();
    $('<tr>' + '<th>' + 'Action type' + '</th>' +
        '<th>' + 'Date' + '</th>' +
        '<th>' + 'Quantity' + '</th>' +
        '<th>' + 'Stock\'s Gate' + '</th>' +
        '<th>' + 'Worth after action' + '</th>' + '</tr>')
        .appendTo($("#TransactionTable"));
    for(var i=0;i<transactions.length;i++) {
        $('<tr>' + '<td>' + transactions[i].date + '</td>' +
            '<td>' + transactions[i].num_of_stocks + '</td>' +
            '<td>' + transactions[i].price_of_stock + '</td>' +
            '<td>' + transactions[i].price_of_stock + '</td>' + '</tr>')
            .appendTo($("#TransactionTable"));
    }
}
function refreshSellList(sellList) {
    //clear all current users
    $("#SellTable").empty();
    $('<tr>' + '<th>' + 'Action type' + '</th>' +
        '<th>' + 'Company Symbol' + '</th>' +
        '<th>' + 'Username' + '</th>' +
        '<th>' + 'Stock Price' + '</th>' +
        '<th>' + 'Stocks Quantity' + '</th>' +
        '<th>' + 'Date' + '</th>' + '</tr>')
        .appendTo($("#SellTable"));
    for(var i=0;i<sellList.length;i++) {
        var actionType;
        if(sellList[i].type == 1) actionType = "LMT";
        else if (sellList[i].type == 2) actionType = "MKT";
        else if (sellList[i].type == 3) actionType = "FOK";
        else actionType = "IOC";
        $('<tr>' + '<td>' + actionType + '</td>' +
            '<td>' + sellList[i].symbol + '</td>' +
            '<td>' + sellList[i].user.name + '</td>' +
            '<td>' + sellList[i].limit + '</td>' +
            '<td>' + sellList[i].sum_of_stocks + '</td>' +
            '<td>' + sellList[i].date + '</td>' + '</tr>')
            .appendTo($("#SellTable"));
    }
}
function refreshBuyList(buyList) {
    //clear all current users
    $("#BuyTable").empty();
    $('<tr>' + '<th>' + 'Action type' + '</th>' +
        '<th>' + 'Company Symbol' + '</th>' +
        '<th>' + 'Username' + '</th>' +
        '<th>' + 'Stock Price' + '</th>' +
        '<th>' + 'Stocks Quantity' + '</th>' +
        '<th>' + 'Date' + '</th>' + '</tr>')
        .appendTo($("#BuyTable"));
    for(var i=0;i<buyList.length;i++) {
        var actionType;
        if(buyList[i].type == 1) actionType = "LMT";
        else if (buyList[i].type == 2) actionType = "MKT";
        else if (buyList[i].type == 3) actionType = "FOK";
        else actionType = "IOC";
        $('<tr>' + '<td>' + actionType + '</td>' +
            '<td>' + buyList[i].symbol + '</td>' +
            '<td>' + buyList[i].user.name + '</td>' +
            '<td>' + buyList[i].limit + '</td>' +
            '<td>' + buyList[i].sum_of_stocks + '</td>' +
            '<td>' + buyList[i].date + '</td>' + '</tr>')
            .appendTo($("#BuyTable"));
    }
}

function ajaxMoneyInAccount() {
    $.ajax({
        url: MONEY_IN_THE_BANK_URL,
        success: function(money) {
            refreshCash(money);
        }
    });
}
function ajaxStocksOption() {
    $.ajax({
        url: STOCKS_OPTION_URL,
        success: function(stocks) {
            refreshStocksOption(stocks);
        }
    });
}
function ajaxTransactionList() {
    $.ajax({
        data: "symbol_of_company="+symbolOfCompany,
        url: TRANSACTIONS_LIST_URL,
        success: function(transactions) {
            refreshTransactionList(transactions);
        },
        error: function () { $("#TransactionTable").empty();
        }
    });
}
function ajaxSellList() {
    $.ajax({
        data: "symbol_of_company="+symbolOfCompany,
        url: SELL_LIST_URL,
        success: function(sellList) {
            refreshSellList(sellList);
        },
        error: function () { $("#SellTable").empty();
        }
    });
}
function ajaxBuyList() {
    $.ajax({
        data: "symbol_of_company="+symbolOfCompany,
        url: BUY_LIST_URL,
        success: function(buyList) {
            refreshBuyList(buyList);
        },
        error: function () { $("#BuyTable").empty();
        }
    });
}
//activate the timer calls after the page is loaded
$(function() {
    //The users list is refreshed automatically every second
    setTimeout(ajaxStocksOption, 37);
    setTimeout(ajaxTransactionList, 37);
    setTimeout(ajaxSellList, 37);
    setTimeout(ajaxBuyList, 37);

    setInterval(ajaxStocksOption, refreshRateOption);
    setInterval(ajaxMoneyInAccount, refreshRate);
    setInterval(ajaxTransactionList, refreshRate);
    setInterval(ajaxSellList, refreshRate);
    setInterval(ajaxBuyList, refreshRate);
});