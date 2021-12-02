package classes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/*************************************************************************************/
/****************************** Sub-Class - Stock ************************************/
/************************************ Start ******************************************/

public class Stock implements  Serializable {
    private String symbol;
    private String company_name;
    private int stock_price;
    private int sum_of_transactions;
    private int stock_cycle;
    private int stock_gate;
    private List<Transaction> list_of_transactions; //  after successeful transaction
    private List<Bid> list_for_sell;
    private List<Bid> list_for_buy;





    /************ Ctors - Stock *************/
    //default
    public Stock()
    {
        symbol=null;
        company_name=null;
        stock_price=0;
        stock_gate=0;
        sum_of_transactions=0;
        stock_cycle=0;
        list_of_transactions = new ArrayList<>();
        list_for_sell = new LinkedList<>();
        list_for_buy = new LinkedList<>();

    }

    ///full
    public Stock(String name_of_stock, String name_of_company, int _stock_price) {
        list_of_transactions = new ArrayList<>();
        list_for_sell = new LinkedList<>();
        list_for_buy = new LinkedList<>();
        this.symbol = name_of_stock.toUpperCase();
        this.company_name = name_of_company;
        this.stock_price = _stock_price;
        sum_of_transactions = 0;
        stock_cycle = 0;
        stock_gate = stock_price;
    }

    /************ Setters - Stock *************/

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setStock_price(int price) {
        this.stock_price = price;
    }

    public void setStockGate(int gate) { this.stock_gate = gate;}

    public void setCompany_name(String Cname) {
        this.company_name = Cname;
    }

    public void setPlusOneToSumOfTransactions()
    {
        sum_of_transactions++;
    }

    /************ Getters - Stock *************/

    public String getSymbol() {
        return this.symbol;
    }

    public String getCompanyName() {
        return this.company_name;
    }

    public int getStockPrice() {
        return this.stock_price;
    }

    public int getStock_cycle() {
        return stock_cycle;
    }

    public int getSum_of_transactions() {
        return sum_of_transactions;
    }

    public int getStock_gate() {
        return stock_gate;
    }

    public List<Bid> getList_for_sell() {
        return list_for_sell;
    }

    public List<Bid> getList_for_buy() {
        return list_for_buy;
    }

    public List<Transaction> getTransactionsList() {
        return list_of_transactions;
    }

    public List<Transaction> getList_of_transactions()
    {
        return list_of_transactions;
    }

    /************ ToString - Stock *************/

    @Override
    public String toString() //Print method for Stock
    {
        return " Company Name: \'" + company_name + '\'' +
                "(" + symbol + ")" + '\n' + '\t' +
                ", stock gate: " + stock_gate +
                ", sum of transactions: " + sum_of_transactions +
                ", stock cycle: " + stock_cycle;
    }


    /*************************************************************************************/
    /*************************** Sub-Class - Transaction *********************************/
    /************************************ Start ******************************************/

    public static class Transaction implements Serializable
    {
        private String date;
        private int num_of_stocks;
        private int price_of_stock;
        private int transaction_sum;


        /************ Ctors - Transaction *************/

        //default
        public Transaction()
        {
            date = DateTimeFormatter.ofPattern("HH:mm:ss:SSS").format(LocalDateTime.now());
            this.price_of_stock = 0;
            this.transaction_sum = 0;
            this.num_of_stocks = 0;
        }
        //full
        public Transaction(int price_of_stock, int transaction_sum, int num_of_stocks) {
            this.num_of_stocks = num_of_stocks;
            this.transaction_sum = transaction_sum;
            this.price_of_stock = price_of_stock;
            this.date = DateTimeFormatter.ofPattern("HH:mm:ss:SSS").format(LocalDateTime.now());

        }


        /************ ToString - Transaction *************/
        @Override
        public String toString()
        {
            return /* "Transaction{" + */

                            ", number of stocks=" + num_of_stocks +
                            ", price of stock=" + price_of_stock +
                            ", transaction sum=" + transaction_sum
                            +  "date=" + date ;
        }


    }

    /*************************************************************************************/
    /*************************** Sub-Class - Transaction *********************************/
    /************************************ End ********************************************/



    /*************************************************************************************/
    /******************************* Sub-Class - Bid *************************************/
    /*********************************** Start *******************************************/

    public static class Bid implements Serializable
    {
        private int action; //0 - for selling, 1- for buying.
        private int type;  // mkt/lmt
        private String symbol;
        private int sum_of_stocks;
        private String date;
        private int limit;
        private boolean to_delete;
        private User user;
        private int killTheRest; // flag for IOC
        private int killAllIfNotFull; // flag for FOK


        /************ Ctors - Bid *************/

