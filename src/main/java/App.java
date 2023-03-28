import dao.AccountsDao;
import dao.DepartmentsDao;
import dao.EmployeesDao;
import model.Accounts;
import model.Departments;
import model.Employees;
import service.AuthenService;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class App {
    private static DepartmentsDao departmentsDao = new DepartmentsDao();
    private static EmployeesDao employeesDao = new EmployeesDao();

//    private static boolean isLoginSuccess = false;

    // Khai bao service
    private static service.AuthenService authenService = new AuthenService();

    private static void mainMenu() {
        System.out.println("--- QUẢN LÝ THÔNG NHÂN SỰ ---\n");
        System.out.println("_*_ Quản lý phòng ban _*_");
        System.out.println("1. Danh sách phòng ban");
        System.out.println("2. Thêm một phòng ban mới");
        System.out.println("3. Xoá một phòng ban theo mã");
        System.out.println("4. Cập nhật thông tin phòng ban");
        System.out.println("5. Tìm kiếm thông tin phòng ban theo mã");
        System.out.println("__________________________________");
        System.out.println("_*_ Quản lý nhân viên _*_");
        System.out.println("6. Danh sách nhân viên");
        System.out.println("7. Thêm một nhân viên mới");
        System.out.println("8. Xoá một nhân viên theo mã");
        System.out.println("9. Cập nhật thông tin nhân viên");
        System.out.println("10. Tìm một nhân viên theo họ tên hoặc mã");
        System.out.println("11. Thêm nhân viên vào phòng ban");
        System.out.println("12. Xóa nhân viên khỏi phòng ban");
        System.out.println("13. Tính thuế thu nhập cá nhân cho nhân viên");
        System.out.println("0. Thoát !!!");
    }

    //quản lý phòng ban
    //hien danh sach phong ban
    private static void option1() {
        List<Departments> departmentsList = departmentsDao.getAll();
        System.out.printf("%-20s %-20s %-20s %-20s", "STT","Mã phòng ban","Tên phòng ban", "Địa chỉ");
        System.out.println();
        for (int i = 0; i < departmentsList.size(); i++) {
            Departments d = departmentsList.get(i);
            System.out.printf("%-20s %-20s %-20s %-20s\n", (i+1), d.getDepartment_id(), d.getDepartment_name(), d.getLocation());
        };
    }
    //them phong ban
    private static void option2(Scanner in){
        Departments d = new Departments();
        System.out.print("Nhập Tên Phòng ban : ");
        d.setDepartment_name(in.nextLine());
        System.out.print("Nhập Location : ");
        d.setLocation(in.nextLine());
        departmentsDao.insert(d);
    }

    //xoa phong ban
    private static void option3(Scanner in) {
        Departments d = new Departments();
        System.out.print("Nhập id phòng ban muốn xóa: ");
        int departments_id = Integer.parseInt(in.nextLine());
        departmentsDao.delete(departments_id);
    }
    //cap nhat phong ban
    private static void option4(Scanner in){
        Departments d = new Departments();
        System.out.print("Nhập mã phòng ban cần cập nhật: ");
        int departments_id = Integer.parseInt(in.nextLine());
        System.out.print("Nhập Tên Phòng Ban Mới : ");
        d.setDepartment_name(in.nextLine());
        System.out.print("Nhập Location Mới : ");
        d.setLocation(in.nextLine());
        departmentsDao.update(d, departments_id);
    }

    public static void option5(Scanner in) {
        System.out.println("Nhập tên hoặc mã phòng ban cần tìm:");
        String departments_name = in.nextLine();
        System.out.println(departmentsDao.filterDepartmentsByFullNameOrId(departments_name));
    }

    //Quản lý nhân viên
    //hien thi danh sach nhan vien
    private static void option6() {
        List<Employees> employeesList = employeesDao.getAll();
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s", "STT","Mã Nhân Viên","Tên Nhân Viên", "Ngày Sinh", "Địa chỉ","Email","Số điện thoại","Ngày Bắt Đầu Làm", "Vị trí công việc", "Lương", "Mã Phòng Ban" );
        System.out.println();
        for (int i = 0; i < employeesList.size(); i++) {
            Employees e = employeesList.get(i);
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s\n", (i+1),e.getEmployees_id(), e.getEmployees_name(), e.getBirth_date(), e.getAddress(), e.getEmail(),e.getPhone_number(),e.getHire_date(),e.getJob_position(),e.getSalary(),e.getDepartments_id());
        };
    }

    //them nhan vien moi
    private static void option7(Scanner in){
        Employees e = new Employees();
        System.out.print("\tNhập ID Nhân Viên : ");
        e.setEmployees_id(in.nextLine());
        System.out.print("\tNhập Tên Nhân Viên : ");
        e.setEmployees_name(in.nextLine());
        System.out.print("\tNgày Sinh : ");
        e.setBirth_date(in.nextLine());
        System.out.print("\tNhập Địa Chỉ : ");
        e.setAddress(in.nextLine());
        System.out.print("\tNhập Email : ");
        e.setEmail(in.nextLine());
        System.out.print("\tNhập Số Điện Thoại : ");
        e.setPhone_number(in.nextLine());
        System.out.print("\tNhập ngày bắt đầu làm việc : ");
        e.setHire_date(in.nextLine());
        System.out.print("\tVị trí công việc : ");
        e.setJob_position(in.nextLine());
        System.out.print("\tNhập Lương : ");
        e.setSalary(Integer.parseInt(in.nextLine()));
        System.out.print("\tNhập id quản lý: ");
        e.setManager_id(in.nextLine());
        System.out.print("\tNhập id phòng ban: ");
        e.setDepartments_id(Integer.parseInt(in.nextLine()));

        employeesDao.insert(e);
    }

    //xoa nhan vien
    private static void option8(Scanner in) {
        Employees e = new Employees();
        System.out.print("Nhập id nhân viên muốn xóa: ");
        String id = in.nextLine();
        employeesDao.delete(id);
    }
    //cap nhat nhan vien
    private static void option9(Scanner in){
        Employees e = new Employees();
        System.out.print("Nhập mã nhân viên cần cập nhật: ");
        String employees_id = in.nextLine();
        System.out.print("\tNhập ID Nhân Viên : ");
        e.setEmployees_id(in.nextLine());
        System.out.print("\tNhập Tên Nhân Viên : ");
        e.setEmployees_name(in.nextLine());
        System.out.print("\tNgày Sinh : ");
        e.setBirth_date(in.nextLine());
        System.out.print("\tNhập Địa Chỉ : ");
        e.setAddress(in.nextLine());
        System.out.print("\tNhập Email : ");
        e.setEmail(in.nextLine());
        System.out.print("\tNhập Số Điện Thoại : ");
        e.setPhone_number(in.nextLine());
        System.out.print("\tNhập ngày bắt đầu làm việc : ");
        e.setHire_date(in.nextLine());
        System.out.print("\tVị trí công việc : ");
        e.setJob_position(in.nextLine());
        System.out.print("\tNhập Lương : ");
        e.setSalary(Integer.parseInt(in.nextLine()));
        System.out.print("\tNhập id quản lý: ");
        e.setManager_id(in.nextLine());
        System.out.print("\tNhập id phòng ban: ");
        e.setDepartments_id(Integer.parseInt(in.nextLine()));

        employeesDao.update(e, employees_id);
    }
    //tim theo ten hoac theo ma nv
    public static void option10(Scanner in) {
        System.out.println("Nhập tên hoặc mã nhân viên cần tìm:");
        String employees_name = in.nextLine();
        System.out.println(employeesDao.filterEmployeesByFullNameOrId(employees_name));
    }

    private static void option11(Scanner in){
        Employees e =new Employees();
        System.out.print("\tNhập mã nhân viên: ");
        String employees_id=in.nextLine();
        while(employeesDao.getById(employees_id)==null) {
            System.out.print("\tMã nhân viên chưa tồn tại, vui lòng nhập lại mã NV: ");
            employees_id =in.nextLine();
        }
        e.setEmployees_id(employees_id);

        System.out.println("\tChọn phòng ban: ");
        List<Departments> departmentsList = departmentsDao.getAll();
        System.out.printf("\t\t%-20s %-20s %-20s \n","Mã phòng ban","Tên phòng ban","Location");
        for (int i = 0; i < departmentsList.size(); i++) {
            System.out.printf("\t\t%-20d %-20s %-20s \n", departmentsList.get(i).getDepartment_id(), departmentsList.get(i).getDepartment_name(),departmentsList.get(i).getLocation());
        }

        System.out.print("Nhập mã phòng ban: ");
        int departments_id=Integer.parseInt(in.nextLine());
        while (departmentsDao.getById(departments_id)==null){
            System.out.println("Mã phòng ban không hợp lệ, vui lòng nhập lại mã phòng ban: ");
            departments_id=Integer.parseInt(in.nextLine());
        }
        e.setDepartments_id(departments_id);

        employeesDao.update_employee_department(e,employees_id);
    }
    private static void option12(Scanner in){
        Employees e=new Employees();
        System.out.print("\tNhập mã nhân viên muốn xóa khỏi phòng ban: ");
        String employees_id=in.nextLine();
        while(employeesDao.getById(employees_id)==null) {
            System.out.print("\tMã nhân viên chưa tồn tại, vui lòng nhập lại mã NV: ");
            employees_id =in.nextLine();
        }
        e.setEmployees_id(employees_id);

        employeesDao.delete_employee_from_department(e,employees_id);
    }

    //tính thuế
    private static void option13(Scanner in)
    {
        Employees e = new Employees();
        System.out.print("\tNhập mã nhân viên: ");
        String employees_id=in.nextLine();
        while(employeesDao.getById(employees_id)==null) {
            System.out.print("\tMã nhân viên chưa tồn tại, vui lòng nhập lại mã NV: ");
            employees_id =in.nextLine();
        }
        e.setEmployees_id(employees_id);
        String leftAlignFormat = " %-12s  %-25s  %-2s %n";
        System.out.format(" Mã nhân viên  Tên nhân viên              Lương%n");
        Employees employees = employeesDao.getById(employees_id);
        System.out.format(leftAlignFormat, employees.getEmployees_id(),
                employees.getEmployees_name(),employeesDao.getById(employees_id).getSalary());

        DecimalFormat formatter = new DecimalFormat("###,###,###");

        System.out.print("\tNhập số người phụ thuộc: ");
        int soNguoiPT = in.nextInt();

        //Mức đóng: BHXH (8%), BHYT (1.5%), BHTN (1%)
        //Bảo hiểm bắt buộc = luongBh x 8% + luongBh x 1.5% + luongBh x 1%
        double BHBB = (employees.getSalary() * 0.08) + (employees.getSalary() * 0.015) + (employees.getSalary() * 0.01);
        System.out.println("\t-Bảo hiểm bắt buộc = "+formatter.format(BHBB)+" VNĐ");

        // giảm trừ bản thân : 11000000
        int GTBT = 11000000;
        System.out.println("\t-Giảm trừ bản thân = "+formatter.format(GTBT)+" VNĐ");

        //Giảm trừ người phụ thuộc = soNguoiPT x 4,400,000 = mặc định
        int GTNPT = soNguoiPT * 4400000;
        System.out.println("\t-Giảm trừ người phụ thuộc = "+formatter.format(GTNPT)+" VNĐ");

        //Thu nhập tính thuế = lương - BHBB - GTBT - GTNPT;
        double luong = employeesDao.getById(employees_id).getSalary();
        double TNTT = luong - BHBB - GTBT - GTNPT;
        System.out.println("\t-Thu nhập tính thuế = "+formatter.format(TNTT)+" VNĐ");

        // Thuế thu nhập cá nhân phải nộp
        double TTNCN;
        if (luong <= 5000000 ){
            TTNCN = TNTT * 0.05;
        }else if (luong <= 10000000){
            TTNCN = (TNTT * 0.1) - 0.25;
        }else if (luong <= 18000000){
            TTNCN = (TNTT * 0.15) - 0.75;
        }else if (luong <= 32000000){
            TTNCN = (TNTT * 0.2) - 1.65;
        }else if (luong <= 52000000){
            TTNCN = (TNTT * 0.25) - 3.25;
        }else if (luong <= 80000000){
            TTNCN = (TNTT * 0.3) - 5.85;
        }else {
            TTNCN = (TNTT * 0.35) - 9.85;
        }
        if (TTNCN > 0){
            System.out.println("\t-Thuế thu nhập cá nhân phải nộp = "+formatter.format(TTNCN)+" VNĐ");
        }else {
            System.out.println("\t-Thuế thu nhập cá nhân phải nộp = 0 VNĐ");
        }
    }

    public static void main(String[] args) {
        //Login
        boolean checklogin=false;
        Scanner in = new Scanner(System.in);
        int i=0;
        do {
            System.out.println("_____ĐĂNG NHẬP_____");
            System.out.print("UserName:\t");
            String user=in.nextLine();
            System.out.print("Password:\t");
            String password=in.nextLine();
            checklogin=authenService.Login(user,password);
            i++;
            if (checklogin==true){
                break;
            }
        }
        while (i<3);
        if (checklogin==false){
            System.out.println("Đăng nhập không thành công !");
        }else {
            System.out.println("Đăng nhập thành công !\n");

        //Menu Main
        int option = -1;

        do {
            mainMenu();
            System.out.print("Nhập lựa chọn: ");
            String nextLine = in.nextLine();
            if(!nextLine.equalsIgnoreCase("")){
                option = Integer.parseInt(nextLine);

            }
            // Làm thêm phần try-catch khi người dùng nhập lỗi
            if (option < 1 || option > 14) {
                System.out.println("Vui lòng nhập lại!");
                continue;
            }
            switch (option) {
                case 1:
                    option1();
                    break;
                case 2:
                    option2(in);
                    break;
                case 3:
                    option3(in);
                    break;
                case 4:
                    option4(in);
                    break;
                case 5:
                    option5(in);
                    break;
                case 6:
                    option6();
                    break;
                case 7:
                    option7(in);
                    break;
                case 8:
                    option8(in);
                    break;
                case 9:
                    option9(in);
                    break;
                case 10:
                    option10(in);
                    break;
                case 11:
                    option11(in);
                    break;
                case 12:
                    option12(in);
                    break;
                case 13:
                    option13(in);
                    break;
            }

        }
        while (option != 0);
        in.close();

    }

}
}
