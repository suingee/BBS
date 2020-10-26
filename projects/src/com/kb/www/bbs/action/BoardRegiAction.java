package com.kb.www.bbs.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kb.www.bbs.service.BoardService;
import com.kb.www.bbs.vo.BoardVo;
import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.LoginManager;

public class BoardRegiAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("callback", "/write.do");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스 입니다.');location.href='/login.do';</script>");
			out.close();
			return null;
		}
		
		String sj = request.getParameter("sj");
		String cn = request.getParameter("cn");
		if (sj == null || sj.equals("") || sj.length() > 50 || cn == null || cn.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스 입니다.');location.href='/login.do';</script>");
			out.close();
			return null;
		}
		
		BoardVo vo = new BoardVo();
		vo.setId(id);
		vo.setSj(sj);
		vo.setCn(cn);
		
		BoardService svc = new BoardService();
		if (!svc.registerBoard(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스 입니다.');history.back';</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/list.do");
		forward.setRedirect(true);
		return forward;
	}
}
