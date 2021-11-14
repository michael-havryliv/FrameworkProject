package tests.api;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class SimpleTests {

    @Test
    public void getTest1(){
        Response response = get("https://reqres.in/api/users?page=2");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
        System.out.println(response.asString());
        System.out.println(response.getStatusLine());

        int statusCode = response.getStatusCode();
        Assertions.assertEquals(statusCode,200);
    }

    @Test
    public void getTest2(){
        given().
                get("https://reqres.in/api/users?page=2").
                then().
                statusCode(200).
                body("data.id[0]",equalTo(7)).
                body("data.firstname",hasItem("Michael"));
    }

    @Test
    public void postTest1(){

        JSONObject request = new JSONObject();

        request.put("name","Sasha");
        request.put("job","Farmer");

        given().body(request.toJSONString()).
                when().
                    post("https://reqres.in/api/users").
                then().
                    statusCode(201);

    }

    @Test
    public void postTest2(){

        Map<String, Object> map = new HashMap<>();
        map.put("name", "Karl");
        map.put("job", "Teacher");
        map.put("age", 26);

        JSONObject request = new JSONObject(map);

        given().body(request.toJSONString()).
                when().
                    post("https://reqres.in/api/users").
                then().
                    statusCode(201);
    }

    @Test
    public void putTest(){
        JSONObject request = new JSONObject();

        request.put("name","Sasha");
        request.put("job","Farmer");

        given().
                body(request.toJSONString()).
                header("Content-Type", "application/json").

        when().
                put("https://reqres.in/api/users/2").
        then().
                statusCode(200).
                log().all();

    }

    @Test
    public void deleteTest(){
        given().
                delete("https://reqres.in/api/users/2").
        then().
            statusCode(204).
            log().all();
    }


}
