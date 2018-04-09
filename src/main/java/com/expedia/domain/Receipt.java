package com.expedia.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: apurv
 * Date: 4/4/18
 * Time: 12:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class Receipt
{

    private List<Item> items;
    private BigDecimal totalTax;
    private BigDecimal totalPrice;

    public Receipt()
    {

    }

    public Receipt( List<Item> items) {

        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}

