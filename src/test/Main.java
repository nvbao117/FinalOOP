package test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.security.KeyStore.Entry;
import java.sql.*;
import java.sql.Date;
import database.*;
import DAO.*;
import model.*;

public class Main {
	public static List<Customer> customers;
	public static List<Employee> employees;
	public static List<Invoice> invoices;
	public static List<Product> products;
	public static List<InvoiceDetail> invoiceDetails;
	public static List<Promotion> promotions;
	public static List<CustomerPromotion> customerPromotions;

	public static void main(String[] args) {

		try (Connection conn = JDBCUtil.getConnection()) {
			GenericDAO genericDAO = new GenericDAO(conn);
			// Lấy danh sách tất cả khách hàng
			customers = genericDAO.getAllCustomers();
			// Lấy danh sách tất cả nhân viên
			employees = genericDAO.getAllEmployees();
			// Lấy danh sách tất cả hóa đơn
			invoices = genericDAO.getAllInvoices();

			// Lấy danh sách tất cả sản phẩm
			products = genericDAO.getAllProducts();

			// Lấy danh sách tất cả chi tiết hóa đơn
			invoiceDetails = genericDAO.getAllInvoiceDetails();

			// Lấy danh sách tất cả khuyến mãi
			promotions = genericDAO.getAllPromotions();

			customerPromotions = genericDAO.getAllCustomerPromotion();
		} catch (SQLException e) {
			e.printStackTrace();
		}

//    	1. Liệt kê tất cả các khách hàng.
		System.out.println("1.Liệt kê tất cả các khách hàng:");
		System.out.println("------------------------------------------------------"); 
		cau1();
//    	2. Liệt kê các sản phẩm có giá lớn hơn 30,000 VND.
		System.out.println("\n2. Liệt kê các sản phẩm có giá lớn hơn 30,000 VND:");
		System.out.println("------------------------------------------------------"); 
		cau2(30000);

//    	3. Hiển thị thông tin các hóa đơn cùng với tên khách hàng và tên nhân viên xử lý.
		System.out.println("\n3. Hiển thị thông tin các hóa đơn cùng với tên khách hàng và tên nhân viên xử lý:");
		System.out.println("------------------------------------------------------"); 
		cau3();

//    	4. Tính tổng số tiền mà khách hàng đã chi tiêu (Tổng tiền của tất cả các hóa đơn).
		System.out.println("\n4. Tính tổng số tiền mà khách hàng đã chi tiêu (Tổng tiền của tất cả các hóa đơn):");
		System.out.println("------------------------------------------------------"); 
		cau4();

//    	5. Liệt kê tên sản phẩm và số lượng của từng sản phẩm trong mỗi hóa đơn.
		System.out.println("\n5. Liệt kê tên sản phẩm và số lượng của từng sản phẩm trong các hóa đơn:");
		System.out.println("------------------------------------------------------"); 
		cau5();

//    	6. Hiển thị thông tin các nhân viên đã làm việc từ ngày 01/01/2022 trở đi.
		System.out.println("\n6. Hiển thị thông tin các nhân viên đã làm việc từ ngày 01/01/2022 trở đi");
		System.out.println("------------------------------------------------------"); 
		cau6(Date.valueOf("2022-01-01"));

//    	7. Tìm tất cả các hóa đơn có tổng tiền lớn hơn 100,000 VND.
		System.out.println("\n7. Tìm tất cả các hóa đơn có tổng tiền lớn hơn 100,000 VND.");
		System.out.println("------------------------------------------------------"); 
		cau7(100000);

//    	8. Liệt kê các khuyến mãi đang còn hiệu lực (trước ngày 31/12/2024).
		System.out.println("\n8. Liệt kê các khuyến mãi đang còn hiệu lực (trước ngày 31/12/2024):");
		System.out.println("------------------------------------------------------"); 
		cau8(Date.valueOf("2024-12-31"));

//    	9. Tìm thông tin các khách hàng và số điểm tích lũy của họ, sắp xếp theo điểm tích lũy giảm dần.
		System.out.println(
				"\n9. Tìm thông tin các khách hàng và số điểm tích lũy của họ, sắp xếp theo điểm tích lũy giảm dần:");
		System.out.println("------------------------------------------------------"); 
		cau9();

//    	10. Tính tổng doanh thu từ các hóa đơn trong tháng 1/2023.
		System.out.println("\n10. Tính tổng doanh thu từ các hóa đơn trong tháng 1/2023:");
		System.out.println("------------------------------------------------------"); 
		cau10(2023, Calendar.JANUARY);

//    	11. Tìm thông tin của khách hàng có số tiền thanh toán lớn nhất trong một hóa đơn.
		System.out.println("\n11. Tìm thông tin của khách hàng có số tiền thanh toán lớn nhất trong một hóa đơn:");
		System.out.println("------------------------------------------------------"); 
		cau11();

//    	12. Liệt kê tất cả các khách hàng và sản phẩm của họ trong hóa đơn (bao gồm tên khách hàng và tên sản phẩm).
		System.out.println(
				"\n12. Liệt kê tất cả các khách hàng và sản phẩm của họ trong hóa đơn (bao gồm tên khách hàng và tên sản phẩm):");
		System.out.println("------------------------------------------------------"); 
		cau12();

//    	13. Tìm sản phẩm có số lượng bán chạy nhất (tính tổng số lượng trong các hóa đơn).
		System.out.println("\n13. Tìm sản phẩm có số lượng bán chạy nhất (tính tổng số lượng trong các hóa đơn):");
		System.out.println("------------------------------------------------------"); 
		cau13();

//    	14. Liệt kê các khuyến mãi đã áp dụng cho khách hàng và điều kiện khuyến mãi.
		System.out.println("\n14. Liệt kê các khuyến mãi đã áp dụng cho khách hàng và điều kiện khuyến mãi:");
		System.out.println("------------------------------------------------------"); 
		cau14();

//    	15. Tính tổng số tiền mà một khách hàng đã chi tiêu trong tất cả các hóa đơn (dựa trên mã khách hàng).
		System.out.println(
				"\n15. Tính tổng số tiền mà một khách hàng đã chi tiêu trong tất cả các hóa đơn (dựa trên mã khách hàng):");
		System.out.println("------------------------------------------------------"); 
		cau15(1);

//    	16. Liệt kê tất cả các hóa đơn có ít nhất một sản phẩm có trạng thái "Hết hàng".
		System.out.println("\n16. Liệt kê tất cả các hóa đơn có ít nhất một sản phẩm có trạng thái \"Hết hàng\".:");
		System.out.println("------------------------------------------------------"); 
		cau16();

//    	17. Tính tổng tiền của từng hóa đơn và tính toán tiền giảm giá nếu có (giảm giá theo khuyến mãi).
		System.out.println("\n17. Tính tổng tiền của từng hóa đơn và tính toán tiền giảm giá nếu có (giảm giá theo khuyến mãi):");
		System.out.println("------------------------------------------------------"); 
		cau17();

//    	18. Tìm tất cả các nhân viên đã xử lý hóa đơn có tổng tiền vượt quá 200,000 VND.
		System.out.println("\n18. Tìm tất cả các nhân viên đã xử lý hóa đơn có tổng tiền vượt quá 200,000 VND:");
		System.out.println("------------------------------------------------------"); 
		cau18(200000);

//    	19. Liệt kê tên sản phẩm và số lần sản phẩm đó xuất hiện trong hóa đơn.
		System.out.println("\n19. Liệt kê tên sản phẩm và số lần sản phẩm đó xuất hiện trong hóa đơn:");
		System.out.println("------------------------------------------------------"); 
		cau19();

//    	20. Liệt kê các khách hàng có điểm tích lũy lớn hơn 150 và có tham gia chương trình khuyến mãi.
		System.out.println(
				"\n20. Liệt kê các khách hàng có điểm tích lũy lớn hơn 150 và có tham gia chương trình khuyến mãi:");
		System.out.println("------------------------------------------------------"); 
		cau20(150);

	}

