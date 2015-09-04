package cn.edu.zucc.inventorymanagement.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.inventorymanagement.model.Exit;
import cn.edu.zucc.inventorymanagement.util.DBUtil;

/*
 * 注：Exit表在数据库内是系统表
 * 所以sql语句中改为[Exit]
 * */

public class ExitManager
{
	public void createExit(Exit exit)
	{
		//创建出库单
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
		//通过exitId查询出库单
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
				exit.setBatchId(rs.getInt(4));
				exit.setCustomerId(rs.getInt(5));
				exit.setExitAmount(rs.getFloat(6));
				exit.setExitTime(rs.getDate(7));
				exit.setExitPrice(rs.getFloat(8));
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
		//加载所有出库表
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
				exit.setBatchId(rs.getInt(4));
				exit.setCustomerId(rs.getInt(5));
				exit.setExitAmount(rs.getFloat(6));
				exit.setExitTime(rs.getDate(7));
				exit.setExitPrice(rs.getFloat(8));
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

}
