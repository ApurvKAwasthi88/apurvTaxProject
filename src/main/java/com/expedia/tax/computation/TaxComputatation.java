package com.expedia.tax.computation;

import com.expedia.domain.Item;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 3/4/18
 * Time: 2:06 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TaxComputatation
{
   public BigDecimal computeTax(Item item) throws ExecutionException;
}
