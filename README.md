# AutomationExercise UI Tests

UI test automation framework for [automationexercise.com](https://automationexercise.com), a public demo e-commerce site built for practicing test automation.

Tests are written in Gherkin (BDD) and run with Cucumber on top of Selenide. Reports are published to GitHub Pages with Allure, including run history.

**Live report:** [Actual Allure report](https://freepoet63.github.io/automationexercise-tests/)

## Tech stack

- Java 17
- Selenide (browser automation)
- Cucumber 7 + JUnit Platform Suite (BDD runner)
- PicoContainer (dependency injection for step classes)
- Allure Report (reporting with screenshots and history)
- Maven
- GitHub Actions + GitHub Pages (CI and report hosting)

## Test scenarios

| Feature | Scenario |
|---|---|
| Registration | Register a new user and delete the account |
| Registration | Sign up with an already registered email |
| Login | Login with valid credentials and logout |
| Login | Login with a wrong password (Scenario Outline, 3 examples) |
| Login | Login with an unregistered email |
| Product search | Search for a product by name (Scenario Outline, 3 examples) |
| Product search | Search for a product that does not exist |
| Cart | Add a product to the cart |
| Cart | Add two different products to the cart |
| Cart | Cart total is price multiplied by quantity |
| Cart | Remove a product from the cart |
| Checkout | Place an order as a registered user |
| Checkout | Guest is asked to register or log in at checkout |

## Project structure

```
src/test
├── java/com/automationexercise
│   ├── api/        UserApi: test data setup and cleanup through the site's API
│   ├── config/     Config: reads config.properties, system properties override it
│   ├── context/    ScenarioContext: state shared between steps in one scenario
│   ├── hooks/      Hooks: Selenide + Allure setup, browser teardown, data cleanup
│   ├── models/     User: test user with a unique generated email
│   ├── pages/      Page Objects (HomePage, LoginPage, SignupPage, AccountResultPage,
│   │               ProductsPage, CartPage)
│   ├── runner/     RunCucumberTest: JUnit Platform Suite entry point
│   └── steps/      Step definitions (AuthSteps, LoginSteps, ProductSteps, CartSteps)
└── resources
    ├── features/   Gherkin feature files
    ├── config.properties
    ├── cucumber.properties
    └── allure.properties
```

## Design notes

- **Page Object pattern.** Locators and page actions live in `pages/`, steps contain no locators.
- **Independent scenarios.** Each scenario creates its own user with a unique email, so tests do not depend on each other and can run in any order.
- **No credentials in scenarios.** Passwords are generated for every test user and never appear in feature files or in the repository. Scenarios describe behavior (for example "a wrong password"), not values.
- **Fast test data via API.** Preconditions such as "a registered user exists" are created through the site's API instead of the UI, and the user is deleted in an `@After` hook.
- **Flaky test handling.** The public demo site is sometimes overloaded or shows ad overlays, so failed scenarios are retried once (`rerunFailingTestsCount` in Surefire). A `Flakes` entry in the Maven summary means a scenario passed only on retry.

## Prerequisites

- JDK 17
- Maven 3.9+
- Google Chrome

Selenide downloads the matching driver automatically.

## Running the tests

Run all tests:

```
mvn clean test
```

Run without a visible browser window:

```
mvn clean test -Dheadless=true
```

Run by tag:

```
mvn clean test -Dcucumber.filter.tags="@smoke"
```

## Configuration

Defaults are in `src/test/resources/config.properties`:

| Property | Default | Description |
|---|---|---|
| `base.url` | `https://automationexercise.com` | Site under test |
| `browser` | `chrome` | Browser name |
| `headless` | `false` | Run without a window |
| `timeout` | `10000` | Selenide wait timeout, ms |

Any of them can be overridden from the command line: `-Dbrowser=firefox`.

## Allure report

View the report locally:

```
mvn clean test
mvn allure:serve
```

`allure:serve` starts a temporary local server and opens the report in the browser. Raw results are written to `target/allure-results`.

The report contains Cucumber steps, a screenshot for every failed step and, for a Scenario Outline, a separate entry for each example row.

## CI

The workflow in `.github/workflows/tests.yml`:

1. Runs the tests on every push to `master`, on pull requests and on manual start.
2. Restores Allure history from the `gh-pages` branch.
3. Builds the Allure report and publishes it to GitHub Pages (from `master` only).
4. Uploads raw Allure results as a workflow artifact.
5. Fails the job if any test failed, after the report is published.

To enable the report site: **Settings → Pages → Source: Deploy from a branch → `gh-pages` / root**.

## Notes

The tested site is a public demo shared by many users. Occasional failures such as "queue full" pages or ad overlays come from the site, not from the tests. Ad domains are blocked in the browser options to reduce this.
