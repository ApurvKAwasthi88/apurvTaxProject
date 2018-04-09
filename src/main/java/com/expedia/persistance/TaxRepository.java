package com.expedia.persistance;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: apurv
 * The intent of the class is to Talk to a data source and load the Tax based on tax types.
 */
public interface TaxRepository
{

    public BigDecimal findTaxByItemType(String itemType) throws ExecutionException;
    public Optional<BigDecimal> findAddOnTaxByItemType(String itemType) throws ExecutionException;

}
