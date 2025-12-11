package com.mynews.crawler.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewsKeywordResponseDto {
    private Long id;
    private String keyword;
    private Integer frequency;
    private String category;
    private Integer sourceCount;
    private String sourceList;
    private String sentiment;
    private String language;
    private String region;
    private String author;
    private LocalDateTime collectTime;
    private LocalDate insertDate;
}
