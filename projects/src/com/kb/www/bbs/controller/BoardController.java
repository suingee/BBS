package com.kb.www.bbs.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kb.www.bbs.action.MemberDeleteAction;
import com.kb.www.bbs.action.MemberFindIdAction;
import com.kb.www.bbs.action.MemberFindIdProcAction;
import com.kb.www.bbs.action.MemberFindPwdAction;
import com.kb.www.bbs.action.MemberFindPwdProcAction;
import com.kb.www.bbs.action.MemberJoinAction;
import com.kb.www.bbs.action.MemberJoinProcAction;
import com.kb.www.bbs.action.MemberLoginAction;
import com.kb.www.bbs.action.MemberLoginProcAction;
import com.kb.www.bbs.action.MemberLogoutAction;
import com.kb.www.bbs.action.MemberUpdateAction;
import com.kb.www.bbs.action.ModifyMemberInfoAction;
import com.kb.www.bbs.action.ModifyMemberInfoProcAction;
import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;

@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardController() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String command = requestURI.substring(contextPath.length());
    	ActionForward forward = null;
    	Action action = null;
    	
    	if (command.equals("/memberJoin.do")) {
    		action = new MemberJoinAction();
    	} else if (command.equals("/memberJoinProc.do")) {
    		action = new MemberJoinProcAction();
    	} else if (command.equals("/memberLogin.do")) {
    		action = new MemberLoginAction();
    	} else if (command.equals("/memberLoginProc.do")) {
    		action = new MemberLoginProcAction();
    	} else if (command.equals("/memberLogout.do")) {
    		action = new MemberLogoutAction();
    	} else if (command.equals("/memberDelete.do")) {
    		action = new MemberDeleteAction();
    	} else if (command.equals("/memberUpdate.do")) {
    		action = new MemberUpdateAction();
    	} else if (command.equals("/modifyMemberInfo.do")) {
    		action = new ModifyMemberInfoAction();
    	} else if (command.equals("/modifyMemberInfoProc.do")) {
    		action = new ModifyMemberInfoProcAction();
    	} else if (command.equals("/findId.do")) {
    		action = new MemberFindIdAction();
    	} else if (command.equals("/findIdProc.do")) {
    		action = new MemberFindIdProcAction();
    	} else if (command.equals("/findIdProc.do")) {
    		action = new MemberFindIdProcAction();
    	} else if (command.equals("/findPwd.do")) {
    		action = new MemberFindPwdAction();
    	} else if (command.equals("/findPwdProc.do")) {
    		action = new MemberFindPwdProcAction();
    	} else if (command.equals("/findPwdAfter.do")) {
    		action = new MemberFindPwdAfterAction();
    	}
    	
    	try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			forward = new ActionForward();
			forward.setPath("/views/error.jsp");
			forward.setRedirect(true);
		}
    	
    	if (forward.isRedirect()) {
    		response.sendRedirect(forward.getPath());
    	} else {
    		RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
    		dispatcher.forward(request, response);
    	}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
