package StepDefinitions;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import PageObjects.HomePageObjects;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Homepage {

    WebDriver driver;
    HomePageObjects homePageObjects;

    // Zero-argument constructor required by Cucumber
    public Homepage() {
    }

    @Given("User is on home page")
    public void user_is_on_home_page() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        driver.get("https://www.amazon.in/");
        homePageObjects = new HomePageObjects(driver); // Initialize HomePageObjects with the driver
        String title = homePageObjects.getHomePageTitle();
        System.out.println(title);
    }

    @And("Mouse hover on Hello Signin")
    public void mouse_hover_on_hello_signin() throws InterruptedException {
    	Thread.sleep(7000);
        homePageObjects.hoverOnHello(); // Use instance method
    }

    @Then("Signin button should display")
    public void signin_button_should_display() {
        homePageObjects.validateSigninBtn(); // Use instance method
    }
}
