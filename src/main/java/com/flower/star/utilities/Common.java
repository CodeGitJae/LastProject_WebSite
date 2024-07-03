package com.flower.star.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Common {
	
	@Value("${uploadImagePath.board}")
    private String uploadPath;
	
 
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
	
// 새로운 파일 이름을 생성하는 메서드
	public String makeSaveNameForSavePath(MultipartFile uploadFile, String folderPath) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		//random 객체 생성
		Random random = new Random();
		
		//0이상 100미만의 랜덤한 정수 반환
		String randomNumber = Integer.toString(random.nextInt(Integer.MAX_VALUE));
		String timeStamp = sdf.format(new Date());	
		String originFilename = uploadFile.getOriginalFilename();
		// 원래 파일 이름이 비어있는지 검증
		assert originFilename != null;
		String saveName = uploadPath + File.separator + folderPath + File.separator + timeStamp + randomNumber + "_" + originFilename; 
//		System.out.println("::::::::::::method saveName:"+ saveName);
		return saveName; 
	}
	
	
	// 날짜 폴더를 생성하는 메서드
	public String makeFolder(String curDir) {
		// 날짜 포맷 생성
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		
		// 날짜 폴더 경로 생성
		String folderPath = str.replace("/", File.separator);
		
		
		// 업로드 경로에 날짜 폴더가 없으면 생성
		File uploadPathFolder = new File(curDir + uploadPath, folderPath);
//		System.out.println("-------------------"+uploadPathFolder);
		if(!uploadPathFolder.exists()) {
			boolean mkdirs = uploadPathFolder.mkdirs();
			System.out.println("##### Successful== " + mkdirs + " ==Successful #####");
		}
		return folderPath;
	}
	
}
