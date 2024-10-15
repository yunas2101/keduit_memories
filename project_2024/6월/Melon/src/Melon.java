import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Melon {
	public static void main(String[] args) throws Exception {

		// 프로그램 실행 시 필요한 인스턴스, 메서드
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			// 로그인 페이지 Start 멜론 계정 로그인
			driver.get(
					"https://member.melon.com/muid/family/ticket/login/web/login_inform.htm?cpId=WP15&returnPage=https://ticket.melon.com/performance/index.htm?prodId=209531");
			WebElement buttonLogin = driver.findElements(By.className("melon")).get(0);
			buttonLogin.click();

			// 멜론 계정 로그인 ID,PW 자동입력 후 로그인
			WebElement inputId = driver.findElement(By.id("id"));
			js.executeScript("arguments[0].value=arguments[1]", inputId, Statics.Melon_ID);
			WebElement inputPw = driver.findElement(By.id("pwd"));
			js.executeScript("arguments[0].value=arguments[1]", inputPw, Statics.Melon_PWD);
			WebElement btnLogin = driver.findElements(By.className("btn_login03")).get(0);
			btnLogin.click();

			// 티켓팅 프로그램 날짜 선택
			// 티켓팅 할 페이지 url 입력
			driver.get("https://ticket.melon.com/performance/index.htm?prodId=209531");
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("dateSelect_20240705")));
			// ID값 변경시 날짜 변경 가능 dateSelect_20240705 Ex)0705,0706,0707
			WebElement dateChoice = driver.findElement(By.id("dateSelect_20240705"));
			dateChoice.click();
			// 날짜별 시간 choice (시간 갯수가 많다면 배열값으로 시간 값 변경 가능, 처음값 0 ~ )
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("item_time")));
			WebElement timeChoice = driver.findElements(By.className("item_time")).get(0);
			timeChoice.click();
			
			// 예매하기 버튼
			WebElement reserveBtn = driver.findElement(By.id("ticketReservation_Btn"));
			reserveBtn.click();
			
			// 클릭 시 예매창 윈도우 팝업
			wait.until(ExpectedConditions.numberOfWindowsToBe(2));			
			String defaultContent = driver.getWindowHandle();
			Set<String> windows = driver.getWindowHandles();
			System.out.println(windows.size());
			for (String window : windows) { // 포커스 변경
				if (!window.equals(defaultContent)) {
					driver.switchTo().window(window);
					break;
				}
			}
			
			// iframe 접근 코드
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("oneStopDiv")));
			WebElement oneStopDiv = driver.findElement(By.id("oneStopDiv"));
			WebElement iframe = oneStopDiv.findElements(By.tagName("iframe")).get(0);
			driver.switchTo().frame(iframe);

			// 셀렉트 요소 검색 후 변수 저장
			// 그린팀
//			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("volume_11907_10067")));
//			WebElement selectElementGreen = driver.findElement(By.id("volume_11907_10067"));
			// 옐로우팀
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("volume_11909_10067")));
			WebElement selectElementYellow = driver.findElement(By.id("volume_11909_10067"));
			selectElementYellow.click();
			// 셀렉트 인스턴스 생성
			Select select = new Select(selectElementYellow);
			// 매수 설정 value("?") ? 안에 숫자입력
			select.selectByValue("1");
			
			// 매수 설정 후 다음 버튼
			WebElement btNext = driver.findElement(By.className("btNext"));
			if(btNext.getAttribute("class").contains("on")) {
				// 다음 결제 페이지로 넘어가는 버튼
				WebElement nextBtn = driver.findElement(By.id("nextPayment"));
				nextBtn.click();
			}

			// 무통장 입금
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("payMethodCode003")));
			WebElement payMentRadio = driver.findElement(By.id("payMethodCode003"));
			payMentRadio.click();

			WebElement bankCode = driver.findElement(By.name("bankCode"));
			Select selectbk = new Select(bankCode);
			// 은행 설정 value("?") ? 안에 숫자입력
			// 은행별 value 코드 기업은행(03), 국민은행(04), 농협은행(11), 하나은행(81), 우리은행(20), 신한은행(88), 경남은행(39), 우체국(71), 부산은행(32), 대구은행(31)
			selectbk.selectByValue("11");
			// 현금영수증 발행
			WebElement cashReceipts = driver.findElement(By.id("cashReceiptIssueCode1"));
			cashReceipts.click();
			// 010 자동배정 현금영수증 코드
			WebElement receiptMidNum = driver.findElement(By.id("cashReceiptRegTelNo2"));
			WebElement receiptLastNum = driver.findElement(By.id("cashReceiptRegTelNo3"));
			// midnum : 중간 번호값 4자리, lastnum : 마지막 번호값 4자리
			receiptMidNum.sendKeys("7233");
			receiptLastNum.sendKeys("4198");
			
			// 현금영수증 미발행
//			WebElement notCashReceipts = driver.findElement(By.id("cashReceiptIssueCode3"));
//			notCashReceipts.click();
			
			// 예매자 동의
			WebElement chkAgreeAll = driver.findElement(By.id("chkAgreeAll"));
			chkAgreeAll.click();
			
			
			// 결제하기 최종 버튼
			WebElement btNextF = driver.findElement(By.className("btNext"));
			if(btNextF.getAttribute("class").contains("on")) {
				WebElement finalPaymentBtn = driver.findElement(By.id("btnFinalPayment"));
//				finalPaymentBtn.click();	
			}

			
		} finally {
			Thread.sleep(5000);
			driver.quit();
		}

	}

}
