package com.winter.app.boards.notice;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.boards.BoardDTO;
import com.winter.app.boards.BoardFileDTO;
import com.winter.app.boards.BoardService;
import com.winter.app.files.FileManger;
import com.winter.app.pages.Pager;

@Service
public class NoticeService implements BoardService{
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private FileManger fileManger;
	
	public List<BoardDTO> getList(Pager pager)throws Exception{
		return noticeDAO.getList(pager);
	}
	
	public BoardDTO getDetail(BoardDTO boardDTO, boolean check)throws Exception{
		if(check) {
			noticeDAO.updateHit(boardDTO);
		}
		return noticeDAO.getDetail(boardDTO);
	}
	
	public int add(BoardDTO boardDTO, HttpSession session, MultipartFile [] attaches)throws Exception{
		//1. DB에 Notice정보를 Insert
		int result = noticeDAO.add(boardDTO);
		//2. HDD에 파일을 저장하고 그 정보들을 DB에 저장
		for(MultipartFile attach: attaches) {
			if(attach.isEmpty()) {
				continue;
			}
			BoardFileDTO boardFileDTO = this.fileSave(attach, session.getServletContext());
			//DB에 저장
			//
			boardFileDTO.setBoardNum(boardDTO.getBoardNum());
			result = noticeDAO.addFile(boardFileDTO);
		}
				
		
		return result;
	}
	
	public int update(BoardDTO boardDTO, MultipartFile [] attaches, HttpSession session)throws Exception{
		int result = noticeDAO.update(boardDTO);
		
		for(MultipartFile attach: attaches) {
			if(attach.isEmpty()) {
				continue;
			}
			BoardFileDTO boardFileDTO = this.fileSave(attach, session.getServletContext());
			//DB에 저장
			//
			boardFileDTO.setBoardNum(boardDTO.getBoardNum());
			result = noticeDAO.addFile(boardFileDTO);
		}
		return result;
		
	}
	
	public int delete(BoardDTO boardDTO, HttpSession session)throws Exception{
		//1. 파일들의 정보를 조회
		boardDTO=noticeDAO.getDetail(boardDTO);
		int result= noticeDAO.fileDeleteAll(boardDTO);
		result= noticeDAO.delete(boardDTO);
		
		//3. HDD 삭제
		 if(result>0) {
			 String path = session.getServletContext().getRealPath("/resources/images/notice/");
			 System.out.println(path);
			 
			 for(BoardFileDTO boardFileDTO: ((NoticeDTO)boardDTO).getBoardFileDTOs()) {
				 fileManger.fileDelete(path, boardFileDTO.getFileName());
			 }
		 }
		
		
		
		return result;
	}
	
	public int fileDelete(BoardFileDTO boardFileDTO, HttpSession session)throws Exception{
		
		//1.정보 조회
		 boardFileDTO = noticeDAO.getFileDetail(boardFileDTO);
		//2.DB삭제
		 int result = noticeDAO.fileDelete(boardFileDTO);
		 
		//3. HDD 삭제
		 if(result>0) {
			 String path = session.getServletContext().getRealPath("/resources/images/notice/");
			 System.out.println(path);
			 fileManger.fileDelete(path, boardFileDTO.getFileName());
		 }
		
		 return result;
	}
	
	public BoardFileDTO getFileDetail(BoardFileDTO boardFileDTO)throws Exception{
		return noticeDAO.getFileDetail(boardFileDTO);
	}
	
	private BoardFileDTO fileSave(MultipartFile attach, ServletContext servletContext)throws Exception{
		//1. 어디에 저장할 것인가??
		String path = servletContext.getRealPath("/resources/images/notice/");
		
		System.out.println(path);
		
		File file = new File(path);
		
		if(!file.exists()) {
			file.mkdirs();
		}
		
		//2. HDD에 파일을 저장하고 저장된 파일명을 리턴
		String fileName=fileManger.fileSave(path, attach);
		
		//3. 파일의 정보를 DTO에 담아서 리턴
		BoardFileDTO boardFileDTO = new BoardFileDTO();
		boardFileDTO.setFileName(fileName);
		boardFileDTO.setOldName(attach.getOriginalFilename());
		
		return boardFileDTO;
	}

}











