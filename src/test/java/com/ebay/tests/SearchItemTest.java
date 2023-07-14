package com.ebay.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchItemTest extends TestBase{
    @Test
    public void searchItem() {

        app.getSelect().selectDepartment("Elektronik");

        app.getSelect().selectCategoryType("1");

        app.getSelect().selectCategoryType("1");

        String secondItemName = app.getItem().getItemName("2");
        System.out.println(secondItemName);

        app.getItem().fillSearchBar(secondItemName);

        String firstItemName = app.getItem().getFirstItemName("2");
        System.out.println(firstItemName);
        //Assert.assertEquals(firstItemName,secondItemName);

        Assert.assertTrue(firstItemName.contains(secondItemName));
    }


}
/*
        1 Open browser and maximize screen
        2 Get ebay.de
        3 Get ‘Elektronik’
        4 Select section ‘Handys & Smartphones’
        5 Select ‘Apple’
        6 Remember second element in search results
        7 Enter the memorized value in the search bar
        8 Find and check that the product name matches the stored value
*/