package com.kb.www.bbs.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kb.www.bbs.service.MemberService;
import com.kb.www.bbs.vo.MemberVo;
import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.BCrypt;
import com.kb.www.common.RegExp;

import static com.kb.www.common.RegExp.*;

public class MemberJoinProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String confirmPwd = request.getParameter("confirm_pwd");
		if (name == null || name.equals("") || !RegExp.isValid(MEMBER_NAME, name)
				|| id == null || id.equals("") || !RegExp.isValid(MEMBER_ID, id)
				|| pwd == null || pwd.equals("") || !RegExp.isValid(MEMBER_PASSWORD, pwd)
				|| confirmPwd == null || confirmPwd.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/';</script>");
			out.close();
			return null;
		}
		
		if (!pwd.equals(confirmPwd)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/';</script>");
			out.close();
			return null;
		}
		MemberService svc = new MemberService();
		int count = svc.getMemberCount(id);
		if (count > 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/';</script>");
			out.close();
			return null;
		}
		
		MemberVo memberVo = new MemberVo();
		memberVo.setNm(name);
		memberVo.setId(id);
		memberVo.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));
		
		if (!svc.joinMember(memberVo)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원 가입에 실패하였습니다.'); location.href='/';</script>");
			out.close();
			return null;
		}
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
	}
}
