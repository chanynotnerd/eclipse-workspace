package kr.ac.mjc.chanyoung.myapp.common.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 테이블과 오브젝트를 매핑하는 functional interface
 */
@FunctionalInterface
public interface RowMapper<T> {

	T mapRow(ResultSet rs) throws SQLException;
}