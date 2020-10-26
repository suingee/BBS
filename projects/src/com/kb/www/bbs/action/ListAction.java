package com.kb.www.bbs.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kb.www.bbs.service.BoardService;
import com.kb.www.bbs.vo.BoardVo;
import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;

public class ListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardService svc = new BoardService();
		ArrayList<BoardVo> list = svc.getBoardList();

		request.setAttribute("list", list);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/listForm.jsp");
		return forward;
	}
}
