package cn.edu.zucc.inventorymanagement.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.inventorymanagement.model.Exit;
import cn.edu.zucc.inventorymanagement.util.BaseException;
import cn.edu.zucc.inventorymanagement.util.BusinessException;
import cn.edu.zucc.inventorymanagement.util.DBUtil;

public class ExitManager
{
	public void createExit(Exit exit)
	{
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "insert into [Exit](houseId,goodsId,customerId,exitAmount,exitTime,exitPrice,batchId,exitNote,workerId) values(?,?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, exit.getHouseId());
			pst.setInt(2, exit.getGoodsId());
			pst.setInt(3, exit.getCustomerId());
			pst.setFloat(4, exit.getExitAmount());
			pst.setDate(5, new java.sql.Date(exit.getExitTime().getTime()));
			pst.setFloat(6, exit.getExitPrice());
			pst.setInt(7, exit.getBatchId());
			pst.setString(8, exit.getExitNote());
			pst.setInt(9, exit.getWorkerId());
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

	public Exit searchExit(int exitId)
	{
		Exit exit = new Exit();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from [Exit] where exitId = '" + exitId + "' ";
			sql += " order by exitId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{

				exit.setExitId(rs.getInt(1));
				exit.setHouseId(rs.getInt(2));
				exit.setGoodsId(rs.getInt(3));
				exit.setCustomerId(rs.getInt(4));
				exit.setExitAmount(rs.getFloat(5));
				exit.setExitTime(rs.getDate(6));
				exit.setExitPrice(rs.getFloat(7));
				exit.setBatchId(rs.getInt(8));
				exit.setExitNote(rs.getString(9));
				exit.setWorkerId(rs.getInt(10));
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
		return exit;
	}

	public List<Exit> loadAllExit()
	{
		List<Exit> result = new ArrayList<Exit>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from [Exit]  ";
			sql += " order by exitId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Exit exit = new Exit();
				exit.setExitId(rs.getInt(1));
				exit.setHouseId(rs.getInt(2));
				exit.setGoodsId(rs.getInt(3));
				exit.setCustomerId(rs.getInt(4));
				exit.setExitAmount(rs.getFloat(5));
				exit.setExitTime(rs.getDate(6));
				exit.setExitPrice(rs.getFloat(7));
				exit.setBatchId(rs.getInt(8));
				exit.setExitNote(rs.getString(9));
				exit.setWorkerId(rs.getInt(10));
				result.add(exit);
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

	/*public List<Exit> loadExit(String keyword)
	{
		// 通过keyword加载所有收入条目
		List<Exit> result = new ArrayList<Exit>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Exit where incomeNote like '%" + keyword
					+ "%' ";
			sql += " and incomeDeleteDate is null ";
			sql += " order by exitId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Exit exit = new Exit();
				exit.setIncomeCreateDate(rs.getDate(1));
				exit.setIncomeAmount(rs.getFloat(2));
				exit.setIncomeId(rs.getInt(3));
				exit.setIncomeTypeId(rs.getInt(4));
				exit.setProjectId(rs.getInt(5));
				exit.setIncomeNote(rs.getString(6));
				result.add(exit);
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

	public List<Exit> loadExitByTime(String lastTime, String nextTime)
	{
		// 通过时间段加载所有收入条目
		List<Exit> result = new ArrayList<Exit>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Exit where incomeCreateDate between '"
					+ lastTime + "' and '" + nextTime + "' ";
			sql += " and incomeDeleteDate is null ";
			sql += " order by exitId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Exit exit = new Exit();
				exit.setIncomeCreateDate(rs.getDate(1));
				exit.setIncomeAmount(rs.getFloat(2));
				exit.setIncomeId(rs.getInt(3));
				exit.setIncomeTypeId(rs.getInt(4));
				exit.setProjectId(rs.getInt(5));
				exit.setIncomeNote(rs.getString(6));
				result.add(exit);
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

	public void deleteExit(int exitId)
	{
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from Exit where exitId=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, exitId);
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
				throw new BusinessException("条目不存在");
			if (rs.getDate("incomeDeleteDate") != null)
				throw new BusinessException("条目已经注销");
			rs.close();
			pst.close();
			sql = "update Exit set incomeDeleteDate=? where exitId=?";
			pst = conn.prepareStatement(sql);
			pst.setTimestamp(1,
					new java.sql.Timestamp(System.currentTimeMillis()));
			pst.setInt(2, exitId);
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

	/*public void modifyExit(Exit exit)
	{
		//修改条目
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from Exit where exitId=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, exit.getExitId());
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
				throw new BusinessException("不存在");
			rs.close();
			pst.close();
			sql = "update Exit set incomeNote=?, incomeAmount=?, incomeCreateDate=?, incomeTypeId=? where exitId=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, exit.getIncomeNote());
			pst.setFloat(2, exit.getIncomeAmount());
			pst.setDate(3, new java.sql.Date(exit.getIncomeCreateDate()
					.getTime()));
			pst.setInt(4, exit.getIncomeTypeId());
			pst.setInt(5, exit.getIncomeId());
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
			sql = "select count(*) from Exit where projectId = '" + projectId
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
			sql = "select sum(incomeAmount) from Exit where projectId = '"
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
