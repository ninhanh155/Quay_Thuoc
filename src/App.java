import java.util.Scanner;
import static java.lang.System.out;

class App {
    public static void main(String[] args) throws Exception {
       
        while (true) {
            out.print("\n+--------------------------------------+");
            out.print("\n| CHƯƠNG TRÌNH QUẢN LÝ                 |");
            out.print("\n+----------------MENU------------------+");
            out.print("\n| 1. Đăng Ký | 2. Đăng Nhập | 0. Thoát |");
            out.print("\n+--------------------------------------+");
            System.out.print("\nChọn menu: ");
            var scan = new Scanner(System.in);
            var menu = scan.nextInt();
            switch (menu) {
                case 1:
                    Quầy_Thuốc.DangKi();
                    break;
                case 2:
                    Quầy_Thuốc.DangNhap();
                    break;
                case 0:
                    out.print("\n Đang thoát...");
                    scan.close();
                    System.exit(0);
                    break;
                default:
                    out.print("\n Hãy nhập menu hợp lệ !");
                    break;
            }

        }
        
        
    }
}
