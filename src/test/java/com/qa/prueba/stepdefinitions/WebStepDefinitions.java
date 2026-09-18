package com.qa.prueba.stepdefinitions;

import com.qa.prueba.screenplay.questions.CurrentUrl;
import com.qa.prueba.screenplay.questions.ElementIsVisible;
import com.qa.prueba.screenplay.questions.PageTitle;
import com.qa.prueba.screenplay.tasks.NavigateToDocumentation;
import com.qa.prueba.screenplay.tasks.NavigateToSeleniumWebsite;
import com.qa.prueba.screenplay.tasks.SearchFor;
import com.qa.prueba.screenplay.ui.DocumentationPage;
import com.qa.prueba.screenplay.ui.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.annotations.Managed;
import org.openqa.selenium.WebDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class WebStepDefinitions {

    @Managed
    WebDriver driver;

    private Actor user;

    @Given("the user opens the selenium website")
    public void theUserOpensTheSeleniumWebsite() {
        OnStage.setTheStage(new OnlineCast());
        user = OnStage.theActorCalled("User");
        user.can(BrowseTheWeb.with(driver));
        user.attemptsTo(NavigateToSeleniumWebsite.homePage());
    }

    @Then("the page title should be {string}")
    public void thePageTitleShouldBe(String expectedTitle) {
        String actualTitle = PageTitle.displayed().answeredBy(user);
        assertThat(actualTitle)
                .as("Page title should contain '%s'", expectedTitle)
                .containsIgnoringCase(expectedTitle);
    }

    @Then("the home page should display the main heading")
    public void theHomePageShouldDisplayTheMainHeading() {
        assertThat(ElementIsVisible.of(HomePage.HERO_HEADING).answeredBy(user))
                .as("Main heading should be visible on the home page")
                .isTrue();
    }

    @When("the user clicks on {string} in the navigation menu")
    public void theUserClicksOnNavigationMenu(String menuItem) {
        user.attemptsTo(NavigateToDocumentation.page());
    }

    @Then("the user should be on the documentation page")
    public void theUserShouldBeOnTheDocumentationPage() {
        assertThat(CurrentUrl.displayed().answeredBy(user))
                .as("URL should contain 'documentation'")
                .containsIgnoringCase("documentation");
    }

    @Then("the documentation page should display its main content")
    public void theDocumentationPageShouldDisplayItsMainContent() {
        assertThat(ElementIsVisible.of(DocumentationPage.MAIN_CONTENT).answeredBy(user))
                .as("Documentation content should be visible")
                .isTrue();
    }

    @When("the user performs a search for {string}")
    public void theUserPerformsASearchFor(String searchTerm) {
        user.attemptsTo(SearchFor.term(searchTerm));
    }

    @Then("the search results page should be displayed")
    public void theSearchResultsPageShouldBeDisplayed() {
        String pageSource = BrowseTheWeb.as(user).getDriver().getPageSource();
        assertThat(pageSource)
                .as("Search should have been triggered and results visible")
                .containsIgnoringCase("DocSearch");
    }

    @Then("the results should contain {string}")
    public void theResultsShouldContain(String expectedText) {
        String pageSource = BrowseTheWeb.as(user).getDriver().getPageSource();
        assertThat(pageSource)
                .as("Search results page should contain '%s'", expectedText)
                .containsIgnoringCase(expectedText);
    }
}
