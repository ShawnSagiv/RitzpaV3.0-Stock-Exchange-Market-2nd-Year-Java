import classes.*;
import exception.XMLLoadingException;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import jaxb.SchemaBasedJAXBMain;
import classes.StockExchange;
import exception.XMLLoadingException;
import jaxb.SchemaBasedJAXBMain;
import jaxb.jaxbWebClasses.RizpaStockExchangeDescriptor;

import java.io.IOException;
import java.util.*;

import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
        String shawn = "SHAWN";
        String yoav = "YOAV";

        StockExchange market = new StockExchange();
      //check for adding user and aadding xml to user.
        market.addUser("shawn",1);
        market.addUser("yoav",1);
        Main.insertXmlForSingleUser(market,shawn);
        Main.insertXmlForSingleUser(market,shawn);
        Main.insertXmlForSingleUser(market,yoav);

        Main.printForCheck(market);

        /*
        System.out.println(market.getUsersMap());
        System.out.println("\n\n");
        Main.insertXmlForSingleUser(market,"shawn");
        Main.insertXmlForSingleUser(market,"yoav");
        System.out.println(market.getBoorsa());

        System.out.println("shawn items : \n");
        System.out.println("\n\n");
        System.out.println(market.getUsersMap().get("SHAWN").getItemsList());
        System.out.println("\n\n");

        System.out.println("yoav items : \n");
        System.out.println("\n\n");
        System.out.println(market.getUsersMap().get("YOAV").getItemsList());
        System.out.println("\n\n");


        market.addUser("yoav");
        market.addUser("menash");
        System.out.println(market.getUsersMap());
        Main.insertXmlForSingleUser(market,"shawn");
        System.out.println(market.getBoorsa());
       System.out.println(market.getUsersMap().get("shawn").getItemsList());
        System.out.println("\n");
        Main.insertXmlForSingleUser(market,"yoav");

        System.out.println(market.getBoorsa());
        Main.insertXmlForSingleUser(market,"menash");
        System.out.println(market.getBoorsa());
        System.out.println(market.getUsersMap());
        System.out.println(market.getUsersMap().get("shawn").getItemsList());

*/

       /*
        System.out.println(market.getUsersMap());
        System.out.println("\n ");
        System.out.println("shawn items list: \n");
        System.out.println(market.getUsersMap().get("SHAWN").getItemsList());
        System.out.println("\n ");
        System.out.println("yoav items list: \n");
        System.out.println(market.getUsersMap().get("YOAV").getItemsList());
        System.out.println("\n ");
        //check for UpdateWorth():

        User shawn = market.getUser("SHAWN");
        User yoav = market.getUser("YOAV");

       // shawn.updateWorth();
        //yoav.updateWorth();

        System.out.println("shawn's worth: " + shawn.getWorth() + "\n");
        System.out.println("yoav's worth: " + yoav.getWorth() + "\n");
        */
/*
        System.out.println(market.getBoorsa());
        printForCheck(market);
        market.getUsersMap().get(shawn).tradeInstraction(market);
        printForCheck(market);
        market.getUsersMap().get(yoav).tradeInstraction(market);
        printForCheck(market);

        //System.out.println("zoom list of traansacttios: \n" );
       // System.out.println(market.getBoorsa().get("ZM").getList_of_transactions());

        market.getUsersMap().get(shawn).tradeInstraction(market);
        printForCheck(market);
        market.getUsersMap().get(yoav).tradeInstraction(market);
        printForCheck(market);
       /*
        market.getUsersMap().get(shawn).tradeInstraction(market);
        printForCheck(market);
        market.getUsersMap().get(shawn).tradeInstraction(market);
        printForCheck(market);


        System.out.println("List to sell : \n");
        System.out.println(market.getBoorsa().get("ZM").getList_for_sell());

        //market.getUser(shawn).showActionHistory();

        //market.getUser(shawn).companyIssue(market,"Bolbol","ZM",500,50000);

       // System.out.println(market.getUser(shawn).getItemsList());


        //System.out.println("\n\nall the stocks: \n");
       // System.out.println(market.getBoorsa());

*/
    }

    public static void printForCheck(StockExchange market)
    {
        System.out.println("/*******************************************************************/\n");
        System.out.println("shawn items list : \n");
        System.out.println(market.getUsersMap().get("SHAWN").getItemsList());
        System.out.println("\n");
        System.out.println("yoav items list : \n");
        System.out.println(market.getUsersMap().get("YOAV").getItemsList());
        System.out.println("\n");
        System.out.println("zoom original stock detail: " +market.getBoorsa().get("ZM") );
        System.out.println("shawn action list : \n");
        System.out.println(market.getUsersMap().get("SHAWN").getList_of_actions());
        System.out.println("yoav action list : \n");
        System.out.println(market.getUsersMap().get("YOAV").getList_of_actions());
        System.out.println("/*******************************************************************/\n");
    }

    //the input name - is the name that suppose to come from login servlet.
    // insert to the "boorsa" the stockes that comes from the inserted xml.
    // inserts the items to the "name" user's items list.

    public static boolean insertXmlForSingleUser(StockExchange market, String name)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter XML full path:");
        String path = scanner.nextLine();
        if(path.length()<=4) {
            System.out.println("No file was given, try again.\n");
            return false;
        }
        String s = path.substring(path.length()-4,path.length());
        if (!s.equals(".xml")) // if the file is xml file
        {
            System.out.println("The file is not XML file, please enter different file");
            //clear func - back to the main menu ** dont erase data from system
            return false;
        }
        //RizpaStockExchangeDescriptor info_about_market = SchemaBasedJAXBMain.XML_Import(path);
        //market.getBoorsa().clear();
        //return boolean - if stocks in Xml file was invalid, createStocksFromRseStocks returns false.
     //   try {
        //    return market.putInfoInStockEx(info_about_market, name) ;
        //} catch (XMLLoadingException e) {
      //      e.printStackTrace();
      //  }


        return true;
    }




}




