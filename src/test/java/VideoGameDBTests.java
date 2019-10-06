import config.EndPoint;
import config.TestConfig;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class VideoGameDBTests extends TestConfig {

    @Test
    public void getAllGames(){
        given().
                spec(videoGame_requestSpec).
        when().get(EndPoint.VIDEOGAMES).
        then().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getSingleGame(){
        given().
                spec(videoGame_requestSpec).
                pathParam("videoGameId", 5).
        when().
                get(EndPoint.SINGLE_VIDEOGAME).
        then().
                statusCode(HttpStatus.SC_OK);
    }

    //send a POST of java object converted to JSON
    @Test
    public void testVideoGameSerialisationByJson() {
        VideoGame videoGame = new VideoGame("89", "2014-06-06", "Halo 5", "90","11","Mature");
            given().
                    spec(videoGame_requestSpec).
                    body(videoGame).
            when().
                    post(EndPoint.VIDEOGAMES).
            then().
                    statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void validateByJsonSchema() {
        given().
                spec(videoGame_requestSpec).
                pathParam("videoGameId", 2).
                when().
                get(EndPoint.SINGLE_VIDEOGAME).
                then().
                body(matchesJsonSchemaInClasspath("VideoGameJsonSchema.json"));
    }
}
