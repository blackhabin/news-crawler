package com.mynews.crawler.service;

import com.mynews.crawler.dto.NewsKeywordDto;
import com.mynews.crawler.dto.NewsKeywordResponseDto;
import com.mynews.crawler.domain.NewsKeyword;
import com.mynews.crawler.repository.NewsKeywordRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsKeywordService {

    private final NewsKeywordRepository newsKeywordRepository;

    public void saveKeyword(NewsKeywordDto dto) {
        NewsKeyword keyword = new NewsKeyword(
                null,  // ID는 auto_increment
                dto.getKeyword(),
                dto.getFrequency(),
                dto.getCategory(),
                dto.getSourceCount(),
                dto.getSourceList(),
                dto.getSentiment(),
                dto.getLanguage(),
                dto.getRegion(),
                dto.getAuthor(),
                dto.getCollectTime(),
                dto.getInsertDate()
        );

        newsKeywordRepository.save(keyword);
        System.out.println("✅ DB 저장 성공");
    }
    
	
	public List<NewsKeywordResponseDto> getAllKeywords() {
	    return newsKeywordRepository.findAll().stream()
	        .map(this::convertToDto)
	        .toList();
	}

	public List<NewsKeywordResponseDto> searchByKeyword(String keyword) {
	    return newsKeywordRepository.findByKeywordContaining(keyword).stream()
	        .map(this::convertToDto)
	        .toList();
	}

	public Optional<NewsKeywordResponseDto> getKeywordById(Long id) {
	    return newsKeywordRepository.findById(id).map(this::convertToDto);
	}
	
    private NewsKeywordResponseDto convertToDto(NewsKeyword entity) {
        return new NewsKeywordResponseDto(
            entity.getId(),
            entity.getKeyword(),
            entity.getFrequency(),
            entity.getCategory(),
            entity.getSourceCount(),
            entity.getSourceList(),
            entity.getSentiment(),
            entity.getLanguage(),
            entity.getRegion(),
            entity.getAuthor(),
            entity.getCollectTime(),
            entity.getInsertDate()
        );
    }
    
    public Page<NewsKeywordResponseDto> getPagedKeywords(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        return newsKeywordRepository.findAll(pageable)
            .map(this::convertToDto);
    }
}
