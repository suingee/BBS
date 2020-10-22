package com.kb.www.bbs.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kb.www.bbs.service.MemberService;
import com.kb.www.bbs.vo.MemberVo;
import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.BCrypt;
import com.kb.www.common.LoginManager;
import com.kb.www.common.RegExp;

import static com.kb.www.common.RegExp.*;

public class MemberDeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�߸��� �����Դϴ�.'); location.href='/';</script>");
			out.close();
			return null;
		}
		
		MemberService svc = new MemberService();
		
		if (!svc.deleteMember(id)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('ȸ�� Ż�� �����Ͽ����ϴ�.'); history.back();</script>");
			out.close();
			return null;
		}
		lm.removeSession(id);
		ActionForward forward = new ActionForward();
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
	}
}
