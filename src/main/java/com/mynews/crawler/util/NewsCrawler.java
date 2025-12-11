package com.mynews.crawler.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewsCrawler {

    // 네이버 뉴스 검색 후 기사 본문 크롤링
    public static List<String> crawlNewsContents(String keyword) {
        List<String> contents = new ArrayList<>();
        try {
            String url = "https://search.naver.com/search.naver?where=news&query=" + URLEncoder.encode(keyword, "UTF-8");
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
                    .get();

            Elements newsLinks = doc.select("a.news_tit");

            for (Element link : newsLinks) {
                String articleUrl = link.attr("href");
                try {
                    Document articleDoc = Jsoup.connect(articleUrl)
                            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
                            .get();

                    String content;

                    if (articleUrl.contains("n.news.naver.com")) {
                        // 네이버 뉴스 본문
                        Element contentElement = articleDoc.selectFirst("#dic_area");
                        content = contentElement != null ? contentElement.text() : "";
                    } else {
                        // 외부 언론사 - 모든 p 태그 합치기
                        Elements paragraphs = articleDoc.select("p");
                        StringBuilder sb = new StringBuilder();
                        for (Element p : paragraphs) {
                            sb.append(p.text()).append(" ");
                        }
                        content = sb.toString();
                    }

                    // 유효한 본문만 추가
                    if (content != null && !content.isBlank()) {
                        contents.add(content.trim());
                    }

                } catch (IOException e) {
                    System.out.println("⚠️ 기사 본문 크롤링 실패: " + articleUrl);
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            System.out.println("❌ 네이버 뉴스 검색 페이지 크롤링 실패");
            e.printStackTrace();
        }

        return contents;
    }

    public static void main(String[] args) {
        System.out.println("✅ 프로그램 시작");

        List<String> articles = crawlNewsContents("인공지능");

        System.out.println("✅ 크롤링 완료. 기사 개수: " + articles.size());
        for (String content : articles) {
            System.out.println("📰 기사 본문:");
            System.out.println(content);
            System.out.println("--------------------------------------------------");
        }

        System.out.println("✅ 프로그램 종료");
    }
}
