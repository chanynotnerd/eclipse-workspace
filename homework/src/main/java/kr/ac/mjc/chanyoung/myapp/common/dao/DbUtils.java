package kr.ac.mjc.chanyoung.myapp.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/**
 * Jdbc helper class
 * 
 * @author Jacob
 */
public class DbUtils {

	DataSource ds = null;

	public DbUtils(DataSource ds) {
		this.ds = ds;
	}

	/**
	 * 목록 조회하는 메서드
	 */
	public <T> List<T> list(String sql, RowMapper<T> rowMapper,
			Object... params) throws DataAccessException {

		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			// 파라미터 넣음
			setParams(ps, params);
			ResultSet rs = ps.executeQuery();
			List<T> list = new ArrayList<>();
			while (rs.next()) {
				T t = rowMapper.mapRow(rs);
				list.add(t);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e);
		}
	}

	/**
	 * 1건 조회하는 메서드
	 */
	public <T> T get(String sql, RowMapper<T> rowMapper, Object... params)
			throws DataAccessException {

		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			// 파라미터 넣음
			setParams(ps, params);
			ResultSet rs = ps.executeQuery();
			T t = null;
			if (rs.next()) {
				t = rowMapper.mapRow(rs);
			}
			return t;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e);
		}
	}

	/**
	 * 추가, 수정, 삭제하는 메서드
	 */
	public int update(String sql, Object... params) throws DataAccessException {
		try (Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
			// 파라미터 넣음
			setParams(ps, params);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e);
		}
	}

	/**
	 * PreparedStatement에 파라미터 세팅
	 */
	private void setParams(PreparedStatement ps, Object... params)
			throws SQLException {
		// 파라미터가 있는 경우 파라미터를 넣음
		if (params != null) {
			for (int i = 0; i < params.length; i++)
				ps.setObject(i + 1, params[i]);
		}
	}
}