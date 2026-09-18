package com.qa.prueba.screenplay.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class NavigateToSeleniumWebsite implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url("https://www.selenium.dev")
        );
    }

    public static NavigateToSeleniumWebsite homePage() {
        return new NavigateToSeleniumWebsite();
    }
}
