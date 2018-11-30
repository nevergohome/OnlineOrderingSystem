package com.onlineordering.util;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 * 数据库操作辅助类
 * @version 3.0
 * @author yaohc
 */
public class DBUtil {

	private static Logger logger = Logger.getLogger("DBUtil");
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	//private static Connection conn;
	/**
	 * 该语句必须是 SQL INSERT、UPDATE 、DELETE 语句
	 * 
	 * @param sql 
	 * @return
	 * @throws Exception
	 */
	public int execute(String sql) throws Exception {
		return execute(sql,new Object[]{});
	}

	
	/**
	 * 该语句必须是 SQL INSERT、UPDATE 、DELETE 语句
	 *      insert into table values(?,?,?,?)
	 * @param sql
	 * @param paramList：参数，与SQL语句中的占位符一
	 * @return
	 * @throws Exception
	 */
	public int execute(String sql,Object[] paramList) throws Exception {
		if (sql == null || sql.trim().equals("")) {
			logger.info("parameter is valid!");
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = getConnection();
			pstmt = DBUtil.getPreparedStatement(conn, sql);
			setPreparedStatementParam(pstmt, paramList);
			if (pstmt == null) {
				return -1;
			}
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new Exception(e);
		} finally {
			closeStatement(pstmt);
			closeConn(conn);
		}

		return result;
	}
	/**
	 * 事物处理类
	 * @param connection
	 * @param sql
	 * @param paramList：参数，与SQL语句中的占位符一
	 * @return
	 * @throws Exception
	 */
	public int execute(Connection conn,String sql,Object[] paramList) throws Exception {
		if (sql == null || sql.trim().equals("")) {
			logger.info("parameter is valid!");
		}

		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = DBUtil.getPreparedStatement(conn, sql);
			setPreparedStatementParam(pstmt, paramList);
			if (pstmt == null) {
				return -1;
			}
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw new Exception(e);
		} finally {
			closeStatement(pstmt);
		}

