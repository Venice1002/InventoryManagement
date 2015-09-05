package cn.edu.zucc.inventorymanagement.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.inventorymanagement.model.Enter;
import cn.edu.zucc.inventorymanagement.model.Exchange;
import cn.edu.zucc.inventorymanagement.util.DBUtil;

public class EnterManager
{
	public void createEnter(Enter enter)
	{
		//创建入库清单
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
		//通过enterId查询入库清单
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
	
	public List<Enter> searchEnter(String lastTime, String nextTime, int listId, int houseId)
	{
		List<Enter> result = new ArrayList<Enter>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Enter where 1=1 ";
			if(!lastTime.equals("")  && !nextTime.equals("") )
			{
				sql += "and enterTime between '" + lastTime + "' and '" + nextTime +"'";
			}
			if(listId != 0)
			{
				sql += "and enterId = '"+ listId + "'";
			}
			if(houseId != 0)
			{
				sql += "and houseId = '"+ houseId + "'";
			}
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

	public List<Enter> loadAllEnter()
	{
		//加载所有入库清单
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

}
