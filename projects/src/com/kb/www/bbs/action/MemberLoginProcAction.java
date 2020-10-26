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

public class MemberLoginProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		if (id == null || id.equals("")
				|| pwd == null || pwd.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�߸��� �����Դϴ�.'); location.href='/';</script>");
			out.close();
			return null;
		}
		
		MemberService svc = new MemberService();
		MemberVo memberVo = svc.getMember(id);
		if (memberVo == null || !BCrypt.checkpw(pwd, memberVo.getPwd())) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('���̵� �Ǵ� ��й�ȣ�� Ȯ���ϼ���.'); history.back();</script>");
            out.close();
            return null;
		}
		
		if (!svc.loginMember(memberVo)) {
			response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('�α��� ������ Ȯ���� �ּ���.'); location.href='/';</script>");
            out.close();
            return null;
		}
		
		LoginManager lm = LoginManager.getInstance();
		lm.setSession(request.getSession(), id);
		
		HttpSession session = request.getSession();
		String callback = (String) session.getAttribute("callback");
		ActionForward forward = new ActionForward();
		if (callback != null) {
			forward.setPath(callback);
			session.removeAttribute("callback");
		} else {
			forward.setPath("/");
		}
		
		forward.setRedirect(true);
		return forward;
	}
}
