import java.util.Scanner;
import static java.lang.System.out;

public class Menu {
    public static 
    void menuAdmin()
    {
         while (true) {
            out.print("\n+-------------------------------------------------------------------------------------------------------------------------------+");
            out.print("\n| CHƯƠNG TRÌNH QUẢN LÝ QUẦY THUỐC                                                                                               |");
            out.print("\n+-----------------------------------------------------------MENU----------------------------------------------------------------+");
            out.print("\n| 1.Thêm | 2.Sửa | 3.Xoá | 4.Sắp Xếp | 5.Phân Loại | 6.Thống Kê | 7.Tìm Kiếm | 8.Ghi File | 9. Đọc File |10.Mua Hàng | 0. Thoát |");
            out.print("\n+-------------------------------------------------------------------------------------------------------------------------------+");

            out.print("\n Chọn menu: ");
            var scan = new Scanner(System.in);
            var menu = scan.nextInt();
            // scan.close();

            switch (menu) {
                case 1:
                    // làm việc 1
                    Quầy_Thuốc.Them();
                    break;
                case 2:
                    // làm việc 2
                    Quầy_Thuốc.Sửa();
                    break;
                case 3:
                    // làm việc 3
                    Quầy_Thuốc.Xóa();
                    break;
                case 4:
                    // làm việc 4
                    Quầy_Thuốc.SapXep();
                    break;
                case 5:
                    // làm việc 5
                    Quầy_Thuốc.PhanLoai();
                    break;
                case 6:
                    // làm việc 6
                    Quầy_Thuốc.ThongKe();
                    break;
                case 7:
                    // làm việc 7
                    Quầy_Thuốc.TimKiem();
                    break;
                case 8:
                    // làm việc 8
                    Quầy_Thuốc.GhiFileJSON();
                    break;
                case 9:
                    // làm việc 9
                    Quầy_Thuốc.ĐọcFileJSON();
                    break;

                case 10:
                    Quầy_Thuốc.muahang();
                    break;

                case 0:
                    out.print("\n Đang thoát...");
                    return;
                default:
                    out.print("\n Hãy nhập menu hợp lệ !");
                    break;
            }

        }
    
    }



    public static 
    void menu()
    {
         while (true) {
            out.print("\n+-----------------------------------------------------------------------------+");
            out.print("\n| QUẦY THUỐC                                                                  |");
            out.print("\n+----------------------------------------MENU---------------------------------+");
            out.print("\n| 1. Danh Sách | 2.Sắp Xếp | 3.Phân Loại | 4.Tìm Kiếm | 5.Mua Hàng | 0. Thoát |");
            out.print("\n+-----------------------------------------------------------------------------+");

            out.print("\n Chọn menu: ");
            var scan = new Scanner(System.in);
            var menu = scan.nextInt();
            // scan.close();

            switch (menu) {
                case 1:
                    Quầy_Thuốc.Gan();
                    Quầy_Thuốc.Bảng();
                    break;
                case 2:
                    Quầy_Thuốc.Gan();
                    Quầy_Thuốc.SapXep();
                    break;
                case 3:
                    Quầy_Thuốc.Gan();
                    Quầy_Thuốc.PhanLoai();
                    break;
                
                case 4:
                    Quầy_Thuốc.Gan();
                    Quầy_Thuốc.TimKiem();
                    break;
               
                case 5:
                    Quầy_Thuốc.Gan();
                    Quầy_Thuốc.muahang();
                    break;

                case 0:
                    out.print("\n Đang thoát...");
                    return;
                default:
                    out.print("\n Hãy nhập menu hợp lệ !");
                    break;
            }

        }
    
    }
}
