package com.expedia.tax.computation;

import com.expedia.domain.Item;
import com.expedia.persistance.TaxRepository;
import com.expedia.persistance.TaxRepositoryImpl;
import com.expedia.util.TaxUtils;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 3/4/18
 * Time: 2:08 AM
 * To change this template use File | Settings | File Templates.
 */
public class USTaxComputationImpl implements TaxComputatation {


    private TaxRepository taxRepository;
    public USTaxComputationImpl()
    {
        this.taxRepository = new TaxRepositoryImpl();
    }


    @Override
    public BigDecimal computeTax(Item item) throws ExecutionException {

         BigDecimal actualPrice  = item.getPrice()
                 .multiply(new BigDecimal(item.getNoOfItem()));

        BigDecimal taxRate = this.taxRepository.findTaxByItemType(item.getItemType());
        Optional<BigDecimal> addOnTax = this.taxRepository.findAddOnTaxByItemType(item.getItemType());
        BigDecimal taxComputed = TaxUtils.calculatePercentageAndRound(actualPrice, taxRate);
        if(addOnTax.isPresent())
        {
            taxComputed = taxComputed.add(addOnTax.get());
        }



        return taxComputed;
    }
}
