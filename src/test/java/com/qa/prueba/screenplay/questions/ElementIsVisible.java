package com.qa.prueba.screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

@Subject("whether the element is visible")
public class ElementIsVisible implements Question<Boolean> {

    private final Target target;

    public ElementIsVisible(Target target) {
        this.target = target;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            return !BrowseTheWeb.as(actor).getDriver()
                    .findElements(By.xpath(target.getCssOrXPathSelector()))
                    .isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public static ElementIsVisible of(Target target) {
        return new ElementIsVisible(target);
    }
}
