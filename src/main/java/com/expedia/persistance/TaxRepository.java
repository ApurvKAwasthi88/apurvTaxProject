package com.expedia.persistance;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: apurv
 * Date: 5/4/18
 * Time: 1:24 AM
 * The intent of the class is to Talk to a data scource and load the Tax based on tax types.
 */
public interface TaxRepository
{

    public BigDecimal findTaxByItemType(String itemType) throws ExecutionException;
    public Optional<BigDecimal> findAddOnTaxByItemType(String itemType) throws ExecutionException;

}
