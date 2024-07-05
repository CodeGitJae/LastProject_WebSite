package com.flower.star.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.flower.star.dto.UploadImgDTO;
import com.flower.star.dto.UploadImgDTO.Define;
import com.flower.star.entity.Board;
import com.flower.star.entity.StarspotImages;
import com.flower.star.repository.BoardRepository;
import com.flower.star.repository.StarspotImagesRepository;
import com.flower.star.utilities.Common;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardSerivce {
	private final BoardRepository bRepository;
	private final StarspotImagesRepository starspotImagesRepository;
	private final Common common;
	
	
	// 입력한 게시글 정보 DB에 저장 (MEMBER 객체도 함께)
	public void insert(Board board, String title, String content) {
	
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String now = sdf.format(date);
		
		board.setCreateDate(now);
		board.setTitle(title);
		board.setContent(content);
		board.setViews(0);
		
		bRepository.save(board);
	}

	public void insertImage(Board board, MultipartFile[] uploadToBoardImage){
		
//		System.out.println(":::::::::::::::service :" + board);
//		System.out.println(":::::::::::::::service :" +uploadToBoardImage);
		
		// 현재 폴더 위치 정보 얻기 (프로젝트 저장한 폴더 위치가 출력됨)
		String curDir = System.getProperty("user.dir");
		
			// 폴더 생성 및 저장할 경로 추가
			curDir += File.separator + "src" + File.separator + "main" + File.separator 
					+ "resources" + File.separator + "static" + File.separator;
//		System.out.println("::::::::::2"+curDir);
		List<UploadImgDTO> uploadDTOList = new ArrayList<>();
		
		for(MultipartFile uploadFile : uploadToBoardImage) {
		
			 // 파일 크기가 20MB 이상의 경우 업로드 할 수 없도록 설정
			if(!Objects.requireNonNull(uploadFile.getContentType()).startsWith("image")) {
				System.out.println("파일의 Type이 이미지가 아닙니다.");
				return;
			}

			// 예외 처리가 정상 동작하지 않은 경우를 대비 20MB 크기 넘는 파일을 저장하지 않음 
			if(uploadFile.getSize() > Define.MAX_FILE_SIZE) {
				System.out.println("#########uploadfile 결과:"+(uploadFile.getSize() > Define.MAX_FILE_SIZE));
				return;
			}
			// 실제 파일 이름 가져오기
//				String originFilename = uploadFile.getOriginalFilename();
			
			// 폴더 생성
			String folderPath = common.makeFolder(curDir);
//			System.out.println("::::::::::::::::::folder"+folderPath);
			
			// 저장할 파일 이름 생성
			String savePathName = common.makeSaveNameForSavePath(uploadFile, folderPath);
//			System.out.println("::::::::::::::::::saveName"+ savePathName);
			
			// 저장 경로 생성
			Path savePath = Paths.get(curDir + savePathName);
//			System.out.println("::::::::::::::::::savePath"+ savePath);
			
			
			try {
				// 범용 고유 식별자 생성
				String uuid = UUID.randomUUID().toString();
				
				// 실제 이미지 저장
				uploadFile.transferTo(savePath);
//				System.out.println("::::::::::::tryCatch uploadFile:"+ uploadFile);
				uploadDTOList.add(new UploadImgDTO(uploadFile.getOriginalFilename(), uuid, folderPath));
//				System.out.println("::::::::::::tryCatch uploadDTOList:"+ uploadDTOList);
				
				StarspotImages starspotImgs = new StarspotImages(null, savePathName, null, board);
				starspotImagesRepository.save(starspotImgs);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		
	}
	
	// 게시물 목록 조회 및 페이지네이션
	public Page<Board> paging(int curPage) {

		 // 한페이지 최대 출력 수
		int pageLimit = 10; 
		Sort sort = Sort.by(Sort.Order.desc("id"));
    	Pageable pageable = PageRequest.of(curPage, pageLimit, sort);
//		System.out.println("::::::::::::::::::service:: pageable::::"+ pageable);
		Page<Board> page = bRepository.findAll(pageable);

		return page;
	}
	
//	// 게시물 목록 조회
//	public List<Board> findAll() {
//		return bRepository.findAll();
//	}

	// 게시물 상세 정보 가져오기
	public Board findById(Integer bId) {
		Optional<Board> optBid = bRepository.findById(bId);
		// 객체가 없는 경우 null을 리턴함.
		if(!optBid.isPresent()) {
			return null;
		}
		Board board = optBid.get();
		return board ;
	}
	
	
	// 조회수 업데이트
	 // 수동적인 쿼리 수행하는 경우라서 transactional을 추가 해야함./ 없을 경우 영속성 부여 구간에서 에러남
	@Transactional
	public void updateViews(Integer bId) {
		bRepository.updateViews(bId);
	}
	
	
	//
	public void update(Board board, MultipartFile[] uploadToBoardImage) {
		
		if(uploadToBoardImage[0].getContentType().startsWith("image") == true) {
//			System.out.println(":::::222222222222222::::::::::::::::::::"+imageId+imagePath +uploadToBoardImage[0].getContentType().startsWith("image"));
			insertImage(board, uploadToBoardImage);
		
		} 
		
			// DB에서 ID 값 기준으로 저장된 정보 가져오기
			Integer bId = board.getId();
			Optional<Board> bid = bRepository.findById(bId);
			if(!bid.isPresent()) {
				return ;
			}
			// Optinal로 DB에서 꺼내온 Board 객체 추출
			Board bIdFromDb = bid.get();
			
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String now = sdf.format(date);
			
			// DB에 저장할 데이터 값 세팅
			board.setUpdateDate(now);
			board.setCreateDate(bIdFromDb.getCreateDate());
			board.setMember(bIdFromDb.getMember());
			board.setViews(bIdFromDb.getViews());
			board.setComment(bIdFromDb.getComment());
			board.setImages(bIdFromDb.getImages());
	
			bRepository.save(board);
	}

	// 아이디 삭제
	public void deleteById(Integer id) {
		bRepository.deleteById(id);
		
	}

	
	// index 페이지에 표시 될 최근 게시글 5개 가져오는 메서드
	public List<Board> findAllForViews() {
	        List<Board> boardList = bRepository.findAll(); // 모든 게시물 가져오기
	        Collections.reverse(boardList); // 리스트를 역순으로 정렬
	       
		return boardList.stream().limit(5).collect(Collectors.toList()); // 최신 5개의 게시물 반환;
	}

}


