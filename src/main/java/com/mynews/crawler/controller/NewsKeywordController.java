package com.mynews.crawler.controller;

import com.mynews.crawler.domain.NewsKeyword;
import com.mynews.crawler.dto.NewsKeywordDto;
import com.mynews.crawler.dto.NewsKeywordResponseDto;
import com.mynews.crawler.service.NewsKeywordService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/keywords")  // 요청 경로: /api/keywords
public class NewsKeywordController {

    private final NewsKeywordService newsKeywordService;

    @PostMapping
    public ResponseEntity<String> saveKeyword(@RequestBody NewsKeywordDto dto) {
        newsKeywordService.saveKeyword(dto);
        return ResponseEntity.ok("✅ 저장 완료");
    }
    
    // 전체 조회
	@GetMapping
	public ResponseEntity<List<NewsKeywordResponseDto>> getAllKeywords() {
	    return ResponseEntity.ok(newsKeywordService.getAllKeywords());
	}
	
	// ID로 단건 조회
	@GetMapping("/{id}")
	public ResponseEntity<NewsKeywordResponseDto> getKeywordById(@PathVariable Long id) {
	    return newsKeywordService.getKeywordById(id)
	        .map(ResponseEntity::ok)
	        .orElse(ResponseEntity.notFound().build());
	}
	
	// 검색어로 조회
	@GetMapping("/search")
	public ResponseEntity<List<NewsKeywordResponseDto>> searchByKeyword(@RequestParam String keyword) {
	    return ResponseEntity.ok(newsKeywordService.searchByKeyword(keyword));
	}
	
	// 페이징 API
	@GetMapping("/paged")
	public ResponseEntity<Page<NewsKeywordResponseDto>> getPagedKeywords(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "10") int size) {
	    return ResponseEntity.ok(newsKeywordService.getPagedKeywords(page, size));
	}
}
