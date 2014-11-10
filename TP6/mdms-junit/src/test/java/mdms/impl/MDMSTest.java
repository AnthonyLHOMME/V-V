package mdms.impl;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import mdms.AbstractMDMSTest;

public class MDMSTest extends AbstractMDMSTest {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /* The title of the welcome page is “MdMS - Diversify” */
    @Test
    public void testTitle() throws Exception {
        driver.get(baseUrl);
        try {
            assertEquals("MdMS - Diversify", driver.getTitle());
        } catch (Error e) {
            verificationErrors.append(e.toString());
        }
    }

    /* When logged-in, the user name is visible in the top-left corner */
    @Test
    public void testLoginUsername() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.name("login")).clear();
        driver.findElement(By.name("login")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.btn.btn-success")).click();
        driver.findElement(By.linkText("I do not want to wait")).click();
        assertTrue(isElementPresent(By.linkText("admin")));
    }

    /* You can log-in with some existing user and password. */
    @Test
    public void testLoginOk() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.name("login")).clear();
        driver.findElement(By.name("login")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.btn.btn-success")).click();
        driver.findElement(By.linkText("I do not want to wait")).click();
        assertTrue(isElementPresent(By.linkText("Sign out")));
    }

    /* You cannot log-in with the wrong password. */
    @Test
    public void testLoginWrongPassword() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.name("login")).clear();
        driver.findElement(By.name("login")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("WRONG_PASSWORD");
        driver.findElement(By.cssSelector("button.btn.btn-success")).click();
        assertTrue(isElementPresent(By.cssSelector(".alert.alert-danger")));
        assertEquals("×\nWrong login and/or password", driver.findElement(By.cssSelector(".alert.alert-danger")).getText());
        assertTrue(isElementPresent(By.id("signin-form")));
    }

    /* You cannot log-in with a user name that doesn’t exist. */
    @Test
    public void testLoginWrongUsername() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.name("login")).clear();
        driver.findElement(By.name("login")).sendKeys("WRONG_USERNAME");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.btn.btn-success")).click();
        assertTrue(isElementPresent(By.cssSelector(".alert.alert-danger")));
        assertEquals("×\nWrong login and/or password", driver.findElement(By.cssSelector(".alert.alert-danger")).getText());
        assertTrue(isElementPresent(By.id("signin-form")));
    }

    /* Someone not logged in cannot edit an article. */
    @Test
    public void testEditNotLoggedIn() throws Exception {
        driver.get(baseUrl);
        WebElement editButton = driver.findElement(By.linkText("Edit"));
        String id_article = "/" + editButton.getAttribute("href").replace(baseUrl, "");
        editButton.click();
        assertTrue(isElementPresent(By.cssSelector(".alert.alert-warning")));
        assertEquals("×\nYou are not allowed to access " + id_article + " resource", driver.findElement(By.cssSelector(".alert.alert-warning")).getText());
        assertEquals(baseUrl, driver.getCurrentUrl());
    }

    /* You can write an article - No title and no content */
    @Test
    public void testWriteArticleNoTitleNoContent() throws Exception {
        // Login
        driver.get(baseUrl);
        driver.findElement(By.name("login")).clear();
        driver.findElement(By.name("login")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.btn.btn-success")).click();
        driver.findElement(By.linkText("I do not want to wait")).click();
        // Add article Button click
        driver.findElement(By.linkText("Add article")).click();
        // Save click (no content on the article and the title)
        driver.findElement(By.id("save")).click();
        // Detect Warning Box
        assertTrue(isElementPresent(By.cssSelector(".alert.alert-warning")));
        assertEquals("×\nWarning! You must give a title and some content in order to add an article :)", driver.findElement(By.cssSelector(".alert.alert-warning")).getText());
        // Verify that the URL is not the base url, so is the edit page yet
        assertNotEquals(baseUrl, driver.getCurrentUrl());
    }

    /* You can write an article - No content but with a title */
    @Test
    public void testWriteArticleNoContentWithTitle() throws Exception {
        // Login
        driver.get(baseUrl);
        driver.findElement(By.name("login")).clear();
        driver.findElement(By.name("login")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.btn.btn-success")).click();
        driver.findElement(By.linkText("I do not want to wait")).click();
        // Add article Button click
        driver.findElement(By.linkText("Add article")).click();
        // Add title
        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("title")).sendKeys("Super Article");
        // Save click (no content on the article and the title)
        driver.findElement(By.id("save")).click();
        // Detect Warning Box
        assertTrue(isElementPresent(By.cssSelector(".alert.alert-warning")));
        assertEquals("×\nWarning! You must give a title and some content in order to add an article :)", driver.findElement(By.cssSelector(".alert.alert-warning")).getText());
        // Verify that the URL is not the base url, so is the edit page yet
        assertNotEquals(baseUrl, driver.getCurrentUrl());
    }


    /* QUESTION 3.a.4, problème de ElementNotVisibleException en Java (fonctionne via l'IDE Firefox)
     * Meme soucis pour 3.a.3
     */

    /* You can write an article - Element is added on the main page after validation */
    @Test
    public void testWriteArticleVerifyMainPage() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.name("login")).clear();
        driver.findElement(By.name("login")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.btn.btn-success")).click();
        driver.findElement(By.linkText("I do not want to wait")).click();
        driver.findElement(By.linkText("Add article")).click();
        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("title")).sendKeys("Nouvel Article");
        // TODO - ElementNotVisibleException: Element is not currently visible and so may not be interacted with
        driver.findElement(By.cssSelector("div > textarea")).clear();
        driver.findElement(By.cssSelector("div > textarea")).sendKeys("Here is the new article ;-)");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.id("save")).click();
        // TODO - Check if the we're in the main page and if the article is present
    }

    /* You can erase an article */
    @Test
    public void testDeleteArticle() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.name("login")).clear();
        driver.findElement(By.name("login")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.cssSelector("button.btn.btn-success")).click();
        driver.findElement(By.linkText("I do not want to wait")).click();
        driver.findElement(By.linkText("Delete")).click(); // Select the first (Experimental App)
        assertEquals("×\nArticle \"article_2\" deleted successfully", driver.findElement(By.xpath("//body/div[2]/div")).getText());
        assertEquals("Diversify Video\nDelete\nEdit", driver.findElement(By.xpath("//body/div[2]/div[2]/div/h2")).getText());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}