package com.mynews.crawler.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsKeywordDto {
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
