package com.expedia.tax.computation;

import com.expedia.domain.Item;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: apurv
 *
  * The contract is resposible for computation of Tax per item
 */
public interface TaxComputatation
{
   public BigDecimal computeTax(Item item) throws ExecutionException;
}
