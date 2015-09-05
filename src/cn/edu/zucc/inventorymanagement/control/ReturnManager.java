package cn.edu.zucc.inventorymanagement.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.inventorymanagement.model.Enter;
import cn.edu.zucc.inventorymanagement.model.Return;
import cn.edu.zucc.inventorymanagement.util.DBUtil;

/*
 * 注：return在java中是保留字
 * 所以变量名使用re
 * */

public class ReturnManager
{
	public void createReturn(Return re)
	{
		//新建退库单
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
		//通过reId查询退库单
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
				re.setCustomerId(rs.getInt(2));
				re.setHouseId(rs.getInt(3));
				re.setGoodsId(rs.getInt(4));
				re.setBatchId(rs.getInt(5));
				re.setReturnAmount(rs.getFloat(6));
				re.setReturnTime(rs.getDate(7));
				re.setReturnNote(rs.getString(8));
				re.setWorkerId(rs.getInt(9));
				re.setReturnPrice(rs.getFloat(10));
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
	
	public List<Return> searchReturn(String lastTime, String nextTime, int listId, int houseId)
	{
		List<Return> result = new ArrayList<Return>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from [Return] where 1=1 ";
			if(!lastTime.equals("")  && !nextTime.equals("") )
			{
				sql += "and returnTime between '" + lastTime + "' and '" + nextTime +"'";
			}
			if(listId != 0)
			{
				sql += "and returnId = '"+ listId + "'";
			}
			if(houseId != 0)
			{
				sql += "and houseId = '"+ houseId + "'";
			}
			sql += " order by returnId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Return re = new Return();
				re.setReturnId(rs.getInt(1));
				re.setCustomerId(rs.getInt(2));
				re.setHouseId(rs.getInt(3));
				re.setGoodsId(rs.getInt(4));
				re.setBatchId(rs.getInt(5));
				re.setReturnAmount(rs.getFloat(6));
				re.setReturnTime(rs.getDate(7));
				re.setReturnNote(rs.getString(8));
				re.setWorkerId(rs.getInt(9));
				re.setReturnPrice(rs.getFloat(10));
				result.add(re);
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
		return result;
	}

	public List<Return> loadAllReturn()
	{
		//加载所有退库清单
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
				re.setCustomerId(rs.getInt(2));
				re.setHouseId(rs.getInt(3));
				re.setGoodsId(rs.getInt(4));
				re.setBatchId(rs.getInt(5));
				re.setReturnAmount(rs.getFloat(6));
				re.setReturnTime(rs.getDate(7));
				re.setReturnNote(rs.getString(8));
				re.setWorkerId(rs.getInt(9));
				re.setReturnPrice(rs.getFloat(10));
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

}
