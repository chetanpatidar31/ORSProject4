package com.rays.pro4.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.pro4.Bean.TaskBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Util.JDBCDataSource;

public class TaskModel {

	public Integer nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_task");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			pk = rs.getInt(1);
		}
		JDBCDataSource.closeConnection(conn);

		return pk + 1;
	}

	public void add(TaskBean bean) throws Exception {

		TaskBean existBean = findByTaskTitle(bean.getTaskTitle());

		if (existBean != null) {
			throw new DuplicateRecordException("tasktitle already exist..!!");
		}
		int pk = nextPk();

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("insert into st_task values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		pstmt.setLong(1, pk);
		pstmt.setDate(2, new java.sql.Date(bean.getCreationDate().getTime()));
		pstmt.setString(3, bean.getTaskTitle());
		pstmt.setString(4, bean.getDetails());
		pstmt.setString(5, bean.getAssignedTo());
		pstmt.setString(6, bean.getTaskStatus());
		pstmt.setString(7, bean.getCreatedBy());
		pstmt.setString(8, bean.getModifiedBy());
		pstmt.setTimestamp(9, bean.getCreatedDatetime());
		pstmt.setTimestamp(10, bean.getModifiedDatetime());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data inserted => " + i);
	}

	public void update(TaskBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(
				"update st_task set creation_date = ?, task_title = ?, details = ?, `assigned_to` = ?, task_status = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

		pstmt.setDate(1, new java.sql.Date(bean.getCreationDate().getTime()));
		pstmt.setString(2, bean.getTaskTitle());
		pstmt.setString(3, bean.getDetails());
		pstmt.setString(4, bean.getAssignedTo());
		pstmt.setString(5, bean.getTaskStatus());
		pstmt.setString(6, bean.getCreatedBy());
		pstmt.setString(7, bean.getModifiedBy());
		pstmt.setTimestamp(8, bean.getCreatedDatetime());
		pstmt.setTimestamp(9, bean.getModifiedDatetime());
		pstmt.setLong(10, bean.getId());

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("Data updated" + i);

	}

	public void delete(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from st_task where id =?");

		pstmt.setLong(1, id);

		int i = pstmt.executeUpdate();

		pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data deleted => " + i);
	}

	public TaskBean findByPk(long id) throws Exception {

		Connection conn = null;
		TaskBean bean = null;

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("select * from st_task where id=?");
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new TaskBean();
				bean.setId(rs.getLong(1));
				bean.setCreationDate(rs.getDate(2));
				bean.setTaskTitle(rs.getString(3));
				bean.setDetails(rs.getString(4));
				bean.setAssignedTo(rs.getString(5));
				bean.setTaskStatus(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));

			}
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting Task by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;

	}

	private TaskBean findByTaskTitle(String taskTitle) throws Exception {
		Connection conn = null;
		TaskBean bean = null;

		conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt;

		pstmt = conn.prepareStatement("select * from st_task where task_title=?");

		pstmt.setString(1, taskTitle);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			bean = new TaskBean();
			bean.setId(rs.getLong(1));
			bean.setCreationDate(rs.getDate(2));
			bean.setTaskTitle(rs.getString(3));
			bean.setDetails(rs.getString(4));
			bean.setAssignedTo(rs.getString(5));
			bean.setTaskStatus(rs.getString(6));
			bean.setCreatedBy(rs.getString(7));
			bean.setModifiedBy(rs.getString(8));
			bean.setCreatedDatetime(rs.getTimestamp(9));
			bean.setModifiedDatetime(rs.getTimestamp(10));

		}

		JDBCDataSource.closeConnection(conn);
		return bean;
	}

	public List list() throws Exception {
		return search(null, 0, 0);
	}

	public List search(TaskBean bean, int pageNo, int pageSize) throws Exception {

		StringBuffer sql = new StringBuffer("select * from st_task where 1=1");

		if (bean != null) {
			if (bean.getTaskTitle() != null && bean.getTaskTitle().length() > 0) {
				sql.append(" and task_title like '" + bean.getTaskTitle() + "%'");
			}

		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		System.out.println("sql ==>> " + sql.toString());

		Connection conn = null;
		List list = new ArrayList();

		try {
			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new TaskBean();
				bean.setId(rs.getLong(1));
				bean.setCreationDate(rs.getDate(2));
				bean.setTaskTitle(rs.getString(3));
				bean.setDetails(rs.getString(4));
				bean.setAssignedTo(rs.getString(5));
				bean.setTaskStatus(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				list.add(bean);
			}
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search task " + e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}
}
