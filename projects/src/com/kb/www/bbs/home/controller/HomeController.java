package com.kb.www.bbs.home.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kb.www.bbs.home.action.HomeAction;
import com.kb.www.common.Action;
import com.kb.www.common.ActionForward;

@WebServlet("/index.jsp")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HomeController() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String command = requestURI.substring(contextPath.length());
    	ActionForward forward = null;
    	Action action = new HomeAction();
    	
    	try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			forward = new ActionForward();
			forward.setPath("/views/error.jsp");
			forward.setRedirect(true);
		}
    	
    	if (forward.isRedirect()) {
    		// �����̷�Ʈ
    		response.sendRedirect(forward.getPath());
    	} else {
    		// ����ġ
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
