package cn.edu.zucc.inventorymanagement.control;

import java.sql.Connection;
import java.sql.SQLException;

import cn.edu.zucc.inventorymanagement.model.Worker;
import cn.edu.zucc.inventorymanagement.util.BusinessException;
import cn.edu.zucc.inventorymanagement.util.DBUtil;

public class WorkerManager
{
	public static Worker currentWorker = null;

	public void createWorker(Worker worker)
	{
		// 创建用户
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "insert into Worker(workerName, workerPwd, workerPermission, workerDepartment, workerPhone) values(?,?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst = conn.prepareStatement(sql);
			pst.setString(1, worker.getWorkerName());
			pst.setString(2, worker.getWorkerPwd());
			pst.setInt(3, worker.getWorkerPermission());
			pst.setString(4, worker.getWorkerDepartment());
			pst.setString(5, worker.getWorkerPhone());
			pst.execute();
			pst.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (conn != null)
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public Worker searchWorker(String name)
	{
		// 在数据库中查询用户是否存在
		Worker worker = new Worker();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Worker where workerName = '" + name + "' ";
			sql += " order by workerId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next())
			{
				worker.setWorkerId(rs.getInt(1));
				worker.setWorkerName(rs.getString(2));
				worker.setWorkerPwd(rs.getString(3));
				worker.setWorkerPermission(rs.getInt(4));
				worker.setWorkerDepartment(rs.getString(5));
				worker.setWorkerPhone(rs.getString(6));
			}
			else
			{
				worker.setWorkerName(null);
				worker.setWorkerPwd(null);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (conn != null)
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return worker;
	}

	public boolean verifyWorker(String name, String password)
			throws SQLException
	{
		Worker worker = (new WorkerManager()).searchWorker(name);
		// 如果数据库返回的用户名、密码与输入的用户名、密码一致 则验证成功
		if (worker.getWorkerName().equals(name)
				&& worker.getWorkerPwd().equals(password))
			return true;
		return false;
	}

	public void modifyWorkerPassword(Worker worker) throws BusinessException
	{
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from Worker where workerId=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, worker.getWorkerId());
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
				throw new BusinessException("不存在");
			rs.close();
			pst.close();
			sql = "update Worker set workerPwd=? where workerId=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, worker.getWorkerPwd());
			pst.setInt(2, worker.getWorkerId());
			pst.execute();
			pst.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (conn != null)
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}