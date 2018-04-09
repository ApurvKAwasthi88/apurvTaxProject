package com.expedia.domain;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 *  User: apurv
 * To change this template use File | Settings | File Templates.
 * Item class is holds the item information post processing the price with Tax is updated
 */
public class Item
{

    private final String itemType;
    private final int noOfItem;
    private final BigDecimal price;
    private BigDecimal priceWithTax;

    public Item(String itemType, int noOfItem, BigDecimal price) {
        this.itemType = itemType;
        this.noOfItem = noOfItem;
        this.price = price;
    }

    public String getItemType() {
        return itemType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getNoOfItem() {
        return noOfItem;
    }

    public BigDecimal getPriceWithTax() {
        return priceWithTax;
    }

    public void setPriceWithTax(BigDecimal priceWithTax) {
        this.priceWithTax = priceWithTax;
    }
}
