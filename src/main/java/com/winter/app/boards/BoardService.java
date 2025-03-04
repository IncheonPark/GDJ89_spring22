package com.winter.app.boards;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.winter.app.pages.Pager;

public interface BoardService {
	
	//list
	public List<BoardDTO> getList(Pager pager)throws Exception;
	
	//detail
	public BoardDTO getDetail(BoardDTO boardDTO, boolean check)throws Exception;
	
	//insert
	public int add(BoardDTO boardDTO, HttpSession session, MultipartFile [] attaches)throws Exception;
	
	//update
	public int update(BoardDTO boardDTO)throws Exception;
	
	//delete
	public int delete(BoardDTO boardDTO)throws Exception;

}
