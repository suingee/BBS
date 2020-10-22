package com.kb.www.bbs.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kb.www.bbs.service.MemberService;
import com.kb.www.bbs.vo.MemberVo;
import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;
import com.kb.www.common.LoginManager;

public class MemberLogoutAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(request.getSession());
		if (id != null) {
			lm.removeSession(id);
		}

		ActionForward forward = new ActionForward();
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
	}

	public void logoutProc(String id) {
		MemberVo memberVo = new MemberVo();
		memberVo.setId(id);

		MemberService svc = new MemberService();
		if (!svc.logoutMember(memberVo)) {
			System.out.println(id + " 회원의 로그아웃 처리에 실패하였습니다.");
		}
	}
}
