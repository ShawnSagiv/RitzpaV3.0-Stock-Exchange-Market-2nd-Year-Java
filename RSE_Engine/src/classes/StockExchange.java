package classes;
import jaxb.SchemaBasedJAXBMain;
import jaxb.jaxbWebClasses.*;

import java.io.*;
import java.util.*;

public class StockExchange  implements Serializable   {
    public static Map<String, Stock> boorsa;
    public static Map<String,User> users_list;
    public static List<User> activeUsers;

    /************ Ctors - StockExchange *************/

    //default
    public StockExchange() {
        boorsa = new HashMap<>();
        users_list = new HashMap<>();
        activeUsers = new ArrayList<>();

    }

    //we dont need full Ctor because its the same

    /************ Getters - StockExchange *************/

    public Map<String, Stock> getBoorsa()
    {
        return boorsa;
    }

    public Stock getStock(String str)
    {
        return boorsa.get(str.toUpperCase());
    }

    public int getSizeOfBoorsa()
    {
        return boorsa.size();
    }

    public Map<String,User> getUsersMap()
    {
        return users_list;
    }

    public User getUser(String name)
    {
        return users_list.get(name);
    }

    public int getSizeOfUserMap(){return users_list.size();}

    public List<User> getActiveUsersList(){return activeUsers;}

    public int getActivesUsersListSize(){return activeUsers.size();}


    /************ Setters - StockExchange *************/
    public void setBoorsa(Map<String, Stock> boorsa) {
        if(this.getSizeOfBoorsa()!=0) this.boorsa.clear();
        for(Stock stock : boorsa.values()) {
            this.addStock(stock);
        }
    }



/*************************************************** Part III **************************************************************************/

    //active Users List:

    public void removeUserFromActiveUsersList(User user)
{
    activeUsers.remove(user);
}

    public void addUserToActiveUsersList(User user)
    {
        activeUsers.add(user);
    }

    public void showActiveUsersList()
{
    System.out.println(activeUsers);
}

    // Users List:

    public void addUser(String name, int type)
    {
        User user = new User(name, type);
        users_list.put(name,user);
        addUserToActiveUsersList(user);
    }


    public void print_list_of_users()
    {
        System.out.println("LIST OF USERS: ");
        System.out.println(users_list);
    }


    //Boorsa:

    public void addStock(Stock x)
    {
        boorsa.put(x.getSymbol(),x);
    }


    //updates the stock gate in every user's item
    public void updateItemsPriceGate(Stock st ) {
    int newPrice = st.getStock_gate();


    for (String str : users_list.keySet()) {
        if (users_list.get(str).getItemsList().containsKey(st.getSymbol()))
        {
            users_list.get(str).getItem(st.getSymbol()).setItemPrice(newPrice);

        }
    }
}

    public void insertXMLFromFileChooser(InputStream inputFile, String usernameFromSession) throws Exception {
        RizpaStockExchangeDescriptor infoMarketFromXML = SchemaBasedJAXBMain.XML_Import(inputFile);

            try {
                this.putInfoInStockEx(infoMarketFromXML, usernameFromSession);
            }catch (Exception exception) {
                throw exception;
            }
    }

    //after loading XML with details about specific user this function inserts orr add the info into the user.
    public boolean putInfoInStockEx(RizpaStockExchangeDescriptor xml, String userName) throws Exception
    {
        RseStocks xml_stocks = xml.getRseStocks();

        /* exception : if there are duplicated stocks */

        for(int i=0;i<xml_stocks.getRseStock().size();i++)
        {
            for(int j=i+1;j<xml_stocks.getRseStock().size();j++) {
                String s1 = xml_stocks.getRseStock().get(i).getRseSymbol();
                String s2 = xml_stocks.getRseStock().get(j).getRseSymbol();
                String s3 = xml_stocks.getRseStock().get(i).getRseCompanyName();
                String s4 =xml_stocks.getRseStock().get(j).getRseCompanyName();

                if(s1.equals(s2)) throw new Exception("ERROR: xml file wrong - Duplicate Stocks symbols");
                else if(s3.equals(s4)) throw new Exception("ERROR: xml file wrong - Duplicate Stocks names");
            }
        }
        /* exception : if there are duplicated stocks */

        Stock stock = null;

        //Puts all new stocks to stock list(boorsa) and ignores stocks that already in list.
        for(RseStock rseStock : xml_stocks.getRseStock())
        {
            if (rseStock==null) {} //handle Problem if iterator
            if(!this.getBoorsa().containsKey(rseStock.getRseSymbol())) {     //**handle error if already market contains the stock.
                stock = new Stock(rseStock.getRseSymbol(),rseStock.getRseCompanyName(),rseStock.getRsePrice());
                boorsa.put(stock.getSymbol(),stock);
            }
        }

        //Checks if the stocl of item is exists, if not then:
        //If item already exists, then adds to old item the amount of the stocks in the new item.
        //Puts new items to user's holding list if not created already
        RseHoldings holdings = xml.getRseHoldings();

        int size =  holdings.getRseItem().size();
        /* exception : if there are duplicated Items */
        for(int k=0;k<size;k++)
        {
            for(int m=k+1;m<size;m++)
            { RseItem itK = holdings.getRseItem().get(k);
                RseItem itM =holdings.getRseItem().get(m);
                if(itK.getSymbol().equals(itM.getSymbol())) {
                        throw new Exception("ERROR: xml file wrong - Duplicate Items symbols");
                }
            }
        }

        Item item = null;
        for(RseItem it : holdings.getRseItem()) {
            try {
                if(this.getBoorsa().containsKey(it.getSymbol().toUpperCase(Locale.ROOT))) {
                    item = new Item(boorsa.get(it.getSymbol()), it.getQuantity(), boorsa.get(it.getSymbol()).getStock_gate());
                }
                else { break;} //handle error if item of a specific stock is in xml, but stock doesn't exist.

                if(users_list.get(userName).getItemsList().containsKey(it.getSymbol()))
                {
                    //add to the quantity
                    int quantitiy = users_list.get(userName).getItemsList().get(it.getSymbol()).getQuantitiy();
                    quantitiy = quantitiy + it.getQuantity();
                    users_list.get(userName).getItemsList().get(it.getSymbol()).setQuantity(quantitiy);
                }
                else
                {
                    users_list.get(userName).getItemsList().put(item.getSymbol(), item);

                }
            }catch (NullPointerException e){
                System.out.println("NULL");
            }
        }

        try{
            users_list.get(userName).updateWorth();
        }catch (NullPointerException nullPointerException) {
            System.out.println("Error: user session was not found.");
        }
        return true;
    }