        //default
        public Bid()
        {
            action=-1;
            type =-1;
            symbol=null;
            sum_of_stocks=0;
            date = DateTimeFormatter.ofPattern("HH:mm:ss:SSS").format(LocalDateTime.now());
            limit=0;
            to_delete=false;
            user=null;
            killTheRest=-1;
            killAllIfNotFull=-1;
        }
        //full
        public Bid (int action,int type, String sy,int sumOfStocks,int limit,User us,int killAllIfNotFull, int killTheRest)
        {
            this.action=action;
            this.type=type;
            symbol=sy;
            sum_of_stocks=sumOfStocks;
            this.limit=limit;
            user=us;
            this.killTheRest = killTheRest;
            this.killAllIfNotFull=killAllIfNotFull;
            date = DateTimeFormatter.ofPattern("HH:mm:ss:SSS").format(LocalDateTime.now());
            to_delete=false;
        }
        //Ctor #1
        public Bid(int action, String symbol, int num_of_stocks, int limit,String name)
        {
            this.action = action;
            this.limit = limit;
            this.sum_of_stocks = num_of_stocks;
            this.symbol = symbol;
            this.date = DateTimeFormatter.ofPattern("HH:mm:ss:SSS").format(LocalDateTime.now());
            to_delete = false;
            killTheRest=-1;
            killAllIfNotFull=-1;

            //this.name = null;
        }
        //Ctor #2
        public Bid(int action, String symbol, int num_of_stocks, int limit)
        {
            this.action = action;
            this.limit = limit;
            this.sum_of_stocks = num_of_stocks;
            this.symbol = symbol;
            this.date = DateTimeFormatter.ofPattern("HH:mm:ss:SSS").format(LocalDateTime.now());
            to_delete = false;
            killTheRest=0;
            killAllIfNotFull=0;
            //this.name = null;
        }
       //Ctor #3
        public Bid(int action, String symbol, int num_of_stocks, int limit, User user) {
            this.action = action;
            this.limit = limit;
            this.sum_of_stocks = num_of_stocks;
            this.symbol = symbol;
            this.date = DateTimeFormatter.ofPattern("HH:mm:ss:SSS").format(LocalDateTime.now());
            to_delete = false;
            this.user = user;
            killTheRest=0;
            killAllIfNotFull=0;
            //this.name=name;
        }


        /*************** setters - bid *************/
        public void setkillTheRest()
        {
            killTheRest=1;
        }

        public void setKillAllIfNotFull()
        {
            killAllIfNotFull =1;
        }

        public void setSum_of_stocks(int num) {
            this.sum_of_stocks = num;
        }

        public void setAction(int action) {
            this.action = action;

        }

        public void setType(int type) {
         this.type = type;
      }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public void setDate() {
            this.date = DateTimeFormatter.ofPattern("HH:mm:ss:SSS").format(LocalDateTime.now());
        }

        public void setTo_delete()
        {
            to_delete=true;
        }

        /*************** ToString - bid *************/
        @Override
        public String toString() {
            return ", Company Symbol: '" + symbol + '\'' +
                    ", sum of stocks: " + sum_of_stocks +
                    ", date: '" + date + '\'' +
                    ", limit: " + limit ;
        }


    }

    /*************************************************************************************/
    /******************************* Sub-Class - Bid *************************************/
    /************************************ End ********************************************/


    /*************** General Funcs - Stock *************/


    public void printSellArr() {
        System.out.println("List Of Sell:");
        if (list_for_sell.size() == 0) {
            System.out.println("EMPTY\n");
            return;
        }
        int index = 1;
        for (Bid t : list_for_sell) {
            System.out.println(index + ". " + t);
            ++index;
        }
        System.out.println();
    }

    public void printBuyArr() {
        System.out.println("List Of Buy:");
        if (list_for_buy.size() == 0) {
            System.out.println("EMPTY\n");
            return;
        }
        int index = 1;
        for (Bid t : list_for_buy) {
            System.out.println(index + ". " + t);
            ++index;
        }
        System.out.println();
    }

    public void printTransactions() {
        System.out.println("List Of Transactions:");
        if (list_of_transactions.size() == 0) {
            System.out.println("EMPTY\n");
            return;
        }

        int index = 1;
        for (Transaction t : list_of_transactions) {
            System.out.println(index + ". " + t);
            ++index;
        }
        System.out.println();
    }

    // deletes the finished Bids (that already accomplish)
    public void deleteBids()
    {

        for (int j = 0; j < list_for_buy.size(); j++) {
            if (list_for_buy.get(j).to_delete == true) {
                list_for_buy.remove(j);
                j--;
            }
        }
        for (int i = 0; i < list_for_sell.size(); i++) {
            if (list_for_sell.get(i).to_delete == true) {
                list_for_sell.remove(i);
                i--;
            }
        }
    }

