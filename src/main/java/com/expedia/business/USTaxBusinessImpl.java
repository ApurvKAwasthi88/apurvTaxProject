package com.expedia.business;

import com.expedia.domain.Item;
import com.expedia.domain.Receipt;
import com.expedia.tax.computation.TaxComputatation;
import com.expedia.tax.computation.USTaxComputationImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 *   User: apurv
 * * Tax Processing and Recipt generation as per US Tax implementation
 */
public class USTaxBusinessImpl implements TaxBusiness
{

    private TaxComputatation taxComputatation ;

    private static final String DEFAULT= "0";

    public USTaxBusinessImpl()
    {
        this.taxComputatation = new USTaxComputationImpl();
    }

    @Override
    public Receipt processTaxGenerateRecepit(List<Item> items)
    {

        Receipt receipt = new Receipt();
        BigDecimal totalTax = new BigDecimal(DEFAULT);
        BigDecimal totalCost = new BigDecimal(DEFAULT);
        totalTax = totalTax.setScale(2,BigDecimal.ROUND_UP);
        totalCost = totalCost.setScale(2,BigDecimal.ROUND_UP);
        try
        {
            for(Item eachItem : items)
            {
                BigDecimal taxComputed =
                        this.taxComputatation.computeTax(eachItem);
                taxComputed = taxComputed.setScale(2,BigDecimal.ROUND_UP);
                eachItem.setPriceWithTax(taxComputed);
                totalTax= totalTax.add(taxComputed);
                BigDecimal pricePerItem = eachItem.getPrice().multiply(new BigDecimal(eachItem.getNoOfItem()));
                pricePerItem = pricePerItem.setScale(2,BigDecimal.ROUND_UP);
                pricePerItem = pricePerItem.add(taxComputed);
                totalCost= totalCost.add(pricePerItem);
                eachItem.setPriceWithTax(pricePerItem);

            }

           receipt.setItems(items);
           receipt.setTotalTax(totalTax);
           receipt.setTotalPrice(totalCost);
        }
        catch (ExecutionException e)
        {
            System.out.println("Exception occured while processing Tax");
            e.printStackTrace();

        }



        return receipt;
    }
}
