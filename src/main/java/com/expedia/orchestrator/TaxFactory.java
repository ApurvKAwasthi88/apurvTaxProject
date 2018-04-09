package com.expedia.orchestrator;

import com.expedia.business.IndiaTaxBusinessImpl;
import com.expedia.business.TaxBusiness;
import com.expedia.business.USTaxBusinessImpl;
import com.expedia.domain.TaxableCountry;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 8/4/18
 * Time: 10:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class TaxFactory
{
    private static TaxBusiness taxBusiness;
    public static TaxBusiness getTaxationByCountry(TaxableCountry taxableCountry)
    {
        switch (taxableCountry)
        {
            case US:

                taxBusiness= new USTaxBusinessImpl();
                break;
            case INDIA:
                taxBusiness = new IndiaTaxBusinessImpl();

        }

        return taxBusiness;
    }


}
