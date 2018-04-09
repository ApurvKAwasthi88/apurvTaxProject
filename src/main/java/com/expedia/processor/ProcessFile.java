package com.expedia.processor;

import com.expedia.domain.Item;
import com.expedia.util.TaxUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: apurv
  * ProcessFile class is responsible  to load the txt file which has sample data and return Items
 * This class is mocked  in test cases to test various scenario.
 */
public class ProcessFile
{



    public List<Item> processFile(String fileName) throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        Scanner scanner = new Scanner(file);
        List<Item> items  =new ArrayList<Item>();
        while(scanner.hasNext())
        {
            String input = scanner.nextLine();
            if(!StringUtils.isEmpty(input)  )
            {

                String [] arr = input.split(" ");
                Item item = new Item(arr[1],
                        Integer.valueOf(arr[0]),new BigDecimal(arr[2] ));
                items.add(item);

            }

        }
        return items;
    }
}