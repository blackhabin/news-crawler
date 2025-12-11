package com.mynews.crawler.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mynews.crawler.domain.NewsKeyword;

public interface NewsKeywordRepository extends JpaRepository<NewsKeyword, Long> {
    // keyword로 포함된 데이터 검색 (부분일치)
    List<NewsKeyword> findByKeywordContaining(String keyword);

}
