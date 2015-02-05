package com.carfinance.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class BaseJdbcDaoImpl {
	final Logger			log	= LoggerFactory.getLogger(getClass());
	private JdbcTemplate	jdbcTemplate;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	private final static int	ORACLE_DEFAULT_FETCH_SIZE	= 30;
	@SuppressWarnings("rawtypes")
	public PaginationSupport find(final String sql, final Object[] params, RowMapper rm, int countOnEachPage, int page) {
		PaginationSupport ps = new PaginationSupport();
		ps.setCountOnEachPage(countOnEachPage);
		ps.setPage(page);
		return find(ps, sql, params, rm);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PaginationSupport find(final PaginationSupport ps, final String sql, final Object[] params, RowMapper rm) {
		try {
			if (log.isDebugEnabled()) {
				log.debug(PaginationHelper.decorateCountSql(sql) + "\n PARAM " + Arrays.asList(params));
				log.debug(PaginationHelper.decorateToPaginationSql(sql, ps.getStartIndex(), ps.getStartIndex() + ps.getCountOnEachPage()) + "\n PARAM " + Arrays.asList(params));
			}
			int totalCount = getJdbcTemplate().queryForInt(PaginationHelper.decorateCountSql(sql), params);
			ps.setTotalCount(totalCount);
			List items = getJdbcTemplate().query(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement pstmt = con.prepareStatement(PaginationHelper.decorateToPaginationSql(sql, ps.getStartIndex(), ps.getCountOnEachPage()));
					for (int i = 0; i < params.length; i++) {
						pstmt.setObject(i + 1, params[i]);
					}
					pstmt.setFetchSize(ORACLE_DEFAULT_FETCH_SIZE);
					return pstmt;
				}
			}, rm);
			ps.setItems(items);
			return ps;
		} catch (DataAccessException e) {
			log.debug("error count sql --" + PaginationHelper.decorateCountSql(sql));
			log.debug("error paging sql --" + PaginationHelper.decorateToPaginationSql(sql, ps.getStartIndex(), ps.getCountOnEachPage()));
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
