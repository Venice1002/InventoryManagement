package cn.edu.zucc.inventorymanagement.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.inventorymanagement.model.Destory;
import cn.edu.zucc.inventorymanagement.util.BaseException;
import cn.edu.zucc.inventorymanagement.util.BusinessException;
import cn.edu.zucc.inventorymanagement.util.DBUtil;

public class DestoryManager
{
	public void createDestory(Destory destory)
	{
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "insert into [Destory](houseId,goodsId,destoryAmount,destoryTime,batchId,destoryNote,workerId) values(?,?,?,?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, destory.getHouseId());
			pst.setInt(2, destory.getGoodsId());
			pst.setFloat(3, destory.getDestoryAmount());
			pst.setDate(4, new java.sql.Date(destory.getDestoryTime().getTime()));
			pst.setInt(5, destory.getBatchId());
			pst.setString(6, destory.getDestoryNote());
			pst.setInt(7, destory.getWorkerId());
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

	public Destory searchDestory(int destoryId)
	{
		Destory destory = new Destory();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from [Destory] where destoryId = '" + destoryId + "' ";
			sql += " order by destoryId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				destory.setDestoryId(rs.getInt(1));
				destory.setHouseId(rs.getInt(2));
				destory.setGoodsId(rs.getInt(3));
				destory.setBatchId(rs.getInt(4));
				destory.setDestoryAmount(rs.getFloat(5));
				destory.setDestoryTime(rs.getDate(6));
				destory.setDestoryNote(rs.getString(7));
				destory.setWorkerId(rs.getInt(8));
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
		return destory;
	}

	public List<Destory> loadAllDestory()
	{
		List<Destory> result = new ArrayList<Destory>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from [Destory]  ";
			sql += " order by destoryId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Destory destory = new Destory();
				destory.setDestoryId(rs.getInt(1));
				destory.setHouseId(rs.getInt(2));
				destory.setGoodsId(rs.getInt(3));
				destory.setBatchId(rs.getInt(4));
				destory.setDestoryAmount(rs.getFloat(5));
				destory.setDestoryTime(rs.getDate(6));
				destory.setDestoryNote(rs.getString(7));
				destory.setWorkerId(rs.getInt(8));
				result.add(destory);
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
