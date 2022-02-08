package tests.api;

import config.EndPointsConfig;
import config.EnvConfig;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import models.api.DataPOJO;
import models.api.SmallUserPOJO;
import models.api.UserPOJO;
import models.api.UsersPOJO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class SimpleTests{

    private static RequestSpecification spec;

    @BeforeAll
    public static void initSpec(){
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(EnvConfig.URL_API)
                .build();
    }

    @Test
    public void checkIfUsersHaveTheSameDomainTest(){
        UsersPOJO users = RestAssured.given()
                .spec(spec)
                .when().get(EndPointsConfig.getUsers)
                .then().statusCode(200).extract().as(UsersPOJO.class);
        List<String> emails = users.getData().stream().map(DataPOJO::getEmail).collect(Collectors.toList());
        emails.forEach(x -> Assertions.assertTrue(x.contains("reqres.in"),"Email domain is not as expected"));
    }

    @Test
    public void checkIfSingleUserHasExpectedParametersTest(){
        UserPOJO user = RestAssured.given()
                .spec(spec)
                .when().get(EndPointsConfig.singleUser)
                .then().statusCode(200).extract().as(UserPOJO.class);
        Assertions.assertEquals(2, user.getData().getId(),"User ID is not as expected");
        Assertions.assertEquals("Janet", user.getData().getFirst_name(),"User First Name is not as expected");
        Assertions.assertEquals("Weaver", user.getData().getLast_name(),"User Last Name is not as expected");
        Assertions.assertEquals("https://reqres.in/img/faces/2-image.jpg", user.getData().getAvatar(),"User Avatar is not as expected");
    }

    @Test
    public void checkThatUserIsNotFound(){
        given()
                .spec(spec)
                .get(EndPointsConfig.singleUserNotFound)
                .then().statusCode(404).log().all();
    }

    @Test
    public void postTest(){
        SmallUserPOJO user = new SmallUserPOJO();
        user.setName("morpheus");
        user.setJob("leader");
        given()
                .spec(spec)
                .body(user)
                .when().post(EndPointsConfig.postUsers)
                .then().statusCode(201).log().all();
    }

    @Test
    public void putTest(){
        SmallUserPOJO user = new SmallUserPOJO();
        user.setName("morpheus");
        user.setJob("leader");
        given()
                .spec(spec)
                .body(user)
                .when().put(EndPointsConfig.postUsers)
                .then().statusCode(200).log().all();
    }

    @Test
    public void deleteTest(){
        given()
                .spec(spec)
                .delete(EndPointsConfig.singleUser)
                .then().statusCode(204).log().all();
    }
}
