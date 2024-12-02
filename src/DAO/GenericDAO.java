package DAO;

import java.util.ArrayList;
import java.sql.*;
import java.util.List;

import model.*;

public class GenericDAO {
	private Connection connection;

	public GenericDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Customer> getAllCustomers() throws SQLException {
		String query = "SELECT * FROM customer";
		List<Customer> customers = new ArrayList<Customer>();
		try (PreparedStatement pstmt = connection.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				customers.add(new Customer(rs.getInt("MAKH"), rs.getString("HoTen"), rs.getString("SDT"),
						rs.getInt("DiemTichLuy")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
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
