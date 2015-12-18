package com.dao.memory;

import java.util.List;

import com.dao.UserDAO;
import com.dao.storage.UserStorage;
import com.entity.User;

public class MemoryUserDAO implements UserDAO {

    private UserStorage storage;

    /**
     * Constructor of MemoryUserDAO class
     *
     * @param storage-UserStorage type
     */
    public MemoryUserDAO(UserStorage storage) {
        this.storage = storage;
    }

    /**
     * Create method - adds User to the memory storage (Map<Integer,String>)
     *
     * @param user - User instance
     * @return User type
     */
    @Override
    public User create(User user) {
        return storage.add(user);
    }

    /**
     * Get method - get User from the memory storage (Map<Integer,String>) by their id
     *
     * @param id - id of User instance with int type
     * @return User type
     */
    @Override
    public User get(int id) {

        return storage.getById(id);
    }

    /**
     * Update method - update User at the memory storage (Map<Integer,String>)
     *
     * @param entity - User instance
     * @return User type
     */
    @Override
    public User update(User entity) {

        return null;
    }

    /**
     * Remove method - remove User from the memory storage (Map<Integer,String>) by their id
     *
     * @param id - id of User instance with int type
     */
    @Override
    public void remove(int id) {
        storage.removeById(id);
    }

    /**
     * GetAll method - get all User from the memory storage (Map<Integer,String>)
     *
     * @return List<T> -list with Users
     */
    @Override
    public List<User> getAll() {
        return storage.all();
    }


    /**
     * This method  get a User by userSurName from the storage (Map<Integer,String>).
     *
     * @param userSurname -SurName of user
     * @return User
     */
    @Override
    public User getUserByUserSurName(String userSurname) {
        return storage.getUserBySurname(userSurname);
    }

    /**
     * This method  get a User by email from the memory storage (Map<Integer,String>).
     *
     * @param email -email of user
     * @return User
     */
    @Override
    public User getUserByUserEmail(String email) {

        return null;
    }


}
