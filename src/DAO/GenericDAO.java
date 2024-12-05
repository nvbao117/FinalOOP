package DAO;

import java.util.ArrayList;
import java.sql.*;
import java.util.List;

import model.*;

public class GenericDAO {
	private Connection connection; 
	// Biến thuộc tính lưu trữ đối tượng kết nối cơ sở dữ liệu (Connection)

	public GenericDAO(Connection connection) {
	    // Hàm khởi tạo của lớp GenericDAO
	    // Nhận đối tượng Connection được truyền từ bên ngoài
	    this.connection = connection; 
	    // Gán đối tượng Connection cho biến thuộc tính của lớp
	}


	public List<Customer> getAllCustomers() throws SQLException {
	    // Câu lệnh SQL để lấy tất cả các bản ghi từ bảng "customer"
	    String query = "SELECT * FROM customer";

	    // Danh sách để lưu các đối tượng Customer được lấy từ cơ sở dữ liệu
	    List<Customer> customers = new ArrayList<Customer>();

	    // Khối try-with-resources để tự động đóng PreparedStatement và ResultSet sau khi dùng
	    try (PreparedStatement pstmt = connection.prepareStatement(query); 
	         ResultSet rs = pstmt.executeQuery()) {
	        
	        // Lặp qua từng bản ghi trong ResultSet
	        while (rs.next()) {
	            // Tạo đối tượng Customer từ dữ liệu của bản ghi hiện tại
	            customers.add(new Customer(
	                rs.getInt("MAKH"),       // Mã khách hàng
	                rs.getString("HoTen"),   // Họ tên khách hàng
	                rs.getString("SDT"),     // Số điện thoại
	                rs.getInt("DiemTichLuy") // Điểm tích lũy
	            ));
	        }
	    } catch (SQLException e) {
	        // Xử lý ngoại lệ khi truy vấn
	        e.printStackTrace(); // In thông tin lỗi ra console
	        throw e;             // Ném lại ngoại lệ để xử lý ở lớp gọi
	    }

	    // Trả về danh sách các khách hàng
	    return customers;
	}

	public List<Employee> getAllEmployees() throws SQLException {
		String query = "SELECT * FROM employee";
		List<Employee> employees = new ArrayList<Employee>();
		try (PreparedStatement pstmt = connection.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				employees.add(new Employee(rs.getInt("MANV"), rs.getString("HoTen"), rs.getString("ChucVu"),
						rs.getString("SDT"), rs.getDate("NgayBatDau")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return employees;
	}

	public List<Invoice> getAllInvoices() throws SQLException {
		String query = "SELECT * FROM INVOICE";
		List<Invoice> invoices = new ArrayList<Invoice>();
		try (PreparedStatement pstmt = connection.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				invoices.add(new Invoice(
					rs.getInt("MAHD"), 
					rs.getInt("MAKH"), 
					rs.getInt("MANV"),
					rs.getTimestamp("ThoiGianTT"),
					rs.getDouble("TongTien"), 
					rs.getString("HinhThucTT"),
					rs.getDouble("SoTienNhan")
				));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return invoices;
	}

	public List<Product> getAllProducts() throws SQLException {
		String query = "SELECT * FROM PRODUCT";
		List<Product> products = new ArrayList<Product>();
		try (PreparedStatement pstmt = connection.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				products.add(new Product(rs.getInt("MASP"), rs.getString("TenSP"), rs.getDouble("DonGia"),
						rs.getString("TinhTrang"), rs.getString("HinhAnh")));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return products;
	}

	public List<InvoiceDetail> getAllInvoiceDetails() throws SQLException {
		String query = "SELECT * FROM INVOICE_DETAIL";
		List<InvoiceDetail> invoiceDetails = new ArrayList<>();
		try (PreparedStatement pstmt = connection.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				invoiceDetails.add(new InvoiceDetail(
						rs.getInt("MAHD"), 
						rs.getInt("MASP"), 
						rs.getInt("SoLuong"),
						rs.getDouble("ThanhTien")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return invoiceDetails;
	}

	public List<Promotion> getAllPromotions() throws SQLException {
		String query = "SELECT * FROM PROMOTION";
		List<Promotion> promotions = new ArrayList<>();
		try (PreparedStatement pstmt = connection.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				promotions.add(new Promotion(
					rs.getInt("MAKM"), 
					rs.getDate("ThoiGianHL"),
					rs.getString("DieuKien"),
					rs.getDouble("MucGiamGia")
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return promotions;
	}
	public List<CustomerPromotion> getAllCustomerPromotion() throws SQLException {
		String query = "SELECT * FROM customer_promotion";
		List<CustomerPromotion> customerPromotion = new ArrayList<>();
		try (PreparedStatement pstmt = connection.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				customerPromotion.add(new CustomerPromotion(
					rs.getInt("MAKH"), 
					rs.getInt("MAKM")
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return customerPromotion;
	}
}
