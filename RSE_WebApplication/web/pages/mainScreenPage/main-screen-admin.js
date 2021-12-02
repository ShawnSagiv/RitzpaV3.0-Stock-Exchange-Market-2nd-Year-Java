var refreshRate = 500; //milli
var refreshRateOption = 5000; //milli seconds

var USER_TABLE_LIST_URL = buildUrlWithContextPath("/pages/mainScreenPage/usersMainScreenTable");
var STOCK_TABLE_LIST_URL = buildUrlWithContextPath("/pages/mainScreenPage/stocksMainScreenTable");
var STOCKS_OPTION_URL = buildUrlWithContextPath("/pages/mainScreenPage/stocksOption");

function refreshUsersList(users) {
    //clear all current users
    $("#usersMainScreenTable").empty();
    $('<tr>' + '<th>' + 'Name' + '</th>' + '<th>' + 'Admin' + '</th>' + '</tr>')
        .appendTo($("#usersMainScreenTable"));
    for(var i=0;i<users.length;i++) {
        var isAdmin = users[i].type;
        if(isAdmin===0) isAdmin = "No"
        else isAdmin = "Yes"
        $('<tr>' + '<td>' + users[i].name + '</td>' + '<td>' + isAdmin + '</td>' + '</tr>')
            .appendTo($("#usersMainScreenTable"));
    }
}

function refreshStockList(stocks) {
    //clear all current users
    $("#stocksMainScreenTable").empty();
    $('<tr>' + '<th>' + 'Symbol' + '</th>' +
        '<th>' + 'Company Name' + '</th>' +
        '<th>' + 'Stock Gate' + '</th>' +
        '<th>' + 'Overall Transactions' + '</th>' +
        '<th>' + 'Stock Cycle' + '</th>' + '</tr>')
        .appendTo($("#stocksMainScreenTable"));
    for(var i=0;i<stocks.length;i++) {
        $('<tr>' + '<td>' + stocks[i].symbol + '</td>' +
            '<td>' + stocks[i].company_name + '</td>' +
            '<td>' + stocks[i].stock_gate + '</td>' +
            '<td>' + stocks[i].sum_of_transactions + '</td>' +
            '<td>' + stocks[i].stock_cycle + '</td>' + '</tr>')
            .appendTo($("#stocksMainScreenTable"));
    }
}

function refreshStocksOption(stocks) {
    //clear all current users
    $("#stocks").empty();
    for(var i=0;i<stocks.length;i++) {
        $('<option>' + stocks[i] + '</option>')
            .appendTo($("#stocks"));
    }
}


function ajaxUsersList() {
    $.ajax({
        url: USER_TABLE_LIST_URL,
        success: function(users) {
            refreshUsersList(users);
        }
    });
}
function ajaxStocksList() {
    $.ajax({
        url: STOCK_TABLE_LIST_URL,
        success: function(stocks) {
            refreshStockList(stocks);
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

//activate the timer calls after the page is loaded
$(function() {
//Initialize data in main screen page
    setTimeout(ajaxStocksOption, 37);
    setTimeout(ajaxUsersList, 37);
    setTimeout(ajaxStocksList, 37);
//Intervals for automatic refresh
    setInterval(ajaxStocksOption, refreshRateOption);
    setInterval(ajaxUsersList, refreshRate);
    setInterval(ajaxStocksList, refreshRate);

});