    public void addTransactions(Transaction tr) {
        list_of_transactions.add(tr);
    }

    /**************************** part III ****************************************/

    public void addLMT(Bid bid)
    {

        if (bid.action == 0) {
            for (Bid b : list_for_buy)
            {    /***********start of if************* part III **************************************/
                if(b.user.getName() != bid.user.getName())
                {
                    if (b.limit >= bid.limit) {
                        if (b.sum_of_stocks < bid.sum_of_stocks)
                        {
                            if(bid.killAllIfNotFull == 1)
                            {
                                //check for me if it gets here
                                System.out.println(" %%%$$$## FOK -> the bid was cancel");
                                return;
                            }
                            bid.setSum_of_stocks(bid.sum_of_stocks - b.sum_of_stocks);
                            //list_for_buy.remove(b);
                            b.to_delete = true;
                            if (stock_gate < bid.limit) {
                                stock_gate = bid.limit;
                            }
                            sum_of_transactions++;
                            stock_cycle += b.limit * b.sum_of_stocks;
                            Transaction tr = new Transaction(b.limit, b.limit * b.sum_of_stocks, b.sum_of_stocks);
                            //b = buyer , bid = seller
                            UpdateQuantitiyInItems(b,bid,b.sum_of_stocks);
                            /************************ part III **************************************/
                            updateAllStockNewPrice(bid.limit,bid,b);//update the stock price in items of the user
                            makeAction(bid,b);
                            /************************ part III **************************************/
                            addTransactions(tr);
                            /************************Updated 17th Jul **************************************/
                            b.user.removeFromExtraMoney(tr.transaction_sum);
                            bid.user.addMoney(tr.transaction_sum);
                            /************************Updated 17th Jul **************************************/


                        } else if (b.sum_of_stocks > bid.sum_of_stocks)
                        {
                            if(bid.killAllIfNotFull == 1)
                            {
                                //check for me if it gets here
                                System.out.println(" %%%$$$## FOK -> the bid was cancel");
                                return;
                            }
                            b.setSum_of_stocks(b.sum_of_stocks - bid.sum_of_stocks);
                            if (stock_gate < bid.limit) {
                                stock_gate = bid.limit;
                            }
                            sum_of_transactions++;
                            stock_cycle += b.limit * bid.sum_of_stocks;
                            Transaction tr = new Transaction(b.limit, b.limit * bid.sum_of_stocks, bid.sum_of_stocks);
                            // bid.user.setItemPrice(bid.symbol, tr.price_of_stock);
                            UpdateQuantitiyInItems(b,bid,bid.sum_of_stocks);
                            /************************ part III **************************************/
                            updateAllStockNewPrice(b.limit,bid,b);//update the stock price in items of the user
                            makeAction(bid,b);
                            /************************ part III **************************************/
                            addTransactions(tr);
                            /************************Updated 17th Jul **************************************/
                            b.user.removeFromExtraMoney(tr.transaction_sum);
                            bid.user.addMoney(tr.transaction_sum);
                            /************************Updated 17th Jul **************************************/

                            return;
                        } else {
                            /******Good for  FOK ********/
                            //list_for_buy.remove(b);
                            b.to_delete = true;
                            if (stock_gate < bid.limit) {
                                stock_gate = bid.limit;
                            }
                            sum_of_transactions++;
                            stock_cycle += b.limit * bid.sum_of_stocks;
                            Transaction tr = new Transaction(b.limit, bid.limit * bid.sum_of_stocks, bid.sum_of_stocks);
                            //bid.user.setItemPrice(bid.symbol, tr.price_of_stock);
                            UpdateQuantitiyInItems(b,bid,bid.sum_of_stocks);
                            /************************ part III **************************************/
                            updateAllStockNewPrice(b.limit,bid,b);//update the stock price in items of the user
                            makeAction(bid,b);
                            /************************ part III **************************************/
                            addTransactions(tr);
                            /************************Updated 17th Jul **************************************/
                            b.user.removeFromExtraMoney(tr.transaction_sum);
                            bid.user.addMoney(tr.transaction_sum);
                            /************************Updated 17th Jul **************************************/
                            return;
                        }
                    }
                }
                /************end of if************ part III **************************************/


            }
            if(bid.killAllIfNotFull ==1)
            {
                //check for me if it gets here
                System.out.println("%%%$$$### FOK - didnt save the rest to list");
            }
            else if(bid.killTheRest == 1)
            {
                //check for me if it gets here
                System.out.println("%%%$$$### IOC - didnt save the rest to list");

            }
            else
            {
                list_for_sell.add(bid);
            }

            Collections.sort(list_for_sell, new Comparator<Bid>() {
                @Override
                public int compare(Bid o1, Bid o2) {
                    return o1.limit - o2.limit;
                }
            });
        } else // action == 1 - buy
        {
            for (Bid b : list_for_sell)
            {
                if(b.user.getName() != bid.user.getName())
                {
                    if (bid.limit >= b.limit) {
                        if (b.sum_of_stocks < bid.sum_of_stocks)
                        {
                            if(b.limit == -1 && bid.limit != -1)
                            {
                                if(bid.killAllIfNotFull == 1)
                                {
                                    System.out.println(" %%%$$$## FOK -> the bid was cancel");
                                    return;
                                }
                                bid.setSum_of_stocks( b.sum_of_stocks);
                                b.to_delete = true;
                                stock_gate = bid.limit;
                                sum_of_transactions++;
                                stock_cycle = bid.limit*b.sum_of_stocks;
                                stock_cycle += b.limit * b.sum_of_stocks;
                                Transaction tr = new Transaction(bid.limit, bid.limit * b.sum_of_stocks, b.sum_of_stocks);
                                UpdateQuantitiyInItems(bid,b,b.sum_of_stocks);
                                addTransactions(tr);
                                /************************Updated 17th Jul **************************************/
                                bid.user.removeFromExtraMoney(tr.transaction_sum);
                                b.user.addMoney(tr.transaction_sum);
                                /************************Updated 17th Jul **************************************/

                            }
                            else
                            {
                                if(bid.killAllIfNotFull == 1)
                                {
                                    System.out.println(" %%%$$$## FOK -> the bid was cancel");
                                    return;
                                }
                                bid.setSum_of_stocks(bid.sum_of_stocks - b.sum_of_stocks);
                                // list_for_sell.remove(b);
                                b.to_delete = true;
                                if (stock_gate < b.limit) {
                                    stock_gate = b.limit;
                                }
                                sum_of_transactions++;
                                //here b ->bid
                                stock_cycle += b.limit * b.sum_of_stocks;
                                Transaction tr = new Transaction(b.limit, b.limit * b.sum_of_stocks, b.sum_of_stocks);
                                //bid.user.setItemPrice(bid.symbol, tr.price_of_stock);
                                UpdateQuantitiyInItems(bid,b,b.sum_of_stocks);
                                /************************ part III **************************************/
                                updateAllStockNewPrice(b.limit,bid,b);//update the stock price in items of the user
                                makeAction(bid,b);
                                /************************ part III **************************************/
                                addTransactions(tr);
                                /************************Updated 17th Jul **************************************/
                                bid.user.removeFromExtraMoney(tr.transaction_sum);
                                b.user.addMoney(tr.transaction_sum);
                                /************************Updated 17th Jul **************************************/

                            }



                        } else if (b.sum_of_stocks > bid.sum_of_stocks)
                        {
                            if(b.limit == -1 && bid.limit != -1 )
                            {
                                if(bid.killAllIfNotFull == 1)
                                {
                                    System.out.println(" %%%$$$## FOK -> the bid was cancel");
                                    return;
                                }

                                b.setSum_of_stocks(b.sum_of_stocks - bid.sum_of_stocks);
                                stock_gate = bid.limit;
                                sum_of_transactions++;
                                stock_cycle = bid.limit * bid.sum_of_stocks;
                                Transaction tr = new Transaction(bid.limit, bid.limit * bid.sum_of_stocks, bid.sum_of_stocks);
                                UpdateQuantitiyInItems(bid,b,bid.sum_of_stocks);
                                /************************ part III **************************************/
                                updateAllStockNewPrice(bid.limit,bid,b);//update the stock price in items of the user
                                makeAction(bid,b);
                                /************************ part III **************************************/
                                addTransactions(tr);
                                /************************Updated 17th Jul **************************************/
                                bid.user.removeFromExtraMoney(tr.transaction_sum);
                                b.user.addMoney(tr.transaction_sum);
                                /************************Updated 17th Jul **************************************/
                            }
                            else
                            {
                                if(bid.killAllIfNotFull == 1)
                                {
                                    System.out.println(" %%%$$$## FOK -> the bid was cancel");
                                    return;
                                }

                                b.setSum_of_stocks(b.sum_of_stocks - bid.sum_of_stocks);
                                if (stock_gate < b.limit) {
                                    stock_gate = b.limit;
                                }
                                sum_of_transactions++;
                                stock_cycle += b.limit * bid.sum_of_stocks;
                                Transaction tr = new Transaction(b.limit, b.limit * bid.sum_of_stocks, bid.sum_of_stocks);
                                //  bid.user.setItemPrice(bid.symbol, tr.price_of_stock);
                                UpdateQuantitiyInItems(bid,b,bid.sum_of_stocks);
                                /************************ part III **************************************/
                                updateAllStockNewPrice(b.limit,bid,b);//update the stock price in items of the user
                                makeAction(bid,b);
                                /************************ part III **************************************/
                                addTransactions(tr);
                                /************************Updated 17th Jul **************************************/
                                bid.user.removeFromExtraMoney(tr.transaction_sum);
                                b.user.addMoney(tr.transaction_sum);
                                /************************Updated 17th Jul **************************************/

                            }

                            return;
                        } else
                        {
                            //list_for_sell.remove(b);
                            if(bid.limit!= -1 && b.limit ==-1)
                            {
                                if(bid.killAllIfNotFull == 1)
                                {
                                    System.out.println(" %%%$$$## FOK -> the bid was cancel");
                                    return;
                                }

                                b.to_delete= true;
                                bid.to_delete = true;
                                stock_gate = bid.limit;
                                sum_of_transactions++;
                                stock_cycle= bid.sum_of_stocks* bid.limit;
                                Transaction tr = new Transaction(bid.limit, bid.limit * bid.sum_of_stocks, bid.sum_of_stocks);
                                UpdateQuantitiyInItems(bid,b,bid.sum_of_stocks);
                                /************************ part III **************************************/
                                updateAllStockNewPrice(bid.limit,bid,b);//update the stock price in items of the user
                                makeAction(bid,b);
                                /************************ part III **************************************/
                                addTransactions(tr);
                                /************************Updated 17th Jul **************************************/
                                bid.user.removeFromExtraMoney(tr.transaction_sum);
                                b.user.addMoney(tr.transaction_sum);
                                /************************Updated 17th Jul **************************************/
                            }
                            else
                            {
                                /**** Good for FOK *********/
                                b.to_delete = true;
                                if (stock_gate < b.limit)
                                {
                                    stock_gate = b.limit;
                                }
                                sum_of_transactions++;
                                stock_cycle += bid.limit * bid.sum_of_stocks;
                                Transaction tr = new Transaction(b.limit, b.limit * bid.sum_of_stocks, bid.sum_of_stocks);
                                //bid.user.setItemPrice(bid.symbol, tr.price_of_stock);
                                UpdateQuantitiyInItems(bid,b,bid.sum_of_stocks);
                                /************************ part III **************************************/
                                updateAllStockNewPrice(bid.limit,bid,b);//update the stock price in items of the user
                                makeAction(bid,b);
                                /************************ part III **************************************/
                                addTransactions(tr);
                                /************************Updated 17th Jul **************************************/
                                bid.user.removeFromExtraMoney(tr.transaction_sum);
                                b.user.addMoney(tr.transaction_sum);
                                /************************Updated 17th Jul **************************************/
                            }
                            return;
                        }


                    }
                }
            }

            if(bid.killAllIfNotFull ==1)
            {
                System.out.println("%%%$$$### FOK - didnt save the rest to list");
            }
            if(bid.killTheRest == 1)
            {
                System.out.println("%%%$$$### IOC - didnt save the rest to list");

            }else
            {
                list_for_buy.add(bid);
            }

            Collections.sort(list_for_buy, new Comparator<Bid>() {
                @Override
                public int compare(Bid o1, Bid o2) {
                    return o2.limit - o1.limit;
                }
            });
        }

    }

