package cn.edu.zucc.inventorymanagement.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.inventorymanagement.model.Store;
import cn.edu.zucc.inventorymanagement.util.BaseException;
import cn.edu.zucc.inventorymanagement.util.BusinessException;
import cn.edu.zucc.inventorymanagement.util.DBUtil;

public class StoreManager
{
	public void createStore(Store store)
	{
		//创建库存清单
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
		//通过storeId查询库存清单
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
		//通过houseId,batchId,goodsId查询库存清单
		//若未找到 返回null
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
			if (rs.next())
			{
				store.setStoreId(rs.getInt(1));
				store.setHouseId(rs.getInt(2));
				store.setGoodsId(rs.getInt(3));
				store.setStoreAmount(rs.getFloat(4));
				store.setBatchId(rs.getInt(5));
				store.setUnit(rs.getString(6));
				store.setStorePrice(rs.getFloat(7));
			}
			else
			{
				return null;
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
		// 修改仓库信息
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
				throw new BusinessException("不存在");
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
		//通过houseId加载所有库存清单
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
		//加载所有库存清单
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
