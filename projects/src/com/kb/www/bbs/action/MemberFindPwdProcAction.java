package com.kb.www.bbs.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kb.www.bbs.service.MemberService;
import com.kb.www.bbs.vo.MemberVo;
import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.BCrypt;
import com.kb.www.common.LoginManager;
import com.kb.www.common.RegExp;

import static com.kb.www.common.RegExp.*;

public class MemberFindPwdProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id != null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�߸��� �����Դϴ�.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		String name = request.getParameter("name");
		id = request.getParameter("id");
		if (name == null || name.equals("")
				|| id == null || id.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�߸��� �����Դϴ�.');location.href='/';</script>");
			out.close();
			return null;
		}
		MemberVo memberVo = new MemberVo();
		memberVo.setNm(name);
		memberVo.setId(id);
		
		MemberService svc = new MemberService();
		
		if (mb_sq == 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원 정보를 찾을 수 없습니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/findPwdProc.jsp");
		return forward;
	}
}
