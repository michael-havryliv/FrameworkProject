package tests.api;

import config.EndPointsConfig;
import config.EnvConfig;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.api.DataPOJO;
import models.api.SmallUserPOJO;
import models.api.UserPOJO;
import models.api.UsersPOJO;
import org.jruby.RubyProcess;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class SimpleTests{

    @BeforeAll
    static void setUp(){
        RestAssured.baseURI = EnvConfig.URL_API;
    }

    @Test
    public void checkIfUsersHaveTheSameDomainTest(){
        UsersPOJO users = RestAssured.given().contentType(ContentType.JSON)
                .when().get(EndPointsConfig.getUsers).then().statusCode(200).extract().as(UsersPOJO.class);
        //is that a good way to check status code like that?
        List<String> emails = users.getData().stream().map(DataPOJO::getEmail).collect(Collectors.toList());
        for(String email : emails) Assertions.assertTrue(email.contains("reqres.in"),"Email domain is not as expected");
    }

    @Test
    public void checkIfSingleUserHasExpectedParametersTest(){
        UserPOJO user = RestAssured.given().contentType(ContentType.JSON)
                .when().get(EndPointsConfig.singleUser).then().statusCode(200).extract().as(UserPOJO.class);
        Assertions.assertEquals(2, user.getData().getId(),"User ID is not as expected");
        Assertions.assertEquals("Janet", user.getData().getFirst_name(),"User First Name is not as expected");
        Assertions.assertEquals("Weaver", user.getData().getLast_name(),"User Last Name is not as expected");
        Assertions.assertEquals("https://reqres.in/img/faces/2-image.jpg", user.getData().getAvatar(),"User Avatar is not as expected");
    }

    @Test
    public void checkThatUserIsNotFound(){
        Response response = get(EndPointsConfig.singleUserNotFound);
        Assertions.assertEquals(404,response.getStatusCode(),"Status code is different from expected");
    }

    @Test
    public void postTest(){
        SmallUserPOJO user = new SmallUserPOJO();
        user.setName("morpheus");
        user.setJob("leader");
        given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(user).when().post(EndPointsConfig.postUsers)
                .then().statusCode(201).log().all();
    }

    @Test
    public void putTest(){
        SmallUserPOJO user = new SmallUserPOJO();
        user.setName("morpheus");
        user.setJob("leader");
        given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(user).when().put(EndPointsConfig.postUsers)
                .then().statusCode(200).log().all();
    }

    @Test
    public void deleteTest(){
        given().
                contentType(ContentType.JSON).accept(ContentType.JSON).
                delete(EndPointsConfig.singleUser).
        then().
            statusCode(204).
            log().all();
    }
}
