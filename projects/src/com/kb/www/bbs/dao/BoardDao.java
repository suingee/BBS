package com.kb.www.bbs.dao;

import java.sql.Connection;

public class BoardDao {
	private Connection con;
	
	// ΩÃ±€≈Ê ∆–≈œ
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
}
