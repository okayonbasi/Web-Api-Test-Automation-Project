package api.trello.actions;

import api.trello.base.BaseLibrary;

import static api.trello.data.GetData.*;

import io.restassured.response.Response;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TrelloActions extends BaseLibrary {


    ArrayList<HashMap<String, String>> cardList = new ArrayList<>();

    String organizationId;
    String boardId;
    String listId;

    public TrelloActions createOrganization(String organizationName) {

        HashMap<String, String> params = new HashMap<>();
        params.put("displayName", organizationName);

        Response response = callPostService(params, organizationEndpoint);
        Assert.assertTrue(getValueOfParam(response, "displayName").equals(organizationName));
        organizationId = getValueOfParam(response, "id");

        return this;

    }

    public TrelloActions createABoard(String boardName) {

        HashMap<String, String> params = new HashMap<>();
        params.put("name", boardName);
        params.put("idOrganization", organizationId);

        Response response = callPostService(params, boardEndpoint);
        Assert.assertTrue(getValueOfParam(response, "name").equals(boardName));
        boardId = getValueOfParam(response, "id");

        return this;
    }


    public TrelloActions createListInBoard(String listName) {

        HashMap<String, String> params = new HashMap<>();
        params.put("name", listName);
        params.put("idBoard", boardId);

        Response response = callPostService(params, listEndpoint);
        Assert.assertTrue(getValueOfParam(response, "name").equals(listName));
        listId = getValueOfParam(response, "id");

        return this;

    }

    public TrelloActions createTwoCard() {

        for (int i = 0; i < 2; i++) {
            String cardName = "Created card " + i;
            String cardId;

            HashMap<String, String> params = new HashMap<>();
            params.put("name", cardName);
            params.put("idList", listId);

            Response response = callPostService(params, cardEndpoint);
            Assert.assertTrue(getValueOfParam(response, "name").equals(cardName));
            cardId = getValueOfParam(response, "id");

            HashMap<String, String> card = new HashMap<>();
            card.put("cardId", cardId);
            cardList.add(card);

        }
        return this;

    }

    public TrelloActions updateRandomCard() {

        Random random = new Random();
        int num = random.nextInt(cardList.size());

        HashMap<String, String> params = new HashMap<>();
        params.put("name", "This card is updated");

        Response response = callUpdateService(params, cardEndpoint, cardList.get(num).get("cardId"));
        Assert.assertTrue(getValueOfParam(response, "name").equals("This card is updated"));

        return this;
    }

    public TrelloActions deleteCards() {

        for (int i = 0; i < cardList.size(); i++) {
            callDeleteServis(cardEndpoint, cardList.get(i).get("cardId"));
        }
        return this;
    }

    public TrelloActions deleteBoard() {
        callDeleteServis(boardEndpoint, boardId);
        return this;
    }

    public TrelloActions deleteOrganization() {
        callDeleteServis(organizationEndpoint, organizationId);
        return this;
    }


}
