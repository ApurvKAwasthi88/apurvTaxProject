package com.expedia.tax.computation;

import com.expedia.domain.Item;

import java.math.BigDecimal;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: apurv
 *
 * IndianTaxImpl can be implemented for calculating Tax for India
 */
public class IndiaTaxComputationImpl implements TaxComputation {
    @Override
    public BigDecimal computeTax(Item item) throws ExecutionException {

        return null;
    }
}
