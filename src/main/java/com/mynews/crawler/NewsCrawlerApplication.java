package com.mynews.crawler;

import com.mynews.crawler.domain.NewsKeyword;
import com.mynews.crawler.dto.NewsKeywordDto;
import com.mynews.crawler.repository.NewsKeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class NewsCrawlerApplication implements CommandLineRunner {

    @Autowired
    private NewsKeywordRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(NewsCrawlerApplication.class, args);
    }

    @Override
    public void run(String... args) {

        // ✅ 1. DTO 생성 (가짜 데이터)
        NewsKeywordDto dto = new NewsKeywordDto(
                "OpenAI",
                100,
                "AI",
                3,
                "ChatGPT,Bing,Google",
                "Positive",
                "EN",
                "Global",
                "System",
                LocalDateTime.now(),
                LocalDate.now()
        );

        // ✅ 2. DTO → Entity 변환 (지금은 수동으로)
        NewsKeyword entity = new NewsKeyword();
        entity.setKeyword(dto.getKeyword());
        entity.setFrequency(dto.getFrequency());
        entity.setCategory(dto.getCategory());
        entity.setSourceCount(dto.getSourceCount());
        entity.setSourceList(dto.getSourceList());
        entity.setSentiment(dto.getSentiment());
        entity.setLanguage(dto.getLanguage());
        entity.setRegion(dto.getRegion());
        entity.setAuthor(dto.getAuthor());
        entity.setCollectTime(dto.getCollectTime());
        entity.setInsertDate(dto.getInsertDate());

        // ✅ 3. 저장
        repository.save(entity);

        // ✅ 4. 확인용 출력
        System.out.println("✅ DB 저장 성공");
    }
}
