package com.expedia.orchestrator;

import com.expedia.business.TaxBusiness;
import com.expedia.business.USTaxBusinessImpl;
import com.expedia.domain.Item;
import com.expedia.domain.Receipt;
import com.expedia.domain.TaxableCountry;
import com.expedia.processor.ProcessFile;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: apurv
 *
 *  TaxOrchestrator is the main class the talks to the business layer computes tax and generates Recipt
 */
public class TaxOrchestrator
{
    private static final String  TEST_FILE_NAME = "expediaTest.txt";
    private  ProcessFile processFile;

    public TaxOrchestrator(ProcessFile processFile)
    {
        this.processFile =processFile;
    }

    public Receipt orchestrateTaxAndGenerateRecipt() throws FileNotFoundException
    {
        List<Item> itemList = this.processFile.processFile(TEST_FILE_NAME);
        TaxBusiness taxBusiness = TaxFactory.getTaxationByCountry(TaxableCountry.US);
        Receipt receipt= taxBusiness.processTaxGenerateRecepit(itemList);
        return receipt;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ProcessFile processFile  = new ProcessFile();
        List<Item> itemList = processFile.processFile(TEST_FILE_NAME);
        TaxBusiness taxBusiness = new USTaxBusinessImpl();
        Receipt receipt= taxBusiness.processTaxGenerateRecepit(itemList);

        for(Item eachItem: receipt.getItems())
        {
            System.out.println("Each Item: "+eachItem.getNoOfItem()+" "+
                    eachItem.getItemType()+" After Tax Computation Price: "+eachItem.getPriceWithTax());
        }

        System.out.println("Total Price: "+receipt.getTotalPrice());
        System.out.println("Total Sales Tax: "+receipt.getTotalTax());


    }

    //We can have Factory of Business


}
