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

public class DepartmentsDao {
    public List<Departments> getAll() {

        List<Departments> DepartmentsList = new ArrayList<>();

        try {
            Connection conn = MyConnection.getConnection();
            final String sql = "SELECT * FROM `departments`";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Departments d = new Departments();
                d.setDepartment_id(rs.getInt("departments_id"));
                d.setDepartment_name(rs.getString("departments_name"));
                d.setLocation(rs.getString("location"));
                DepartmentsList.add(d);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return DepartmentsList;
    }

//    /**
//     * Lấy phòng ban theo ID
//     *
//     * @param id
//     * @return nếu không tìm thấy trả về null
//     */
    public Departments getById(int departments_id) {
    try {
        Connection conn = MyConnection.getConnection();
        final String sql =  String.format("SELECT * FROM departments WHERE departments_id = '%s'", departments_id);

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery(sql);
        Departments d = null;
        if (rs.next()) {
            d = new Departments();
            d.setDepartment_id(rs.getInt("departments_id"));
            d.setDepartment_name(rs.getString("departments_name"));
            d.setLocation(rs.getString("location"));
        }
        rs.close();
        stmt.close();
        conn.close();
        return d;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
    }
    public List<Departments> filterDepartmentsByFullNameOrId(String keyword) {
        Connection connection = null;
        try {

            List<Departments> d = new ArrayList<>();
            connection = MyConnection.getConnection();
            String sql = "SELECT * FROM departments WHERE departments_name LIKE ? OR departments_id LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Departments departments = new Departments();
                departments.setDepartment_id(rs.getInt("departments_id"));
                departments.setDepartment_name(rs.getString("departments_name"));
                departments.setLocation(rs.getString("location"));
                d.add(departments);
            }
            return d;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void insert(Departments d) {
        String sql = String.format("INSERT INTO `departments` (`departments_name`,`location`) VALUES ('%s','%s')",
                d.getDepartment_name(),
                d.getLocation()
        );
        System.out.println(d.getDepartment_name());

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

    public void update(Departments d, int departments_id) {
        Departments tmp = getById(departments_id);
        if (tmp == null) {
            System.out.println("Không tồn tại nhân viên có id = " + departments_id);
            return;
        }
        final String sql = String.format("UPDATE `departments` SET `departments_name`= '%s' ,`location`='%s' WHERE `departments_id`='%s' ",
                d.getDepartment_name(),
                d.getLocation(),
                departments_id
        );

        System.out.println(sql);
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void delete(int departments_id) {
        Departments d = getById(departments_id);
        if (d == null) {
            throw new RuntimeException("Phòng ban không tồn tại!");
        }

        final String sql = String.format("DELETE FROM `departments` WHERE `departments_id` = '%s'", departments_id);
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Xoá thất bại");
            }
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


