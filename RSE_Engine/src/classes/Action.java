package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Action
{

    private String kindOfAction ; // sell or buy
    private String symbol ; // the sumbol of the transaction stock
    private String date ; // the date the  action happened
    private int sum ; // the sum of stocks that the action is about
    private int worthBeforAction; //the user's worth value before the transaction
    private int worthAfterAction;//the user's worth value after the transaction


    /************ Ctors *************/
    public Action (String kind, String symbol, String Date, int sum, int befor,int after)
    {
        kindOfAction=kind;
        this.symbol=symbol;
        this.date=date;
        this.sum = sum;
        worthBeforAction = befor;
        worthAfterAction=after;
    }

    public Action()
    {
        kindOfAction="Money from system";
        symbol=null;
        date=null;
        sum=0;
        worthAfterAction=-1;
        worthBeforAction=-1;

    }
    /************ ToString *************/

    @Override
    public String toString() {
        return "Action{" +
                "kindOfAction='" + kindOfAction + '\'' +
                ", symbol='" + symbol + '\'' +
                ", date='" + date + '\'' +
                ", sum=" + sum +
                ", worthBeforAction=" + worthBeforAction +
                ", worthAfterAction=" + worthAfterAction +
                '}';
    }
    /************ Setters *************/
    public void setKindOfAction(int n)
    {
        if (n ==0)
        {
            String temp = " Sell";
            this.kindOfAction = temp;
        }
        if(n==1)
        {
            String temp = " Buy";
            this.kindOfAction = temp;
        }
    }
    public void setSymbol(String sy)
    {
        this.symbol=sy;
    }

    public void setSum(int sum)
    {
        this.sum=sum;
    }

    public void setWorthBeforAction(int befor)
    {
        this.worthBeforAction = befor;
    }

    public void setWorthAfterAction(int after)
    {
        this.worthAfterAction=after;
    }

    public void setDate()
    {
        this.date = DateTimeFormatter.ofPattern("HH:mm:ss:SSS").format(LocalDateTime.now());
    }

    /************ Getters *************/

    public String getKindOfAction(){return  kindOfAction;}

    public String getSymbol(){return symbol;}

    public String getDate(){return date;}

    public int getSum(){return sum;}

    public int getWorthBeforAction(){ return  worthBeforAction;}

    public int getWorthAfterAction(){ return worthAfterAction;}


}
