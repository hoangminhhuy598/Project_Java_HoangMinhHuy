package service;

import dao.AccountsDao;
import model.Accounts;

public class AuthenService {
    AccountsDao accDao = new AccountsDao();
    public boolean Login(String user, String password){
        if(accDao.getByacc(user,password)== null){
            System.out.println("Tên tài khoản hoặc mật khẩu sai!");
            return false;
        }
        return  true;
    }
}
