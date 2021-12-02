package exception;

import java.util.Locale;

public class XMLLoadingException extends Throwable{
    private int errorType;

    public XMLLoadingException(int errorTypeNumber) {
        this.errorType = errorTypeNumber;
    }

    public String getMessageOfError() {
        switch (errorType) {
            case 1:
                return this.getMessageDuplicatedStocksError();
            case 2:
                return this.getMessageDuplicatedCompaniesError();
            case 3:
                return this.getMessageDuplicatedUsersError();
            case 4:
                return this.getMessageDuplicatedHoldingsError();
            default:
                return this.getStackTrace().toString();
        }
    }

    private String getMessageDuplicatedStocksError() {
        return new String("There is Duplicated Stocks.\nPlease try to load other XMl file.");
    }

    private String getMessageDuplicatedCompaniesError() {
        return new String("There is Duplicated companies.\nPlease try to load other XMl file.");
    }

    private String getMessageDuplicatedUsersError() {
        return new String("There is Duplicated Users.\nPlease try to load other XMl file.");
    }

    private String getMessageDuplicatedHoldingsError() {
        return new String("There is Duplicated Holdings for the same user.\nPlease try to load other XMl file.");
    }
}
