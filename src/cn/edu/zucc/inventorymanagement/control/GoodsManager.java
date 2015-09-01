package cn.edu.zucc.inventorymanagement.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.inventorymanagement.model.Goods;
import cn.edu.zucc.inventorymanagement.util.BaseException;
import cn.edu.zucc.inventorymanagement.util.BusinessException;
import cn.edu.zucc.inventorymanagement.util.DBUtil;

public class GoodsManager
{
	public void createGoods(Goods goods) throws BaseException
	{
		// 创建物料
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "insert into Goods(goodsName,goodsType,produceDate,limitedDate,goodsNote,unit,manufacturer,goodsLevel,goodsVolume) values(?,?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst = conn.prepareStatement(sql);
			pst.setString(1, goods.getGoodsName());
			pst.setString(2, goods.getUnit());
			pst.setDate(3, (Date) goods.getProduceDate());
			pst.setDate(4, (Date) goods.getLimitedDate());
			pst.setString(5, goods.getGoodsNote());
			pst.setString(6, goods.getUnit());
			pst.setString(7, goods.getManufacturer());
			pst.setInt(8, goods.getGoodsLevel());
			pst.setString(9, goods.getGoodsVolume());
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

	public List<Goods> loadAllGoods()
	{
		List<Goods> result = new ArrayList<Goods>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Goods ";
			sql += " order by goodsId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Goods goods = new Goods();
				goods.setGoodsId(rs.getInt(1));
				goods.setGoodsName(rs.getString(2));
				goods.setGoodsType(rs.getString(3));
				goods.setProduceDate(rs.getDate(4));
				goods.setLimitedDate(rs.getDate(5));
				goods.setGoodsNote(rs.getString(6));
				goods.setUnit(rs.getString(7));
				goods.setManufacturer(rs.getString(8));
				goods.setGoodsLevel(rs.getInt(9));
				goods.setGoodsVolume(rs.getString(10));
				result.add(goods);
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

	public Goods searchGoodsByGoodsId(int goodsId) throws BaseException
	{
		// 通过goodsId在数据库中查询物料
		Goods result = new Goods();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Goods where goodsId = '" + goodsId + "' ";
			sql += " order by goodsId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next())
			{
				Goods goods = new Goods();
				goods.setGoodsId(rs.getInt(1));
				goods.setGoodsName(rs.getString(2));
				goods.setGoodsType(rs.getString(3));
				goods.setProduceDate(rs.getDate(4));
				goods.setLimitedDate(rs.getDate(5));
				goods.setGoodsNote(rs.getString(6));
				goods.setUnit(rs.getString(7));
				goods.setManufacturer(rs.getString(8));
				goods.setGoodsLevel(rs.getInt(9));
				goods.setGoodsVolume(rs.getString(10));
				result = goods;
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

	public Goods searchGoodsByGoodsName(String keyword)
			throws BaseException
	{
		// 通过goodsName在数据库中查询物料
		Goods result = new Goods();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Goods where goodsName = '" + keyword + "' ";
			sql += " order by goodsId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Goods goods = new Goods();
				goods.setGoodsId(rs.getInt(1));
				goods.setGoodsName(rs.getString(2));
				goods.setGoodsType(rs.getString(3));
				goods.setProduceDate(rs.getDate(4));
				goods.setLimitedDate(rs.getDate(5));
				goods.setGoodsNote(rs.getString(6));
				goods.setUnit(rs.getString(7));
				goods.setManufacturer(rs.getString(8));
				goods.setGoodsLevel(rs.getInt(9));
				goods.setGoodsVolume(rs.getString(10));
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

	public void modifyGoods(Goods goods) throws BaseException
	{
		// 修改物料信息
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from Goods where goodsId=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, goods.getGoodsId());
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
				throw new BusinessException("不存在");
			rs.close();
			pst.close();

			sql = "update Goods set goodsName=?,goodsType=?,produceDate=?,limitedDate=?,goodsNote=?,unit=?,manufacturer=?,goodsLevel=?,goodsVolume=? where goodsId = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, goods.getGoodsName());
			pst.setString(2, goods.getUnit());
			pst.setDate(3, (Date) goods.getProduceDate());
			pst.setDate(4, (Date) goods.getLimitedDate());
			pst.setString(5, goods.getGoodsNote());
			pst.setString(6, goods.getUnit());
			pst.setString(7, goods.getManufacturer());
			pst.setInt(8, goods.getGoodsLevel());
			pst.setString(9, goods.getGoodsVolume());
			pst.setInt(10, goods.getGoodsId());
			
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

	public void deleteGoods(int goodsId)
	{
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from Goods where goodsId=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, goodsId);
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
				throw new BusinessException("物料不存在");
			rs.close();
			pst.close();
			sql = "delete from Goods where goodsId=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, goodsId);
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
	}
}