	// 1.Liệt kê tất cả các khách hàng.
	public static void cau1() {
		System.out.printf("%-10s %-30s %-15s %-10s %n", "ID", "Họ Và Tên", "Số Điện Thoại" ,"Điểm tích lũy"); 
		System.out.println("------------------------------------------------------"); 
		// In thông tin khách hàng 
		customers.forEach(customer -> System.out.printf("%-10s %-30s %-15s %-10s %n", customer.getId(), customer.getName(), customer.getPhone(), customer.getPoints()));
	}

//2.Liệt kê các sản phẩm có giá lớn hơn 30,000 VND.	
	public static void cau2(double price) {
		System.out.printf("%-10s %-30s %-15s %-10s %n", "Mã SP", "Tên Sản Phẩm", "Giá" ,"Tình trạng"); 
		System.out.println("------------------------------------------------------");
		products.stream().filter(e -> e.getPrice() > price).collect(Collectors.toList())
		.forEach(e -> System.out.printf("%-10s %-30s %-15s %-10s %n", e.getId(), e.getName(), e.getPrice() , e.getStatus()));
	}

//	Hiển thị thông tin các hóa đơn cùng với tên khách hàng và tên nhân viên xử lý.
	public static void cau3() {
		System.out.printf("%-15s %-30s %-30s  %-15s %-30s %n ", "Mã Hóa Đơn","Tên Khách Hàng","Tên Nhân Viên","Tổng Tiền","Thời gian");
		System.out.println("------------------------------------------------------");
		invoices.forEach(invoice -> {
			Optional<String> customerName = customers.stream()
					.filter(customer -> customer.getId() == invoice.getCustomerId()).findFirst().map(Customer::getName);
			Optional<String> employeeName = employees.stream()
					.filter(employee -> employee.getId() == invoice.getEmployeeId()).findFirst().map(Employee::getName);
			 
			System.out.printf("%-15s %-30s %-30s %-15.2f %-30s %n", 
					invoice.getId(), 
					customerName.orElse("Unknown"), 
					employeeName.orElse("Unknown"), 
					invoice.getTotalAmount(),
					invoice.getPaymentTime());
		});
	}

//	4. Tính tổng số tiền mà khách hàng đã chi tiêu (Tổng tiền của tất cả các hóa đơn).
	public static void cau4() {
		System.out.printf("%-20s %-30s %-20s %n","Mã Khách Hàng","Họ Và Tên","Tổng Tiền");
		System.out.println("------------------------------------------------------");
		Map<Integer, Double> totalAmountByCustomer = invoices.stream().collect(
				Collectors.groupingBy(Invoice::getCustomerId, Collectors.summingDouble(Invoice::getTotalAmount)));

		totalAmountByCustomer.forEach((customerID, totalAmount) -> {
			String customerName = customers.stream().filter(customer -> customer.getId() == customerID)
					.map(Customer::getName).findFirst().orElse("Unknow");
			System.out.printf("%-20s %-30s %-20s %n", customerID ,customerName ,totalAmount);
		});

	}

//	5. Liệt kê tên sản phẩm và số lượng của từng sản phẩm trong các hóa đơn.
	public static void cau5() {
	    invoices.forEach(invoice -> {
	        System.out.printf("Hóa đơn ID: %-10d%n", invoice.getId());
	        Map<String, Integer> productQuantitiesPerInvoice = invoiceDetails.stream()
	                .filter(detail -> detail.getInvoiceId() == invoice.getId())
	                .collect(Collectors.groupingBy(
	                        detail -> products.stream()
	                                          .filter(product -> product.getId() == detail.getProductId())
	                                          .map(Product::getName)
	                                          .findFirst()
	                                          .orElse("Unknown"),
	                        Collectors.summingInt(InvoiceDetail::getQuantity)));

	        productQuantitiesPerInvoice.forEach((productName, quantity) -> 
	            System.out.printf(" %-30s: %d%n", productName, quantity));
	        
	        System.out.println();
	    });
	}


//	6. Hiển thị thông tin các nhân viên đã làm việc từ ngày 01/01/2022 trở đi.
	public static void cau6(Date startDate) {
        System.out.printf("%-20s %-30s %-25s %-15s %-20s %n","Mã Nhân Viên","Họ Và Tên","Chức Vụ","Số Điện Thoại","Ngày Bắt Đầu");
		System.out.println("------------------------------------------------------");
		employees.stream().filter(employee -> employee.getStartDate().after(startDate))
				.forEach(employee -> System.out.printf("%-20s %-30s %-25s %-15s %-20s %n", 
						employee.getId(),
						employee.getName() , 
						employee.getPosition() , 
						employee.getPhone() , 
						employee.getStartDate()));
	}

//7. Tìm tất cả các hóa đơn có tổng tiền lớn hơn 100,000 VND.
	public static void cau7(double amount) {
		System.out.printf("%-5s %-5s %-10s %-30s %-20s %-20s %-20s %n","MAHD","MAKH","MANV","Thời Gian","Tổng Tiền","Hình Thức TT","Số Tiền Nhận" );
		System.out.println("------------------------------------------------------");
		invoices.stream().filter(invoice -> invoice.getTotalAmount() > amount)
		.forEach(invoice -> System.out.printf("%-5s %-5s %-10s %-30s %-20s %-20s %-20s %n",
					invoice.getId(),
					invoice.getCustomerId(),
					invoice.getEmployeeId(),
					invoice.getPaymentTime().toString(),
					invoice.getTotalAmount(),
					invoice.getPaymentMethod(),
					invoice.getReceivedAmount()));
	}

//	8. Liệt kê các khuyến mãi đang còn hiệu lực (trước ngày 31/12/2024).
	public static void cau8(Date endDate) {
		System.out.printf("%-10s %-20s %-50s %-30s %n", "MaKM","Thời Gian Hiệu Lực","Điều Kiện","Mức Giảm");
		System.out.println("------------------------------------------------------");
		promotions.stream().filter(promotion -> promotion.getEffectiveTime().before(endDate))
				.forEach(promotion-> System.out.printf("%-10s %-20s %-50s %-30s %n",
						promotion.getId(),
						promotion.getEffectiveTime(),
						promotion.getCondition(),
						promotion.getDiscount()));
	}

//	9. Tìm thông tin các khách hàng và số điểm tích lũy của họ, sắp xếp theo điểm tích lũy giảm dần.
	public static void cau9() {
		System.out.printf("%-10s %-30s %-15s %-10s %n", "ID", "Họ Và Tên", "Số Điện Thoại" ,"Điểm tích lũy"); 
		System.out.println("------------------------------------------------------"); 
		customers.stream().sorted(Comparator.comparingInt(Customer::getPoints).reversed())
		.forEach(c -> System.out.printf("%-10s %-30s %-15s %-10s %n",
				c.getId(),
				c.getName(),
				c.getPhone(),
				c.getPoints()));
	}

//	10.Tính tổng doanh thu từ các hóa đơn trong tháng 11/2024.
	public static void cau10(int year, int month) {
		double revenue = invoices.stream().filter(invoice -> {
			Calendar cal = Calendar.getInstance();
			cal.setTime(invoice.getPaymentTime());
			return cal.get(Calendar.MONTH) == month && cal.get(Calendar.YEAR) == year;
		}).mapToDouble(Invoice::getTotalAmount).sum();
		System.out.printf("Tổng doanh thu cho tháng %d năm %d: %.2f%n", month+1, year,revenue);
	}

//	11.Tìm thông tin của khách hàng có số tiền thanh toán lớn nhất trong một hóa đơn.
	public static void cau11() {
		invoices.stream().max(Comparator.comparingDouble(Invoice::getTotalAmount)).ifPresent(invoice -> {
			Optional<Customer> maxCustomer = customers.stream()
					.filter(customer -> customer.getId() == invoice.getCustomerId()).findFirst();
			System.out.printf("Khách hàng: %-30s %nSố tiền: %-10.2f%n", 
					maxCustomer.map(Customer::getName).orElse("Unknown"),
					invoice.getTotalAmount());
		});
	}

//	12.Liệt kê tất cả các khách hàng và sản phẩm của họ trong hóa đơn (bao gồm tên khách hàng và tên sản phẩm).
	public static void cau12() {
		System.out.printf("%-30s %-30s %-15s %n", "Họ Tên","Tên Sản Phẩm","Số Lượng");
		System.out.println("------------------------------------------------------"); 
		invoices.forEach(invoice -> {
			Optional<Customer> customer = customers.stream()
					.filter(c -> c.getId() == invoice.getCustomerId())
					.findFirst();

			Map<String, Integer> productQuantities = invoiceDetails.stream() 
					.filter(detail -> invoice.getId() == detail.getInvoiceId()) 
					.collect(Collectors.groupingBy( 
						detail -> products.stream() 
							.filter(product -> product.getId() == detail.getProductId()) 
							.map(Product::getName) 
							.findFirst() 
							.orElse("Unknown"),
						Collectors.summingInt(InvoiceDetail::getQuantity)));
			
			productQuantities.forEach((productName, quantity) -> { 
				System.out.printf("%-30s %-30s %-15d %n", 
						customer.map(Customer::getName).orElse("Unknown"),
						productName, quantity);
			});
		});
	}

//	13.Tìm sản phẩm có số lượng bán chạy nhất (tính tổng số lượng trong các hóa đơn).
	public static void cau13() {
		Map<String, Integer> productQuantities = invoiceDetails.stream()
				.collect(Collectors.groupingBy(
						detail -> products.stream().filter(product -> product.getId() == detail.getProductId())
								.map(Product::getName).findFirst().orElse("Unknow"),
						Collectors.summingInt(InvoiceDetail::getQuantity)));

		productQuantities.entrySet().stream().max(Map.Entry.comparingByValue()).ifPresent(topProduct -> System.out
				.println("Sản phẩm: " + topProduct.getKey() + ", Số lượng: " + topProduct.getValue()));
	}

//	14.Liệt kê các khuyến mãi đã áp dụng cho khách hàng và điều kiện khuyến mãi.
	public static void cau14() {
		System.out.printf("%-30s %-10s %-50s %n", "Họ Và Tên","Ma KM","Điều kiện");
		System.out.println("------------------------------------------------------"); 
		customerPromotions.stream().forEach(cp -> {
			Optional<Customer> customer = customers.stream().filter(c -> c.getId() == cp.getCustomerId()).findFirst();
			Optional<Promotion> promotion = promotions.stream().filter(p -> p.getId() == cp.getPromotionId())
					.findFirst();
			if (customer.isPresent() && promotion.isPresent()) {
				{
					System.out.printf("%-30s %-10s %-50s %n",customer.get().getName(),promotion.get().getId(),promotion.get().getCondition());
				}
			}
		});
	}

//	15.Tính tổng số tiền mà một khách hàng đã chi tiêu trong tất cả các hóa đơn (dựa trên mã khách hàng).
	public static void cau15(int customerId) {
		double totalSpentByCustomer = invoices.stream().filter(invoice -> invoice.getCustomerId() == customerId)
				.mapToDouble(Invoice::getTotalAmount).sum();
		System.out.println("Khách hàng " + customerId + " đã chi tiêu: " + totalSpentByCustomer);
	}
	
//	16.Liệt kê tất cả các hóa đơn có ít nhất một sản phẩm có trạng thái "Hết hàng".
	public static void cau16() {
		System.out.printf("%-7s %-30s %-7s %n","Ma HD","Thời Gian TT","Ma KH");
		System.out.println("------------------------------------------------------"); 
		invoices.stream().filter(invoice -> invoiceDetails.stream()
				.filter(detail -> detail.getInvoiceId() == invoice.getId())
				.anyMatch(detail -> products.stream().filter(product -> product.getId() == detail.getProductId())
						.anyMatch(product -> product.getStatus().equalsIgnoreCase("Hết hàng"))))
				.forEach(detail->System.out.printf("%-7s %-30s %-7s %n", detail.getId(),detail.getPaymentTime(),detail.getCustomerId()));

	}

//	17.Tính tổng tiền của từng hóa đơn và tính toán tiền giảm giá nếu có (giảm giá theo khuyến mãi).
	public static void cau17() {
		System.out.printf("%-7s %-7s %-20s %-20s %-20s %n","MaHD","MaKH","Tổng Tiền","Giảm Giá","Tổng tiền sau giảm");
		System.out.println("------------------------------------------------------"); 
		Map<Integer, Double> promotionMap = promotions.stream()
				.collect(Collectors.toMap(Promotion::getId, Promotion::getDiscount));
		invoices.forEach(invoice -> {
			double totalAmount = invoice.getTotalAmount();
			double discount = customerPromotions.stream().filter(cp -> cp.getCustomerId() == invoice.getCustomerId())
					.mapToDouble(cp -> promotionMap.getOrDefault(cp.getPromotionId(), 0.0)).sum();
			double finalAmount = totalAmount - (discount );
			System.out.printf("%-7s %-7s %-20s %-20s %-20s %n",invoice.getId(),invoice.getCustomerId(),totalAmount,discount,finalAmount);
		});	
	}

//	18.Tìm tất cả các nhân viên đã xử lý hóa đơn có tổng tiền vượt quá 200,000 VND.
	public static void cau18(double amount) {
		System.out.printf("%-20s %-30s %-25s %-15s %-20s %n","Mã Nhân Viên","Họ Và Tên","Chức Vụ","Số Điện Thoại","Ngày Bắt Đầu");
		System.out.println("------------------------------------------------------");
		
		employees.stream()
				.filter(employee -> invoices.stream().filter(
						invoice -> invoice.getEmployeeId() == employee.getId() && invoice.getTotalAmount() > amount)
						.count() > 0)
				.forEach(e -> System.out.printf("%-20s %-30s %-25s %-15s %-20s %n", 
						e.getId(),
						e.getName(),
						e.getPosition(),
						e.getPhone(),
						e.getStartDate()));
	}

//	19.Liệt kê tên sản phẩm và số lần sản phẩm đó xuất hiện trong hóa đơn.
	public static void cau19() {
		System.out.printf("%-35s %-15s %n", "Tên Sản Phẩm","Số lượng");
		System.out.println("------------------------------------------------------");
		Map<String, Long> productOccurrences = invoiceDetails.stream()
				.collect(Collectors.groupingBy(
						detail -> products.stream().filter(product -> product.getId() == detail.getProductId())
								.map(Product::getName).findFirst().orElse("Unknow"),
						Collectors.counting()));

		productOccurrences.entrySet().stream().sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
				.forEach(entry -> System.out.printf("%-35s %-15s %n",entry.getKey(),entry.getValue()));
	}

//	20. Liệt kê các khách hàng có điểm tích lũy lớn hơn 150 và có tham gia chương trình khuyến mãi.
	public static void cau20(int pointsThreshold) {
		customers.stream()
				.filter(customer -> customer.getPoints() > pointsThreshold
						&& customerPromotions.stream().anyMatch(cp -> cp.getCustomerId() == customer.getId()))
				.distinct().forEach(customer -> System.out
						.println("Khách hàng: " + customer.getName() + ", Điểm tích lũy: " + customer.getPoints()));
	}
}
