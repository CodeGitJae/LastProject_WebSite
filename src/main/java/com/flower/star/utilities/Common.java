package com.flower.star.utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class Common {
	
 
	public String isEmpty(String word) {
		return Objects.requireNonNullElse(word, "데이터 없음");
	}
	
	
	// 랜덤 번호 생성 메서드
	public String randomString(int length) {
		StringBuilder builder = new StringBuilder();
		
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int charsLength = chars.length();
		
		Random random = new Random();
		
		for (int i = 0; i < length; i++) {
			builder.append(chars.charAt(random.nextInt(charsLength)));
		}
		
		return builder.toString();
	}

	
	// 페이지 네이션
	public Map<String, Object> paginate(int currentPage, Page<?> items, String search, String type, String sort) {
        int totalPages = items.getTotalPages();
        int start = Math.max(1, currentPage / 10 * 10 + 1);
        int end = Math.min(start + 9, totalPages);

        Map<String, Object> paginatePosition = new HashMap<>();
        paginatePosition.put("items", items);
        paginatePosition.put("currentPage", currentPage);
        paginatePosition.put("totalPages", totalPages);
        paginatePosition.put("start", start);
        paginatePosition.put("end", end);
        paginatePosition.put("search", search);
        paginatePosition.put("type", type);
        paginatePosition.put("sort", sort);

        return paginatePosition;
    }
	
	
	public String getLoginUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) return ((UserDetails) principal).getUsername();
            else return principal.toString();
        }

        return null;
    }
	
	
}
