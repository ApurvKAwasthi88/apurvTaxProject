package com.expedia.integartion.test;

import com.expedia.orchestrator.TaxOrchestrator;
import com.expedia.domain.Receipt;
import com.expedia.processor.ProcessFile;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 9/4/18
 * Time: 1:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestIntegrationWithoutMock
{
    @Test
    public void testScenarioTwoGivenExample() throws FileNotFoundException {

        ProcessFile processFile  = new ProcessFile();
        TaxOrchestrator taxOrchestrator = new TaxOrchestrator(processFile);
        Receipt receipt =taxOrchestrator.orchestrateTaxAndGenerateRecipt();
        Assert.assertNotNull(receipt);
        assertEquals( receipt.getTotalPrice().toString(), new BigDecimal("60.98").toString());
        assertEquals( receipt.getTotalTax().toString(), new BigDecimal("9.60").toString());
        assertEquals("Size Of Items",receipt.getItems().size(),4);
        assertEquals("Wine Price After Tax",receipt.getItems().get(0).getPriceWithTax(),new BigDecimal("24.69"));
        assertEquals("pills Price After Tax",receipt.getItems().get(1).getPriceWithTax(),new BigDecimal("4.15"));
        assertEquals("Pins Price After Tax",receipt.getItems().get(2).getPriceWithTax(),new BigDecimal("13.25"));
        assertEquals("CD Price After Tax",receipt.getItems().get(3).getPriceWithTax(),new BigDecimal("18.89"));

    }



}
