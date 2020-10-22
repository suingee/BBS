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

public class MemberLoginProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		if (id == null || id.equals("")
				|| pwd == null || pwd.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.'); location.href='/';</script>");
			out.close();
			return null;
		}
		
		MemberService svc = new MemberService();
		MemberVo memberVo = svc.getMember(id);
		if (memberVo == null || !BCrypt.checkpw(pwd, memberVo.getPwd())) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('아이디 또는 비밀번호를 확인하세요.'); history.back();</script>");
            out.close();
            return null;
		}
		
		if (!svc.loginMember(memberVo)) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인 정보를 확인해 주세요.'); location.href='/';</script>");
            out.close();
            return null;
		}
		
		LoginManager lm = LoginManager.getInstance();
		lm.setSession(request.getSession(), id);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
	}
}
