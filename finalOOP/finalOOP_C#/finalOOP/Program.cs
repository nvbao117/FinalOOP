using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace finalOOP
{
    internal class Program
    {
        
        public static DataClasses1DataContext dc1dc = new DataClasses1DataContext();
        static void Main(string[] args)
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8;

            DataClasses1DataContext dc1dc = new DataClasses1DataContext();

            cau1();
            cau2(30000);
            cau3();
            cau4();
            cau5();
            cau6(new DateTime(2022, 1, 1));
            cau7(100000);
            cau8(new DateTime(2024, 12, 31));
            cau9();
            cau10(2023, 1);
            cau11();
            cau12();
            cau13();
            cau14();
            cau15(1);
            cau16();
            cau17();
            cau18();
            cau19();
            cau20();

            Console.ReadKey();
        }
        // 1.Liệt kê tất cả các khách hàng.
        public static void cau1()
        {
            var customers = from c in dc1dc.CUSTOMERs
                            select c;
            Console.WriteLine("1. Liệt kê tất cả các khách hàng:");
            Console.WriteLine("------------------------------------------------------");
            Console.WriteLine(String.Format("{0,-10}{1,-30}{2,-15}{3,-10}", "ID", "Họ Và Tên", "Số Điện Thoại", "Điểm tích lũy"));
            Console.WriteLine("------------------------------------------------------");

            foreach (var customer in customers)
            {
                // Định dạng từng dòng thông tin khách hàng
                Console.WriteLine(String.Format("{0,-10}{1,-30}{2,-15}{3,-10}", customer.MAKH, customer.HoTen, customer.SDT, customer.DiemTichLuy));
            }
        }

        //2.Liệt kê các sản phẩm có giá lớn hơn 30,000 VND.
        public static void cau2(double price) 
        {
            Console.WriteLine("\n2. Liệt kê các sản phẩm có giá lớn hơn 30,000 VND:");
            Console.WriteLine("------------------------------------------------------");
            Console.WriteLine(String.Format("{0,-10}{1,-30}{2,-15}{3,-10}", "Mã SP","Tên Sản Phẩm","Giá","Tình Trạng "));
            Console.WriteLine("------------------------------------------------------");
            decimal priceDecimal = (decimal)price;
            dc1dc.PRODUCTs.Where(e => e.DonGia > priceDecimal)
                .ToList()
                .ForEach(p => Console.WriteLine(String.Format("{0,-10}{1,-30}{2,-15}{3,-10}", p.MASP, p.TenSP, p.DonGia, p.TinhTrang)));
        }

        //3.Hiển thị thông tin các hóa đơn cùng với tên khách hàng và tên nhân viên xử lý.
        public static void cau3()
        {
            var result = from i in dc1dc.INVOICEs
                         join c in dc1dc.CUSTOMERs on i.MAKH equals c.MAKH
                         join e in dc1dc.EMPLOYEEs on i.MAKH equals e.MANV
                         select new
                         {
                             MAHD = i.MAHD,
                             HoTenKhachHang = c.HoTen,
                             HoTenNhanVien = e.HoTen,
                             TongTien = i.TongTien,
                             ThoiGianTT = i.ThoiGianTT
                         };
            Console.WriteLine("\n3. Hiển thị thông tin các hóa đơn cùng với tên khách hàng và tên nhân viên xử lý:");
            Console.WriteLine("------------------------------------------------------");
            Console.WriteLine("{0,-15} {1,-30} {2,-30} {3,-15} {4,-30}", 
                        "Mã Hóa Đơn", "Tên Khách Hàng", "Tên Nhân Viên", "Tổng Tiền", "Thời gian");
            Console.WriteLine("------------------------------------------------------");
            foreach (var item in result)
            {
                Console.WriteLine("{0,-15} {1,-30} {2,-30} {3,-15:F2} {4,-30}",
                item.MAHD,
                item.HoTenKhachHang ?? "Unknown",
                item.HoTenNhanVien ?? "Unknown",
                item.TongTien,
                item.ThoiGianTT);

            }
        }



        //4.Tính tổng số tiền mà khách hàng đã chi tiêu(Tổng tiền của tất cả các hóa đơn).
        public static void cau4()
        {
            var result = from i in dc1dc.INVOICEs
                         join c in dc1dc.CUSTOMERs on i.MAKH equals c.MAKH
                         group i by new { c.MAKH, c.HoTen } into grouped
                         select new
                         {
                             MAKH = grouped.Key.MAKH,
                             HoTen = grouped.Key.HoTen,
                             TotalSpent = grouped.Sum(i => i.TongTien)
                         };
            Console.WriteLine("\n4. Tính tổng số tiền mà khách hàng đã chi tiêu (Tổng tiền của tất cả các hóa đơn):");
            Console.WriteLine("------------------------------------------------------");
            Console.WriteLine("{0,-20} {1,-30} {2,-20}", "Mã Khách Hàng", "Họ Và Tên", "Tổng Tiền");
            Console.WriteLine("------------------------------------------------------");
            result.ToList().ForEach(customer =>
            {
                Console.WriteLine($"{customer.MAKH,-20} {customer.HoTen,-30} {customer.TotalSpent,-20}");
            });
        }



        //5.Liệt kê tên sản phẩm và số lượng của từng sản phẩm trong mỗi hóa đơn.
        public static void cau5()
        {
            var result = from id in dc1dc.INVOICE_DETAILs
                         join p in dc1dc.PRODUCTs on id.MASP equals p.MASP
                         orderby id.MAHD
                         select new
                         {
                             MAHD = id.MAHD,
                             TenSP = p.TenSP,
                             SoLuong = id.SoLuong
                         };
            Console.WriteLine("\n5. Liệt kê tên sản phẩm và số lượng của từng sản phẩm trong các hóa đơn:");
            Console.WriteLine("------------------------------------------------------");
            result.ToList().ForEach(product => {
                Console.WriteLine($" {product.MAHD,-10}{product.TenSP,-30}: {product.SoLuong}");
            });
        }

        //6.Hiển thị thông tin các nhân viên đã làm việc từ ngày 01/01/2022 trở đi.
        public static void cau6(DateTime date)
        {
            var result = from e in dc1dc.EMPLOYEEs
                         where e.NgayBatDau > date
                         select new
                         {
                             MANV = e.MANV,
                             HOVATEN = e.HoTen,
                             ChucVu = e.ChucVu,
                             SDT = e.SDT,
                             MgayBatDau = e.NgayBatDau
                         };
            Console.WriteLine("\n6. Hiển thị thông tin các nhân viên đã làm việc từ ngày 01/01/2022 trở đi:");
            Console.WriteLine("------------------------------------------------------");
            Console.WriteLine($"{"Mã Nhân Viên",-20}{"Họ Và Tên",-30}{"Chức Vụ",-25}{"Số Điện Thoại",-15}{"Ngày Bắt Đầu",-20}"); 
            result.ToList().ForEach(employee =>
            {
                Console.WriteLine($"{employee.MANV,-20}{employee.HOVATEN,-30}{employee.ChucVu,-25}{employee.SDT,-15}{employee.MgayBatDau , -20}"); 
            });
              
        }
        //7.Tìm tất cả các hóa đơn có tổng tiền lớn hơn 100,000 VND.
        public static void cau7(decimal amount)
        {
            var result = from i in dc1dc.INVOICEs
                         where i.TongTien > amount
                         select i;
            Console.WriteLine("\n7. Tìm tất cả các hóa đơn có tổng tiền lớn hơn 100,000 VND:");
            Console.WriteLine("------------------------------------------------------");
            Console.WriteLine($"{"MAHD",-5}{"MAKH",-5}{"MANV",-10}{"Thời Gian TT",-30}{"Tổng Tiền",-20}{"Hình Thức TT",-20}{"Số Tiền Nhận",-20}");
            Console.WriteLine("------------------------------------------------------");
            result.ToList().ForEach(invoice =>
            {
                Console.WriteLine($"{invoice.MAHD,-5}{invoice.MANV,-5}{invoice.MAKH,-10}" +
                    $"{invoice.ThoiGianTT,-30}{invoice.TongTien,-20}{invoice.HinhThucTT,-20}{invoice.SoTienNhan,-20}");
            });
        }


        //8.Liệt kê các khuyến mãi đang còn hiệu lực(trước ngày 31/12/2024).
        public static void cau8(DateTime date)
        {
            var result = from p in dc1dc.PROMOTIONs 
                         where p.ThoiGianHL < date 
                         select p;
            Console.WriteLine("\n8. Liệt kê các khuyến mãi đang còn hiệu lực (trước ngày 31/12/2024):");
            Console.WriteLine("------------------------------------------------------");
            Console.WriteLine($"{"MAKM",-10}{"Thời Gian HIệu Lực",-20}{"Điều Kiện",-50}{"Mức Giảm Giá",-30}");
            Console.WriteLine("------------------------------------------------------");
            result.ToList().ForEach(promotion =>
            {
                Console.WriteLine($"{promotion.MAKM,-10}{promotion.ThoiGianHL,-20}{promotion.DieuKien,-50}{promotion.MucGiamGia,-30}");
            });
        }
        ////9.Tìm thông tin các khách hàng và số điểm tích lũy của họ, sắp xếp theo điểm tích lũy giảm dần.
        public static void cau9()
        {
            var result = from c in dc1dc.CUSTOMERs
                         orderby c.DiemTichLuy descending 
                         select c;
            Console.WriteLine("\n9. Tìm thông tin các khách hàng và số điểm tích lũy của họ, sắp xếp theo điểm tích lũy giảm dần:");
            Console.WriteLine("------------------------------------------------------");
            Console.WriteLine("{0,-10} {1,-30} {2,-15} {3,-10}", "ID", "Họ Và Tên", "Số Điện Thoại", "Điểm tích lũy");
            Console.WriteLine("------------------------------------------------------");
            result.ToList().ForEach(c =>
            {
                Console.WriteLine("{0,-10} {1,-30} {2,-15} {3,-10}",c.MAKH,c.HoTen,c.SDT,c.DiemTichLuy);
            });
        }

        ////10.Tính tổng doanh thu từ các hóa đơn trong tháng 1/2023.
        public static void cau10(int year , int month)
        {
            var totalRevenue = (from i in dc1dc.INVOICEs
                               where i.ThoiGianTT.Month == month && i.ThoiGianTT.Year == year
                               select i.TongTien).Sum();
                               
            Console.WriteLine("\n10. Tính tổng doanh thu từ các hóa đơn trong tháng 1/2023:");
            Console.WriteLine("------------------------------------------------------");
            Console.WriteLine("Tổng doanh thu cho tháng {0} năm {1}: {2:F2}",month , year , totalRevenue);
        }

        //11.Tìm thông tin của khách hàng có số tiền thanh toán lớn nhất trong một hóa đơn.
        public static void cau11()
        {
            var maxAmount = dc1dc.INVOICEs.Max(i => i.TongTien);

            var result = (from i in dc1dc.INVOICEs
                         join c in dc1dc.CUSTOMERs on i.MAKH equals c.MAKH
                         where i.TongTien == maxAmount
                         select new
                         {
                             c.MAKH,
                             c.HoTen,
                             i.MAHD,
                             i.TongTien
                         }).FirstOrDefault();
            Console.WriteLine("\n11. Tìm thông tin của khách hàng có số tiền thanh toán lớn nhất trong một hóa đơn:");
            Console.WriteLine("------------------------------------------------------");
            if(result != null)
            {
                Console.WriteLine("Khách hàng: {0,-30} \nSố tiền: {1,-10:F2}", result.HoTen, result.TongTien);
            }
            else
            {
                Console.WriteLine("Không có khách hàng nào thỏa mãn điều kiện.");
            }
           
        }
        ////12.Liệt kê tất cả các khách hàng và sản phẩm của họ trong hóa đơn(bao gồm tên khách hàng và tên sản phẩm).
        public static void cau12()
        {
            var result = from id in dc1dc.INVOICE_DETAILs
                         join i in dc1dc.INVOICEs on id.MAHD equals i.MAHD
                         join c in dc1dc.CUSTOMERs on i.MAKH equals c.MAKH
                         join p in dc1dc.PRODUCTs on id.MASP equals p.MASP
                         orderby c.MAKH, i.MAHD
                         select new
                         {
                             c.HoTen,
                             p.TenSP,
                             id.SoLuong
                         };
            Console.WriteLine("\n12. Liệt kê tất cả các khách hàng và sản phẩm của họ trong hóa đơn (bao gồm tên khách hàng và tên sản phẩm):");
            Console.WriteLine("------------------------------------------------------");
            Console.WriteLine("{0,-30} {1,-30} {2,-15}", "Họ Tên", "Tên Sản Phẩm", "Số Lượng");
            Console.WriteLine("------------------------------------------------------");
            result.ToList().ForEach(x => Console.WriteLine("{0,-30} {1,-30} {2,-15}", x.HoTen, x.TenSP, x.SoLuong)); 

        }

        //13.Tìm sản phẩm có số lượng bán chạy nhất(tính tổng số lượng trong các hóa đơn).
        public static void cau13()
        {
            var result = (from id in dc1dc.INVOICE_DETAILs
                          join p in dc1dc.PRODUCTs on id.MASP equals p.MASP
                          group id by p.TenSP into grouped
                          orderby grouped.Sum(x => x.SoLuong) descending
                          select new
                          {
                              TenSP = grouped.Key,
                              TotalQuantity = grouped.Sum(x => x.SoLuong)
                          }).FirstOrDefault();
            Console.WriteLine("\n13. Tìm sản phẩm có số lượng bán chạy nhất (tính tổng số lượng trong các hóa đơn):");
            Console.WriteLine("------------------------------------------------------");
            Console.WriteLine("{0} :{1}", result.TenSP, result.TotalQuantity); 

        }
        ////14.Liệt kê các khuyến mãi đã áp dụng cho khách hàng và điều kiện khuyến mãi.
        public static void cau14()
        {
            var result = from cp in dc1dc.CUSTOMER_PROMOTIONs
                         join c in dc1dc.CUSTOMERs on cp.MAKH equals c.MAKH
                         join p in dc1dc.PROMOTIONs on cp.MAKM equals p.MAKM
                         orderby c.HoTen
                         select new
                         {
                             HoTen = c.HoTen,
                             MAKM = p.MAKM,
                             DieuKien = p.DieuKien
                         };
            Console.WriteLine("\n14. Liệt kê các khuyến mãi đã áp dụng cho khách hàng và điều kiện khuyến mãi:");
            Console.WriteLine("------------------------------------------------------");
            Console.WriteLine("{0,-30} {1,-10} {2,-50}", "Họ Và Tên", "Ma KM", "Điều kiện");
            Console.WriteLine("------------------------------------------------------");
            result.ToList().ForEach(x => Console.WriteLine("{0,-30} {1,-10} {2,-50}",x.HoTen,x.MAKM,x.DieuKien)); 

        }
        ////15.Tính tổng số tiền mà một khách hàng đã chi tiêu trong tất cả các hóa đơn(dựa trên mã khách hàng).
        public static void cau15(int MAKH)
        {
            var result = (from i in dc1dc.INVOICEs
                          where i.MAKH == MAKH
                          group i by i.MAKH into g
                          select new
                          {
                              MAKH = g.Key,
                              TONGTIEN = g.Sum(x => x.TongTien)
                          }).FirstOrDefault();

            if (result != null)
            {
                Console.WriteLine("\n15. Tính tổng số tiền mà một khách hàng đã chi tiêu trong tất cả các hóa đơn (dựa trên mã khách hàng):");
                Console.WriteLine("------------------------------------------------------");
                Console.WriteLine($"Khách hàng: {result.MAKH}");
                Console.WriteLine($"Tổng số tiền: {result.TONGTIEN:F2}");
            }
            else
            {
                Console.WriteLine("Không tìm thấy thông tin khách hàng.");
            }
        }
        ////16.Liệt kê tất cả các hóa đơn có ít nhất một sản phẩm có trạng thái "Hết hàng".
        public static void cau16()
        {
            var result = (from i in dc1dc.INVOICEs
                          join id in dc1dc.INVOICE_DETAILs on i.MAHD equals id.MAHD
                          join p in dc1dc.PRODUCTs on id.MASP equals p.MASP
                          where p.TinhTrang == "Hết hàng"
                          select new
                          {
                              MAHD = i.MAHD,
                              ThoiGianTT = i.ThoiGianTT,
                              MAKH = i.MAKH
                          }).Distinct().ToList();
            Console.WriteLine("\n16. Liệt kê tất cả các hóa đơn có ít nhất một sản phẩm có trạng thái \"Hết hàng\":");
            Console.WriteLine("------------------------------------------------------");
            Console.WriteLine("{0,-7} {1,-30} {2,-7}", "Ma HD", "Thời Gian TT", "Ma KH");
            Console.WriteLine("------------------------------------------------------");
            foreach (var detail in result)
            {
                Console.WriteLine("{0,-7} {1,-30} {2,-7}", detail.MAHD, detail.ThoiGianTT, detail.MAKH);
            }
        }

        ////17.Tính tổng tiền của từng hóa đơn và tính toán tiền giảm giá nếu có(giảm giá theo khuyến mãi).
        public static void cau17()
        {
            var result = (from i in dc1dc.INVOICEs
                          join cp in dc1dc.CUSTOMER_PROMOTIONs on i.MAKH equals cp.MAKH into cpGroup
                          from cp in cpGroup.DefaultIfEmpty()
                          join p in dc1dc.PROMOTIONs on cp.MAKM equals p.MAKM into pGroup
                          from p in pGroup.DefaultIfEmpty()
                          group new { i, p } by new { i.MAHD, i.MAKH, i.TongTien } into grouped
                          select new
                          {
                              MAHD = grouped.Key.MAHD,
                              MAKH = grouped.Key.MAKH,
                              TotalAmount = grouped.Key.TongTien,
                              Discount = grouped.Max(x => x.p != null ? x.p.MucGiamGia : 0),
                              FinalAmount = grouped.Key.TongTien - (grouped.Max(x => x.p != null ? x.p.MucGiamGia : 0))
                          }).ToList();
            Console.WriteLine("\n17.Tính tổng tiền của từng hóa đơn và tính toán tiền giảm giá nếu có(giảm giá theo khuyến mãi)");
            Console.WriteLine("------------------------------------------------------");
            Console.WriteLine("{0,-10} {1,-10} {2,-15} {3,-10} {4,-15}", "MAHD", "MAKH", "TotalAmount", "Discount", "FinalAmount"); 
            Console.WriteLine("---------------------------------------------------------------------");
            foreach (var item in result) { Console.WriteLine("{0,-10} {1,-10} {2,-15:F2} {3,-10:F2} {4,-15:F2}", 
                item.MAHD, item.MAKH, item.TotalAmount, item.Discount, item.FinalAmount); }
        }
        //18.Tìm tất cả các nhân viên đã xử lý hóa đơn có tổng tiền vượt quá 200,000 VND.
        public static void cau18()
        {
            var result = (from i in dc1dc.INVOICEs
                          join e in dc1dc.EMPLOYEEs on i.MANV equals e.MANV
                          where i.TongTien > 200000
                          select e.HoTen).Distinct().ToList();
            Console.WriteLine("\n18. Tìm tất cả các nhân viên đã xử lý hóa đơn có tổng tiền vượt quá 200,000 VND:");
            Console.WriteLine("------------------------------------------------------");

            foreach(var e in result)
            {
                Console.WriteLine(e);
            }

        }
        //19.Liệt kê tên sản phẩm và số lần sản phẩm đó xuất hiện trong hóa đơn.
        public static void cau19()
        {
            var result = (from id in dc1dc.INVOICE_DETAILs
                          join p in dc1dc.PRODUCTs on id.MASP equals p.MASP
                          group id by p.TenSP into grouped
                          orderby grouped.Count() descending
                          select new
                          {
                              TenSP = grouped.Key,
                              SoLanXuatHien = grouped.Count()
                          }).ToList();
            Console.WriteLine("\n19. Liệt kê tên sản phẩm và số lần sản phẩm đó xuất hiện trong hóa đơn:");
            Console.WriteLine("------------------------------------------------------");
            Console.WriteLine("{0,-35} {1,-15}", "Tên Sản Phẩm", "Số lượng");
            Console.WriteLine("------------------------------------------------------"); 
            foreach( var e in result)
            {
                Console.WriteLine("{0,-35} {1,-15}", e.TenSP,e.SoLanXuatHien);
            }
        }

        //20. Liệt kê các khách hàng có điểm tích lũy lớn hơn 150 và có tham gia chương trình khuyến mãi.
        public static void cau20()
        {
            var result = (from c in dc1dc.CUSTOMERs
                          join cp in dc1dc.CUSTOMER_PROMOTIONs on c.MAKH equals cp.MAKH
                          join p in dc1dc.PROMOTIONs on cp.MAKM equals p.MAKM
                          where c.DiemTichLuy > 150
                          select new
                          {
                              HoTen = c.HoTen,
                              DiemTichLuy = c.DiemTichLuy
                          }).Distinct().ToList();
            Console.WriteLine("\n20. Liệt kê các khách hàng có điểm tích lũy lớn hơn 150 và có tham gia chương trình khuyến mãi:");
            Console.WriteLine("------------------------------------------------------");
            foreach (var customer in result) { 
                Console.WriteLine("Khách hàng: {0}, Điểm tích lũy: {1}", customer.HoTen, customer.DiemTichLuy); 
            }
        }
    }
}
