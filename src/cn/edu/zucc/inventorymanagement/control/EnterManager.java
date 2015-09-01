package cn.edu.zucc.inventorymanagement.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.inventorymanagement.model.Enter;
import cn.edu.zucc.inventorymanagement.util.BaseException;
import cn.edu.zucc.inventorymanagement.util.BusinessException;
import cn.edu.zucc.inventorymanagement.util.DBUtil;

public class EnterManager
{
	public void createEnter(Enter enter)
	{
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "insert into Enter(houseId,goodsId,supplierId,enterAmount,enterTime,enterPrice,batchId,enterNote,workerId) values(?,?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst = conn.prepareStatement(sql);
			pst.setInt(1, enter.getHouseId());
			pst.setInt(2, enter.getGoodsId());
			pst.setInt(3, enter.getSupplierId());
			pst.setFloat(4, enter.getEnterAmount());
			pst.setDate(5, new java.sql.Date(enter.getEnterTime().getTime()));
			pst.setFloat(6, enter.getEnterPrice());
			pst.setInt(7, enter.getBatchId());
			pst.setString(8, enter.getEnterNote());
			pst.setInt(9, enter.getWorkerId());
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

	public Enter searchEnter(int enterId)
	{
		Enter enter = new Enter();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Enter where enterId = '" + enterId + "' ";
			sql += " order by enterId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{

				enter.setEnterId(rs.getInt(1));
				enter.setHouseId(rs.getInt(2));
				enter.setGoodsId(rs.getInt(3));
				enter.setSupplierId(rs.getInt(4));
				enter.setEnterAmount(rs.getFloat(5));
				enter.setEnterTime(rs.getDate(6));
				enter.setEnterPrice(rs.getFloat(7));
				enter.setBatchId(rs.getInt(8));
				enter.setEnterNote(rs.getString(9));
				enter.setWorkerId(rs.getInt(10));
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
		return enter;
	}

	public List<Enter> loadAllEnter()
	{
		List<Enter> result = new ArrayList<Enter>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Enter  ";
			sql += " order by enterId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Enter enter = new Enter();
				enter.setEnterId(rs.getInt(1));
				enter.setHouseId(rs.getInt(2));
				enter.setGoodsId(rs.getInt(3));
				enter.setSupplierId(rs.getInt(4));
				enter.setEnterAmount(rs.getFloat(5));
				enter.setEnterTime(rs.getDate(6));
				enter.setEnterPrice(rs.getFloat(7));
				enter.setBatchId(rs.getInt(8));
				enter.setEnterNote(rs.getString(9));
				enter.setWorkerId(rs.getInt(10));
				result.add(enter);
			}
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
		return result;
	}

	/*public List<Enter> loadEnter(String keyword)
	{
		// 通过keyword加载所有收入条目
		List<Enter> result = new ArrayList<Enter>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Enter where incomeNote like '%" + keyword
					+ "%' ";
			sql += " and incomeDeleteDate is null ";
			sql += " order by enterId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Enter enter = new Enter();
				enter.setIncomeCreateDate(rs.getDate(1));
				enter.setIncomeAmount(rs.getFloat(2));
				enter.setIncomeId(rs.getInt(3));
				enter.setIncomeTypeId(rs.getInt(4));
				enter.setProjectId(rs.getInt(5));
				enter.setIncomeNote(rs.getString(6));
				result.add(enter);
			}
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
		return result;
	}

	public List<Enter> loadEnterByTime(String lastTime, String nextTime)
	{
		// 通过时间段加载所有收入条目
		List<Enter> result = new ArrayList<Enter>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Enter where incomeCreateDate between '"
					+ lastTime + "' and '" + nextTime + "' ";
			sql += " and incomeDeleteDate is null ";
			sql += " order by enterId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Enter enter = new Enter();
				enter.setIncomeCreateDate(rs.getDate(1));
				enter.setIncomeAmount(rs.getFloat(2));
				enter.setIncomeId(rs.getInt(3));
				enter.setIncomeTypeId(rs.getInt(4));
				enter.setProjectId(rs.getInt(5));
				enter.setIncomeNote(rs.getString(6));
				result.add(enter);
			}
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
		return result;
	}

	public void deleteEnter(int enterId)
	{
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from Enter where enterId=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, enterId);
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
				throw new BusinessException("条目不存在");
			if (rs.getDate("incomeDeleteDate") != null)
				throw new BusinessException("条目已经注销");
			rs.close();
			pst.close();
			sql = "update Enter set incomeDeleteDate=? where enterId=?";
			pst = conn.prepareStatement(sql);
			pst.setTimestamp(1,
					new java.sql.Timestamp(System.currentTimeMillis()));
			pst.setInt(2, enterId);
			pst.execute();
			pst.close();
		}
		catch (BusinessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	}*/

	/*public void modifyEnter(Enter enter)
	{
		//修改条目
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from Enter where enterId=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, enter.getEnterId());
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
				throw new BusinessException("不存在");
			rs.close();
			pst.close();
			sql = "update Enter set incomeNote=?, incomeAmount=?, incomeCreateDate=?, incomeTypeId=? where enterId=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, enter.getIncomeNote());
			pst.setFloat(2, enter.getIncomeAmount());
			pst.setDate(3, new java.sql.Date(enter.getIncomeCreateDate()
					.getTime()));
			pst.setInt(4, enter.getIncomeTypeId());
			pst.setInt(5, enter.getIncomeId());
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
	}*/

	/*public int countIncomeNum(int projectId)
	{
		int num = 0;
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select count(*) from Enter where projectId = '" + projectId
					+ "' ";
			sql += " and incomeDeleteDate is null ";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next())
			{
				num = rs.getInt(1);
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
		return num;
	}*/

	/*public float countIncomeAmount(int projectId)
	{
		float amount = 0;
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select sum(incomeAmount) from Enter where projectId = '"
					+ projectId + "' ";
			sql += " and incomeDeleteDate is null ";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next())
			{
				amount = rs.getInt(1);
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
		return amount;
	}*/
}
