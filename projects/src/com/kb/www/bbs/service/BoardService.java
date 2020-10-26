package com.kb.www.bbs.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kb.www.bbs.dao.BoardDao;
import com.kb.www.bbs.dao.MemberDao;
import com.kb.www.bbs.vo.BoardVo;
import com.kb.www.bbs.vo.MemberVo;

import static com.kb.www.common.JdbcUtil.*;

public class BoardService {
	public ArrayList<BoardVo> getBoardList() {
		BoardDao dao = BoardDao.getInstance();
        Connection con = getConnection();
        dao.setConnection(con);
        ArrayList<BoardVo> list = dao.getBoardList();
        close(con);
        return list;
	}
	
	public boolean registerBoard(BoardVo vo) {
		MemberDao dao = MemberDao.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean isSucess = false;
		int count = dao.registerBoard(vo);
		if (count > 0) {
			isSucess = true;
			commit(con);
		} else {
			isSucess = false;
			rollback(con);
		}
		close(con);
		return isSucess;
	}
}
