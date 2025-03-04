package com.winter.app.boards.notice;

import java.sql.Date;
import java.util.List;

import com.winter.app.boards.BoardDTO;
import com.winter.app.boards.BoardFileDTO;

public class NoticeDTO extends BoardDTO {
	
	
	private List<BoardFileDTO> boardFileDTOs;

	public List<BoardFileDTO> getBoardFileDTOs() {
		return boardFileDTOs;
	}

	public void setBoardFileDTOs(List<BoardFileDTO> boardFileDTOs) {
		this.boardFileDTOs = boardFileDTOs;
	}
	
	

}
