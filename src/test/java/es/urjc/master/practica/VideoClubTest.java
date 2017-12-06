package es.urjc.master.practica;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import es.urjc.master.practica.configurations.DatabaseConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {VideoClubApp.class, DatabaseConfiguration.class})
@WebAppConfiguration
public class VideoClubTest {
	
	@Autowired
	private WebApplicationContext context;
  
	private WebDriver driver;
	
	@Before
	public void initalize() {
		MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		driver = MockMvcHtmlUnitDriverBuilder.mockMvcSetup(mockMvc).build();
//		driver = new FirefoxDriver();
	}
	
	@Test 
	public void testViewLogin() {
		checkRootPage();
		login();
//		checkLogged();
	}
	
	private void checkRootPage() {
		driver.get("http://localhost:8080/");
		
		WebElement user = driver.findElement(By.id("user"));
		assertTrue(user.isDisplayed());		
		
		WebElement password = driver.findElement(By.id("password"));
		assertTrue(password.isDisplayed());
	}
	
	private void login() {
		driver.get("http://localhost:8080/");

		WebElement user = driver.findElement(By.id("user"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement login = driver.findElement(By.id("login"));		

		user.sendKeys("root");
		password.sendKeys("root1");
		login.click();
	}
	
	private void checkLogged() {		
		WebElement search = driver.findElement(By.id("search"));
		assertTrue(search.isDisplayed());
	}
}
