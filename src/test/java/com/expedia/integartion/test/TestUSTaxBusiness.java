package com.expedia.integartion.test;

import com.expedia.business.TaxBusiness;
import com.expedia.business.USTaxBusinessImpl;
import com.expedia.domain.Item;
import com.expedia.domain.Receipt;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: HP
 * Date: 9/4/18
 * Time: 1:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestUSTaxBusiness
{
    private TaxBusiness taxBusiness;

  @Before
  public void setUp()
  {
      this.taxBusiness = new USTaxBusinessImpl();
  }

  @Test
  public void testMultipleDifferentItems()
  {

      List<Item> itemList =new ArrayList<>();
      itemList.add(new Item("BOOK",2,new BigDecimal("29.49")));
      itemList.add(new Item("CD",2,new BigDecimal("15.99")));

      this.taxBusiness = new USTaxBusinessImpl();
      Receipt receipt =
              taxBusiness.processTaxGenerateReceipt(itemList);


      assertEquals( receipt.getTotalPrice().toString(), new BigDecimal("108.16").toString());
      assertEquals( receipt.getTotalTax().toString(), new BigDecimal("17.20").toString());
      assertEquals("Size Of Items",receipt.getItems().size(),2);

      assertEquals("BOOK Price After Tax",receipt.getItems().get(0).getPriceWithTax(),new BigDecimal("69.33"));
      assertEquals("No Of Items",receipt.getItems().get(0).getNoOfItem(),2);

      assertEquals("CD Price After Tax", receipt.getItems().get(1).getPriceWithTax(), new BigDecimal("38.83"));
      assertEquals("No Of Items",receipt.getItems().get(1).getNoOfItem(),2);



  }

    //No Tax on Medical Items
    @Test
    public void testMedicalItems()
    {
        List<Item> itemList =new ArrayList<>();
        itemList.add(new Item("MEDICAL",2,new BigDecimal("30.49")));
        itemList.add(new Item("MEDICAL",2,new BigDecimal("5.99")));
        itemList.add(new Item("MEDICAL",1,new BigDecimal("5.75")));

        Receipt receipt =
                taxBusiness.processTaxGenerateReceipt(itemList);
        assertEquals( receipt.getTotalPrice().toString(), new BigDecimal("78.71").toString());
        assertEquals( receipt.getTotalTax().toString(), new BigDecimal("0.00").toString());
        assertEquals("Size Of Items",receipt.getItems().size(),3);

        assertEquals("No Of Items",receipt.getItems().get(0).getNoOfItem(),2);
        assertEquals("MEDICAL Price After Tax",receipt.getItems().get(0).getPriceWithTax(),new BigDecimal("60.98"));


        assertEquals("No Of Items",receipt.getItems().get(1).getNoOfItem(),2);
        assertEquals("MEDICAL Price After Tax", receipt.getItems().get(1).getPriceWithTax(), new BigDecimal("11.98"));


        assertEquals("No Of Items",receipt.getItems().get(2).getNoOfItem(),1);
        assertEquals("MEDICAL Price After Tax", receipt.getItems().get(2).getPriceWithTax(), new BigDecimal("5.75"));




    }
     //Additional Tax on Music Items
     @Test
    public void testMusicItems()
    {
        List<Item> itemList =new ArrayList<>();
        itemList.add(new Item("CD",2,new BigDecimal("29.49")));
        itemList.add(new Item("CD",2,new BigDecimal("15.99")));
        itemList.add(new Item("CD",1,new BigDecimal("10.75")));
        Receipt receipt =
                taxBusiness.processTaxGenerateReceipt(itemList);
        assertEquals( receipt.getTotalPrice().toString(), new BigDecimal("123.31").toString());
        assertEquals( receipt.getTotalTax().toString(), new BigDecimal("21.60").toString());
        assertEquals("Size Of Items",receipt.getItems().size(),3);

        assertEquals("CD Price After Tax",receipt.getItems().get(0).getPriceWithTax(),new BigDecimal("70.58"));
        assertEquals("No Of Items",receipt.getItems().get(0).getNoOfItem(),2);

        assertEquals("CD Price After Tax",receipt.getItems().get(1).getPriceWithTax(),new BigDecimal("38.83"));
         assertEquals("No Of Items",receipt.getItems().get(1).getNoOfItem(),2);

        assertEquals("CD Price After Tax",receipt.getItems().get(2).getPriceWithTax(),new BigDecimal("13.90"));
        assertEquals("No Of Items",receipt.getItems().get(2).getNoOfItem(),1);


    }






}
