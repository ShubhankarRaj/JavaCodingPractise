import java.util.Date;
import com.thoughtworks.selenium.*;
import org.testng.Reporter;
import org.testng.annotations.*;
import testlink.api.java.client.TestLinkAPIResults;
public class Selenium_Methods implements IConstantes {
	Selenium selenium;
	@Parameters({"selenium.host","selenium.port","selenium.browser","selenium.url"})
	@BeforeMethod(alwaysRun=true)
	public void startBrowser(String host,String port,String browser,String url){
		this.selenium= new DefaultSelenium(host, Integer.parseInt(port), browser, url);
		this.selenium.start("captureNetworkTraffic=true, addCustomRequestHeader=true,captureNetworkTraffic=true");
		this.selenium.open(url);
	} //startBrowser();
	@Test public void limitedSearch() throws Exception {
		String resultado = null;
		String nota = null;
		try
		{
			selenium.open("/");
			selenium.click("link=Login");
			selenium.waitForPageToLoad("30000");
			selenium.type("login", "bala");
			selenium.type("password", "krishna");
			selenium.click("Submit");
			selenium.waitForPageToLoad("30000");
			if(selenium.isTextPresent("Welcome balakrishna"))
				Reporter.log("Successfully log in");
			else
				Reporter.log("Error on page");
			selenium.click("link=Limited Search Member");
			selenium.waitForPageToLoad("30000");
			selenium.type("email", "10");
			selenium.click("//input[@type='submit']");
			selenium.waitForPageToLoad("30000");
			selenium.click("link=Logout");
			selenium.waitForPageToLoad("30000");
			resultado = TestLinkAPIResults.TEST_PASSED;
		}
		catch (Exception e) {
			resultado = TestLinkAPIResults.TEST_FAILED;
			nota = e.getMessage();
			e.printStackTrace();
		}
		finally {
			ResultadoExecucao.reportTestCaseResult1(PROJETO, PLANO, CASO_TESTE1, BUILD, nota, resultado);
		}
	}
	@AfterMethod(alwaysRun=true)
	public void stopSelenium() {
		Date dt;
		dt = new Date();
		System.out.println("Execution Terminated at "+ dt);
		this.selenium.stop();
	} //stopSelenium();
}
