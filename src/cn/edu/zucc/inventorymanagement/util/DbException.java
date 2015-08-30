package cn.edu.zucc.inventorymanagement.util;

public class DbException extends BaseException {
	public DbException(java.lang.Throwable ex){
		super("db error "+ex.getMessage());
	}
}
