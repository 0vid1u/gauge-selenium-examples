package com.ovixeu.core;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;

import java.util.List;
import java.util.Map;

public class Browser {
    final static Logger logger = Logger.getLogger(Browser.class);

    private final Map<BrowserType, Dimension> browserMap = Maps.newHashMapWithExpectedSize(5);

    public Browser(final String browserWebPropertyStr,
                   final String browserTabletPropertyStr,
                   final String browserMobilePropertyStr,
                   final String browserPhantomPropertyStr) {
        List<Integer> browserWebProperty = stringToInts(browserWebPropertyStr);
        List<Integer> browserTabletProperty = stringToInts(browserTabletPropertyStr);
        List<Integer> browserMobileProperty = stringToInts(browserMobilePropertyStr);
        List<Integer> browserPhantomProperty = stringToInts(browserPhantomPropertyStr);

        browserMap.put(BrowserType.WEB, new Dimension(browserWebProperty.get(0), browserWebProperty.get(1)));
        browserMap.put(BrowserType.TABLET, new Dimension(browserTabletProperty.get(0), browserTabletProperty.get(1)));
        browserMap.put(BrowserType.MOBILE, new Dimension(browserMobileProperty.get(0), browserMobileProperty.get(1)));
        browserMap.put(BrowserType.PHANTOM, new Dimension(browserPhantomProperty.get(0), browserPhantomProperty.get(1)));
    }


    private List<Integer> stringToInts(String stringOfInts) {
        String[] futureInts = stringOfInts.split(",");
        List<Integer> integersToReturn = Lists.newArrayList();
        for (String stringToInt : futureInts) {
            integersToReturn.add(Integer.parseInt(stringToInt));
        }
        return integersToReturn;
    }

    /**
     * Gets dimension.
     *
     * @return the dimension
     */
    public Dimension getDimension(BrowserType browserType) {
        return browserMap.get(browserType);
    }
}


