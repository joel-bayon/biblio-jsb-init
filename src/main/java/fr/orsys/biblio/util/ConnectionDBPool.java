package fr.orsys.biblio.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionDBPool {
	static ComboPooledDataSource pool  = new ComboPooledDataSource();
	
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = pool.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void close(Connection cxn) {
		try {
			cxn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	static public DataSource getDataSource() {
		return pool;
	}
}
