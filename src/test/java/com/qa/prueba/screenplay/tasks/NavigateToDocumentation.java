package com.qa.prueba.screenplay.tasks;

import com.qa.prueba.screenplay.ui.NavBar;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class NavigateToDocumentation implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(NavBar.DOCUMENTATION_LINK)
        );
    }

    public static NavigateToDocumentation page() {
        return new NavigateToDocumentation();
    }
}
