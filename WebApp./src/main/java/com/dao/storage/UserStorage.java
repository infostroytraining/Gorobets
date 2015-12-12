package com.dao.storage;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import com.entity.User;
import org.apache.log4j.Logger;

public class UserStorage {

//    private static final Logger LOGGER = Logger.getLogger(UserStorage.class);



    private Map<Integer, User> storage;
    private AtomicInteger id = new AtomicInteger();

    public UserStorage() {
        storage = new HashMap<>();
    }

    public User add(User user) {
        int id = generateId();
        user.setId(id);
        storage.put(id, user);
        return user;
    }

    public List<User> all() {
        return new ArrayList<>(storage.values());
    }

    private int generateId() {
        return id.incrementAndGet();
    }

    public User getById(int id) {
        User user = null;
        if(id!=0){
          user =  storage.get(id);
        }
        return user;
    }

    public boolean removeById(int id) {
        int storageCapacity = storage.size();
        if(id!=0) {
            storage.remove(id);
        }
        int newStorageCapacity = storage.size();
        if (storageCapacity == newStorageCapacity - 1) {
            return true;

        } else {
            return false;
        }

    }

    public User getUserBySurname(String surname) {
        User resultUser = null;
        Set<Map.Entry<Integer, User>> pairSet = storage.entrySet();
        for (Map.Entry<Integer, User> map : pairSet) {
            if (surname!=null && map.getValue().getSurname().equals(surname)) {
                resultUser = map.getValue();

            } else {
//                LOGGER.info("User doesn't exist for  surname " + surname);

            }

        }
        return  resultUser;
    }
}
