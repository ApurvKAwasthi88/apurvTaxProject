package com.expedia.util;

import jdk.nashorn.internal.runtime.options.Option;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: apurv
 *
 *  TaxUtils is utility class which helps the business to compute percentage  and helps the repo to load data
 */
public class TaxUtils
{

    private static final String TAX_PROPERTY_FILE_NAME ="tax.properties";
    private static final String ADDITIONAL_TAX_PROPERTY_FILE_NAME ="additionalTax.properties";
    private static final String DISCOUNT_TAX_PROPERTY_FILE_NAME ="discount.properties";
    private static final String PERCENTAGE ="100";
    private static final String DEFAULT_TAX_RATE_PROP ="DEFAULT_TAX_RATE";
    private static final String ADDITIONAL_DEFAULT_TAX_RATE ="ADDITIONAL_DEFAULT_TAX_RATE";
    private static  BigDecimal ROUNDING;
    private static Properties properties;
    private static Properties additionalTaxProperties;
    private static Properties discountTaxProperties;

    static
    {
        try {
            properties = loadTaxSettings();
            additionalTaxProperties = loadAdditionalTaxSettings();
            discountTaxProperties = loadDiscountTaxSettings();
            ROUNDING = new BigDecimal(properties.getProperty("rounding"));


        } catch (IOException e) {
            System.out.println("IO Exception occured while loading Tax Rates");
            e.printStackTrace();
        }

    }
    private TaxUtils()
    {

    }

    public static BigDecimal calculatePercentageAndRound(BigDecimal base, BigDecimal percentageTax){
        return calculateRounding(base.multiply(percentageTax).divide(new BigDecimal(PERCENTAGE)));
    }

    private  static BigDecimal calculateRounding(BigDecimal value) {
        BigDecimal divided = value.divide(ROUNDING, 0, BigDecimal.ROUND_UP);
        return divided.multiply(ROUNDING);
    }
    public static BigDecimal getTaxForType(String type)
    {
        String taxRate =properties.getProperty(type);
        if (taxRate ==null)
        {
            System.out.println("Got nul D/B for type:"+type);
            taxRate = properties.getProperty(DEFAULT_TAX_RATE_PROP);
        }
        return new BigDecimal(taxRate);
    }
    public static Optional<BigDecimal> getAddOnTaxByType(String type)
    {
        String taxRate =additionalTaxProperties.getProperty(type);
        Optional<BigDecimal> bigDecimalOption = Optional.empty();
        if(taxRate != null)
        {
            bigDecimalOption = Optional.of( new BigDecimal(taxRate));
        }
         else
        {
            bigDecimalOption =
                    Optional.of(new BigDecimal(additionalTaxProperties.getProperty(ADDITIONAL_DEFAULT_TAX_RATE)));
        }


        return bigDecimalOption;
    }

    public static Optional<BigDecimal> getDiscountTaxByType(String type)
    {
        return Optional.empty();
    }


    public static Properties loadAdditionalTaxSettings() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = TaxUtils
                .class.getClassLoader().getResourceAsStream(ADDITIONAL_TAX_PROPERTY_FILE_NAME);
        prop.load(inputStream);
        return prop;

    }

    public static Properties loadTaxSettings() throws IOException {
        Properties prop = new Properties();

        InputStream inputStream = TaxUtils
                .class.getClassLoader().getResourceAsStream(TAX_PROPERTY_FILE_NAME);
        prop.load(inputStream);
        return prop;

    }


    public static Properties loadDiscountTaxSettings() throws IOException {
        Properties prop = new Properties();

        InputStream inputStream = TaxUtils
                .class.getClassLoader().getResourceAsStream(DISCOUNT_TAX_PROPERTY_FILE_NAME);
        prop.load(inputStream);
        return prop;

    }

}
