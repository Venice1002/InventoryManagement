package cn.edu.zucc.inventorymanagement.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.inventorymanagement.model.Exchange;
import cn.edu.zucc.inventorymanagement.util.DBUtil;

public class ExchangeManager
{
	public void createExchange(Exchange exchange)
	{
		//创建移库清单
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "insert into Exchange(lastHouseId,nextHouseId,goodsId,exchangeAmount,exchangeTime,batchId,exchangeNote,workerId) values(?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst = conn.prepareStatement(sql);
			pst.setInt(1, exchange.getLastHouseId());
			pst.setInt(2, exchange.getNextHouseId());
			pst.setInt(3, exchange.getGoodsId());
			pst.setFloat(4, exchange.getExchangeAmount());
			pst.setDate(5, new java.sql.Date(exchange.getExchangeTime().getTime()));
			pst.setInt(6, exchange.getBatchId());
			pst.setString(7, exchange.getExchangeNote());
			pst.setInt(8, exchange.getWorkerId());
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

	public Exchange searchExchange(int exchangeId)
	{
		//通过exchangId查询移库清单
		Exchange exchange = new Exchange();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Exchange where exchangeId = '" + exchangeId + "' ";
			sql += " order by exchangeId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				exchange.setExchangeId(rs.getInt(1));
				exchange.setLastHouseId(rs.getInt(2));
				exchange.setNextHouseId(rs.getInt(3));
				exchange.setGoodsId(rs.getInt(4));
				exchange.setBatchId(rs.getInt(5));
				exchange.setExchangeAmount(rs.getFloat(6));
				exchange.setExchangeTime(rs.getDate(7));
				exchange.setExchangeNote(rs.getString(8));
				exchange.setWorkerId(rs.getInt(9));
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
		return exchange;
	}

	public List<Exchange> loadAllExchange()
	{
		//加载所有移库单
		List<Exchange> result = new ArrayList<Exchange>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Exchange  ";
			sql += " order by exchangeId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Exchange exchange = new Exchange();
				exchange.setExchangeId(rs.getInt(1));
				exchange.setLastHouseId(rs.getInt(2));
				exchange.setNextHouseId(rs.getInt(3));
				exchange.setGoodsId(rs.getInt(4));
				exchange.setBatchId(rs.getInt(5));
				exchange.setExchangeAmount(rs.getFloat(6));
				exchange.setExchangeTime(rs.getDate(7));
				exchange.setExchangeNote(rs.getString(8));
				exchange.setWorkerId(rs.getInt(9));
				result.add(exchange);
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
