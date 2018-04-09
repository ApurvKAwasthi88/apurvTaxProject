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
 *  User: apurv
 *
 * USTaxComputationImpl interacts with Tax repo finds the tax rate and computes the tax.
 */
public class USTaxComputationImpl implements TaxComputation {


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
        Optional<BigDecimal> discountOnTax = this.taxRepository.findDiscountTaxByItemType(item.getItemType());
        BigDecimal taxComputed = TaxUtils.calculatePercentageAndRound(actualPrice, taxRate);
        if(addOnTax.isPresent())
        {
            taxComputed = taxComputed.add(addOnTax.get());
        }
        if(discountOnTax.isPresent())
        {
            taxComputed = taxComputed.subtract(discountOnTax.get());

        }
        return taxComputed;
    }
}
