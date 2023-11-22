import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import static java.lang.System.out;

import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
class mảng_thuốc {
    // Dữ liệu
    // static Thuốc[] mảng; // null
    static Thuốc[] mảng = new Thuốc[0];

    static Thuốc[] ds() {
        return mảng;
    }

    // Hành Động, Thao Tác Dữ Liệu
    static void Gán() {

        // Khởi tạo sinh viên
        var t1 = new Thuốc();
        t1.Tên = "pepsin";
        t1.hsd = "11/11/2023";
        t1.Giá = 6.5f;
        t1.nsx = "Pharma";
        t1.số_lượng = 30;

        var t2 = new Thuốc();
        t2.Tên = "elsu";
        t2.hsd = "20/11/2024";
        t2.Giá = 9.5f;
        t2.nsx = "Pharma";
        t2.số_lượng = 500;

        var t3 = new Thuốc();
        t3.Tên = "cr7";
        t3.hsd = "10/10/3000";
        t3.Giá = 7.5f;
        t3.nsx = "Pharma";
        t3.số_lượng = 1;

        mảng = new Thuốc[] {
                t1, t2, t3
        };
    }

    static void Nhập() {
        var scan = new Scanner(System.in);

        // Nhập độ dài mảng
        int độ_dài;
        out.print("\n Nhập độ dài mảng: ");
        độ_dài = scan.nextInt();
        // scan.close();

        // khởi tạo
        mảng = new Thuốc[độ_dài]; // 3 phần tử đang null

        // Nhập dữ liệu cho từng phần tử
        for (int i = 0; i < mảng.length; i++) {

            var sv = new Thuốc();
            out.printf("\n Nhập dữ liệu cho phần tử mảng[%d]: ", i);
            // Nhập tên
            out.print("\n Nhập tên: ");
            sv.Tên = scan.next();
            // scan.close();

            // Nhập năm
            out.print("\n Nhập hạn sử dụng: ");
            sv.hsd = scan.next();

            // Nhập Giá
            out.print("\n Nhập Giá: ");
            sv.Giá = scan.nextFloat();

            // Nhập nhóm
            out.print("\n Nhập nhà sản xuất: ");
            sv.nsx = scan.next();

            // Nhập số_lượng
            out.print("\n Nhập số lượng ");
            sv.số_lượng = scan.nextInt();

            // Đẩy thuốc vào mảng/danh sách sau khi khởi tạo
            mảng[i] = sv; // Biến phái sinh
            // scan.close();

        }

        // Thông báo đã nhập
        out.println("\n Đã hoàn tất việc nhập mảng !");
    }

    static void Xuất() {

        // in cột
        Cột();

        // in các dòng
        for (int i = 0; i < mảng.length; i++) 
        {
            int stt = i + 1;
            Thuốc dữ_liệu = mảng[i];

            Dòng(dữ_liệu, stt);
        }

    }

    static void Bảng() {
        out.print("\n Bảng Dữ Liệu Thuốc:");

        // in cột
        Cột();

        // in các dòng
        // @todo Nếu dữ liệu dòng NULL thì sao ?
        for (int i = 0; i < mảng.length; i++) {
            int stt = i + 1;
            Thuốc dữ_liệu = mảng[i];
            Dòng(dữ_liệu, stt);
        }

    }

    static// Hàm hiện
    void Cột() {
        out.print("\n+---------------------------------------------------------------+");
        out.print("\n| STT | Tên Thuốc | Hạn Sử Dụng | Giá | Nhà Sản Xuất | Số Lượng |");
        out.print("\n+---------------------------------------------------------------+");
    }

    static// Hàm hiện
    void Dòng(Thuốc đt, int stt) {
        

        out.printf("\n| %3d | %9s | %11s | %3.2f | %12s | %8d |",
                stt, đt.Tên, đt.hsd, đt.Giá, đt.nsx, đt.số_lượng);
        out.print("\n+---------------------------------------------------------------+");
    }

    static void XếpGiảmDầnTheoGiá() {
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
        Xuất();
    }

    static void PhânLoạiTheonsx() {

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
    void MMA()
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

        out.printf("\n Giá min: %.2f", min);
        out.printf("\n Giá max: %.2f", max);
        out.printf("\n Giá trung bình: %.2f", avg);
    }

    public static void Thêm() {
        // - Tạo mảng mới có độ dài lớn hơn độ dài mảng cũ (1 đơn vị)
        // - Copy dữ liệu của mảng cũ sang
        // - Khởi tạo phần tử mới,
        // - Lưu phần tử mới vào cuối mảng mới
        // - Gán mảng mới vào mảng cũ

        // 1. Tạo mảng mới có độ dài lớn hơn
        Thuốc[] mảng_mới = new Thuốc[mảng.length + 1];

        // 2. Copy dữ liệu của mảng cũ sang:
        for (int i = 0; i < mảng.length; i++) {
            mảng_mới[i] = mảng[i];
        }

        // 3. Khởi tạo phần tử mới
        var scan = new Scanner(System.in);

        var t = new Thuốc();
        out.printf("\n Nhập dữ liệu cho phần tử mới: ");
        // Nhập tên
        out.print("\n Nhập tên: ");
        t.Tên = scan.next();
        // scan.close();

        // Nhập năm
        out.print("\n Nhập hạn sử dụng: ");
        t.hsd = scan.next();

        // Nhập Giá
        out.print("\n Nhập Giá: ");
        t.Giá = scan.nextFloat();

        // Nhập nhà sản xuất
        out.print("\n Nhập nhà sản xuất: ");
        t.nsx = scan.next();

        // Nhập số_lượng
        out.print("\n số lượng");
        t.số_lượng = scan.nextInt();
        // 4. Đẩy thuốc mới vào cuối của mảng mới
        mảng_mới[mảng.length] = t;

        mảng = mảng_mới;

        out.print("\n Danh sách thuốc sau khi thêm mới:");
        Xuất();

    }

