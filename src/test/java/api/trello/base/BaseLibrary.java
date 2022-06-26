package api.trello.base;

import api.trello.data.GetData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class BaseLibrary {

    public static RequestSpecBuilder reqBuilder;
    public static RequestSpecification reqSpec;

    public BaseLibrary() {
        reqBuilder = new RequestSpecBuilder();
        reqBuilder.setBaseUri(GetData.baseUrl);
        reqBuilder.setContentType(ContentType.JSON);
        reqBuilder.addQueryParam("key", GetData.key);
        reqBuilder.addQueryParam("token", GetData.token);
        reqSpec = reqBuilder.build().log().all();
    }

    public Response callPostService(HashMap<String, String> params, String endpoint) {

        Response response = given()
                .spec(reqSpec)
                .queryParams(params)
                .when()
                .post(endpoint)
                .then().log().body()
                .statusCode(200)
                .extract()
                .response();

        return response;
    }

    public Response callUpdateService(HashMap<String, String> params, String endpoint, String id) {

        Response response = given()
                .spec(reqSpec)
                .queryParams(params)
                .when()
                .put(endpoint + "/" + id)
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();

        return response;
    }

    public Response callDeleteServis(String endpoint, String id) {

        return given()
                .spec(reqSpec)
                .when()
                .delete(endpoint + "/" + id)
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();
    }

    public String getValueOfParam(Response response, String param) {
        JsonPath jsonpath = new JsonPath(response.asString());
        return jsonpath.getString(param);
    }


}
