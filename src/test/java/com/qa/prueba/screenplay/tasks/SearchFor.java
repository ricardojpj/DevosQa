package com.qa.prueba.screenplay.tasks;

import com.qa.prueba.screenplay.ui.SearchWidget;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.Keys;

public class SearchFor implements Task {

    private final String searchTerm;

    public SearchFor(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(SearchWidget.TRIGGER_BUTTON),
                Enter.theValue(searchTerm).into(SearchWidget.INPUT).thenHit(Keys.ENTER)
        );
    }

    public static SearchFor term(String searchTerm) {
        return new SearchFor(searchTerm);
    }
}
