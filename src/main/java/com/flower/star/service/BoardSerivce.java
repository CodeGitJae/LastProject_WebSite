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
import java.util.Random;
import java.util.UUID;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.flower.star.dto.UploadImgDTO;
import com.flower.star.dto.UploadImgDTO.Define;
import com.flower.star.entity.Board;
import com.flower.star.entity.Member;
import com.flower.star.entity.StarspotImages;
import com.flower.star.repository.BoardRepository;
import com.flower.star.repository.StarspotImagesRepository;

import lombok.RequiredArgsConstructor;

@Service
public class BoardSerivce {

	@Autowired
	private BoardRepository bRepository;
	@Autowired
	private StarspotImagesRepository starspotImagesRepository;
	
	@Value("${uploadImagePath.board}")
    private String uploadPath;
	

	// 입력한 게시글 정보 DB에 저장 (MEMBER 객체도 함께)
	public void insert(Board board) {
		Member member = new Member();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String now = sdf.format(date);
		
//		board.setMember(member);
		board.setCreateDate(now);
		board.setViews(0);
		
		bRepository.save(board);	
	}

	public void insertImage(Board board, MultipartFile[] uploadToBoardImage){
		
		System.out.println(":::::::::::::::service :" + board);
		System.out.println(":::::::::::::::service :" +uploadToBoardImage);
		String curDir = System.getProperty("user.dir");
		System.out.println("::::::::::1"+curDir);
			curDir += File.separator + "src" + File.separator + "main" + File.separator 
					+ "resources" + File.separator + "static" + File.separator;
		System.out.println("::::::::::2"+curDir);
		List<UploadImgDTO> uploadDTOList = new ArrayList<>();
		
		for(MultipartFile uploadFile : uploadToBoardImage) {
			
			 // 파일 크기가 20MB 이상의 경우 업로드 할 수 없도록 설정
			if(uploadFile !=null ? uploadFile.getSize() > Define.MAX_FILE_SIZE : null) {
				System.out.println("20MB 이하 파일만 업로드 할 수 있습니다.");
			}
			String originFilename = uploadFile.getOriginalFilename();
			String newFileName = generateUniqueFileName(originFilename);
			System.out.println("newFileName: "+ newFileName);
			System.out.println(curDir);
			
			// 폴더 생성
			String folderPath = makeFolder(curDir);
			System.out.println("::::::::::::::::::folder"+folderPath);
			
			// 범용 고유 식별자 생성
			String uuid = UUID.randomUUID().toString();
			
			// 저장 할 파일 이름
			String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + newFileName;
			System.out.println("::::::::::::::::::saveName"+ saveName);
			Path savePath = Paths.get(curDir + saveName);
			
			
			try {
				// 실제 이미지 저장
				uploadFile.transferTo(savePath);
				uploadDTOList.add(new UploadImgDTO(newFileName, uuid, folderPath));
				
				
				StarspotImages starspotImgs = new StarspotImages(null, saveName, null, board);
				starspotImagesRepository.save(starspotImgs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	// 새로운 파일 이름을 생성하는 메서드
	private String generateUniqueFileName(String originaFileName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		
		//random 객체 생성
		Random random = new Random();
		
		//0이상 100미만의 랜덤한 정수 반환
		String randomNumber = Integer.toString(random.nextInt(Integer.MAX_VALUE));
		String timeStamp = sdf.format(new Date());
		System.out.println("::::::::::::timeStamp:"+timeStamp);
		System.out.println("::::::::::::randomNumber:"+randomNumber);
		System.out.println("::::::::::::originaFileName:"+originaFileName);
		return timeStamp + randomNumber + originaFileName; 
	}
	
	// 날짜 폴더를 생성하는 메서드
	private String makeFolder(String curDir) {
		// 날짜 포맷 생성
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		
		// 날짜 폴더 경로 생성
		String folderPath = str.replace("/", File.separator);
		
		// 업로드 경로에 날짜 폴더가 없으면 생성
		File uploadPathFolder = new File(curDir + uploadPath, folderPath);
		if(uploadPathFolder.exists()) {
			boolean mkdirs = uploadPathFolder.mkdirs();
			System.out.println("##### Successful== "+ mkdirs + " ==Successful#####");
		}
		return folderPath;
	}
}
