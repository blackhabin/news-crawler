package com.mynews.crawler.util;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;

public class NewsSeleniumCrawler {

    public static List<String> crawlNewsContents(String keyword) {
        System.setProperty("webdriver.chrome.driver", "/Users/apple/chromedriver_mac64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);
        List<String> contents = new ArrayList<>();

        try {
            String url = "https://search.naver.com/search.naver?where=news&query=" + URLEncoder.encode(keyword, "UTF-8");
            driver.get(url);

            // ---- 이게 중요 부분 ----
            // 링크 로딩 대기 (최대 10초)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[data-heatmap-target=\".tit\"]")));

            // 변경된 선택자
            List<WebElement> newsLinks = driver.findElements(By.cssSelector("a[data-heatmap-target=\".tit\"]"));
            System.out.println("🔗 링크 개수: " + newsLinks.size());

            for (int i = 0; i < newsLinks.size(); i++) {
                // 요소를 매번 다시 가져와야 함 (stale 방지)
                List<WebElement> updatedLinks = driver.findElements(By.cssSelector("a[data-heatmap-target=\".tit\"]"));
                WebElement link = updatedLinks.get(i);
                String articleUrl = link.getAttribute("href");
                System.out.println("📰 기사 링크: " + articleUrl);

                driver.get(articleUrl);

                String bodyContent = "";
                try {
                    WebElement contentDiv = driver.findElement(By.cssSelector("div#dic_area"));
                    bodyContent = contentDiv.getText();
                } catch (NoSuchElementException e) {
                    List<WebElement> paragraphs = driver.findElements(By.cssSelector("p"));
                    StringBuilder sb = new StringBuilder();
                    for (WebElement p : paragraphs) {
                        sb.append(p.getText()).append(" ");
                    }
                    bodyContent = sb.toString();
                }

                if (!bodyContent.isBlank()) {
                    contents.add(bodyContent);
                }

                // 다시 검색결과 페이지로 복귀
                driver.get(url);
                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[data-heatmap-target=\".tit\"]")));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

        return contents;
    }

    public static void main(String[] args) {
        List<String> list = crawlNewsContents("인공지능");
        System.out.println("✅ 총 기사 본문 수: " + list.size());

        for (String content : list) {
            System.out.println("===============================");
            System.out.println(content);
        }
    }
}
