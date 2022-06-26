package com.gittigidiyor.action;

import com.gittigidiyor.base.BaseLibrary;
import com.gittigidiyor.data.GetData;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.gittigidiyor.objectrepo.HomepageObjectRepo.*;

public class HomepageActions extends BaseLibrary {


    public HomepageActions(RemoteWebDriver driver) {
        super(driver);
    }

    public HomepageActions openPage() {
        openUrl(GetData.url);
        return this;
    }
    public HomepageActions searchProduct(String product) throws InterruptedException {
        sendKeys(searchBoxInput, product);
        click(findButton);

        return this;
    }

    public HomepageActions openBasket() {

        click(basketButton);
        return this;
    }
}
