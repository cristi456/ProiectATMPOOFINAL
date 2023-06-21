package service;

import java.util.List;

import javax.persistence.Persistence;

import dao.UserDao;
import model.Account;

public class UserService {
    private UserDao userDao;

    public UserService() {
        try {
            userDao = new UserDao(Persistence.createEntityManagerFactory("App"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addUser(Account newUser) {
        userDao.create(newUser);
    }

    public void updateUser(Account updatedUser) {
        userDao.update(updatedUser);
    }
    
    public void deleteUser(Account deletedUser) {
    	userDao.remove(deletedUser, deletedUser.getIduser());
    }

    public List<Account> getAllUsers() {
        return userDao.findAll();
    }

    // for login
    
    public Account findUser(String acc, String pin) throws UserServiceException {
        List<Account> accs = userDao.find(acc);
        if (accs.size() == 0) {
            throw UserServiceException.userNotFoundException(acc);
        }
        Account a = accs.get(0);

        if (!pin.equals(a.getPin())) {
            throw UserServiceException.pinMismatchException();
        }
        return a;
    }
    
    public Account addUser(String name, String pin, String acc) throws UserServiceException {
        List<Account> accs = userDao.find(acc);
        if (accs.size() == 0) {
            throw UserServiceException.accountNotFoundException();
        }
        Account a = accs.get(0);

        if (!pin.equals(a.getPin())) {
            throw UserServiceException.pinMismatchException();
        }
        return a;
    }
    
    
    
}
