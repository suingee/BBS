package com.kb.www.bbs.dao;

import static com.kb.www.common.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.kb.www.bbs.vo.BoardVo;

public class BoardDao {
	private Connection con;

	private BoardDao() {

	}

	public static BoardDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final BoardDao INSTANCE = new BoardDao();
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public ArrayList<BoardVo> getBoardList() {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardVo> list = new ArrayList<BoardVo>();
		try {
			pstmt = con.prepareStatement("SELECT * FROM board ORDER BY db_sq DESC");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVo vo = new BoardVo();
				vo.setDb_sq(rs.getInt("db_sq"));
				vo.setSj(rs.getString("sj"));
				vo.setHit(rs.getInt("hit"));
				vo.setDttm(rs.getString("dttm"));
				vo.setId(rs.getString("id"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int registerBoard(BoardVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("INSERT INTO board(sj, cn, id) VALUES(?, ?, ?)");
			pstmt.setString(1, vo.getSj());
			pstmt.setString(2, vo.getCn());
			pstmt.setString(3, vo.getId());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
}
