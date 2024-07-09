package com.flower.star.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.flower.star.entity.Board;
import com.flower.star.repository.StarspotImagesRepository;
import com.flower.star.service.BoardSerivce;
import com.flower.star.service.MemberService;
import com.flower.star.util.Pagination;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	private final MemberService mService;
	private final BoardSerivce bService;
	private final StarspotImagesRepository ssimageRepository;
	
	@Value("${uploadImagePath.board}")
    private String uploadPath;	

	 // 글작성 화면 띄워주기
	@GetMapping("/write")
	public String showWrite() {
		return "/board/write";
	}
	
	
	// 입력된 게시물 정보 저장 / 이미지가 있는 경우도 포함
	@PostMapping("/write")
	public String writeBoard(@RequestParam("username") String mUsername, @RequestParam("title") String title,
							@RequestParam("content") String content, Board board, MultipartFile[] uploadToBoardImage, 
							RedirectAttributes redirectAttributes) {
		
		try {
			// 로그인되어 있는 인증 정보를 받아 member 테이블에 DB 저장
			board.setMember(mService.findByUsername(mUsername));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		try {
			bService.insert(board, title, content);
			bService.insertImage(board, uploadToBoardImage);
			
			
			if(mUsername !=null && !mUsername.isEmpty() ) {
				redirectAttributes.addFlashAttribute("success", "게시글 등록을 성공 했습니다.");
			}
			
            // 성공적으로 업로드되었을 경우
            redirectAttributes.addFlashAttribute("message", "파일 업로드가 성공적으로 완료되었습니다.");     
            return "redirect:/board/list";
          
            // 사용자 예외 처리  
		} catch (MaxUploadSizeExceededException exc) {
			redirectAttributes.addFlashAttribute("errorMessage", "파일 크기가 너무 큽니다. 최대 허용 크기는 20MB입니다.");
			return "redirect:/board/list";
		}
	}
	
	
	@GetMapping("/list")
	public String paging(@RequestParam(name="page", defaultValue = "1") int curPage, Model model) {

		// 한 페이지당 출력 게시물 수 
		int blockLimit = 5;
		Page<Board> paging = bService.paging(curPage -1);
		Pagination pagination = new Pagination(curPage, paging.getTotalPages(), blockLimit);
//		int nextKey = (int) (Math.floor((curPage - 1) / 5) * 5 + 6);
//		int prevKey = (int) (Math.floor((curPage - 1) / 5) * 5);
		
		model.addAttribute("paging", paging);
		model.addAttribute("pagination", pagination);
//		model.addAttribute("nextKey", nextKey);
//		model.addAttribute("prevKey", prevKey);
		
		return "/board/list";
		
	}
	
	
	@GetMapping("/detail")
	public String BoardDetails(@RequestParam("id") Integer bId, Model model, Authentication authentication) {
//		System.out.println("::::::::::::::::bid: "+bId);
		Board board = bService.findById(bId);
		bService.updateViews(bId);
		
		model.addAttribute("authentication", authentication);
		model.addAttribute("b", board);
		return"/board/detail";
	}
	
	@GetMapping("/update")
	public String updateBoard(@RequestParam("id") Integer bId, Model model) {
		
		Board board = bService.findById(bId);
		model.addAttribute("b", board);
		return "/board/update";
	}
	
	
	
	@PostMapping("/update")
	public String updateBoard(Board board, MultipartFile[] uploadToBoardImage, RedirectAttributes redirectAttributes) {
		
		// 제목 또는 내용이 비어 있을 때 페이지 안넘기기
		if(board.getTitle().isEmpty() && board.getContent().isEmpty()) {
			return "/board/update?id="+board.getId();
		}
		
		bService.update(board, uploadToBoardImage);
		redirectAttributes.addFlashAttribute("success", "게시글 수정을 성공했습니다.");
		return "redirect:/board/detail?id="+ board.getId();
	}
	
	// 수정 화면에서 이미지 개별 삭제 기능 추가
	@ResponseBody
	@PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(@RequestParam("imageFilePath") String fileName,
    										 @RequestParam("imageId") Integer imageId){
        String srcFilePath = null;

        try {
        	
        	// 현재 디렉토리 기준 절대 경로 설정
        	String curDir = System.getProperty("user.dir");
        	curDir += File.separator + "src" + File.separator + "main" + File.separator 
					+ "resources" + File.separator + "static" + File.separator;
        	
        	// 절대 경로 + 이미지 path 결합
        	String absolutfileName = curDir + fileName;
        	fileName = absolutfileName.replace("/", File.separator);
        	
        	// 이미지 URL 디코딩
        	srcFilePath = URLDecoder.decode(fileName,"UTF-8");
            File file = new File(srcFilePath);
            
            // 절대 경로로 변경
            file = file.getAbsoluteFile();
                       
            // 파일이 실제로 존재하는지 확인
            if (!file.exists()) {
                System.out.println("File does not exist: " + file);
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
            
            // db에 이미지 주소 삭제 요청
            ssimageRepository.deleteById(imageId);
            boolean result = file.delete();
//          System.out.println("::::::::::::::::result::::::::::::::::::::"+result);
            
            return new ResponseEntity<>(result,HttpStatus.OK);

        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return new ResponseEntity<>(false,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	
	@GetMapping("/delete")
	public String deleteToBoard(@RequestParam("id") Integer id) {
		if(id == null) {
			return "redirect:/board/detail?id=" + id;
		}
		bService.deleteById(id);
		return "redirect:/board/list";
	}
	
}
