package cn.edu.zucc.inventorymanagement.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.inventorymanagement.model.Return;
import cn.edu.zucc.inventorymanagement.util.BaseException;
import cn.edu.zucc.inventorymanagement.util.BusinessException;
import cn.edu.zucc.inventorymanagement.util.DBUtil;

public class ReturnManager
{
	public void createReturn(Return re)
	{
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "insert into [Return](houseId,goodsId,customerId,returnAmount,returnTime,returnPrice,batchId,returnNote,workerId) values(?,?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, re.getHouseId());
			pst.setInt(2, re.getGoodsId());
			pst.setInt(3, re.getCustomerId());
			pst.setFloat(4, re.getReturnAmount());
			pst.setDate(5, new java.sql.Date(re.getReturnTime().getTime()));
			pst.setFloat(6, re.getReturnPrice());
			pst.setInt(7, re.getBatchId());
			pst.setString(8, re.getReturnNote());
			pst.setInt(9, re.getWorkerId());
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

	public Return searchReturn(int reId)
	{
		Return re = new Return();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from [Return] where returnId = '" + reId + "' ";
			sql += " order by returnId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{

				re.setReturnId(rs.getInt(1));
				re.setHouseId(rs.getInt(2));
				re.setGoodsId(rs.getInt(3));
				re.setCustomerId(rs.getInt(4));
				re.setReturnAmount(rs.getFloat(5));
				re.setReturnTime(rs.getDate(6));
				re.setReturnPrice(rs.getFloat(7));
				re.setBatchId(rs.getInt(8));
				re.setReturnNote(rs.getString(9));
				re.setWorkerId(rs.getInt(10));
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
		return re;
	}

	public List<Return> loadAllReturn()
	{
		List<Return> result = new ArrayList<Return>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from [Return]  ";
			sql += " order by returnId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Return re = new Return();
				re.setReturnId(rs.getInt(1));
				re.setHouseId(rs.getInt(2));
				re.setGoodsId(rs.getInt(3));
				re.setCustomerId(rs.getInt(4));
				re.setReturnAmount(rs.getFloat(5));
				re.setReturnTime(rs.getDate(6));
				re.setReturnPrice(rs.getFloat(7));
				re.setBatchId(rs.getInt(8));
				re.setReturnNote(rs.getString(9));
				re.setWorkerId(rs.getInt(10));
				result.add(re);
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

	/*public List<Return> loadReturn(String keyword)
	{
		// 通过keyword加载所有收入条目
		List<Return> result = new ArrayList<Return>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Return where incomeNote like '%" + keyword
					+ "%' ";
			sql += " and incomeDeleteDate is null ";
			sql += " order by reId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Return re = new Return();
				re.setIncomeCreateDate(rs.getDate(1));
				re.setIncomeAmount(rs.getFloat(2));
				re.setIncomeId(rs.getInt(3));
				re.setIncomeTypeId(rs.getInt(4));
				re.setProjectId(rs.getInt(5));
				re.setIncomeNote(rs.getString(6));
				result.add(re);
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

	public List<Return> loadReturnByTime(String lastTime, String nextTime)
	{
		// 通过时间段加载所有收入条目
		List<Return> result = new ArrayList<Return>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Return where incomeCreateDate between '"
					+ lastTime + "' and '" + nextTime + "' ";
			sql += " and incomeDeleteDate is null ";
			sql += " order by reId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Return re = new Return();
				re.setIncomeCreateDate(rs.getDate(1));
				re.setIncomeAmount(rs.getFloat(2));
				re.setIncomeId(rs.getInt(3));
				re.setIncomeTypeId(rs.getInt(4));
				re.setProjectId(rs.getInt(5));
				re.setIncomeNote(rs.getString(6));
				result.add(re);
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

	public void deleteReturn(int reId)
	{
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from Return where reId=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, reId);
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
				throw new BusinessException("条目不存在");
			if (rs.getDate("incomeDeleteDate") != null)
				throw new BusinessException("条目已经注销");
			rs.close();
			pst.close();
			sql = "update Return set incomeDeleteDate=? where reId=?";
			pst = conn.prepareStatement(sql);
			pst.setTimestamp(1,
					new java.sql.Timestamp(System.currentTimeMillis()));
			pst.setInt(2, reId);
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

	/*public void modifyReturn(Return re)
	{
		//修改条目
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from Return where reId=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, re.getReturnId());
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
				throw new BusinessException("不存在");
			rs.close();
			pst.close();
			sql = "update Return set incomeNote=?, incomeAmount=?, incomeCreateDate=?, incomeTypeId=? where reId=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, re.getIncomeNote());
			pst.setFloat(2, re.getIncomeAmount());
			pst.setDate(3, new java.sql.Date(re.getIncomeCreateDate()
					.getTime()));
			pst.setInt(4, re.getIncomeTypeId());
			pst.setInt(5, re.getIncomeId());
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
			sql = "select count(*) from Return where projectId = '" + projectId
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
			sql = "select sum(incomeAmount) from Return where projectId = '"
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