    public void addMKT(Bid bid) {
        if (bid.action == 0) {
            for (Bid b : list_for_buy)
            {// bid =seller, b = buyer
                if(b.user.getName() != bid.user.getName())
                {
                    if (bid.sum_of_stocks > b.sum_of_stocks)
                    {
                        if(b.limit == bid.limit && b.limit == -1)
                        {
                            sum_of_transactions++;
                            stock_cycle = b.sum_of_stocks*stock_gate;
                            b.to_delete = true;
                            bid.sum_of_stocks = bid.sum_of_stocks- b.sum_of_stocks;
                            Transaction tr = new Transaction(stock_gate, stock_gate * b.sum_of_stocks, b.sum_of_stocks);
                            UpdateQuantitiyInItems(b,bid,b.sum_of_stocks);
                            /************************ part III **************************************/
                            updateAllStockNewPrice(stock_gate,bid,b);//update the stock price in items of the user
                            makeAction(bid,b);
                            /************************ part III **************************************/
                            addTransactions(tr);
                            /************************Updated 17th Jul **************************************/
                            b.user.removeFromExtraMoney(tr.transaction_sum);
                            bid.user.addMoney(tr.transaction_sum);
                            /************************Updated 17th Jul **************************************/
                        }
                        else
                        {
                            bid.setSum_of_stocks(bid.sum_of_stocks - b.sum_of_stocks);
                            bid.limit = b.limit;
                            b.to_delete = true;
                            sum_of_transactions++;
                            stock_gate = b.limit;
                            stock_cycle += b.sum_of_stocks * b.limit;
                            Transaction tr = new Transaction(b.limit, b.sum_of_stocks * b.limit, b.sum_of_stocks);
                            UpdateQuantitiyInItems(b,bid,b.sum_of_stocks);
                            /************************ part III **************************************/
                            updateAllStockNewPrice(b.limit,bid,b);//update the stock price in items of the user
                            makeAction(bid,b);
                            /************************ part III **************************************/
                            addTransactions(tr);
                            /************************Updated 17th Jul **************************************/
                            b.user.removeFromExtraMoney(tr.transaction_sum);
                            bid.user.addMoney(tr.transaction_sum);
                            /************************Updated 17th Jul **************************************/
                        }


                    } else if (bid.sum_of_stocks < b.sum_of_stocks)
                    {
                        if(b.limit == bid.limit && b.limit == -1)
                        {
                            sum_of_transactions++;
                            stock_cycle = bid.sum_of_stocks*stock_gate;
                            bid.to_delete = true;
                            b.sum_of_stocks = b.sum_of_stocks- bid.sum_of_stocks;
                            Transaction tr = new Transaction(stock_gate, stock_gate * bid.sum_of_stocks, bid.sum_of_stocks);
                            UpdateQuantitiyInItems(bid,b,bid.sum_of_stocks);
                            /************************ part III **************************************/
                            updateAllStockNewPrice(stock_gate,bid,b);//update the stock price in items of the user
                            makeAction(bid,b);
                            /************************ part III **************************************/
                            addTransactions(tr);
                            /************************Updated 17th Jul **************************************/
                            b.user.removeFromExtraMoney(tr.transaction_sum);
                            bid.user.addMoney(tr.transaction_sum);
                            /************************Updated 17th Jul **************************************/

                        }
                        else
                        {
                            b.setSum_of_stocks(b.sum_of_stocks - bid.sum_of_stocks);
                            sum_of_transactions++;
                            stock_cycle += bid.sum_of_stocks * b.limit;
                            stock_gate = b.limit;
                            Transaction tr = new Transaction(b.limit, bid.sum_of_stocks * b.limit, bid.sum_of_stocks);
                            UpdateQuantitiyInItems(b,bid,bid.sum_of_stocks);
                            /************************ part III **************************************/
                            updateAllStockNewPrice(b.limit,bid,b);//update the stock price in items of the user
                            makeAction(bid,b);
                            /************************ part III **************************************/
                            addTransactions(tr);
                            /************************Updated 17th Jul **************************************/
                            b.user.removeFromExtraMoney(tr.transaction_sum);
                            bid.user.addMoney(tr.transaction_sum);
                            /************************Updated 17th Jul **************************************/
                        }

                        return;

                    } else {

                        if(b.limit == bid.limit && b.limit == -1)
                        {
                            sum_of_transactions++;
                            stock_cycle = bid.sum_of_stocks*stock_gate;
                            bid.to_delete = true;
                            b.to_delete = true;
                            b.sum_of_stocks = b.sum_of_stocks- bid.sum_of_stocks;
                            Transaction tr = new Transaction(stock_gate, stock_gate * bid.sum_of_stocks, bid.sum_of_stocks);
                            UpdateQuantitiyInItems(b,bid,bid.sum_of_stocks);
                            /************************ part III **************************************/
                            updateAllStockNewPrice(stock_gate,bid,b);//update the stock price in items of the user
                            makeAction(bid,b);
                            /************************ part III **************************************/
                            addTransactions(tr);
                            /************************Updated 17th Jul **************************************/
                            b.user.removeFromExtraMoney(tr.transaction_sum);
                            bid.user.addMoney(tr.transaction_sum);
                            /************************Updated 17th Jul **************************************/
                        }
                        else
                        {
                            sum_of_transactions++;
                            stock_cycle += b.sum_of_stocks * b.limit;
                            stock_gate = b.limit;
                            b.to_delete = true;
                            Transaction tr = new Transaction(b.limit, b.limit * b.sum_of_stocks, b.sum_of_stocks);
                            /************************ part III **************************************/
                            updateAllStockNewPrice(b.limit,bid,b);//update the stock price in items of the user
                            makeAction(bid,b);
                            /************************ part III **************************************/
                            addTransactions(tr); /// to ckeck again if this line is needed
                            /************************Updated 17th Jul **************************************/
                            b.user.removeFromExtraMoney(tr.transaction_sum);
                            bid.user.addMoney(tr.transaction_sum);
                            /************************Updated 17th Jul **************************************/

                        }

                        return;
                    }

                }

            }
            if(bid.limit == -1) bid.setLimit(stock_gate);
            list_for_sell.add(bid);
            Collections.sort(list_for_sell, new Comparator<Bid>() {
                @Override
                public int compare(Bid o1, Bid o2) {
                    return o1.limit - o2.limit;
                }
            });
        } else {
            for (Bid b : list_for_sell)
            {
                if(b.user.getName() != bid.user.getName())
                {
                    if (bid.sum_of_stocks > b.sum_of_stocks)
                    {
                        if(b.limit == bid.limit && b.limit == -1)
                        {
                            sum_of_transactions++;
                            stock_cycle = bid.sum_of_stocks*stock_gate;
                            b.to_delete = true;
                            bid.sum_of_stocks = bid.sum_of_stocks- b.sum_of_stocks;
                            Transaction tr = new Transaction(stock_gate, stock_gate * b.sum_of_stocks, b.sum_of_stocks);
                            UpdateQuantitiyInItems(bid,b,b.sum_of_stocks);
                            /************************ part III **************************************/
                            updateAllStockNewPrice(stock_gate,bid,b);//update the stock price in items of the user
                            makeAction(bid,b);
                            /************************ part III **************************************/
                            addTransactions(tr);
                            /************************Updated 17th Jul **************************************/
                            bid.user.removeFromExtraMoney(tr.transaction_sum);
                            b.user.addMoney(tr.transaction_sum);
                            /************************Updated 17th Jul **************************************/
                        }
                        else
                        {
                            bid.setSum_of_stocks(bid.sum_of_stocks - b.sum_of_stocks);
                            bid.limit = b.limit;
                            sum_of_transactions++;
                            stock_cycle += b.sum_of_stocks * b.limit;
                            stock_gate = b.limit;
                            Transaction tr = new Transaction(b.limit, b.limit * b.sum_of_stocks, b.sum_of_stocks);
                            UpdateQuantitiyInItems(bid,b,b.sum_of_stocks);
                            /************************ part III **************************************/
                            updateAllStockNewPrice(b.limit,bid,b);//update the stock price in items of the user
                            makeAction(bid,b);
                            /************************ part III **************************************/
                            addTransactions(tr);
                            /************************Updated 17th Jul **************************************/
                            bid.user.removeFromExtraMoney(tr.transaction_sum);
                            b.user.addMoney(tr.transaction_sum);
                            /************************Updated 17th Jul **************************************/
                            b.to_delete = true;
                        }



                    } else if (bid.sum_of_stocks < b.sum_of_stocks)
                    {
                        if(b.limit == bid.limit && b.limit == -1)
                        {
                            sum_of_transactions++;
                            stock_cycle = bid.sum_of_stocks*stock_gate;
                            bid.to_delete = true;
                            b.sum_of_stocks = b.sum_of_stocks- bid.sum_of_stocks;
                            Transaction tr = new Transaction(stock_gate, stock_gate * bid.sum_of_stocks, bid.sum_of_stocks);
                            UpdateQuantitiyInItems(bid,b,bid.sum_of_stocks);
                            /************************ part III **************************************/
                            updateAllStockNewPrice(stock_gate,bid,b);//update the stock price in items of the user
                            makeAction(bid,b);
                            /************************ part III **************************************/
                            addTransactions(tr);
                            /************************Updated 17th Jul **************************************/
                            bid.user.removeFromExtraMoney(tr.transaction_sum);
                            b.user.addMoney(tr.transaction_sum);
                            /************************Updated 17th Jul **************************************/
                        }
                        else
                        {
                            sum_of_transactions++;
                            stock_cycle += bid.sum_of_stocks * b.limit;
                            stock_gate = b.limit;
                            b.sum_of_stocks = b.sum_of_stocks - bid.sum_of_stocks;
                            Transaction tr = new Transaction(b.limit, bid.sum_of_stocks * b.limit, bid.sum_of_stocks);
                            UpdateQuantitiyInItems(bid,b,bid.sum_of_stocks);
                            /************************ part III **************************************/
                            updateAllStockNewPrice(b.limit,bid,b);//update the stock price in items of the user
                            makeAction(bid,b);
                            /************************ part III **************************************/
                            addTransactions(tr);
                            /************************Updated 17th Jul **************************************/
                            bid.user.removeFromExtraMoney(tr.transaction_sum);
                            b.user.addMoney(tr.transaction_sum);
                            /************************Updated 17th Jul **************************************/
                        }
                        return;

                    } else //bid == b
                    {
                        if(b.limit == bid.limit && b.limit == -1)
                        {
                            sum_of_transactions++;
                            stock_cycle = b.sum_of_stocks*stock_gate;
                            b.to_delete = true;
                            bid.to_delete=true;
                            Transaction tr = new Transaction(stock_gate, stock_gate * b.sum_of_stocks, b.sum_of_stocks);
                            UpdateQuantitiyInItems(bid,b,b.sum_of_stocks);
                            /************************ part III **************************************/
                            updateAllStockNewPrice(stock_gate,bid,b);//update the stock price in items of the user
                            makeAction(bid,b);
                            /************************ part III **************************************/
                            addTransactions(tr);
                            /************************Updated 17th Jul **************************************/
                            bid.user.removeFromExtraMoney(tr.transaction_sum);
                            b.user.addMoney(tr.transaction_sum);
                            /************************Updated 17th Jul **************************************/

                        }
                        else
                        {
                            sum_of_transactions++;
                            stock_cycle += b.sum_of_stocks * b.limit;
                            stock_gate = b.limit;
                            b.to_delete = true;
                            Transaction tr = new Transaction(b.limit, b.limit * b.sum_of_stocks, b.sum_of_stocks);
                            UpdateQuantitiyInItems(bid,b,b.sum_of_stocks);
                            /************************ part III **************************************/
                            updateAllStockNewPrice(b.limit,bid,b);//update the stock price in items of the user
                            makeAction(bid,b);
                            /************************ part III **************************************/
                            addTransactions(tr);
                            /************************Updated 17th Jul **************************************/
                            bid.user.removeFromExtraMoney(tr.transaction_sum);
                            b.user.addMoney(tr.transaction_sum);
                            /************************Updated 17th Jul **************************************/
                        }

                        return;
                    }
                }


            }
            if(bid.limit == -1) bid.setLimit(stock_gate);
            list_for_buy.add(bid);
            Collections.sort(list_for_buy, new Comparator<Bid>() {
                @Override
                public int compare(Bid o1, Bid o2) {
                    return o2.limit - o1.limit;
                }
            });
        }
    }

