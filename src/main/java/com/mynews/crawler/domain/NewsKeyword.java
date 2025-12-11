package com.mynews.crawler.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NEWS_KEYWORDS")
public class NewsKeyword {

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getSourceCount() {
		return sourceCount;
	}

	public void setSourceCount(Integer sourceCount) {
		this.sourceCount = sourceCount;
	}

	public String getSourceList() {
		return sourceList;
	}

	public void setSourceList(String sourceList) {
		this.sourceList = sourceList;
	}

	public String getSentiment() {
		return sentiment;
	}

	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDateTime getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(LocalDateTime collectTime) {
		this.collectTime = collectTime;
	}

	public LocalDate getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(LocalDate insertDate) {
		this.insertDate = insertDate;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 기본 키

    @Column(length = 100)
    private String keyword;  // 키워드

    private Integer frequency;  // 빈도수

    @Column(length = 50)
    private String category;  // 카테고리

    private Integer sourceCount;  // 출처 개수

    @Column(length = 1000)
    private String sourceList;  // 출처 리스트

    @Column(length = 20)
    private String sentiment;  // 감성 분석 결과

    @Column(length = 20)
    private String language;  // 언어

    @Column(length = 50)
    private String region;  // 지역

    @Column(length = 100)
    private String author;  // 작성자

    private LocalDateTime collectTime;  // 수집 시각

    private LocalDate insertDate;  // 등록 일자
}
