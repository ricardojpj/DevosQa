package com.qa.prueba.screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Page Component: Home Page hero section
 */
public class HomePage {

    public static final Target HERO_HEADING = Target.the("home page hero heading")
            .locatedBy("//h1[contains(@class,'d-1')]");
}