    public void addBid(Bid bid) {
        switch (bid.limit) {
            case (-1):
                addMKT(bid);
                break;
            default:
                addLMT(bid);
        }

    }

    public void UpdateQuantitiyInItems(Bid buyer, Bid seller, int stocksToForword)
    {

        if(!buyer.user.getItemsList().containsKey(buyer.symbol))
        {
            buyer.user.getItemsList().put(buyer.symbol,new Item(this,0,this.getStockPrice() ));
        }

        int originalQuantOfBuyer = buyer.user.getItemsList().get(buyer.symbol).getQuantitiy();

        buyer.user.getItemsList().get(buyer.symbol).setQuantity(originalQuantOfBuyer+stocksToForword);

        int originalQuantitiyOfSeller = seller.user.getItemsList().get(seller.symbol).getQuantity();

        seller.user.getItemsList().get(seller.symbol).setQuantity(originalQuantitiyOfSeller - stocksToForword);

        if(seller.user.getItemsList().get(seller.symbol).getQuantity() ==0)
        {
            seller.user.getItemsList().remove(seller.symbol);
        }
    }

    //updated the stock price in b.user -> item = stock & bid.user -> item = stock
    public void updateAllStockNewPrice(int np, Bid bid, Bid b)
    {
        bid.user.getItemsList().get(bid.symbol).setItemPrice(np);
        bid.user.getItemsList().get(bid.symbol).getStock().stock_gate = np;

        if(b.user.getList_of_items().get(b.symbol) != null)
        {
            b.user.getItemsList().get(bid.symbol).setItemPrice(np);
            b.user.getItemsList().get(bid.symbol).getStock().stock_gate = np;
        }

    }