    /*************************************************** Part III **************************************************************************/

    /************ General Funcs - StockExchange *************/

    public void printAllTransactions(Stock st)
    {
        List<Stock.Transaction> list = null;
             if(st.getTransactionsList().size()==0)
             {

                 System.out.println("NO Transactions occurred");
                 System.out.println();
             }
             else
             {
                 list= st.getTransactionsList();
                 try {
                     for (Stock.Transaction tr : list)
                     {
                         System.out.println(tr);
                     }
                 }
                 catch(NullPointerException e)
                 {
                     return;
                 }
             }
        }
    
    public void addBid(String name, Stock.Bid bid)
    {
        try
        {
            boorsa.get(name).addBid(bid);
            //add add bid for user history list
        }catch (NullPointerException e)
        {
            System.out.println("Stock \"" + name + "\" doesn't exist, Please1 try again.");
            return;
        }




       //Delete non neasecery bids.
       boorsa.get(name).deleteBids();
    }

    public void addTransactions(Stock.Transaction tr, String name_of_stock) {
        boorsa.get(name_of_stock).addTransactions(tr);
    }

    /************ Old or unnecessary funcs *************/

    public static void boorsaPresentation(StockExchange market)
    {
        Map<String, Stock> x = market.getBoorsa();
        System.out.println("Market Presentation:\n");

        for (String name : market.getBoorsa().keySet())
        {
            Stock y =  market.getStock(name);

            System.out.println("Stock name: " + name);
            y.printSellArr();
            y.printBuyArr();
            y.printTransactions();
            System.out.println("stock cycle: " + y.getStock_cycle());
            System.out.println("sum of transactions: " + y.getSum_of_transactions());
            System.out.println("stock gate: " + y.getStock_gate());
            System.out.println("-----------------------------");

        }
    }

    public boolean writeToBinaryFile(String fileName) throws IOException {
        if(fileName.length()<=4){
            System.out.println("No file was given.");
            return false;
        }
        String checksIfBin = fileName.substring(fileName.length()-4,fileName.length());
        if (!checksIfBin.equals(".bin")) // if the file is xml file
        {
            System.out.println("Requested file for saving must ends with \'.bin\'.\nPlease enter file name with ending \'.bin\'.");
            return false;
        }
        ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream(fileName));
        outFile.writeObject(boorsa);
        outFile.flush();
        return true;
    }

    public boolean readFromBinaryFile(String fileName) throws IOException, ClassNotFoundException {
        File checkIfFileExist = new File(fileName);
        if (!checkIfFileExist.exists()) {
            System.out.println("The file \"" + fileName + "\" doesn't exist.\nPlease try again");
            return false;
        }
        else {
            if(fileName.length()<=4) {
                System.out.println("No file was given.");
                return false;
            }
            String s = fileName.substring(fileName.length()-4,fileName.length());
            if (!s.equals(".bin")) // if the file is xml file
            {
                System.out.println("The file is not Binary file.\nLoad Failed.\nPlease enter file with ending - \'.bin\'.");
                return false;
            }
        }
        ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(fileName));
        setBoorsa((Map<String ,Stock>) inFile.readObject());
        return true;
    }

    public boolean isCmpNameExist(StockExchange market ,String name)
    {
        for(String st : market.getBoorsa().keySet())
        {
            if(name == market.getBoorsa().get(st).getCompanyName())
            {
                return true;
            }
        }
        return false;

    }



}

