package cn.edu.zucc.inventorymanagement.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.inventorymanagement.model.Supplier;
import cn.edu.zucc.inventorymanagement.util.BaseException;
import cn.edu.zucc.inventorymanagement.util.BusinessException;
import cn.edu.zucc.inventorymanagement.util.DBUtil;

public class SupplierManager
{
	public void createSupplier(Supplier supplier) throws BaseException
	{
		// 创建供应商
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "insert into Supplier(supplierName,supplierAddress,supplierPhone) values(?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst = conn.prepareStatement(sql);
			pst.setString(1, supplier.getSupplierName());
			pst.setString(2, supplier.getSupplierAddress());
			pst.setString(3, supplier.getSupplierPhone());
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

	public List<Supplier> loadAllSupplier()
	{
		List<Supplier> result = new ArrayList<Supplier>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Supplier ";
			sql += " order by supplierId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Supplier supplier = new Supplier();
				supplier.setSupplierId(rs.getInt(1));
				supplier.setSupplierName(rs.getString(2));
				supplier.setSupplierAddress(rs.getString(3));
				supplier.setSupplierPhone(rs.getString(4));
				result.add(supplier);
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

	public Supplier searchSupplierBySupplierId(int supplierId) throws BaseException
	{
		// 通过supplierId在数据库中查询供应商
		Supplier result = new Supplier();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Supplier where supplierId = '" + supplierId + "' ";
			sql += " order by supplierId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next())
			{
				Supplier supplier = new Supplier();
				supplier.setSupplierId(rs.getInt(1));
				supplier.setSupplierName(rs.getString(2));
				supplier.setSupplierAddress(rs.getString(3));
				supplier.setSupplierPhone(rs.getString(4));
				result = supplier;
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

	public List<Supplier> searchSupplierBySupplierName(String keyword)
			throws BaseException
	{
		// 通过supplierName在数据库中查询供应商
		List<Supplier> result = new ArrayList<Supplier>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Supplier where supplierName = '" + keyword + "' ";
			sql += " order by supplierId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Supplier supplier = new Supplier();
				supplier.setSupplierId(rs.getInt(1));
				supplier.setSupplierName(rs.getString(2));
				supplier.setSupplierAddress(rs.getString(3));
				supplier.setSupplierPhone(rs.getString(4));
				result.add(supplier);
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

	public void modifySupplier(Supplier supplier) throws BaseException
	{
		// 修改供应商信息
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from Supplier where supplierId=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, supplier.getSupplierId());
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
				throw new BusinessException("不存在");
			rs.close();
			pst.close();

			sql = "update Supplier set supplierName=?,supplierAddress=?,supplierPhone=? where supplierId = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, supplier.getSupplierName());
			pst.setString(2, supplier.getSupplierAddress());
			pst.setString(3, supplier.getSupplierPhone());
			pst.setInt(4, supplier.getSupplierId());
			
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

	public void deleteSupplier(int supplierId)
	{
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from Supplier where supplierId=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, supplierId);
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
				throw new BusinessException("供应商不存在");
			rs.close();
			pst.close();
			sql = "delete from Supplier where supplierId=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, supplierId);
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
