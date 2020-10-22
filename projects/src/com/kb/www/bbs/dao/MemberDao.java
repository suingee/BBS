package com.kb.www.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kb.www.bbs.vo.MemberVo;

import static com.kb.www.common.JdbcUtil.*;

public class MemberDao {
	private Connection con;
	
	// ΩÃ±€≈Ê ∆–≈œ
	private MemberDao() {
		
	}
	
	public static MemberDao getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	private static class LazyHolder {
		private static final MemberDao INSTANCE = new MemberDao();
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int getMemberCount(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("SELECT COUNT(*) FROM member WHERE BINARY(id) = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return count;
	}
	
	public int joinMember(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("INSERT INTO member(nm, id, pwd) VALUES(?, ?, ?)");
			pstmt.setString(1, vo.getNm());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getPwd());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
	
	public MemberVo getMember(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVo vo = null;
		try {
			pstmt = con.prepareStatement("SELECT * FROM member WHERE BINARY(id) = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new MemberVo();
				vo.setMb_sq(rs.getInt("mb_sq"));
				vo.setNm(rs.getString("nm"));
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return vo;
	}
	
	public int updateLoginState(MemberVo vo) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("UPDATE member SET mb_sq = ?");
			pstmt.setInt(1, vo.getMb_sq());
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return count;
	}
	
	public int getMemberSequence(String id) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int sq = 0;
        try {
            pstmt = con.prepareStatement("SELECT mb_sq FROM member WHERE id = ?");
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                sq = rs.getInt("mb_sq");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            close(rs);
            close(pstmt);
        }
        return sq;
    }
	
	public int deleteMember(String id) {
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			pstmt = con.prepareStatement("DELETE FROM member WHERE BINARY(id) = ?");
			pstmt.setString(1, id);
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return count;
	}
}
