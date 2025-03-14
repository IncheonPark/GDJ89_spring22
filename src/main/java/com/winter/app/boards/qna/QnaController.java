package com.winter.app.boards.qna;

import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.boards.BoardDTO;
import com.winter.app.boards.BoardFileDTO;
import com.winter.app.boards.notice.NoticeDTO;
import com.winter.app.pages.Pager;
import com.winter.app.users.UserDTO;

@Controller
@RequestMapping(value="/qna/*")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("kind")
	public String getKind() {
		return "qna";
	}
	
	
	
	@RequestMapping(value="list", method = RequestMethod.GET)
	public String getList(Pager pager, Model model)throws Exception{
		List<BoardDTO> ar= qnaService.getList(pager);
		model.addAttribute("list", ar);
		return "board/list";
	}
	
	@RequestMapping(value="detail", method = RequestMethod.GET)
	public String getDetail(QnaDTO boardDTO, Model model, HttpSession session)throws Exception{
		//"board" : set(글번호들,,)
		Object obj = session.getAttribute("board");
		boolean check=false;
		if(obj != null) {
			HashSet<Long> ar = (HashSet<Long>)obj;
			if(!ar.contains(boardDTO.getBoardNum())) {
				check=true;
			}else {
				ar.add(boardDTO.getBoardNum());
			}
		}else {
			HashSet<Long> num = new HashSet<Long>();
			num.add(boardDTO.getBoardNum());
			session.setAttribute("board", num);
			check=true;
		}
		
		boardDTO= (QnaDTO)qnaService.getDetail(boardDTO, check);
		model.addAttribute("dto", boardDTO);
		return "board/detail";
	}
	@RequestMapping(value="add", method = RequestMethod.GET)
	public String add()throws Exception{
		return "board/boardForm";
	}
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	public String add(QnaDTO boardDTO, HttpSession session, MultipartFile [] attaches)throws Exception{
		
		UserDTO userDTO = (UserDTO)session.getAttribute("user");
		boardDTO.setUserName(userDTO.getUserName());
		int result = qnaService.add(boardDTO, session, attaches);
		
		return "redirect:./list";
	}
	
	@RequestMapping(value="update", method = RequestMethod.GET)
	public String update(QnaDTO boardDTO, Model model)throws Exception{
		boardDTO = (QnaDTO)qnaService.getDetail(boardDTO, false);
		model.addAttribute("dto", boardDTO);
		return "board/boardForm";
	}
	
	@RequestMapping(value="update", method = RequestMethod.POST)
	public String update(BoardDTO boardDTO, MultipartFile [] attaches, HttpSession session)throws Exception{
		int result =  qnaService.update(boardDTO, attaches, session);
		
		//return "redirect:./list";
		return "redirect:./detail?boardNum="+boardDTO.getBoardNum();
		
	}
	
	@RequestMapping(value="delete", method = RequestMethod.GET)
	public String delete(BoardDTO boardDTO, Model model, HttpSession session)throws Exception{
		int result = qnaService.delete(boardDTO, session);
		String s = "삭제 실패";
		if(result>0) {
			s = "삭제 성공";
		}
		model.addAttribute("result", s);
		model.addAttribute("path", "./list");
		
		return "commons/result";
		
	}
	
	@RequestMapping(value = "reply", method = RequestMethod.GET)
	public String reply(@ModelAttribute("dto") QnaDTO boardDTO)throws Exception{
		return "board/boardForm";
	}
	
	@RequestMapping(value = "reply", method = RequestMethod.POST)
	public String reply(QnaDTO boardDTO, HttpSession session)throws Exception{
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		
		boardDTO.setUserName(userDTO.getUserName());
		
		int result = qnaService.reply(boardDTO);
		
		return "redirect:./list";
	}
	
	
	@RequestMapping(value = "fileDelete", method = RequestMethod.POST)
	public String fileDelete(BoardFileDTO boardFileDTO, Model model, HttpSession session)throws Exception{
		int result = qnaService.fileDelete(boardFileDTO, session);
		model.addAttribute("result", result);
		return "commons/ajaxResult";
		
	}

}