    public static void Sửa() {
        // hiện lại
        Bảng();

        var scan = new Scanner(System.in);
        out.print("\n Chọn số thứ tự để sửa:");
        int stt = scan.nextInt();

        int chỉ_số_cũ = stt - 1;

        var t = new Thuốc();
        out.printf("\n Cập nhật dữ liệu cho phần tử mảng[%d]: ", chỉ_số_cũ);
        // Nhập tên
        out.print("\n Nhập tên: ");
        t.Tên = scan.next();
        // scan.close();

        // Nhập năm
        out.print("\n Nhập hạn sử dụng: ");
        t.hsd = scan.next();

        // Nhập Giá
        out.print("\n Nhập Giá: ");
        t.Giá = scan.nextFloat();

        // Nhập nhóm
        out.print("\n Nhập nhà sản xuất: ");
        t.nsx = scan.next();

        // Nhập số_lượng
        out.print("\n số lượng: ");
        t.số_lượng = scan.nextInt();

        // Lưu dữ liệu mới của thuốc vào vị trí cũ
        mảng[chỉ_số_cũ] = t;

        out.print("\n Danh sách thuốc sau khi sửa: ");

        Bảng();

    }

    public static void Xóa() {
        // hiện lại
        Bảng();

        var scan = new Scanner(System.in);
        out.print("\n Chọn số thứ tự để xóa:");
        int stt = scan.nextInt();

        int chỉ_số_cũ = stt - 1;

        int độ_dài_mới = mảng.length - 1;

        // @todo độ dài mới <= 1 thì sao ?

        Thuốc[] mảng_mới = new Thuốc[độ_dài_mới];

        // Copy dữ liệu mảng cũ sang mảng mới, trừ phần tử muốn xóa
        for (int i = 0; i < mảng_mới.length; i++) {

            if (i < chỉ_số_cũ)
                // Copy những phần tử đứng trước chỉ số cũ
                mảng_mới[i] = mảng[i];
            else
                // Copy những phần tử đứng sau chỉ số cũ
                mảng_mới[i] = mảng[i + 1];
        }

        mảng = mảng_mới;

        out.print("\n Danh sách sau khi xóa: ");
        Bảng();
    }

    public static 
        void Tìm()
        {
            // thân hàm
            out.print("\n Đang tìm kiếm....");
        }

    // /**
    //  * @abstract Lưu dữ liệu mảng vào tệp, với định dạng JSON
    //  * Các dị thường có thể xảy ra: 
    //  * -UnsupportedEncodingException: Chuỗi kí tự sử dụng lược đồ mã hóa không hỗ trợ
    //  * -FileNotFoundException: Không tìm thấy tệp trên ổ cứng
    //  * Khi nhập đường dẫn tệp file từ Terminal/Console thì nên
    //  * dùng dấu suộc trái '/' để biểu diễn. Đỡ bị lỗi FileNotFoundException
    //  */
    public static void GhiFileJSON() {

        // Chuyển đổi mảng Java Array sang chuỗi Java String JSON
        Gson gson = new Gson();
        String jsonContent = gson.toJson(mảng);
        // out.println(jsonContent);

        // Đối tượng chịu trách nhiệm viết/ghi
        // nội dung JSON tiếng Việt vào tệp/file trên ổ cứng
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
            writer.close(); // Viết xong phải đóng nó lại nếu không là công cốc !
                            // Đến lúc mở tệp ra lại chẳng thấy có dữ liệu nào được viết vào

        } catch (Exception ex) {
            out.print("\n Lỗi tệp file hoặc mã hóa bộ kí tự UTF8: ");
            ex.printStackTrace();
        }
        // finally {
        // writer.close();
        // }

        System.out.println("\n Đã ghi file JSON");
    }

    // /**
    //  * @abstract Đọc dữ liệu tệp JSON và chuyển đổi nó thành mảng
    //  * Các dị thường có thể xảy ra: 
    //  * -UnsupportedEncodingException: Chuỗi kí tự sử dụng lược đồ mã hóa không hỗ trợ
    //  * -FileNotFoundException: Không tìm thấy tệp trên ổ cứng
    //  * 
    //  * Khi nhập đường dẫn tệp file từ Terminal/Console thì nên
    //  * dùng dấu suộc trái '/' để biểu diễn. Đỡ bị lỗi FileNotFoundException
    //  */
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

            Xuất();
        } catch (Exception e) {
            out.print("\n Lỗi tệp file hoặc mã hóa bộ kí tự UTF8: ");
            e.printStackTrace();
        }
    }
}
