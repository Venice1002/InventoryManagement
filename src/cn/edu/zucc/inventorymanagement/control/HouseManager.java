package cn.edu.zucc.inventorymanagement.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.inventorymanagement.model.House;
import cn.edu.zucc.inventorymanagement.util.BaseException;
import cn.edu.zucc.inventorymanagement.util.BusinessException;
import cn.edu.zucc.inventorymanagement.util.DBUtil;

public class HouseManager
{
	public void createHouse(House house) throws BaseException
	{
		// 创建仓库
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "insert into House(houseName,houseAddress,linkman,linkPhone,totalAmount,houseType,totalPrice,houseState,lastCheckDate,houseNote,houseLevel) values(?,?,?,?,?,?,?,?,?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst = conn.prepareStatement(sql);
			pst.setString(1, house.getHouseName());
			pst.setString(2, house.getHouseAddress());
			pst.setString(3, house.getLinkman());
			pst.setString(4, house.getLinkPhone());
			pst.setFloat(5, house.getTotalAmount());
			pst.setString(6, house.getHouseType());
			pst.setFloat(7, house.getTotalPrice());
			pst.setString(8, house.getHouseState());
			pst.setDate(9, (Date) house.getLastCheckDate());
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

	public List<House> loadAllHouse()
	{
		//加载所有仓库
		List<House> result = new ArrayList<House>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from House ";
			sql += " order by houseId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				House house = new House();
				house.setHouseId(rs.getInt(1));
				house.setHouseName(rs.getString(2));
				house.setHouseAddress(rs.getString(3));
				house.setLinkman(rs.getString(4));
				house.setLinkPhone(rs.getString(5));
				house.setTotalAmount(rs.getFloat(6));
				house.setHouseType(rs.getString(7));
				house.setTotalPrice(rs.getFloat(8));
				house.setHouseState(rs.getString(9));
				house.setLastCheckDate(rs.getDate(10));
				house.setHouseNote(rs.getString(11));
				house.setHouseLevel(rs.getInt(12));
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

	public House searchHouseByHouseId(int houseId) throws BaseException
	{
		// 通过houseId在数据库中查询仓库
		House result = new House();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from House where houseId = '" + houseId + "' ";
			sql += " order by houseId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next())
			{
				House house = new House();
				house.setHouseId(rs.getInt(1));
				house.setHouseName(rs.getString(2));
				house.setHouseAddress(rs.getString(3));
				house.setLinkman(rs.getString(4));
				house.setLinkPhone(rs.getString(5));
				house.setTotalAmount(rs.getFloat(6));
				house.setHouseType(rs.getString(7));
				house.setTotalPrice(rs.getFloat(8));
				house.setHouseState(rs.getString(9));
				house.setLastCheckDate(rs.getDate(10));
				house.setHouseNote(rs.getString(11));
				house.setHouseLevel(rs.getInt(12));
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

	public House searchHouseByHouseName(String keyword)
			throws BaseException
	{
		// 通过houseName在数据库中查询仓库
		House result = new House();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from House where houseName = '" + keyword + "' ";
			sql += " order by houseId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				House house = new House();
				house.setHouseId(rs.getInt(1));
				house.setHouseName(rs.getString(2));
				house.setHouseAddress(rs.getString(3));
				house.setLinkman(rs.getString(4));
				house.setLinkPhone(rs.getString(5));
				house.setTotalAmount(rs.getFloat(6));
				house.setHouseType(rs.getString(7));
				house.setTotalPrice(rs.getFloat(8));
				house.setHouseState(rs.getString(9));
				house.setLastCheckDate(rs.getDate(10));
				house.setHouseNote(rs.getString(11));
				house.setHouseLevel(rs.getInt(12));
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

	public void modifyHouse(House house) throws BaseException
	{
		// 修改仓库
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from House where houseId=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, house.getHouseId());
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
				throw new BusinessException("不存在");
			rs.close();
			pst.close();

			sql = "update House set houseName = ?,houseAddress = ?,linkman = ?,linkPhone = ?,totalAmount = ?,houseType = ?,totalPrice = ?,houseState = ?,lastCheckDate = ?,houseNote = ?,houseLevel = ? where houseId = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, house.getHouseName());
			pst.setString(2, house.getHouseAddress());
			pst.setString(3, house.getLinkman());
			pst.setString(4, house.getLinkPhone());
			pst.setFloat(5, house.getTotalAmount());
			pst.setString(6, house.getHouseType());
			pst.setFloat(7, house.getTotalPrice());
			pst.setString(8, house.getHouseState());
			pst.setDate(9, (Date) house.getLastCheckDate());
			pst.setString(10, house.getHouseNote());
			pst.setInt(11, house.getHouseLevel());
			pst.setInt(12, house.getHouseId());
			
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

	public void deleteHouse(int houseId)
	{
		//删除仓库（硬删除）
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from House where houseId=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, houseId);
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
				throw new BusinessException("仓库不存在");
			rs.close();
			pst.close();
			sql = "delete from House where houseId=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, houseId);
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
	
	public static void main(String[] args) throws BaseException
	{
		//main for test
		HouseManager hm = new HouseManager();
		System.out.println(hm.searchHouseByHouseName("啊哈").getHouseId());
	}
}
