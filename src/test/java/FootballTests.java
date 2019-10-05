import config.TestConfig;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class FootballTests extends TestConfig {

    //assert a value in the body of a response
    @Test
    public void getDetailsOfOneArea() {
        given().
                spec(football_requestSpec).
                queryParam("areas", 2072).
        when().get("areas/").
                then().
                body("count", equalTo(1));
    }

    //assert a value in the body of a response
    @Test
    public void getDateFounded() {
        given().
                spec(football_requestSpec).
        when().
                get("teams/57").
        then().
                body("founded", equalTo(1886));
    }

    //assert a value in a array of a body response
    @Test
    public void getFirstTeamName() {
        given().
                spec(football_requestSpec).
        when().
                get("competitions/2021/teams").
        then().
                body("teams.name[0]", equalTo("Arsenal FC"));
    }

    //extract the body of a response as string
    @Test
    public void getAllTeams() {
        String responseAllTeams =
                given().
                        spec(football_requestSpec).
                when().
                        get("competitions/2021/teams").asString();
        System.out.println(responseAllTeams);
    }

    //extract the body of a response as a string
    @Test
    public void getAllTeams_asString() {
        Response allTeams =
                given().
                        spec(football_requestSpec).
                when().
                        get("competitions/2021/teams").
                then().
                        extract().response();
        System.out.println(allTeams.asString());
    }

    //extract all headers or a single header from the response
    @Test
    public void extractHeaders() {
        Response responseAllTeams =
                given().
                        spec(football_requestSpec).
                when().
                        get("competitions/2021/teams").
                then().
                        extract().response();
        //all headers
        Headers headers = responseAllTeams.getHeaders();
        System.out.println(headers);
        //single header "Date"
        String date = responseAllTeams.getHeader("Date");
        System.out.println(date);
    }

    //extract first team name from response
    @Test
    public void extractFirstTeamName() {
        String firstTeamname =
                given().
                        spec(football_requestSpec).
                when().
                        get("competitions/2021/teams").path("teams.name[0]").toString();
        System.out.println(firstTeamname);
    }

    //extract all team names in a list
    @Test
    public void extractAllTeamNames() {
        Response response =
                given().
                        spec(football_requestSpec).
                when().
                        get("competitions/2021/teams").
                then().
                        extract().response();

        List<String> teamNames = response.path("teams.name");

        for (String teamName : teamNames) {
            System.out.println(teamName);
        }
    }


}
