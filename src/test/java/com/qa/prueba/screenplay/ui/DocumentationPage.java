package com.qa.prueba.screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

/**
 * Page Component: Documentation page content area
 */
public class DocumentationPage {

    public static final Target MAIN_CONTENT = Target.the("documentation main content")
            .locatedBy("//main[@id='td-section-nav']//article | //main//article | //main");
}
