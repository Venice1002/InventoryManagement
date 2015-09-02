package cn.edu.zucc.inventorymanagement.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.inventorymanagement.model.House;
import cn.edu.zucc.inventorymanagement.model.Store;
import cn.edu.zucc.inventorymanagement.util.BaseException;
import cn.edu.zucc.inventorymanagement.util.BusinessException;
import cn.edu.zucc.inventorymanagement.util.DBUtil;

public class StoreManager
{
	public void createStore(Store store)
	{
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "insert into Store(houseId,goodsId,storeAmount,batchId,unit,storePrice) values(?,?,?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst = conn.prepareStatement(sql);
			pst.setInt(1, store.getHouseId());
			pst.setInt(2, store.getGoodsId());
			pst.setFloat(3, store.getStoreAmount());
			pst.setInt(4, store.getBatchId());
			pst.setString(5, store.getUnit());
			pst.setFloat(6, store.getStorePrice());
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

	public Store searchStore(int storeId)
	{
		Store store = new Store();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Store where storeId = '" + storeId + "' ";
			sql += " order by storeId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				store.setStoreId(rs.getInt(1));
				store.setHouseId(rs.getInt(2));
				store.setGoodsId(rs.getInt(3));
				store.setStoreAmount(rs.getFloat(4));
				store.setBatchId(rs.getInt(5));
				store.setUnit(rs.getString(6));
				store.setStorePrice(rs.getFloat(7));
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
		return store;
	}
	
	public Store searchStore(int houseId, int batchId, int goodsId)
	{
		Store store = new Store();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Store where houseId = ? and batchId = ? and goodsId = ? ";
			sql += " order by storeId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, houseId);
			pst.setInt(2, batchId);
			pst.setInt(3, goodsId);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				store.setStoreId(rs.getInt(1));
				store.setHouseId(rs.getInt(2));
				store.setGoodsId(rs.getInt(3));
				store.setStoreAmount(rs.getFloat(4));
				store.setBatchId(rs.getInt(5));
				store.setUnit(rs.getString(6));
				store.setStorePrice(rs.getFloat(7));
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
		return store;
	}

	public void modifyStore(Store store) throws BaseException
	{
		// ÐÞ¸Ä²Ö¿â
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from Store where houseId = ? and batchId = ? and goodsId = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, store.getHouseId());
			pst.setInt(2, store.getBatchId());
			pst.setInt(3, store.getGoodsId());
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
				throw new BusinessException("²»´æÔÚ");
			rs.close();
			pst.close();

			sql = "update Store set storeAmount = ?,storePrice = ? where houseId = ? and batchId = ? and goodsId = ?";
			pst = conn.prepareStatement(sql);
			pst.setFloat(1, store.getStoreAmount());
			pst.setFloat(2, store.getStorePrice());
			pst.setInt(3, store.getHouseId());
			pst.setInt(4, store.getBatchId());
			pst.setInt(5, store.getGoodsId());

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

	public List<Store> loadStoreByHouseId(int houseId)
	{
		List<Store> result = new ArrayList<Store>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Store  where houseId = '" + houseId + "'";
			sql += " order by storeId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Store store = new Store();
				store.setStoreId(rs.getInt(1));
				store.setHouseId(rs.getInt(2));
				store.setGoodsId(rs.getInt(3));
				store.setStoreAmount(rs.getFloat(4));
				store.setBatchId(rs.getInt(5));
				store.setUnit(rs.getString(6));
				store.setStorePrice(rs.getFloat(7));
				result.add(store);
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

	public List<Store> loadAllStore()
	{
		List<Store> result = new ArrayList<Store>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Store  ";
			sql += " order by storeId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Store store = new Store();
				store.setStoreId(rs.getInt(1));
				store.setHouseId(rs.getInt(2));
				store.setGoodsId(rs.getInt(3));
				store.setStoreAmount(rs.getFloat(4));
				store.setBatchId(rs.getInt(5));
				store.setUnit(rs.getString(6));
				store.setStorePrice(rs.getFloat(7));
				result.add(store);
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
