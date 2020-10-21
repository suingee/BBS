package com.kb.www.bbs.home.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;

public class HomeAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("/views/home.jsp");
		return forward;
	}

}
