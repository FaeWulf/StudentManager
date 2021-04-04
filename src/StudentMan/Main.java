package StudentMan;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/*
    MSSV: 19120268
    Tên Ngô Đặng Gia Lâm
    % hoàn thành: 99%
    From 4/2/2021 to 4/3/2021.
 */


public class Main {

    protected static String savePath = "students.dat";
    protected static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        Map<String, Student> list = readFile();

        boolean loop_app = true;

        while (loop_app) {
            System.out.print(
                    """


                            \t\t\t -PHẦN MỀM QUẢN LÝ HỌC SINH-

                             [1] Thêm thông tin một học sinh.
                             [2] Cập nhật thông tin một học sinh.
                             [3] Xoá thông tin một học sinh.
                             [4] Xem danh sách các học sinh.
                             [5] Xuất dữ liệu ra file "export.csv".
                             [6] Nhập dữ liệu từ file "import.csv".
                             [7] Thoát chương trình.

                             Nhập lệnh:\s""");

            int choose = betterInput.getInt();

            switch (choose) {
                case 1 -> addStudent(list);
                case 2 -> updateStudent(list);
                case 3 -> deleteStudent(list);
                case 4 -> viewStudent(list);
                case 5 -> exportCSV(list);
                case 6 -> importCSV(list);
                case 7 -> loop_app = false;
                default -> System.out.print("\n Lệnh không hợp lệ!");
            }
        }

    }

    public static void saveData(Map<String, Student> list) throws IOException {
        FileOutputStream os = new FileOutputStream(savePath);
        ObjectOutputStream Dos = new ObjectOutputStream(os);
        try {
            Dos.writeObject(list);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    public static Map<String, Student> readFile() throws IOException {
        FileInputStream is = new FileInputStream(savePath);
        ObjectInputStream Dis = new ObjectInputStream(is);
        Map<String, Student> list = null;
        try {
            list = (Map<String, Student>) Dis.readObject();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                Dis.close();
            } catch (IOException e) {
                System.err.println(e);
            }

        }
       //System.out.println(list.get("19120268").TenHS);
        return list;
    }

    public static void addStudent(Map<String, Student> list) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Student temp = new Student();

        System.out.print("\n Nhập mã học sinh: ");
        String MHS = scanner.nextLine();

        if(list.containsKey(MHS)){
            System.out.print("MHS " + MHS + " đã tồn tại trong dữ liệu, bạn có muốn tiếp tục ghi đè lên hay không? (y/n): ");

            boolean loop = true;

            while (loop) {
                String choice = scanner.nextLine();

                switch (choice.toLowerCase()) {
                    case "y", "yes" -> loop = false;
                    case "n", "no" -> {
                        System.out.print("\n Đã huỷ yêu cầu!");
                        return;
                    }
                    default -> System.out.print("\n Lệnh không hợp lệ, nhập lại: ");
                }
            }
        }

        System.out.print(" Nhập tên học sinh: ");
        temp.TenHS = scanner.nextLine();

        System.out.print(" Nhập điểm của học sinh: ");
        temp.Diem = betterInput.getFloat();

        System.out.print(" Nhập đường dẫn đến ảnh của học sinh: ");
        temp.HinhAnh = scanner.nextLine();

        System.out.print(" Nhập địa chỉ: ");
        temp.DiaChi = scanner.nextLine();

        System.out.print(" Nhập ghi chú: ");
        temp.GhiChu = scanner.nextLine();

        list.put(MHS, temp);

        System.out.println(" Đã nhập dữ liệu của học sinh có mã sinh viên " + MHS + " thành công!");

        saveData(list);
    }

    public static void updateStudent(Map<String, Student> list) throws IOException {
        String searchString;
        System.out.print("\n Nhập MHS cần chỉnh sửa thông tin: ");
        searchString = scanner.nextLine();

        if(!list.containsKey(searchString)) {
            System.out.println("\n Không tồn tại học sinh có MHS là " + searchString);
            return;
        }

        boolean loop_main = true;

        while (loop_main) {
            Student temp = list.get(searchString);

            System.out.println("\n Thông tin học sinh có MHS " + searchString + ": " +
                    "\n Tên: " + temp.TenHS +
                    "\n Điểm: " + temp.Diem +
                    "\n Đường dẫn ảnh: " + temp.HinhAnh +
                    "\n Địa chỉ: " + temp.DiaChi +
                    "\n Ghi chú: " + temp.GhiChu);

            System.out.print(
                    """

                            \t Danh sách lệnh
                             [1] Sửa tên học sinh.
                             [2] Sửa đường dẫn hình ảnh học sinh.
                             [3] Sửa điểm học sinh.
                             [4] Sửa địa chỉ.
                             [5] Sửa ghi chú.
                             [6] Thoát.

                             Nhập lệnh:\s""");

            int choose = betterInput.getInt();

            switch (choose) {
                case 1: {
                    System.out.print("\n Nhập tên: ");
                    temp.TenHS = scanner.nextLine();
                    System.out.println(" Đã sửa thành công tên thành " + temp.TenHS);

                }
                break;
                case 2: {
                    System.out.print("\n Nhập đường ảnh: ");
                    temp.HinhAnh = scanner.nextLine();
                    System.out.println(" Đã sửa thành công đường ảnh thành " + temp.HinhAnh);
                }
                break;
                case 3: {
                    System.out.print("\n Nhập điểm: ");
                    temp.Diem = betterInput.getFloat();
                    System.out.println(" Đã sửa thành công điềm thành " + temp.Diem);
                }
                break;
                case 4: {
                    System.out.print("\n Nhập địa chỉ: ");
                    temp.DiaChi = scanner.nextLine();
                    System.out.println(" Đã sửa thành công địa chỉ thành " + temp.DiaChi);
                }
                break;
                case 5: {
                    System.out.print("\n Nhập ghi chú: ");
                    temp.GhiChu = scanner.nextLine();
                    System.out.println(" Đã sửa thành công ghi chú thành " + temp.GhiChu);
                }
                break;
                case 6: {
                    loop_main = false;
                }
                default: {
                    System.out.print("\n Lệnh không hợp lệ.");
                    scanner.nextLine();
                }
                break;
            }
            list.replace(searchString,temp);
            saveData(list);
        }
    }

    public static void deleteStudent(Map<String, Student> list) throws IOException {
        String searchString;
        System.out.print("\n Nhập MHS cần xoá: ");
        searchString = scanner.nextLine();

        if(!list.containsKey(searchString)) {
            System.out.println("\n Không tồn tại học sinh có MHS là " + searchString);
            return;
        }

        Student temp = list.get(searchString);

        System.out.println("\n Thông tin học sinh có MHS " + searchString + ": " +
                "\n Tên: " + temp.TenHS +
                "\n Điểm: " + temp.Diem +
                "\n Đường dẫn ảnh: " + temp.HinhAnh +
                "\n Địa chỉ: " + temp.DiaChi +
                "\n Ghi chú: " + temp.GhiChu);

        System.out.print(" Bạn có chắc chắn muốn xoá thông tin của học sinh trên không? (y/n): ");

        boolean loop = true;

        while (loop) {
            String choice = scanner.nextLine();

            switch (choice.toLowerCase()) {
                case "y", "yes" -> {
                    list.remove(searchString, temp);
                    System.out.print("\n Đã xoá thành công!");
                    loop = false;
                }
                case "n", "no" -> {
                    System.out.print("\n Đã huỷ yêu cầu!");
                    loop = false;
                }
                default -> System.out.print("\n Lệnh không hợp lệ, nhập lại: ");
            }
        }

        saveData(list);
    }

    public static void viewStudent(Map<String, Student> list){

        boolean loop_main = true;
        while (loop_main) {
            System.out.print(
                    """

                            \t Danh sách lệnh
                             [1] Xem theo MHS tăng dần.
                             [2] Xem theo MHS giảm dần.
                             [3] Xem theo Điểm tăng dần.
                             [4] Xem theo Điểm giảm dần.
                             [5] Thoát.

                             Nhập lệnh:\s""");

            int choose = betterInput.getInt();

            switch (choose) {
                case 1 -> {
                    System.out.print("\n\t\t DANH SÁCH HỌC SINH THEO MHS TĂNG DẦN \n");

                    SortedSet<String> keys = new TreeSet<>(list.keySet());
                    int index = 1;
                    for (String key : keys) {
                        Student temp = list.get(key);
                        System.out.print("\n [" + index++ + "]" + "\t MHS: " + key + "," +
                                "\t\t Tên: " + temp.TenHS + "," +
                                "\t\t Điểm: " + temp.Diem + "," +
                                "\t\t Đường dẫn ảnh: " + temp.HinhAnh + "," +
                                "\t\t Địa chỉ: " + temp.DiaChi + "," +
                                "\t\t Ghi chú: " + temp.GhiChu + ".");
                    }
                    loop_main = false;
                }
                case 2 -> {
                    System.out.print("\n\t\t DANH SÁCH HỌC SINH THEO MHS GIẢM DẦN \n");

                    SortedSet<String> keys = new TreeSet<>(list.keySet());

                    Object[] arrays = keys.toArray();
                    int index = 1;
                    for (int i = keys.size() - 1; i >= 0; i--) {
                        Student temp = list.get(arrays[i]);
                        System.out.print("\n [" + index++ + "]" + "\t MHS: " + arrays[i] + "," +
                                "\t\t Tên: " + temp.TenHS + "," +
                                "\t\t Điểm: " + temp.Diem + "," +
                                "\t\t Đường dẫn ảnh: " + temp.HinhAnh + "," +
                                "\t\t Địa chỉ: " + temp.DiaChi + "," +
                                "\t\t Ghi chú: " + temp.GhiChu + ".");
                    }

                    loop_main = false;
                }
                case 3 -> {
                    Set<Map.Entry<String, Student>> entries = list.entrySet();

                    Comparator<Map.Entry<String, Student>> valueComparator = (e1, e2) -> {
                        Student v1 = e1.getValue();
                        Student v2 = e2.getValue();

                        return Float.compare(v1.Diem, v2.Diem);
                    };

                    List<Map.Entry<String, Student>> listOfEntries = new ArrayList<>(entries);

                    listOfEntries.sort(valueComparator);

                    System.out.print("\n\t\t DANH SÁCH HỌC SINH THEO ĐIỂM TĂNG DẦN \n");
                    int index = 1;
                    for (Map.Entry<String, Student> listOfEntry : listOfEntries) {

                        Student temp = listOfEntry.getValue();

                        System.out.print("\n [" + index++ + "]" + "\t MHS: " + listOfEntry.getKey() + "," +
                                "\t\t Tên: " + temp.TenHS + "," +
                                "\t\t Điểm: " + temp.Diem + "," +
                                "\t\t Đường dẫn ảnh: " + temp.HinhAnh + "," +
                                "\t\t Địa chỉ: " + temp.DiaChi + "," +
                                "\t\t Ghi chú: " + temp.GhiChu + ".");
                    }

                    loop_main = false;
                }
                case 4 -> {
                    Set<Map.Entry<String, Student>> entries = list.entrySet();

                    Comparator<Map.Entry<String, Student>> valueComparator = (e1, e2) -> {
                        Student v1 = e1.getValue();
                        Student v2 = e2.getValue();

                        return Float.compare(v1.Diem, v2.Diem);
                    };

                    List<Map.Entry<String, Student>> listOfEntries = new ArrayList<>(entries);

                    listOfEntries.sort(valueComparator);

                    System.out.print("\n\t\t DANH SÁCH HỌC SINH THEO ĐIỂM GIẢM DẦN \n");
                    int index = 1;
                    for (int i = listOfEntries.size() - 1; i >= 0; i--) {

                        Student temp = listOfEntries.get(i).getValue();
                        System.out.print("\n [" + index++ + "]" + "\t MHS: " + listOfEntries.get(i).getKey() + "," +
                                "\t\t Tên: " + temp.TenHS + "," +
                                "\t\t Điểm: " + temp.Diem + "," +
                                "\t\t Đường dẫn ảnh: " + temp.HinhAnh + "," +
                                "\t\t Địa chỉ: " + temp.DiaChi + "," +
                                "\t\t Ghi chú: " + temp.GhiChu + ".");

                    }

                    loop_main = false;
                }
                case 5 -> loop_main = false;
                default -> System.out.print(" Lệnh không hợp lệ, nhập lại! ");
            }

            System.out.print("\n\n Nhấn Enter để tiếp tuc...");
            scanner.nextLine();

        }
    }

    public static void exportCSV(Map<String, Student> list) throws IOException {
        FileOutputStream os = new FileOutputStream("export.csv");

        try {
            int index = 0;
            for (String key: list.keySet()) {
                Student temp = list.get(key);
                index++;
                String put = key + "," +
                        temp.TenHS + "," +
                        temp.Diem + "," +
                        temp.HinhAnh + "," +
                        temp.DiaChi + "," +
                        temp.GhiChu;
                if (index < list.size())
                    put += "\n";
                os.write(put.getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }

        System.out.print("\n\n Đã xuất thành công dữ liệu ra file export.csv!\n Nhấn Enter để tiếp tục...");
        scanner.nextLine();
    }

    public static void importCSV(Map<String, Student> list) throws IOException {

        System.out.print("""

                Chọn cách xử lí:\s
                [1] Ghi đè lên và xoá hết dữ liệu cũ.
                [2] Gộp chung dữ liệu từ import.csv và dữ liệu hiện có. (Nếu MHS trùng, ghi đè từ import.csv)
                [3] Gộp chung dữ liệu từ import.csv và dữ liệu hiện có. (Nếu MHS trùng thì bỏ qua)
                [4] Thoát.""".indent(1));

        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("import.csv"));
        }
        catch (Exception e){
            System.out.print("\n\n Đã xảy ra lỗi trong quá trình đọc file import.csv, vui lòng kiểm tra lại.");
            return;
        }

        boolean loop_main = true;

        while (loop_main){
            System.out.print("\n Chọn: ");
            int choice = betterInput.getInt();
            switch (choice) {
                case 1 -> {

                    list.clear();

                    String line = reader.readLine();
                    while (line != null) {

                        String[] temp = line.split(",");
                        Student sert = new Student();

                        sert.TenHS = temp[1];
                        sert.Diem = Float.parseFloat(temp[2]);
                        sert.HinhAnh = temp[3];
                        sert.DiaChi = temp[4];
                        sert.GhiChu = temp[5];

                        list.put(temp[0], sert);
                        // read next line
                        line = reader.readLine();
                    }

                    System.out.print("\n Đã nhập thành công dữ liệu từ file import.csv!");
                    loop_main = false;
                }
                case 2 -> {
                    String line = reader.readLine();
                    while (line != null) {

                        String[] temp = line.split(",");
                        Student sert = new Student();

                        sert.TenHS = temp[1];
                        sert.Diem = Float.parseFloat(temp[2]);
                        sert.HinhAnh = temp[3];
                        sert.DiaChi = temp[4];
                        sert.GhiChu = temp[5];

                        list.put(temp[0], sert);
                        // read next line
                        line = reader.readLine();
                    }
                    System.out.print("\n Đã nhập thành công dữ liệu từ file import.csv!");
                    loop_main = false;
                }
                case 3 -> {
                    String line = reader.readLine();
                    while (line != null) {

                        String[] temp = line.split(",");
                        Student sert = new Student();

                        sert.TenHS = temp[1];
                        sert.Diem = Float.parseFloat(temp[2]);
                        sert.HinhAnh = temp[3];
                        sert.DiaChi = temp[4];
                        sert.GhiChu = temp[5];

                        if (!list.containsKey(temp[1]))
                            list.put(temp[0], sert);
                        // read next line
                        line = reader.readLine();
                    }
                    System.out.print("\n Đã nhập thành công dữ liệu từ file import.csv!");
                    loop_main = false;
                }
                case 4 -> loop_main = false;
                default -> System.out.print("\n Lệnh không hợp lệ! ");
            }
        }

        reader.close();
        saveData(list);
    }
}
