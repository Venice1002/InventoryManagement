package cn.edu.zucc.inventorymanagement.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.inventorymanagement.model.Customer;
import cn.edu.zucc.inventorymanagement.util.BaseException;
import cn.edu.zucc.inventorymanagement.util.BusinessException;
import cn.edu.zucc.inventorymanagement.util.DBUtil;

public class CustomerManager
{
	public void createCustomer(Customer customer) throws BaseException
	{
		// �����ͻ�
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "insert into Customer(customerName,customerAddress,customerPhone) values(?,?,?)";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst = conn.prepareStatement(sql);
			pst.setString(1, customer.getCustomerName());
			pst.setString(2, customer.getCustomerAddress());
			pst.setString(3, customer.getCustomerPhone());
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

	public List<Customer> loadAllCustomer()
	{
		List<Customer> result = new ArrayList<Customer>();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Customer ";
			sql += " order by customerId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Customer customer = new Customer();
				customer.setCustomerId(rs.getInt(1));
				customer.setCustomerName(rs.getString(2));
				customer.setCustomerAddress(rs.getString(3));
				customer.setCustomerPhone(rs.getString(4));
				result.add(customer);
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

	public Customer searchCustomerByCustomerId(int customerId) throws BaseException
	{
		// ͨ��customerId�����ݿ��в�ѯ�ͻ�
		Customer result = new Customer();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Customer where customerId = '" + customerId + "' ";
			sql += " order by customerId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next())
			{
				Customer customer = new Customer();
				customer.setCustomerId(rs.getInt(1));
				customer.setCustomerName(rs.getString(2));
				customer.setCustomerAddress(rs.getString(3));
				customer.setCustomerPhone(rs.getString(4));
				result = customer;
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

	public Customer searchCustomerByCustomerName(String keyword)
			throws BaseException
	{
		// ͨ��customerName�����ݿ��в�ѯ�ͻ�
		Customer result = new Customer();
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql;
			sql = "select * from Customer where customerName = '" + keyword + "' ";
			sql += " order by customerId";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			java.sql.ResultSet rs = pst.executeQuery();
			while (rs.next())
			{
				Customer customer = new Customer();
				customer.setCustomerId(rs.getInt(1));
				customer.setCustomerName(rs.getString(2));
				customer.setCustomerAddress(rs.getString(3));
				customer.setCustomerPhone(rs.getString(4));
				result = customer;
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

	public void modifyCustomer(Customer customer) throws BaseException
	{
		// �޸Ŀͻ���Ϣ
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from Customer where customerId=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, customer.getCustomerId());
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
				throw new BusinessException("������");
			rs.close();
			pst.close();

			sql = "update Customer set customerName=?,customerAddress=?,customerPhone=? where customerId = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, customer.getCustomerName());
			pst.setString(2, customer.getCustomerAddress());
			pst.setString(3, customer.getCustomerPhone());
			pst.setInt(4, customer.getCustomerId());
			
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

	public void deleteCustomer(int customerId)
	{
		Connection conn = null;
		try
		{
			conn = DBUtil.getConnection();
			String sql = "select * from Customer where customerId=?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, customerId);
			java.sql.ResultSet rs = pst.executeQuery();
			if (!rs.next())
				throw new BusinessException("�ͻ�������");
			rs.close();
			pst.close();
			sql = "delete from Customer where customerId=?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, customerId);
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
