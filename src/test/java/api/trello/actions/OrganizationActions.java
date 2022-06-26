package api.trello.actions;

import api.trello.base.BaseLibrary;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class OrganizationActions extends BaseLibrary {

    String organizationName = "MyFirstOrganization";

    public OrganizationActions createOrganization() {

        HashMap<String, String> params = new HashMap<>();
        params.put("displayName", organizationName);

        Response response = callPostService(params, "organizations");

        String organizationId = getValueOfParam(response, "id");

        Assert.assertTrue(getValueOfParam(response, "displayName").equals(organizationName));

        return this;
    }
}
