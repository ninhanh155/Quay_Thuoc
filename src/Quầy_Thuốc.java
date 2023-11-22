import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Quầy_Thuốc {
    static Thuốc[] mảng = new Thuốc[0];

    static Thuốc[] ds() {
        return mảng;
    }

    static void Bảng() {
        out.print("\n Bảng Dữ Liệu Thuốc:");

        // in cột
        Cột();

        for (int i = 0; i < mảng.length; i++) {
            int stt = i + 1;
            Thuốc dữ_liệu = mảng[i];
            Dòng(stt, dữ_liệu);
        }

    }

    static// Hàm hiện
    void Cột() {
        out.print("\n+---------------------------------------------------------------------+");
        out.print("\n| STT | Tên Thuốc | Hạn Sử Dụng |    Giá    | Nhà Sản Xuất | Số Lượng |");
        out.print("\n+---------------------------------------------------------------------+");
    }

    static// Hàm hiện
    void Dòng(int stt, Thuốc đt) {
        

        out.printf("\n| %3d | %9s | %11s | %9.1f | %12s | %8d |",
                stt, đt.Tên, đt.hsd, đt.Giá, đt.nsx, đt.số_lượng);
        out.print("\n+---------------------------------------------------------------------+");
    }

    public static void Them() {
        Thuốc[] mang_moi = new Thuốc[mảng.length + 1];
    
        for (int i = 0; i < mảng.length; i++) {
            mang_moi[i] = mảng[i];
        }
        var scan = new Scanner(System.in);
        var thuoc = new Thuốc();
    
        do {
            System.out.print("\nNhập tên thuốc: ");
            if (scan.hasNextLine()) {
                String input = scan.nextLine();
                if (input.matches("[a-zA-Z ]+")) {
                    thuoc.Tên = input;
                    break;
                } else {
                    System.out.println("Tên không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("Không có dữ liệu đầu vào hợp lệ.");
                return;
            }
        } while (true);
    
        do {
            System.out.print("\nNhập hạn sử dụng: ");
            if (scan.hasNextLine()) {
                String input = scan.nextLine();
                if (input.matches("[a-zA-Z ]+")) {
                    thuoc.hsd = input;
                    break;
                } else {
                    System.out.println("Không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("Không có dữ liệu đầu vào hợp lệ.");
                return;
            }
        } while (true);
    
        do {
            System.out.print("Nhập giá: ");
            if (scan.hasNextFloat()) {
                float input = scan.nextFloat();
                scan.nextLine();
                if (input >= 0) {
                    thuoc.Giá = input;
                    break;
                } else {
                    System.out.println("Giá không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("Không có dữ liệu đầu vào hợp lệ.");
                scan.nextLine(); // Đọc dòng mới sau khi gặp lỗi
            }
        } while (true);
    
        do {
            System.out.print("\nNhập tên nhà cung cấp: ");
            if (scan.hasNextLine()) {
                String input = scan.nextLine();
                if (input.matches("[a-zA-Z ]+")) {
                    thuoc.nsx = input;
                    break;
                } else {
                    System.out.println("Tên không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("Không có dữ liệu đầu vào hợp lệ.");
                return;
            }
        } while (true);
    
        do {
            System.out.print("Nhập số lượng tồn kho: ");
            if (scan.hasNextInt()) {
                int input = scan.nextInt();
                scan.nextLine();
                if (input > 0) {
                    thuoc.số_lượng = input;
                    break;
                } else {
                    System.out.println("Số lượng tồn kho không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("Không có dữ liệu đầu vào hợp lệ.");
                scan.nextLine(); // Đọc dòng mới sau khi gặp lỗi
            }
        } while (true);
    
        mang_moi[mảng.length] = thuoc;
        mảng = mang_moi;
        Bảng();
    }
    public static void Sửa() {
        // hiện lại
        Bảng();

        var scan = new Scanner(System.in);
        
        int stt = -1;
        boolean check = false;
        do {
            System.out.print("\nChọn số thứ tự để sửa: ");
            if (scan.hasNextInt()) {
                stt = scan.nextInt();
                scan.nextLine();
                if (stt <= 0 || stt > mảng.length) {
                    System.out.println("STT không hợp lệ. Vui lòng nhập lại một số nguyên dương!");
                } else {
                    check = true;
                }
            } else {
                System.out.println("Vui lòng nhập một số nguyên!");
                scan.nextLine();
            }
        } while (!check);
        
        int viTri = stt - 1;

        var thuoc = new Thuốc();
        out.printf("\n Cập nhật dữ liệu cho phần tử mảng[%d]: ", viTri);
        // Nhập tên
        do {
            System.out.print("\nNhập tên thuốc: ");
            if (scan.hasNextLine()) {
                String input = scan.nextLine();
                if (input.matches("[a-zA-Z ]+")) {
                    thuoc.Tên = input;
                    break;
                } else {
                    System.out.println("Tên không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("Không có dữ liệu đầu vào hợp lệ.");
                return;
            }
        } while (true);
    
        do {
            System.out.print("\nNhập hạn sử dụng: ");
            if (scan.hasNextLine()) {
                String input = scan.nextLine();
                if (input.matches("[a-zA-Z ]+")) {
                    thuoc.hsd = input;
                    break;
                } else {
                    System.out.println("Không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("Không có dữ liệu đầu vào hợp lệ.");
                return;
            }
        } while (true);
    
        do {
            System.out.print("Nhập giá: ");
            if (scan.hasNextFloat()) {
                float input = scan.nextFloat();
                scan.nextLine();
                if (input >= 0) {
                    thuoc.Giá = input;
                    break;
                } else {
                    System.out.println("Giá không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("Không có dữ liệu đầu vào hợp lệ.");
                scan.nextLine(); // Đọc dòng mới sau khi gặp lỗi
            }
        } while (true);
    
        do {
            System.out.print("\nNhập tên nhà cung cấp: ");
            if (scan.hasNextLine()) {
                String input = scan.nextLine();
                if (input.matches("[a-zA-Z ]+")) {
                    thuoc.nsx = input;
                    break;
                } else {
                    System.out.println("Tên không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("Không có dữ liệu đầu vào hợp lệ.");
                return;
            }
        } while (true);
    
        do {
            System.out.print("Nhập số lượng tồn kho: ");
            if (scan.hasNextInt()) {
                int input = scan.nextInt();
                scan.nextLine();
                if (input > 0) {
                    thuoc.số_lượng = input;
                    break;
                } else {
                    System.out.println("Số lượng tồn kho không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("Không có dữ liệu đầu vào hợp lệ.");
                scan.nextLine(); // Đọc dòng mới sau khi gặp lỗi
            }
        } while (true);

        // Lưu dữ liệu mới của thuốc vào vị trí cũ
        mảng[viTri] = thuoc;

        out.print("\n Danh sách thuốc sau khi sửa: ");

        Bảng();

    }

    public static void Xóa() {
        // hiện lại
        Bảng();

        var scan = new Scanner(System.in);
        int stt = -1;
        boolean check = false;
        do {
            System.out.print("\nChọn số thứ tự để xóa: ");
            if (scan.hasNextInt()) {
                stt = scan.nextInt();
                scan.nextLine();
                if (stt <= 0 || stt > mảng.length) {
                    System.out.println("STT không hợp lệ. Vui lòng nhập lại một số nguyên dương!");
                } else {
                    check = true;
                }
            } else {
                System.out.println("Vui lòng nhập một số nguyên!");
                scan.nextLine();
            }
        } while (!check);
        
        int viTri = stt - 1;

        int độ_dài_mới = mảng.length - 1;


        Thuốc[] mang_moi = new Thuốc[độ_dài_mới];

        // Copy dữ liệu mảng cũ sang mảng mới, trừ phần tử muốn xóa
        for (int i = 0; i < mang_moi.length; i++) {

            if (i < viTri)
                // Copy những phần tử đứng trước chỉ số cũ
                mang_moi[i] = mảng[i];
            else
                // Copy những phần tử đứng sau chỉ số cũ
                mang_moi[i] = mảng[i + 1];
        }

        mảng = mang_moi;

        out.print("\n Danh sách sau khi xóa: ");
        Bảng();
    }

    static void SapXep() {
        for (int i = 0; i < mảng.length; i++) {
            for (int j = i + 1; j < mảng.length; j++) {
                var logic = mảng[i].Giá > mảng[j].Giá;

                if// nếu
                (!logic)// logic sai, không thỏa mãn
                {
                    Thuốc tạm = mảng[i];
                    mảng[i] = mảng[j];
                    mảng[j] = tạm;
                }
            }
        }

        out.print("\n Đã sắp xếp giảm dần theo Giá: ");
        Bảng();
    }

    public static void ThongKe()
    {
        float min;
        float max;
        float sum; // tổng
        float avg; //trung_bình;

        min = mảng[0].Giá;
        max = mảng[0].Giá;
        sum = 0.0f;
        avg = 0.0f;

        for (Thuốc t : mảng) {
            if(min > t.Giá)
                min = t.Giá;
            if(max < t.Giá)
                max = t.Giá;

            sum += t.Giá;
        }
        avg = sum/mảng.length;

        out.printf("\n Giá min: %.1f", min);
        out.printf("\n Giá max: %.1f", max);
        out.printf("\n Giá trung bình: %.1f", avg);
    }

    static void PhanLoai() {

        // Mảng chứa dữ liệu thống kê
        int[] sl = new int[mảng.length]; // mối phần tử = 0

        for (int i = 0; i < sl.length; i++) {
            sl[i] = 1;
        }

        // Tinh chỉnh dữ liệu thống kê
        for (int i = 0; i < mảng.length; i++) {
            for (int j = i + 1; j < mảng.length; j++) {
                var i_j_cùng_loại = mảng[i].nsx == mảng[j].nsx;

                if// nếu
                (i_j_cùng_loại && sl[j] != 0) {
                    sl[i]++;
                    sl[j]--;
                }
            }
        }

        // In dữ liệu thống kê, phân loại ra màn hình
        for (int i = 0; i < sl.length; i++) {
            if (sl[i] != 0) {
                out.printf("\n Nhà sản xuất %s có %d thuốc.", mảng[i].nsx, sl[i]);
            }
        }
    }

    public static 
        void TimKiem()
        {
            Scanner scan = new Scanner(System.in);
            
            boolean check = false; 
             // In cột tiêu đề
            
            do {
                System.out.print("Nhập tên thuốc cần tìm: ");
                String charInput = scan.nextLine().toLowerCase();

                if (!charInput.matches("[a-zA-Z ]+")) {
                    System.out.println("\nTên không hợp lệ. Vui lòng nhập lại.");
                }
                else{
                    Cột();
                    for (int i = 0; i < mảng.length; i++) {
                    String ten = mảng[i].Tên.toLowerCase();
                    if (ten.startsWith(charInput)) {
                        check = true;
                        int stt = i + 1;
                        Thuốc dulieu = mảng[i];
                        Dòng( stt, dulieu);
                        break;// In ra thông tin của sinh viên tìm thấy
                    }
                    }
                if (!check) {
                    System.out.println("\nKhông có dữ liệu tên thuốc bạn muốn tìm");
                    break;
                }
                }  
            }while(!check);
        }

    public static void GhiFileJSON() {

        
        Gson gson = new Gson();
        String jsonContent = gson.toJson(mảng);

        Writer writer;
        try {
            // Đường dẫn tĩnh để test nhanh
            String filePath = "C:\\Users\\Public\\mảng-thuốc.json";

            // Đường dẫn động nhập từ bàn phím:
            var scan = new Scanner(System.in);
            out.print("\n Nhập đường dẫn tệp file cần ghi dữ liệu: ");
            filePath = scan.next();
            // ví dụ: c:/users/public/mang-thuốc.json

            writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(filePath),
                            "UTF-8"));
            writer.write(jsonContent);
            writer.flush();
            writer.close(); 

        } catch (Exception ex) {
            out.print("\n Lỗi tệp file hoặc mã hóa bộ kí tự UTF8: ");
            ex.printStackTrace();
        }
        // finally {
        // writer.close();
        // }

        System.out.println("\n Đã ghi file JSON");
    }

    
    public static void ĐọcFileJSON() {
        Gson gson = new Gson();

        try {
            // Đường dẫn tĩnh để test nhanh
            String filePath = "C:\\Users\\Public\\mảng-thuốc.json";

            // Đường dẫn động nhập từ bàn phím:
            var scan = new Scanner(System.in);
            out.print("\n Nhập đường dẫn tệp file cần đọc dữ liệu: ");
            filePath = scan.next();
            // ví dụ: c:/users/public/mảng-thuốc.json

            JsonReader reader = new JsonReader(new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filePath),
                            "UTF-8")));

            mảng = gson.fromJson(reader, Thuốc[].class);

            Bảng();
        } catch (Exception e) {
            out.print("\n Lỗi tệp file hoặc mã hóa bộ kí tự UTF8: ");
            e.printStackTrace();
        }
    }
}
