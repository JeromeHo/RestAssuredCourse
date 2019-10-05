import config.EndPoint;
import config.TestConfig;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.*;

public class MyFirstTest extends TestConfig {

    @Test
    public void getGameId1() {
        given().
                //hardcode the get endpoint
                        when().get("http://localhost:8080/app/videogames/1").
                then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void myFirstTest() {
        given().
                spec(videoGame_requestSpec).
        when().
                get("videogames/1").
        then().
                statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getAllGames(){
        given().
                spec(videoGame_requestSpec).
        when().
                get(EndPoint.VIDEOGAMES).
        then().
                statusCode(HttpStatus.SC_OK);
    }

}
