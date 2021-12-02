package classes;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable {
    private String name;
    private Map<String, Item> listOfItems;
    private List<Action> list_of_actions;
    private int worth;
    private int type; // 0 - Merchant , 1 - Admin
    private  int extraMoney;// we can make the hole worth of the user as 2 separated things,
    // 1. the money from the stocks : stocks * price
    // 2. "Extra Money" the money the user added.
    // We can add a function for "Buy Stocks With the - Extra Money"
    // and then the function will take the extra money and with that it makes a bid.



    /************ Ctors - User *************/

    //default:
    public User()
    {
        this.name = null;
        this.listOfItems = new HashMap<>();
        this.list_of_actions = new ArrayList<>();
        worth =0;
        type=0; // default is merchant
        extraMoney=0;
    }
    //full:
    public User(String name, int ty) {
        this.name = name;
        listOfItems = new HashMap<>();
        this.list_of_actions = new ArrayList<>();
        worth =0;
        type =ty;
        extraMoney=0;
    }
    //mid:
    public User(String name) {
        this.name = name;
        listOfItems = new HashMap<>();
        this.list_of_actions = new ArrayList<>();
        worth =0;
        type =0;// default is merchant
        extraMoney=0;
    }


    /************ Getters - User *************/

    public String getName() {
        return name;
    }

    public int getWorth()
    {
        return worth;
    }

    public int getType()
    {
        return type;
    }

    public Map<String, Item> getList_of_items() {
        return listOfItems;
    }

    public Map<String, Item> getItemsList()
    {
        return listOfItems;
    }

    public List<Action> getList_of_actions()
    {
        return this.list_of_actions;
    }

    public Item getItem(String itemName)
    {
        return listOfItems.get(itemName);
    }

    /************ Setters - User *************/

    public void setUserName(String name)
    {
        this.name=name;
    }

    public void setType(int ty)
    {
        type=ty;
    }

    void addItem(Item newItem) {
        listOfItems.put(newItem.getSymbol(), newItem);
    }

    public void setItemPrice(String item,int price)
    {
        listOfItems.get(item).setItemPrice(price);


    }

    /************ Adders - User *************/

    public void addToActionList(Action ac )
    {
        list_of_actions.add(ac);
    }

    /************ ToStrinng - User *************/

    @Override
    public String toString() {
        return
                "Name:" + name.toUpperCase(Locale.ROOT)  + "\n"+"\t"+
                        " Holdings: " + listOfItems;
    }


    public void printUserInfo() {
        System.out.println("Name: " + name + ".\n"
                + "All holding Stocks: " + listOfItems + ".\n"
                + "Total holding Stocks: " + this.calculateTotalHoldings());
    }


    /************ General Funcs - User *************/

    //updates the worth of every user. go to
    public void updateWorth()
    {
        this.worth =0;
        for(Item it : getList_of_items().values())
        {
            this.worth += (it.getQuantity()*it.getStock().getStock_gate());

        }
    }
    //trade instruction for part III
    public void tradeInstraction(StockExchange market)
    {

        Scanner scanner = new Scanner(System.in);
        int marketaction=0;
        Stock.Bid bid = new Stock.Bid();

        try{
            System.out.println("please enter type of market action: 1 - LMT / 2-MKT / 3-FOK / 4-IOC");
            marketaction = scanner.nextInt();
            if( marketaction == 4)
            {
                marketaction=1;
                bid.setkillTheRest();

            }
            if(marketaction == 3)
            {
                marketaction=1;
                bid.setKillAllIfNotFull();
            }
            if(marketaction!=1 && marketaction !=2)
            {
                System.out.println("Wrong input, choose 1 to LMT or 2 to MKT or 3 to FOK or 4 to IOC.\nPlease try again.");
                return;
            }
            int limit=0;
            if(marketaction==1)
            {
                System.out.println("Please enter: 1. 0=sell /1-buy \n 2.stock symbol \n 3. sum of stocks \n 4. limit\n" );

            }
            else
            {
                System.out.println("Please enter: 1. 0=sell /1-buy \n 2.stock symbol \n 3. sum of stocks \n" );


            }
            int buy_or_sell = scanner.nextInt();
            if(buy_or_sell!=0 && buy_or_sell !=1)
            {
                System.out.println("Wrong input, choose 0 to sell or 1 to buy.\nPlease try again.");
                return;
            }
            String symbol = scanner.next();
            symbol=symbol.toUpperCase();
            int sum_of_stocks = scanner.nextInt();
            if(sum_of_stocks<0)
            {
                System.out.println("Wrong input, can't add number of stock that lower than 0.\nPlease try again.");
                return;
            }
            if(marketaction==1)
            {
                limit = scanner.nextInt();
                if(limit<0)
                {
                    System.out.println("Wrong input, can't add number of limit that lower than 0.\nPlease try again.");
                    return;
                }
            }
            else
            {
                limit=-1;
            }

            bid.setUser(market.getUser(name));
            bid.setLimit(limit);
            bid.setDate();
            bid.setAction(buy_or_sell);
            bid.setSymbol(symbol);
            bid.setSum_of_stocks(sum_of_stocks);

            // to do: check if the stock name existed and if the num of stocks above 0
            // check if there is file in the system - xml
            market.addBid(symbol,bid);
        }catch (InputMismatchException e)
        {
            System.out.println("Wrong input please try again adding new bid.");
            return;
        }




    }
    // this func make a new stock and added it to the market. moreover it will forward all the shares to the user who issue it.
    public void companyIssue(StockExchange market ,String cmpName, String symbol, int sumOfStocks, int worth) throws Exception {

        if (sumOfStocks == 0) throw new Exception("Sum Of Stocks Cant Be 0");
        if (sumOfStocks < 0) throw new Exception("Sum Of Stocks Cant Be Negative");
        if (worth == 0) throw new Exception("Worth Cant Be 0");
        if (worth < 0) throw new Exception("Worth Cant Be Negative");
        if(market.isCmpNameExist(market,cmpName)) throw new Exception("Company Name Already Exist");
        //check if the stock doesnt exist:
        if(market.getBoorsa().containsKey(symbol)) throw new Exception("this stock already exist");
        else{
            //build the stock:
            Stock ns = new Stock();
            ns.setCompany_name(cmpName);
            ns.setSymbol(symbol);
            int stockGate =  worth/sumOfStocks;
            ns.setStock_price(stockGate);
            ns.setStockGate(stockGate);
            //add the stock to the market
            market.getBoorsa().put(ns.getSymbol(),ns);
            //add the stock to the users items list:
            //make the item:
            Item it = new Item(ns,sumOfStocks,stockGate);
            this.listOfItems.put(symbol,it);
        }
    }

    /****************************** Updated 16 Jul  **********************************/
    //Add the "sum" amount of money to your account
    public void  addMoney(int sum)
    {
        // I added the "Time Stamp" as an action.
        Action ac = new Action();
        ac.setSymbol("Add Money To Account");
        ac.setDate();
        ac.setSum(-1);
        ac.setWorthBeforAction(this.worth + this.extraMoney);
        this.extraMoney += sum;
        ac.setWorthAfterAction(this.worth + this.extraMoney);
        this.addToActionList(ac);

    }
    /****************************** Updated 16 Jul  **********************************/
    //shows the action history of this user
    public void showActionHistory()
    {
        System.out.println(this.getList_of_actions());
    }

    public int calculateTotalHoldings()
    {
        int sum = 0;
        int size = listOfItems.size();

        for (int i = 0; i < size; i++) {
            Set<String> x = listOfItems.keySet();

            for (String t : x) {
                sum += (listOfItems.get(t).getStock().getStockPrice() * listOfItems.get(t).getQuantity());
            }
        }

        return sum;
    }

    //Full Worth Of User = Worth + Extra money.
    public int getFullWorthOfUser()
    {
        return worth+extraMoney;
    }

    //extraMoney = extraMoney - sum
    public void removeFromExtraMoney(int sum)
    {
        this.extraMoney = this.extraMoney-sum;
    }


    public int getExtraMoney()
    {
        return extraMoney;
    }


}