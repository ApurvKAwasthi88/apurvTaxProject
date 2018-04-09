package com.expedia.tax.computation;

import com.expedia.domain.Item;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: apurv
 *
  * The contract is responsible for computation of Tax per item
 */
public interface TaxComputation
{
   public BigDecimal computeTax(Item item) throws ExecutionException;
}
