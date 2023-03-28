package dao;

import connection.MyConnection;
import model.Departments;
import model.Employees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDao {
    /**
     * Lấy ra toàn bộ nhân viên
     *
     * @return
     */
    public List<Employees> getAll() {
        List<Employees> employeesList = new ArrayList<>();
        // Bước 1: tạo kết nối
        // Bước 2: chuẩn bị câu lệnh
        // Bước 3: thực thi
        // Bước 4: đóng kết nối

        try {
            Connection conn = MyConnection.getConnection();
            final String sql = "SELECT * FROM employees";

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Employees e = new Employees();
                e.setEmployees_id(rs.getString("employees_id"));
                e.setEmployees_name(rs.getString("employees_name"));
                e.setBirth_date(rs.getString("birth_date"));
                e.setAddress(rs.getString("address"));
                e.setEmail(rs.getString("email"));
                e.setPhone_number(rs.getString("phone_number"));
                e.setHire_date(rs.getString("hire_date"));
                e.setJob_position(rs.getString("job_position"));
                e.setSalary(rs.getInt("salary"));
                e.setManager_id(rs.getString("manager_id"));
                e.setDepartments_id(rs.getInt("departments_id"));
                employeesList.add(e);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeesList;
    }

     // Thêm 1 nhân viên vào database
    public void insert(Employees e) {
        final String sql = String.format("INSERT INTO `employees` VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s',%d)",
                e.getEmployees_id(),
                e.getEmployees_name(),
                e.getBirth_date(),
                e.getAddress(),
                e.getEmail(),
                e.getPhone_number(),
                e.getHire_date(),
                e.getJob_position(),
                e.getSalary(),
                e.getManager_id(),
                e.getDepartments_id()
                );

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();

            int rs = stmt.executeUpdate(sql);
            if (rs == 0) {
                System.out.println("Thêm thất bại!");
            }
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*
     * Lấy nhân viên theo ID
     *
     * @param id
     * @return nếu không tìm thấy trả về null
     */
    public Employees getById(String employees_id ) {
        try {
            Connection conn = MyConnection.getConnection();
            final String sql = "SELECT * FROM employees WHERE employees_id = '" + employees_id + "' ";

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            Employees e = null;
            if (rs.next()) {
                e = new Employees();
                e.setEmployees_id(rs.getString("employees_id"));
                e.setEmployees_name(rs.getString("employees_name"));
                e.setBirth_date(rs.getString("birth_date"));
                e.setAddress(rs.getString("address"));
                e.setEmail(rs.getString("email"));
                e.setPhone_number(rs.getString("phone_number"));
                e.setHire_date(rs.getString("hire_date"));
                e.setJob_position(rs.getString("job_position"));
                e.setSalary(rs.getInt("salary"));
                e.setManager_id(rs.getString("manager_id"));
                e.setDepartments_id(rs.getInt("departments_id"));
            }
            rs.close();
            stmt.close();
            conn.close();
            return e;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Employees> filterEmployeesByFullNameOrId(String keyword) {
        Connection connection = null;
        try {

            List<Employees> e = new ArrayList<>();
            connection = MyConnection.getConnection();
            String sql = "SELECT * FROM employees WHERE employees_name LIKE ? OR employees_id LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Employees employees = new Employees();
                employees.setEmployees_id(rs.getString("employees_id"));
                employees.setEmployees_name(rs.getString("employees_name"));
                employees.setBirth_date(rs.getString("birth_date"));
                employees.setAddress(rs.getString("address"));
                employees.setEmail(rs.getString("email"));
                employees.setPhone_number(rs.getString("phone_number"));
                employees.setHire_date(rs.getString("hire_date"));
                employees.setJob_position(rs.getString("job_position"));
                employees.setSalary(rs.getInt("salary"));
                employees.setManager_id(rs.getString("manager_id"));
                employees.setDepartments_id(rs.getInt("departments_id"));

                e.add(employees);
            }
            return e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void update(Employees e, String employees_id) {
        Employees tmp = getById(employees_id);
        if(tmp == null){
            System.out.println("Không tồn tại nhân viên có id = " + employees_id);
            return;
        }
        final String sql = String.format("UPDATE employees SET `employees_id`='%s',`employees_name`='%s',`birth_date`='%s',`address`='%s',`email`='%s',`phone_number`='%s', `hire_date`='%s', `job_position`='%s', `salary`='%s', `manager_id`='%s', `departments_id`=%d  WHERE `employees_id`='%s' " ,
                e.getEmployees_id(), e.getEmployees_name(), e.getBirth_date(), e.getAddress(), e.getEmail(),e.getPhone_number(),e.getHire_date(),e.getJob_position(),e.getSalary(),e.getManager_id(),e.getDepartments_id(), employees_id
        );

        System.out.println(sql);
        try{
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void delete(String employees_id) {
        try {
            Connection conn = MyConnection.getConnection();
            final String sql = "DELETE FROM employees WHERE employees_id = '" + employees_id + "'";

            Statement stmt = conn.createStatement();

            long rs = stmt.executeUpdate(sql);
            if (rs == 0) {
                System.out.println("Xoá thất bại");
            }
            stmt.close();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void update_employee_department(Employees e, String employees_id) {
        Employees tmp = getById(employees_id);
        if(tmp == null){
            System.out.println("Không tồn tại nhân viên có id = " + employees_id);
            return;
        }
        final String sql = String.format("UPDATE employees SET `departments_id`=%s WHERE `employees_id` LIKE '%s' " ,
                e.getDepartments_id(), employees_id
        );

        System.out.println(sql);
        try{
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void delete_employee_from_department(Employees e, String employees_id) {
        Employees tmp = getById(employees_id);
        if(tmp == null){
            System.out.println("Không tồn tại nhân viên có id = " + employees_id);
            return;
        }
        final String sql = String.format("UPDATE employees SET `departments_id`=NULL WHERE `employees_id` LIKE '%s' " , employees_id);

        try{
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Xóa thất bại");
            }

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
//    tính thuế
    public void tinhthuethunhap(String employees_id)
    {
        //check ma nhan vien
        Employees employees = getById(employees_id);
        if (employees == null) {
            System.out.println("Lỗi");
            throw new RuntimeException("id không tồn tại!");
        }
        final String sql = "SELECT * FROM employees WHERE employees_id=?";
        try{
            Connection conn= MyConnection.getConnection();
            PreparedStatement st = (PreparedStatement) conn
                    .prepareStatement(sql);
            st.setString(1,employees_id);
            ResultSet rs = st.executeQuery();
            long luong = 0;
            double baoHiemXH = 0;
            long tong = 0;
            long thue = 0;
            while (rs.next())
            {
                luong=rs.getInt("salary");
            }
            if(luong <11000000)
            {
                System.out.println("Nhân viên này không cần phải đóng thuế thu nhập cá nhân");
            }
            else{
                //Bảo hiểm xã hội, bảo hiểm y tế, bảo hiểm thất nghiệp: 10,5%
                baoHiemXH=luong*0.105;
                //Tổng các khoản được giảm trừ
                tong=11000000+(long) baoHiemXH;
                System.out.println("Tổng các khoản được giản trừ: "+tong);
                //Tính thu nhập tính thuế
                thue=luong-tong;
                System.out.println("Thu nhập tính thuế: "+thue);
                if(thue<5000000)
                {
                    System.out.printf("Tiền thuế thu nhập của nhân viên %s cần phải đóng là: %d ",employees_id,thue*5/100);
                    System.out.println();

                }else if(thue>5000000 && thue<10000000)
                {
                    System.out.printf("Tiền thuế thu nhập của nhân viên %s cần phải đóng là: %d ",employees_id,thue*6/100);
                    System.out.println();

                }
                else{
                    System.out.printf("Tiền thuế thu nhập của nhân viên %s cần phải đóng là: %d ",employees_id,thue*15/100);
                    System.out.println();
                }

            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

