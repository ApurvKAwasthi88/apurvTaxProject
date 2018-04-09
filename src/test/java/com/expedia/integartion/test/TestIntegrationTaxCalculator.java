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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 5/4/18
 * Time: 2:46 AM
 * To change this template use File | Settings | File Templates.
 */


public class TestIntegrationTaxCalculator
{


    private ProcessFile processFile;

    private static final String  TEST_FILE_NAME = "expediaTest.txt";

    @Before
    public void setupMock() throws FileNotFoundException {


        processFile = mock(ProcessFile.class);
        List<Item> itemList =new ArrayList<>();
        itemList.add(new Item("BOOK",1,new BigDecimal("29.49")));
        itemList.add(new Item("CD",1,new BigDecimal("15.99")));
        itemList.add(new Item("SNACK",1,new BigDecimal("0.75")));
        when(processFile.processFile(TEST_FILE_NAME)).thenReturn(itemList);

    }


    @Test
    public void testScenarioOneGivenExample() throws FileNotFoundException {

        TaxOrchestrator taxOrchestrator = new TaxOrchestrator(this.processFile);
        Receipt receipt =taxOrchestrator.orchestrateTaxAndGenerateRecipt();
        Assert.assertNotNull(receipt);
        assertEquals("Size Of Items",receipt.getItems().size(),3);
        assertEquals("Book Price After Tax",receipt.getItems().get(0).getPriceWithTax(),new BigDecimal("34.69"));
        assertEquals("Music Price After Tax",receipt.getItems().get(1).getPriceWithTax(),new BigDecimal("20.04"));
        assertEquals("Snack Price After Tax",receipt.getItems().get(2).getPriceWithTax(),new BigDecimal("0.90"));

        assertEquals( receipt.getTotalPrice().toString(), new BigDecimal("55.63").toString());
        assertEquals( receipt.getTotalTax().toString(), new BigDecimal("9.40").toString());


   }



}
