package org.example;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import org.example.payload.UserCredentialsJSON;

import java.io.File;

import static io.restassured.RestAssured.given;

/**
 * Created By: Sambhav
 * Created On: 24-11-2020 || 12:12 PM
 * Project Name: jira-api-automation
 **/

public class AddAttachment_Test {

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

        //Add attachment
        given()
                .log().all()
                .filter(session)
                .pathParam("id", "10002")
                .header("X-Atlassian-Token", "no-check")
                .header("Content-Type", "multipart/form-data")
                .multiPart("attachment", new File("src/main/java/org/example/attachments/demo.txt"))

                .when()
                .post("/rest/api/2/issue/{id}/attachments")

                .then()
                .assertThat().statusCode(200);
    }
}
