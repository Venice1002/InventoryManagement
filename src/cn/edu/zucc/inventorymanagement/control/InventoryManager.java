package cn.edu.zucc.inventorymanagement.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.inventorymanagement.model.Inventory;
import cn.edu.zucc.inventorymanagement.model.Store;
import cn.edu.zucc.inventorymanagement.util.DBUtil;

public class InventoryManager
{
	public void createInventory(Inventory inventory)
	{
		//创建库存清单
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "insert into Inventory(houseId,goodsId,enterAmount,exitAmount,exchangeInAmount,exchangeOutAmount,returnAmount,destoryAmount,storeAmount,averagePrice) values(?,?,?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst = conn.prepareStatement(sql);
			pst.setInt(1, inventory.getHouseId());
			pst.setInt(2, inventory.getGoodsId());
			pst.setFloat(3, inventory.getEnterAmount());
			pst.setFloat(4, inventory.getExitAmount());
			pst.setFloat(5, inventory.getExchangeInAmount());
			pst.setFloat(6, inventory.getExchangeOutAmount());
			pst.setFloat(7, inventory.getReturnAmount());
			pst.setFloat(8, inventory.getDestoryAmount());
			pst.setFloat(9, inventory.getStoreAmount());
			pst.setFloat(10, inventory.getAveragePrice());
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

	public void clearInventory()
	{
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM Inventory";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst = conn.prepareStatement(sql);
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

	public List<Inventory> loadAllInventory()
	{
		//加载所有库存清单
		List<Inventory> result = new ArrayList<Inventory>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select distinct * from Inventory  ";
			sql += " order by houseId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Inventory inventory = new Inventory();
				inventory.setHouseId(rs.getInt(1));
				inventory.setGoodsId(rs.getInt(2));
				inventory.setEnterAmount(rs.getFloat(3));
				inventory.setExitAmount(rs.getFloat(4));
				inventory.setExchangeInAmount(rs.getFloat(5));
				inventory.setExchangeOutAmount(rs.getFloat(6));
				inventory.setReturnAmount(rs.getFloat(7));
				inventory.setDestoryAmount(rs.getFloat(8));
				inventory.setStoreAmount(rs.getFloat(9));
				inventory.setAveragePrice(rs.getFloat(10));
				result.add(inventory);
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

	public void createAllInventory(String lastTime, String nextTime)
	{
		//加载所有库存清单
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Store ";
			sql += " order by houseId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Inventory inventory = new Inventory();
				inventory.setHouseId(rs.getInt(2));
				inventory.setGoodsId(rs.getInt(3));
				inventory.setEnterAmount((new EnterManager().countEnterAmount(lastTime, nextTime, inventory.getHouseId(), inventory.getGoodsId())));
//				inventory.setExitAmount(rs.getFloat(4));
//				inventory.setExchangeInAmount(rs.getFloat(5));
//				inventory.setExchangeOutAmount(rs.getFloat(6));
//				inventory.setReturnAmount(rs.getFloat(7));
//				inventory.setDestoryAmount(rs.getFloat(8));
//				inventory.setStoreAmount(rs.getFloat(9));
//				inventory.setAveragePrice(rs.getFloat(10));
				this.createInventory(inventory);
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
	}
}
