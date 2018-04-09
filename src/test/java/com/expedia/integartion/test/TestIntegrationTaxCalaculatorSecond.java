package com.expedia.integartion.test;

import com.expedia.domain.Item;
import com.expedia.domain.Receipt;
import com.expedia.processor.ProcessFile;
import com.expedia.orchestrator.TaxOrchestrator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 8/4/18
 * Time: 7:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestIntegrationTaxCalaculatorSecond
{
    private ProcessFile processFile;
    private static final String  TEST_FILE_NAME = "expediaTest.txt";
    @Before
    public void setupMock() throws FileNotFoundException {

        processFile = mock(ProcessFile.class);
        List<Item> itemList =new ArrayList<>();
        itemList.add(new Item("WINE",1,new BigDecimal("20.99")));
        itemList.add(new Item("MEDICAL",1,new BigDecimal("4.15")));
        itemList.add(new Item("PINS",1,new BigDecimal("11.25")));
        itemList.add(new Item("CD",1,new BigDecimal("14.99")));
        when(processFile.processFile(TEST_FILE_NAME)).thenReturn(itemList);

    }

    @Test
    public void testScenarioTwoGivenExample() throws FileNotFoundException {

        TaxOrchestrator taxOrchestrator = new TaxOrchestrator(this.processFile);
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
