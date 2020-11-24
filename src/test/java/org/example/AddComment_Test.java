package org.example;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import org.example.payload.AddCommentJSON;
import org.example.payload.UserCredentialsJSON;

import static io.restassured.RestAssured.given;

/**
 * Created By: Sambhav
 * Created On: 15-10-2020 || 10:43 PM
 * Project Name: jira-api-automation
 **/

public class AddComment_Test {
    public static void main(String[] args) {

        RestAssured.baseURI = "http://localhost:8080/";

        SessionFilter session = new SessionFilter();

        //For getting the session-id for a particular session.
        String response =
                given()
                        .log().all()
                        .filter(session)
                        .body(UserCredentialsJSON.getCurrentUser())
                        .header("Content-Type", "application/json")

                        .when()
                        .post("rest/auth/1/session")

                        .then()
                        .assertThat().statusCode(200)
                        .extract().response().asString();

        //For Adding a comment based on an issue-id.
        given()
                .log().all()
                .filter(session)
                .pathParam("id", "10002")
                .header("Content-Type", "application/json")
                .body(AddCommentJSON.getCommentInfo())

                .when()
                .post("/rest/api/2/issue/{id}/comment")

                .then()
                .log().all()
                .assertThat().statusCode(201);
    }
}
