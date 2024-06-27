package com.flower.star.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.flower.star.dto.UploadImgDTO;
import com.flower.star.entity.Observatory;
import com.flower.star.repository.ObservatoryRepository;

@Service
public class ObservatoryService {

    @Autowired
    private ObservatoryRepository observatoryRepository;
    

    @Value("${uploadImagePath.observatory}")
    private String uploadPath;
    
    
    public Page<Observatory> find(int page, String sortField) {
    	int size = 6;
    	Sort sort = Sort.by(Sort.Order.desc(sortField));
    	
    	Pageable pageable = PageRequest.of(page, size, sort);
    	
    	return observatoryRepository.findAll(pageable);
    	
    }
    

    // observatory 엔티티 저장 메서드
    public void insert(Observatory observatory) {
    	Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		String date = sdf.format(now);

		observatory.setCreatedate(date);
		observatory.setViews(0);
		
		observatoryRepository.save(observatory);
    }
    
 // 이미지 업로드 및 저장 메서드
    public void insertImg(Observatory observatory, MultipartFile[] uploadImages) {

        // 현재 프로젝트의 루트 경로를 얻어와 static 디렉토리 경로로 설정
        String currentDir = System.getProperty("user.dir");
        currentDir +=  File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator;

        List<UploadImgDTO> resultDTOList = new ArrayList<>();

        for (MultipartFile uploadFile : uploadImages) {

            // 이미지 파일만 업로드
            if (!Objects.requireNonNull(uploadFile.getContentType()).startsWith("image")) {
                System.out.println("this file is not image type");
                return;
            }

            // 실제 파일 이름 가져오기 (IE나 Edge는 전체 경로가 들어올 수 있음)
            String originalName = uploadFile.getOriginalFilename();
            assert originalName != null;
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            System.out.println("fileName: " + fileName);
            System.out.println(currentDir);

            // 날짜 폴더 생성
            String folderPath = makeFolder(currentDir);

            // UUID 생성
            String uuid = UUID.randomUUID().toString();

            // 저장할 파일 이름 중간에 "_"를 이용해서 구현
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

            Path savePath = Paths.get(currentDir + saveName);

            try {
                // 실제 이미지 저장
                uploadFile.transferTo(savePath);
                resultDTOList.add(new UploadImgDTO(fileName, uuid, folderPath));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    

    // 날짜 폴더 생성 메서드
    private String makeFolder(String currentDir) {
        // 현재 날짜를 yyyy/MM/dd 형식으로 포맷
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        // 날짜 폴더 경로 생성
        String folderPath = str.replace("/", File.separator);

        // 업로드 경로에 날짜 폴더가 없으면 생성
        File uploadPathFolder = new File(currentDir + uploadPath, folderPath);
        if (!uploadPathFolder.exists()) {
            boolean mkdirs = uploadPathFolder.mkdirs();
            System.out.println("-------------------makeFolder------------------");
            System.out.println("uploadPathFolder.exists(): " + uploadPathFolder.exists());
            System.out.println("mkdirs: " + mkdirs);
        }

        return folderPath;
    }

}
    
    
    