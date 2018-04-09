package com.expedia.business;

import com.expedia.domain.Item;
import com.expedia.domain.Receipt;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: apurv
 *  The contract is responsible for processing Tax an Generating Receipt
 */
public interface TaxBusiness
{

    public Receipt processTaxGenerateRecepit(List<Item> items);

}
