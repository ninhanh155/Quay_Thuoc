import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
// import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Quầy_Thuốc {
    static Thuốc[] mảng = new Thuốc[0];

    static Thuốc[] ds() {
        return mảng;
    }

    static void Bảng() {
        out.print("\n********Bảng Dữ Liệu Thuốc********");

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
                    System.out.println("  => Tên không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("  => Không có dữ liệu đầu vào hợp lệ.");
                return;
            }
        } while (true);
    
        do {
            System.out.print("\nNhập hạn sử dụng (định dạng dd/mm/yyyy): ");
            if (scan.hasNextLine()) {
                String input = scan.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                try {
                    LocalDate date = LocalDate.parse(input, formatter);
                    LocalDate today = LocalDate.now();

                    if (date.isAfter(today) || date.isEqual(today)) {
                        thuoc.hsd = formatter.format(date);
                        break;
                    } else {
                        System.out.println("  => Ngày không hợp lệ. Hãy thử kiểm tra form nhập hoặc ngày xem đã quá hạn chưa nhé!");
                    }
                } catch (java.time.format.DateTimeParseException e) {
                    System.out.println("  => Định dạng ngày không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("  => Không có dữ liệu đầu vào hợp lệ.");
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
                    System.out.println("  => Giá không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("  => Không có dữ liệu đầu vào hợp lệ.");
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
                    System.out.println("  => Tên không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("  => Không có dữ liệu đầu vào hợp lệ.");
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
                    System.out.println("  => Số lượng tồn kho không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("  => Không có dữ liệu đầu vào hợp lệ.");
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
                    System.out.println("  => STT không hợp lệ. Vui lòng nhập lại một số nguyên dương!");
                } else {
                    check = true;
                }
            } else {
                System.out.println("  => Vui lòng nhập một số nguyên!");
                scan.nextLine();
            }
        } while (!check);
        
        int viTri = stt - 1;

        var thuoc = new Thuốc();
        out.printf("\n Cập nhật dữ liệu cho thuốc: ", viTri);
        // Nhập tên
        do {
            System.out.print("\nNhập tên thuốc: ");
            if (scan.hasNextLine()) {
                String input = scan.nextLine();
                if (input.matches("[a-zA-Z ]+")) {
                    thuoc.Tên = input;
                    break;
                } else {
                    System.out.println("  => Tên không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("  => Không có dữ liệu đầu vào hợp lệ.");
                return;
            }
        } while (true);
    
        do {
            System.out.print("\nNhập hạn sử dụng (định dạng dd/mm/yyyy): ");
            if (scan.hasNextLine()) {
                String input = scan.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                try {
                    LocalDate date = LocalDate.parse(input, formatter);
                    LocalDate today = LocalDate.now();

                    if (date.isAfter(today) || date.isEqual(today)) {
                        thuoc.hsd = formatter.format(date);
                        break;
                    } else {
                        System.out.println("  => Ngày không hợp lệ. Hãy thử kiểm tra form nhập hoặc ngày xem đã quá hạn chưa nhé!");
                    }
                } catch (java.time.format.DateTimeParseException e) {
                    System.out.println("  => Định dạng ngày không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("  => Không có dữ liệu đầu vào hợp lệ.");
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
                    System.out.println("  => Giá không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("  => Không có dữ liệu đầu vào hợp lệ.");
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
                    System.out.println("  => Tên không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("  => Không có dữ liệu đầu vào hợp lệ.");
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
                    System.out.println("  => Số lượng tồn kho không hợp lệ. Vui lòng nhập lại.");
                }
            } else {
                System.out.println("  => Không có dữ liệu đầu vào hợp lệ.");
                scan.nextLine(); // Đọc dòng mới sau khi gặp lỗi
            }
        } while (true);

        // Lưu dữ liệu mới của thuốc vào vị trí cũ
        mảng[viTri] = thuoc;

        out.print("\n  => Danh sách thuốc sau khi sửa: ");

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
                    System.out.println("  => STT không hợp lệ. Vui lòng nhập lại một số nguyên dương!");
                } else {
                    check = true;
                }
            } else {
                System.out.println("  => Vui lòng nhập một số nguyên!");
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

        out.print("\n  => Danh sách sau khi xóa: ");
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

        out.print("\n********Đã Sắp Xếp Giảm Dần Theo Giá********");
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

        out.printf("\n  => Giá min: %.1f", min);
        out.printf("\n  => Giá max: %.1f", max);
        out.printf("\n  => Giá trung bình: %.1f", avg);
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
                out.printf("\n  -> Nhà sản xuất %s có %d thuốc.", mảng[i].nsx, sl[i]);
            }
        }
    }

    public static void TimKiem() {
        Scanner scan = new Scanner(System.in);
        boolean check = false; 
    
        do {
            System.out.print("Nhập từ khóa cần tìm: ");
            String keywordInput = scan.nextLine().toLowerCase();
    
            if (!keywordInput.matches("[a-zA-Z ]+")) {
                System.out.println("\nTừ khóa không hợp lệ. Vui lòng nhập lại.");
            } else {
                Cột();
                for (int i = 0; i < mảng.length; i++) {
                    String ten = mảng[i].Tên.toLowerCase();
                    if (ten.contains(keywordInput)) {
                        check = true;
                        int stt = i + 1;
                        Thuốc dulieu = mảng[i];
                        Dòng(stt, dulieu);
                        
                    }
                }
                if (!check) {
                    System.out.println("\n  => Không có dữ liệu thuốc bạn muốn tìm");
                    break;
                }
            }  
        } while(!check);
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
            out.print("\n  => Lỗi tệp file hoặc mã hóa bộ kí tự UTF8: ");
            ex.printStackTrace();
        }

        System.out.println("\n*****Đã ghi file JSON*****");
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
            out.print("\n  => Lỗi tệp file hoặc mã hóa bộ kí tự UTF8: ");
            e.printStackTrace();
        }
    }

    public static void muahang()
    {
            Scanner scan = new Scanner(System.in);
        
            boolean check = false;
            // Nhập tên thuốc
            System.out.print("Nhập tên thuốc: ");
            String Tên = scan.nextLine();
    
            // Nhập số lượng thuốc
            System.out.print("Nhập số lượng: ");
            int số_lượng = scan.nextInt();
            float dulieu = 0;
            for (int i = 0; i < mảng.length; i++) {
                String ten = mảng[i].Tên.toLowerCase();
                if (ten.startsWith(Tên)) {
                    check = true;
                    int stt = i + 1;
                    dulieu = mảng[i].Giá;
                    break;// In ra thông tin của sinh viên tìm thấy
                }
            
            }
            if (check == false) {
                System.out.println("\n  => Không có loại thuốc bạn cần tìm");
            }
            else{
                double thanhtien = số_lượng * dulieu;
                System.out.println("Thông tin đơn thuốc($):");
                System.out.println("    +> Tên thuốc: " + Tên);
                System.out.println("    +> Số lượng: " + số_lượng);
                System.out.println("    +> Giá: " +  dulieu);
                System.out.println("    +> Thành tiền: " + thanhtien);
            }
    }
    // đăng ký tài khoảng 
    public static void DangKi() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Đăng ký");

        while (true) {
            System.out.print("Nhập tên: ");
            String name = scan.nextLine();

            System.out.print("Nhập tên tài khoản: ");
            String username = scan.nextLine();

            if (username.equals("admin")) {
                System.out.println("Tài khoản admin đã được đăng ký.");
                continue;
            }

            System.out.print("Nhập mật khẩu: ");
            String password = scan.nextLine();

            System.out.print("Nhập lại mật khẩu: ");
            String passwordAgain = scan.nextLine();

            if (password.equals(passwordAgain)) {
                if (!checkSpecialCharacter(username)) {

                    String hashedPassword = hashPassword(password);
                    String hashedPasswordStr = new String(hashedPassword);

                    Random random = new Random();
                    int id = random.nextInt(100000) + 1;

                    User userObj = new User();
                    userObj.setId(id);
                    userObj.setName(name);
                    userObj.setUsername(username);
                    userObj.setPassword(hashedPasswordStr);

                    String filePath = "data/User/" + userObj.getUsername() + ".json";
                    File file = new File(filePath);

                    if (file.exists()) {
                        System.out.println("Tài khoản này đã được đăng ký, vui lòng đăng ký tài khoản khác.");
                        break;
                    } else {
                        try {
                            saveUserToJsonFile(userObj, filePath);
                            System.out.println("Đăng ký thành công");
                            Menu.menu();
                            return;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("Tên người dùng không được chứa ký tự đặc biệt.");
                }
            } else {
                System.out.println("Mật khẩu không khớp.");
            }
        }
    }
    
    public static 
        void Gan()
        {
            Gson gson = new Gson();

        try {
            // Đường dẫn tĩnh để test nhanh
            
            
            String filePath = "test.json";
            JsonReader reader = new JsonReader(new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(filePath),
                            "UTF-8")));

            mảng = gson.fromJson(reader, Thuốc[].class);

        } catch (Exception e) {
            // out.print("\n Lỗi tệp file hoặc mã hóa bộ kí tự UTF8: ");
            e.printStackTrace();
        }
        }

    public static boolean checkSpecialCharacter(String str) {
        // Kiểm tra xem str có chứa ký tự đặc biệt hay không
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
    
    public static String hashPassword(String password) {
        // Thực hiện mã hóa mật khẩu và trả về mật khẩu đã được mã hóa
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean checkAdminPassword(String password) {
        return password.equals("1234");  
    }

    public static void saveUserToJsonFile(User user, String filePath) throws IOException {
        Gson gson = new Gson();
    
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(user, writer);
        }
    }
    // đăng nhập
    public static void DangNhap() {
        Scanner scan = new Scanner(System.in);
        boolean adminCheck = false;

        while (true) {
            System.out.print("*****Đăng nhập*****\n");
            System.out.print("Nhập tên tài khoản: ");
            String username = scan.nextLine();

            System.out.print("Nhập mật khẩu: ");
            String password = scan.nextLine();

            if (username.equals("admin")) {
                if (username.equals("admin")) {
                    if (checkAdminPassword(password)) {
                        System.out.println("********Đăng nhập thành công với tài khoản admin********");
                        Menu.menuAdmin();
                    } else {
                        System.out.println("  => Sai mật khẩu của tài khoản admin.");
                    }
                
                }
                break;
            }
            else{

                String filePath = "data/User/" + username + ".json";

                if (new File(filePath).exists()) {
                    JSONParser parser = new JSONParser();

                    try {
                        Object obj = parser.parse(new FileReader(filePath));
                        JSONObject user = (JSONObject) obj;

                        if (user.get("username").equals(username)) {
                            String hashedPassword = (String) user.get("password");
                            String hashedInputPassword = hashPassword(password);

                            if (hashedPassword.equals(hashedInputPassword)) {
                                System.out.print("********Đăng nhập thành công********");
                                adminCheck = true;
                                Menu.menu();
                                break; // Thoát khỏi vòng lặp nếu đăng nhập thành công
                            } else {
                                System.out.println("  => Mật khẩu không chính xác.");
                            }
                            
                        } else {
                            System.out.println("  => Người dùng không tồn tại.");
                        }
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.print("  => Người dùng không tồn tại");
                }
            }
        }
    }



}