    //updates the action list of b.user & bid.user
    public void makeAction(Bid bid, Bid b)
    {
        // Add an action to action list of bid (user #1 -seller)
        Action ac= new Action();
        ac.setDate();
        ac.setKindOfAction(bid.action);
        ac.setSum(-(b.sum_of_stocks*b.limit));
        ac.setSymbol(bid.symbol);
        ac.setWorthBeforAction(bid.user.getWorth()+bid.user.getExtraMoney());
        bid.user.updateWorth();
        ac.setWorthAfterAction(bid.user.getWorth()+bid.user.getExtraMoney());
        bid.user.addToActionList(ac);

        // Add an action to action list of b (user #2)
        Action ax= new Action();
        ax.setDate();
        ax.setKindOfAction(b.action);
        ax.setSum((b.sum_of_stocks*b.limit));
        ax.setSymbol(b.symbol);
        ax.setWorthBeforAction(b.user.getWorth()+b.user.getExtraMoney());
        b.user.updateWorth();
        ax.setWorthAfterAction(b.user.getWorth()+b.user.getExtraMoney());
        b.user.addToActionList(ax);

    }

    /**************************** part III ****************************************/

    //from part I - write the boorsa to binary file.
    public void writeToBinaryFile(String fileName) throws IOException {
        ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(fileName));
        outFile.writeObject(this);
        outFile.flush();
    }
}

/*************************************************************************************/
/****************************** Sub-Class - Stock ************************************/
/************************************ End ********************************************/






