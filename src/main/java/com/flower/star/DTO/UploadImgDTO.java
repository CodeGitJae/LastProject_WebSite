package com.flower.star.dto;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadImgDTO {
	
	private String fileName;
    private String uuid;
    private String folderPath;

    public String getImageURL() {
        return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName, StandardCharsets.UTF_8);
    }
    
    public class Define{
    	public final static int MAX_FILE_SIZE = 1024 * 1024 * 20;   // 최대 20MB 파일 업로드 
    	// 1 byte == 8bit
    	// 1 KB == 1,024 byte
    	// 1 MB == 1,048,476 byte (1024 * 1024)
    }
}
