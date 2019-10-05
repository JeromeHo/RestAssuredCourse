package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static org.hamcrest.Matchers.lessThan;

public class TestConfig {

    private static final String BASE_URI = "http://localhost";
    private static final int PORT = 8080;
    private static final String BASE_URL = "/app/";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String ACCCEPT = "Accept";
    private static final String HEADER_VALUE = "application/json";


    public static RequestSpecification videoGame_requestSpec;
    public static RequestSpecification football_requestSpec;

    @BeforeAll
    public static void setup(){

        RestAssured.proxy("localhost", 8888); //comment out if not running tests through a proxy

        RestAssured.baseURI= BASE_URI;
        RestAssured.port= PORT;
        RestAssured.basePath= BASE_URL;

        videoGame_requestSpec = new RequestSpecBuilder().
                addHeader(CONTENT_TYPE, HEADER_VALUE).
                addHeader(ACCCEPT, HEADER_VALUE).
                build();
        RestAssured.requestSpecification = videoGame_requestSpec;

        football_requestSpec = new RequestSpecBuilder().
                setBaseUri("https://api.football-data.org").
                setBasePath("/v2/").
                addHeader("X-Auth-Token", "ebe769fdccd946d18a6c4dc045ba83a9").
                addHeader("X-Response-Control", "minified").
                //addHeader("Content-Type", "application/json").
                //addHeader("Accept", "application/json").
                build();
        RestAssured.requestSpecification = football_requestSpec;

        ResponseSpecification responseSpec = new ResponseSpecBuilder().
                //validate the response is JSON format
                expectContentType(ContentType.JSON).
                expectResponseTime(lessThan(2000L)).
                build();
        RestAssured.responseSpecification = responseSpec;
    }

    @AfterEach
    public void cleanUpEach() {
        System.out.println("AfterEach cleanUpEach() method called");
    }

    @AfterAll
    public static void cleanUp() {
        System.out.println("AfterAll cleanUP() method called after all tests");
    }
}
