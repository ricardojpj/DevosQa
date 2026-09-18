package com.qa.prueba.screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Page Component: Navigation Bar
 * Targets scoped to the main navbar of selenium.dev
 */
public class NavBar {

    public static final Target DOCUMENTATION_LINK = Target.the("Documentation nav link")
            .locatedBy("//nav//a[@href='/documentation']");

    public static final Target DOWNLOADS_LINK = Target.the("Downloads nav link")
            .locatedBy("//nav//a[@href='/downloads']");
}
