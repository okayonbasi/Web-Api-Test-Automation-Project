package api.trello.test;

import api.trello.actions.TrelloActions;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrelloTests {

    TrelloActions trelloActions;

    @BeforeMethod
    public void beforeMethod() {
        trelloActions = new TrelloActions();
    }

    @Test(enabled = true, description = "TestCase01")
    public void TestCase() {

        String organizationName = "My First Organization";
        String boardName = "My First Board";
        String listName = "My First List";

        trelloActions.
                createOrganization(organizationName).
                createABoard(boardName).
                createListInBoard(listName).
                createTwoCard().
                updateRandomCard().
                deleteCards().
                deleteBoard().
                deleteOrganization();
    }


}
