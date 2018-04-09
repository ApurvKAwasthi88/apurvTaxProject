package com.expedia.persistance;

import com.expedia.util.TaxUtils;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 * User: apurv
 * The intent of the class is to Talk to a data source and load the Tax based on tax types.
 * The actual impl will load the values from d/b as of now i am using prop file to load the tax based on types
 */
public class TaxRepositoryImpl implements TaxRepository {


    private LoadingCache<String, BigDecimal> taxRulesCache;
    private LoadingCache<String, BigDecimal> additionalTaxRate;
    public  TaxRepositoryImpl() {

        buildCache();
        loadTaxRates();
        loadAdditionalTaxRates();


    }


    public void buildCache()
    {

        taxRulesCache = CacheBuilder.newBuilder()
                .build(new CacheLoader<String, BigDecimal>() {
                    @Override
                    public BigDecimal load(String taxRules) throws Exception {
                        return getTaxRate(taxRules);
                    }
                });

        additionalTaxRate =  CacheBuilder.newBuilder()
                    .build(new CacheLoader<String, BigDecimal>() {
                        @Override
                        public BigDecimal load(String taxRules) throws Exception {
                            return getAddOnTaxRate(taxRules).get();
                        }
                    });
    }

        @Override
    public BigDecimal findTaxByItemType(String itemType) throws ExecutionException {

          return this.taxRulesCache.get(itemType);
    }

    @Override
    public Optional<BigDecimal> findAddOnTaxByItemType(String itemType) throws ExecutionException {


        Optional<BigDecimal> bigDecimalOptional=  Optional.of(this.additionalTaxRate.get(itemType));
        return bigDecimalOptional;
    }


    private  BigDecimal getTaxRate(String taxTYPES)
    {
        return TaxUtils.getTaxForType(taxTYPES);
    }


    private  Optional<BigDecimal> getAddOnTaxRate(String taxTYPES)
    {
        return TaxUtils.getAddOnTaxByType(taxTYPES);
    }

    private void loadTaxRates()  {

        try{

            Properties properties = TaxUtils.loadTaxSettings();
            Set<Map.Entry<Object,Object>> entries = properties.entrySet();
            for(Map.Entry<Object,Object> eachEntry : entries)
            {
                this.taxRulesCache.put(String.valueOf(eachEntry.getKey()),
                        new BigDecimal(String.valueOf(eachEntry.getValue())));
            }
        }
        catch (IOException exception)
        {
            System.out.println("Failed to Load the Tax Property");
            exception.printStackTrace();

        }


    }

    private void loadAdditionalTaxRates(){

        try{

            Properties properties = TaxUtils.loadAdditionalTaxSettings();

            Set<Map.Entry<Object,Object>> entries = properties.entrySet();
            for(Map.Entry<Object,Object> eachEntry : entries)
            {
                this.additionalTaxRate.put(String.valueOf(eachEntry.getKey()),
                        new BigDecimal(String.valueOf(eachEntry.getValue())));
            }
        }
        catch (IOException exception){
            System.out.println("Failed to Load the Tax Property");
            exception.printStackTrace();
        }

    }
}


