package com.qa.prueba.stepdefinitions;

import com.qa.prueba.screenplay.models.CreateUserResponse;
import com.qa.prueba.screenplay.models.UpdateUserResponse;
import com.qa.prueba.screenplay.models.UserListResponse;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiStepDefinitions {

    private static final String BASE_URL = "https://reqres.in/api";
    private Response response;

    @When("the user requests the list of users on page 2")
    public void theUserRequestsTheListOfUsers() {
        response = SerenityRest
                .given()
                    .baseUri(BASE_URL)
                    .header("Content-Type", "application/json")
                .when()
                    .get("/users?page=2")
                .then()
                    .extract().response();
    }

    @Then("the API response status should be {int}")
    public void theApiResponseStatusShouldBe(int expectedStatus) {
        assertThat(response.getStatusCode())
                .as("Response status code should be %d", expectedStatus)
                .isEqualTo(expectedStatus);
    }

    @Then("the response should contain a list of users")
    public void theResponseShouldContainAListOfUsers() {
        UserListResponse userList = response.as(UserListResponse.class);
        assertThat(userList.getData())
                .as("User list should not be empty")
                .isNotEmpty();
    }

    @Then("each user should have id, email, first_name and last_name fields")
    public void eachUserShouldHaveRequiredFields() {
        UserListResponse userList = response.as(UserListResponse.class);
        userList.getData().forEach(user -> {
            assertThat(user.getId()).as("User id should not be 0").isNotZero();
            assertThat(user.getEmail()).as("User email should not be blank").isNotBlank();
            assertThat(user.getFirst_name()).as("User first_name should not be blank").isNotBlank();
            assertThat(user.getLast_name()).as("User last_name should not be blank").isNotBlank();
        });
    }

    @When("the user creates a new user with name {string} and job {string}")
    public void theUserCreatesANewUser(String name, String job) {
        String body = String.format("{\"name\": \"%s\", \"job\": \"%s\"}", name, job);
        response = SerenityRest
                .given()
                    .baseUri(BASE_URL)
                    .header("Content-Type", "application/json")
                    .body(body)
                .when()
                    .post("/users")
                .then()
                    .extract().response();
    }

    @Then("the response should contain the created user name {string}")
    public void theResponseShouldContainCreatedUserName(String expectedName) {
        CreateUserResponse created = response.as(CreateUserResponse.class);
        assertThat(created.getName())
                .as("Created user name should be '%s'", expectedName)
                .isEqualTo(expectedName);
    }

    @Then("the response should contain the created user job {string}")
    public void theResponseShouldContainCreatedUserJob(String expectedJob) {
        CreateUserResponse created = response.as(CreateUserResponse.class);
        assertThat(created.getJob())
                .as("Created user job should be '%s'", expectedJob)
                .isEqualTo(expectedJob);
    }

    @Then("the response should contain an id and a createdAt timestamp")
    public void theResponseShouldContainIdAndCreatedAt() {
        CreateUserResponse created = response.as(CreateUserResponse.class);
        assertThat(created.getId()).as("Created user id should not be blank").isNotBlank();
        assertThat(created.getCreatedAt()).as("Created user createdAt should not be blank").isNotBlank();
    }

    @When("the user updates user {int} with name {string} and job {string}")
    public void theUserUpdatesUser(int userId, String name, String job) {
        String body = String.format("{\"name\": \"%s\", \"job\": \"%s\"}", name, job);
        response = SerenityRest
                .given()
                    .baseUri(BASE_URL)
                    .header("Content-Type", "application/json")
                    .body(body)
                .when()
                    .put("/users/" + userId)
                .then()
                    .extract().response();
    }

    @Then("the response should contain the updated name {string}")
    public void theResponseShouldContainUpdatedName(String expectedName) {
        UpdateUserResponse updated = response.as(UpdateUserResponse.class);
        assertThat(updated.getName())
                .as("Updated user name should be '%s'", expectedName)
                .isEqualTo(expectedName);
    }

    @Then("the response should contain the updated job {string}")
    public void theResponseShouldContainUpdatedJob(String expectedJob) {
        UpdateUserResponse updated = response.as(UpdateUserResponse.class);
        assertThat(updated.getJob())
                .as("Updated user job should be '%s'", expectedJob)
                .isEqualTo(expectedJob);
    }

    @Then("the response should contain an updatedAt timestamp")
    public void theResponseShouldContainUpdatedAt() {
        UpdateUserResponse updated = response.as(UpdateUserResponse.class);
        assertThat(updated.getUpdatedAt())
                .as("updatedAt timestamp should not be blank")
                .isNotBlank();
    }
}
