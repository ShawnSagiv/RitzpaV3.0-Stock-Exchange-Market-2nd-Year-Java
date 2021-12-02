package jaxb;

import jaxb.jaxbWebClasses.RizpaStockExchangeDescriptor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SchemaBasedJAXBMain {

//    private static String JAXB_XML_GAME_PACKAGE_NAME = "jaxb.jaxbclasses";
    private static String JAXB_XML_GAME_PACKAGE_NAME = "jaxb.jaxbWebClasses";



    private static RizpaStockExchangeDescriptor deserializeFrom(InputStream in) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(JAXB_XML_GAME_PACKAGE_NAME);
        Unmarshaller u = jc.createUnmarshaller();
        return (RizpaStockExchangeDescriptor) u.unmarshal(in);
    }

    public static RizpaStockExchangeDescriptor XML_Import(InputStream inputStream)
    {
        RizpaStockExchangeDescriptor new_stocks = null;
        try {
            new_stocks = deserializeFrom(inputStream);
            return new_stocks;
        } catch (JAXBException e) {

            //  e.printStackTrace();
            System.out.println("wrong file ");
            //clear dont erase data
        }
        return new_stocks;
    }
}
