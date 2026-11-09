package com.prasanth.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.prasanth.base.BaseTest;
import com.prasanth.pages.LoginPage;
import com.prasanth.pages.ProductsPage;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void validLoginTest() {

        LoginPage login = new LoginPage(driver);

        login.login("standard_user", "secret_sauce");

        ProductsPage products = new ProductsPage(driver);

        Assert.assertEquals(
            products.getPageTitle(),
            "Products"
        );
    }

    @Test(priority = 2)
    public void invalidLoginTest() {

        LoginPage login = new LoginPage(driver);

        login.login("invalid_user", "wrong_password");

        Assert.assertTrue(
            login.getErrorMessage().contains(
                "Username and password do not match"
            )
        );
    }

    @Test(priority = 3)
    public void lockedUserTest() {

        LoginPage login = new LoginPage(driver);

        login.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(
            login.getErrorMessage().contains(
                "Sorry, this user has been locked out"
            )
        );
    }
}
