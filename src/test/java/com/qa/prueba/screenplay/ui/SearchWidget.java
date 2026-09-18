package com.qa.prueba.screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Page Component: Search Widget (Algolia DocSearch)
 * Targets scoped to the DocSearch modal on selenium.dev
 */
public class SearchWidget {

    public static final Target TRIGGER_BUTTON = Target.the("DocSearch trigger button")
            .locatedBy("//button[contains(@class,'DocSearch-Button')]");

    public static final Target INPUT = Target.the("DocSearch input field")
            .locatedBy("//input[@class='DocSearch-Input']");

    public static final Target HITS_CONTAINER = Target.the("DocSearch hits container")
            .locatedBy("//div[@class='DocSearch-Hits']");

    public static final Target FIRST_HIT = Target.the("first DocSearch hit")
            .locatedBy("(//li[contains(@class,'DocSearch-Hit')])[1]");
}
