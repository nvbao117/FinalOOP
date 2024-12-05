package database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.DatabaseMetaData;

public class JDBCUtil {
	private static final String URL = "jdbc:mysql://localhost:3306/FastFoodManagement";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public static Connection getConnection() throws SQLException {
	    try {
	        // Tải lớp Driver JDBC của MySQL
	        // Class.forName() dùng để nạp driver vào bộ nhớ để JDBC sử dụng
	        // "com.mysql.cj.jdbc.Driver" là driver hiện đại cho MySQL (JDBC 8+)
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        // Nếu không tìm thấy driver, ném ngoại lệ SQLException
	        // Thông báo lỗi "Unable to load MySQL driver" kèm thông tin lỗi gốc (e)
	        throw new SQLException("Unable to load MySQL driver", e);
	    }
	    // Tạo và trả về đối tượng Connection bằng DriverManager
	    // Dùng URL, USER, PASSWORD để kết nối đến cơ sở dữ liệu
	    return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	public static void closeConnection(Connection conn) {
	    // Kiểm tra kết nối có khác null không
	    if (conn != null) { 
	        try {
	            // Đóng kết nối nếu nó đang mở
	            conn.close(); 
	        } catch (SQLException e) {
	            // Bắt lỗi nếu xảy ra lỗi trong quá trình đóng kết nối
	            // In chi tiết lỗi ra màn hình để tiện debug
	            e.printStackTrace();
	        }
	    }
	}


	public static void rollbackConnection(Connection conn) {
	    // Kiểm tra nếu kết nối không phải null
	    if (conn != null) { 
	        try {
	            // Thực hiện rollback (hoàn tác) các thay đổi chưa được commit
	            conn.rollback(); 
	        } catch (SQLException e) {
	            // Bắt lỗi nếu xảy ra vấn đề trong quá trình rollback
	            // In chi tiết lỗi ra màn hình để debug
	            e.printStackTrace();
	        }
	    }
	}

	public static void printInfo(Connection c) {
	    try {
	        // Kiểm tra nếu kết nối (Connection) không phải null
	        if (c != null) {
	            // Lấy metadata (siêu dữ liệu) của cơ sở dữ liệu từ kết nối
	            DatabaseMetaData mtdt = c.getMetaData();
	            
	            // In tên hệ quản trị cơ sở dữ liệu (VD: MySQL, PostgreSQL)
	            System.out.println(mtdt.getDatabaseProductName());
	            
	            // In phiên bản của hệ quản trị cơ sở dữ liệu (VD: 8.0.30)
	            System.out.println(mtdt.getDatabaseProductVersion());
	        }
	    } catch (SQLException e) {
	        // Bắt lỗi nếu có vấn đề trong quá trình truy cập metadata
	        // In chi tiết lỗi ra màn hình để debug
	        e.printStackTrace();
	    }
	}

}
