package myPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.*;

import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

public class myClass implements iConstantes{


	public static void main(String[] args) throws TestLinkAPIException {
		String resultado = null;
		String nota = null;
		// TODO Auto-generated method stub
		try{
			WebDriver ffDriver = new FirefoxDriver();
			ffDriver.get("http://www.google.com");
			ffDriver.navigate().to("http://www.yahoo.com");
			ffDriver.navigate().refresh();
			ffDriver.navigate().back();
			ffDriver.navigate().refresh();
			ffDriver.navigate().forward();
			//ffDriver.close();

			ffDriver.get("http://selenium.googlecode.com/svn/trunk/docs/api/java/index.html");
			ffDriver.switchTo().frame("classFrame");
			ffDriver.findElement(By.linkText("Deprecated")).click();

			//		ffDriver.switchTo().alert().accept();
			//		ffDriver.manage().timeouts().implicitlyWait(12,TimeUnit.SECONDS);
			//		
			//		WebDriverWait ffWait = new WebDriverWait(ffDriver, 10);
			//		ffWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
			//		ffDriver.findElement(By.id("username")).sendKeys("tutorial");

			ffDriver.close();
			System.out.println("This is the end of program !!");
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
}


