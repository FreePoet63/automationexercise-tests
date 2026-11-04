package com.automationexercise.hooks;

import com.automationexercise.api.UserApi;
import com.automationexercise.config.Config;
import com.automationexercise.context.ScenarioContext;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class Hooks {

    private final ScenarioContext context;

    public Hooks(ScenarioContext context) {
        this.context = context;
    }

    @Before
    public void setUp() {
        Configuration.baseUrl = Config.get("base.url");
        Configuration.browser = Config.get("browser");
        Configuration.headless = Boolean.parseBoolean(Config.get("headless"));
        Configuration.timeout = Long.parseLong(Config.get("timeout"));
        Configuration.browserSize = "1920x1080";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--host-resolver-rules="
                + "MAP googleads.g.doubleclick.net 127.0.0.1,"
                + "MAP pagead2.googlesyndication.com 127.0.0.1,"
                + "MAP securepubads.g.doubleclick.net 127.0.0.1,"
                + "MAP tpc.googlesyndication.com 127.0.0.1,"
                + "MAP adservice.google.com 127.0.0.1,"
                + "MAP www.googletagservices.com 127.0.0.1");
        Configuration.browserCapabilities = options;

        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(false).includeSelenideSteps(false));
    }

    @After
    public void tearDown() {
        SelenideLogger.removeListener("AllureSelenide");
        closeWebDriver();

        if (context.isApiUserCreated()) {
            try {
                UserApi.delete(context.getUser());
            } catch (RuntimeException e) {
                System.err.println("Cleanup failed for " + context.getUser().email() + ": " + e.getMessage());
            }
        }
    }
}