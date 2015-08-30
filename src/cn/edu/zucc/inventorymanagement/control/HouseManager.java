package cn.edu.zucc.inventorymanagement.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.edu.zucc.inventorymanagement.model.House;
import cn.edu.zucc.inventorymanagement.util.BaseException;
import cn.edu.zucc.inventorymanagement.util.DBUtil;

public class HouseManager
{
	public void createHouse(House house) throws BaseException
	{
		// 创建项目
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "insert into House(houseName,houseAddress,linkman,linkPhone,totalAmount,houseType,totalPrice,houseState,lastCheckDate,houseNote,houseLevel) values(?,?,?,?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst = conn.prepareStatement(sql);
			pst.setString(1, house.getHouseName());
			pst.setString(2, house.getHouseAddress());
//			pst.setTimestamp(3,
//					new java.sql.Timestamp(System.currentTimeMillis()));
			pst.setString(3, house.getLinkman());
			pst.setInt(4, house.getLinkPhone());
			pst.setFloat(5, house.getTotalAmount());
			pst.setString(6, house.getHouseType());
			pst.setFloat(7, house.getTotalPrice());
			pst.setString(8, house.getHouseState());
			pst.setDate(9, house.getLastCheckDate());
			pst.setString(10, house.getHouseNote());
			pst.setInt(11, house.getHouseLevel());
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

/*	public List<House> searchHouseByUserId(int userId) throws BaseException
	{
		// 通过userId在数据库中查询项目
		List<House> result = new ArrayList<House>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from House where userId = '" + userId + "' ";
			sql += " and deleteDate is null ";
			sql += " order by userid";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				House house = new House();
				house.setHouseId(rs.getInt(1));
				house.setUserId(rs.getInt(2));
				house.setHouseName(rs.getString(3));
				house.setCreateDate(rs.getDate(4));
				result.add(house);
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
*/
/*	public House searchHouseByHouseId(int houseId) throws BaseException
	{
		// 通过housejectId在数据库中查询项目
		House result = new House();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from House where housejectId = '" + houseId
					+ "' ";
			sql += " and deleteDate is null ";
			sql += " order by housejectId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next())
			{
				House house = new House();
				house.setHouseId(rs.getInt(1));
				house.setUserId(rs.getInt(2));
				house.setHouseName(rs.getString(3));
				result = house;
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

	public List<House> searchHouseByHouseName(String keyword)
			throws BaseException
	{
		// 通过housejectName在数据库中查询项目
		List<House> result = new ArrayList<House>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from House where housejectName = '" + keyword
					+ "' ";
			sql += " and deleteDate is null ";
			sql += " order by housejectId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				House house = new House();
				house.setHouseId(rs.getInt(1));
				house.setUserId(rs.getInt(2));
				house.setHouseName(rs.getString(3));
				result.add(house);
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

	public void modifyHouse(House house) throws BaseException
	{
		// 修改项目
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from House where housejectId=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, house.getHouseId());
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
				throw new BusinessException("不存在");
			rs.close();
			pst.close();
			sql = "update House set housejectName=? where housejectId=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, house.getHouseName());
			pst.setInt(2, house.getHouseId());
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

	public void deleteHouse(int housejectId)
	{
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from House where housejectId=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, housejectId);
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
				throw new BusinessException("项目不存在");
			if (rs.getDate("deleteDate") != null)
				throw new BusinessException("项目已经注销");
			rs.close();
			pst.close();
			sql = "update House set deleteDate=? where housejectId=?";
			pst = conn.prepareStatement(sql);
			pst.setTimestamp(1,
					new java.sql.Timestamp(System.currentTimeMillis()));
			pst.setInt(2, housejectId);
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
}
