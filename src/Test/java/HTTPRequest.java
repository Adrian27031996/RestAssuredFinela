import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;


import static io.restassured.RestAssured.*;
public class HTTPRequest {
    int id;
    @Test (priority = 1)
    void getUsers(){
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .log().all();

    }

    @Test (priority = 2)
    void getUser(){
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .log().all();

    }

    @Test (priority = 3)
    void createUser(){
        JSONObject data = new JSONObject();
        data.put("name", "morpheus");
        data.put("job", "leader");
        id=given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
                //.then()
                //.statusCode(201)
                //.log().all();

    }

    @Test (priority = 4)
    void updateUser(){

        JSONObject data = new JSONObject();
        data.put("name", "Adrian");
        data.put("job", "QA");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users"+id)
                .then()
                .statusCode(201)
                    .log().all();
    }

    @Test (priority = 5)
    void deleteUser(){

        given()
                .when()
                .delete("https://reqres.in/api/users"+id)
                .then()
                .statusCode(204)
                .log().all();
    }


}
