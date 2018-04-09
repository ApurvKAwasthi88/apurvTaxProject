package com.expedia.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: apurv
 *
 * Recipt class holds items, total Tax and total price for the given list of Items
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