		return result;
	}
	
	/**
	 * 获取实体类型的方法，type为实体类类型。
	 * @param type
	 * @param sql
	 * @param paramList
	 * @return
	 * @throws Exception
	 */
	public Object getObject(Class<?> type, String sql,Object[] paramList)  throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		Object obj = type.newInstance();
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		Map	map = getObject(sql, paramList);
		if(map != null){
			for (int i = 0; i< propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (map != null && map.containsKey(propertyName)) {
					Object value = map.get(propertyName);
					Object[] args = new Object[1];
					args[0] = value;
					try{
						descriptor.getWriteMethod().invoke(obj, args);
					}catch(Exception e){
						logger.info("检测一下Table列，和实体类属性：" + propertyName + ""
								+ "是否一致，并且是否是" + value.getClass() + "类型");
						throw new Exception("检测一下Table列，和实体类属性：" + propertyName + ""
								+ "是否一致，并且是否是" + value.getClass() + "类型");
					}
				}
			}
		}else{
			obj = null;
		}
		return obj;
	}
	
	public List<Class<?>> getQueryList(Class<?> type, String sql,Object[] paramList)  throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		List<Map<String,Object>> list = getQueryList(sql, paramList);
		List beanList = new ArrayList();
		
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			Object obj = type.newInstance();
			for (int i = 0; i< propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (map != null && map.containsKey(propertyName)) {
					Object value = map.get(propertyName);
					Object[] args = new Object[1];
					args[0] = value;
					try{
						descriptor.getWriteMethod().invoke(obj, args);
					}catch(Exception e){
						logger.info("检测一下Table列，和实体类属性：" + propertyName + ""
								+ "是否一致，并且是否是" + value.getClass() + "类型");
						throw new Exception("检测一下Table列，和实体类属性：" + propertyName + ""
								+ "是否一致，并且是否是" + value.getClass() + "类型");					}
				}
			}
			beanList.add(obj);
		}
		
		return beanList;
	}
	
	
	/**
	 * 将查询数据库获得的结果集转换为Map对象
	 * 
	 * @param sql：查询
	 * @return
	 */
	public List<Map<String, Object>> getQueryList(String sql) throws Exception {
		return getQueryList(sql,new Object[]{});
	}

	/**
	 * 将查询数据库获得的结果集转换为Map对象
	 * 
	 * @param sql：查询
	 * @param paramList：参数
	 * @return
	 */
	public List<Map<String, Object>> getQueryList(String sql, Object[] paramList) throws Exception {
		if (sql == null || sql.trim().equals("")) {
			logger.info("parameter is valid!");
			return null;
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Map<String, Object>> queryList = null;
		try {
			conn = getConnection();
			pstmt = DBUtil.getPreparedStatement(conn, sql);
			setPreparedStatementParam(pstmt, paramList);
			if (pstmt == null) {
				return null;
			}
			rs = getResultSet(pstmt);
			queryList = getQueryList(rs);
		} catch (RuntimeException e) {
			logger.info(e.getMessage());
			System.out.println("parameter is valid!");
			throw new Exception(e);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConn(conn);
		}
		return queryList;
	}

	/**
	 * 分页查询
	 * @param sql 
	 * @param params 查询条件参数
	 * @param page  分页信息
	 * @return
	 */
	public Page getQueryPage(Class<?> type, String sql,Object[] params,Page page){
		int totalPages = 0;	//页数
		Long rows = 0l;//数据记录数
		
		//分页工具类
		List<Class<?>> list = null;
		Map countMap = null;
		try {
			list = this.getQueryList(type,sql
					+ " limit " 
					+ (page.getCurPage()-1)*page.getPageNumber() 
					+" , "+page.getPageNumber(),params);			
			countMap = this.getObject(" "
					+ "select count(*) c from ("+ sql +") as t ");
			rows =  (Long)countMap.get("c");
			//求余数
			if(rows % page.getPageNumber() ==0){
				totalPages = rows.intValue() / page.getPageNumber();
			}else{
				totalPages = rows.intValue() / page.getPageNumber() + 1;
			}
			
			page.setRows(rows.intValue());
			page.setDate(list);
			page.setTotalPage(totalPages);
		}catch(Exception e){
			e.printStackTrace();
		}
		return page;
	}
	
	/**
	 * 将查询数据库获得的结果集转换为Map对象
	 * @param sql：查询
	 * @return
	 */
	public Map<String, Object> getObject(String sql) throws Exception {
		return getObject(sql,new Object[]{});
	}
	
	/**
	 * 将查询数据库获得的结果集转换为Map对象
	 * 
	 * @param sql：查询
	 * @param paramList：参数
	 * @return
	 */
	public Map<String, Object> getObject(String sql, Object[] paramList) throws Exception {
		if (sql == null || sql.trim().equals("")) {
			logger.info("parameter is valid!");
			return null;
		}

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map map = new HashMap<String,Object>();
		try {
			conn = getConnection();
			pstmt = DBUtil.getPreparedStatement(conn, sql);
			setPreparedStatementParam(pstmt, paramList);
			if (pstmt == null) {
				return null;
			}
			rs = getResultSet(pstmt);
			List list = getQueryList(rs);
			if(list.isEmpty()){
				return null;
			}
			map = (HashMap) list.get(0);
		} catch (RuntimeException e) {
			logger.info(e.getMessage());
			logger.info("parameter is valid!");
			throw new Exception(e);
		} finally {
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConn(conn);
		}
		return map;
	}

	
	private static PreparedStatement getPreparedStatement(Connection conn, String sql) throws Exception {
		if (conn == null || sql == null || sql.trim().equals("")) {
			return null;
		}
		PreparedStatement pstmt = conn.prepareStatement(sql.trim());
		return pstmt;
	}

	private void setPreparedStatementParam(PreparedStatement pstmt, Object[] paramList) throws Exception {
		if (pstmt == null || paramList == null) {
			return;
		}
		DateFormat df = DateFormat.getDateTimeInstance();
		for (int i = 0; i < paramList.length; i++) {
			//-
			if (paramList[i] instanceof Integer) {
				int paramValue = ((Integer) paramList[i]).intValue();
				pstmt.setInt(i + 1, paramValue);
			} else if (paramList[i] instanceof Float) {
				float paramValue = ((Float) paramList[i]).floatValue();
				pstmt.setFloat(i + 1, paramValue);
			} else if (paramList[i] instanceof Double) {
				double paramValue = ((Double) paramList[i]).doubleValue();
				pstmt.setDouble(i + 1, paramValue);
			} else if (paramList[i] instanceof Date) {
				pstmt.setString(i + 1, df.format((Date) paramList[i]));
			} else if (paramList[i] instanceof Long) {
				long paramValue = ((Long) paramList[i]).longValue();
				pstmt.setLong(i + 1, paramValue);
			} else if (paramList[i] instanceof String) {
				pstmt.setString(i + 1, (String) paramList[i]);
			}
			//= pstmt.setObject(i + 1, paramList[i]);
		}
		return;
	}

	/**
	 * 获得数据库查询结果集
	 * 
	 * @param pstmt
	 * @return
	 * @throws Exception
	 */
	private ResultSet getResultSet(PreparedStatement pstmt) throws Exception {
		if (pstmt == null) {
			return null;
		}
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}

	/**
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	private List<Map<String, Object>> getQueryList(ResultSet rs) throws Exception {
		if (rs == null) {
			return null;
		}
		ResultSetMetaData rsMetaData = rs.getMetaData();
		int columnCount = rsMetaData.getColumnCount();
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		while (rs.next()) {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			for (int i = 0; i < columnCount; i++) {
				dataMap.put(rsMetaData.getColumnLabel(i + 1), rs.getObject(i + 1));
			}
			dataList.add(dataMap);
		}
		return dataList;
	}

	/**
	 * 关闭数据库
	 * 
	 * @param conn
	 */
	private void closeConn(Connection conn) {
		if (conn == null) {
			return;
		}
		try {
			conn.close();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * 关闭
	 * 
	 * @param stmt
	 */
	private void closeStatement(Statement stmt) {
		if (stmt == null) {
			return;
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * 关闭
	 * 
	 * @param rs
	 */
	private void closeResultSet(ResultSet rs) {
		if (rs == null) {
			return;
		}
		try {
			rs.close();
		} catch (SQLException e) {
			logger.info(e.getMessage());
		}
	}

	/**
	 * 可以选择三个不同的数据库连接
	 * 
	 * @param JDBC
	 *            ,JNDI（依赖web容器 DBCP
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {
		Connection conn = tl.get();
		if(conn ==null){
			conn = DBDataSource.getConnectionDbcp();
		}
		return conn;
	}
	/***********事务处理方法************/
	/**
	 * 开启事务
	 */
	public static void beginTranscation() throws SQLException{
		Connection conn = tl.get();
		if(conn != null){
			logger.info("事务已经开始！");
			throw new SQLException("事务已经开始！");
		}
		conn = DBDataSource.getConnectionDbcp();
		conn.setAutoCommit(false);
		tl.set(conn);
	}
	/**
	 * 结束事务
	 * @throws SQLException
	 */
	public static void endTranscation() throws SQLException{
		Connection conn = tl.get();
		if(conn == null){
			logger.info("当前没有事务！");
			throw new SQLException("当前没有事务！");
		}
		conn.commit();
	}
	/**
	 * 回滚
	 * @throws SQLException
	 */
	public static void rollback() throws SQLException{
		Connection conn = tl.get();
		if(conn == null){
			logger.info("当前没有事务,不能回滚！");
			throw new SQLException("当前没有事务,不能回滚！");
		}
		conn.rollback();
	}
	
	/**
	 * 事务处理，关闭资源
	 * @throws SQLException
	 */
	public static void closeConn() throws SQLException{
		Connection conn = tl.get();
		if(conn == null){
			logger.info("当前没有连接，不需要关闭Connection。");
			throw new SQLException("当前没有连接，不需要关闭Connection。");
		}
		conn.close();
		tl.remove();
	}
	
}
