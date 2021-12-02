package classes;

import java.io.Serializable;
import java.util.Locale;

public class Item implements Serializable {
    private int quantity;
    private Stock stock;
    private int stockPrice;


    /************ Ctors *************/

    //default
    public Item()
    {
        quantity=0;
        stock=null; // connections to the actual stock for info.
        stockPrice=0;
    }
    //full
    public  Item(Stock stock, int quantity,int stockPrice) {
        this.quantity=quantity;
        this.stock=stock;
        this.stockPrice=stockPrice;

    }


    /************ Getters *************/

    public int getWorthOfItem()
    {
        return this.quantity * this.stockPrice;
    }

    public String getSymbol()
    {
        return stock.getSymbol();
    }

    public int getQuantity()
    {
        return this.quantity;
    }

    public int getQuantitiy()
    {
        return this.quantity;
    }

    public Stock getStock()
    {
        return this.stock;
    }

    /************ Setters *************/

//   // public void setStockPrice(int price)
//    {
//        this.stockPrice= this.stock.getStockPrice();
//    }

    public void setItemPrice(int price)
    {
        this.stockPrice=price;
    }

    void setQuantity(int q)
    {
        this.quantity=q;
    }

    void setStock(Stock st)
    {
        this.stock=st;
    }

    /************ ToString *************/
    @Override
    public String toString()
    {
        return
                "quantity:" + quantity +
                ", stock name: " + stock.getCompanyName() +
                ", stock Price:" + stock.getStock_gate() ;
    }






}