package com.winter.app.boards;

import java.sql.Date;

public class BoardDTO extends CommentDTO {
	
	
	private String boardTitle;
	private Long boardHit;
	
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public Long getBoardHit() {
		return boardHit;
	}
	public void setBoardHit(Long boardHit) {
		this.boardHit = boardHit;
	}
	
	
	
	

}
