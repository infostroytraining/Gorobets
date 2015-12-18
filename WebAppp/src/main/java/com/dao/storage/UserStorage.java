package com.dao.storage;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import com.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 */
public class UserStorage {

    private static final Logger LOGGER = LogManager.getLogger();


    private Map<Integer, User> storage = new HashMap<>();
    private AtomicInteger id = new AtomicInteger();

    /**
     * Constructor of UserStorage , it initialize a Map for a memory storage
     */
    public UserStorage() {
        storage = new HashMap<>();
    }

    /**
     * Add method - adds User to the storage
     *
     * @param user - User instance
     * @return User type
     */
    public User add(User user) {

        int id = generateId();

        storage.put(id, user);
        LOGGER.info("User was added to memory ");
        return user;
    }

    /**
     * All method - get all User from the storage
     *
     * @return List<T> -list with Users
     */
    public List<User> all() {

        return new ArrayList<>(storage.values());
    }

    /**
     * GenerateId method - generate id for user
     *
     * @return - int id of user
     */
    private int generateId() {
        return id.incrementAndGet();
    }

    /**
     * GetById method - get User from the storage by their id
     *
     * @param id - id of User instance with int type
     * @return User type
     */
    public User getById(int id) {
        User user = null;
        if (id != 0) {
            user = storage.get(id);
        }
        return user;
    }

    /**
     * RemoveById method - remove User from the storage by their id
     *
     * @param id - id of User instance with int type
     * @return boolean type, if true- user is removed successfully, else-false.
     */
    public boolean removeById(int id) {
        int storageCapacity = storage.size();
        if (id != 0) {
            storage.remove(id);
        }
        int newStorageCapacity = storage.size();
        if (storageCapacity == newStorageCapacity - 1) {
            LOGGER.info("User was removed from memory by id " + id);

            return true;

        } else {
            LOGGER.info("User wasn't removed from memory by id " + id);
            return false;
        }

    }

    /**
     * * This method  get a User by userSurName from the storage.
     *
     * @param surname
     * @return User
     */
    public User getUserBySurname(String surname) {
        User resultUser = null;
        Set<Map.Entry<Integer, User>> pairSet = storage.entrySet();
        for (Map.Entry<Integer, User> map : pairSet) {
            if (surname != null && map.getValue().getSurname().equals(surname)) {
                resultUser = map.getValue();

            } else {
                LOGGER.info("User doesn't exist for  surname " + surname);

            }

        }
        return resultUser;
    }

}
