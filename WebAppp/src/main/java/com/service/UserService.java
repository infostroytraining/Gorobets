package com.service;

import com.dao.exception.DAOException;
import com.dto.UserDTO;
import com.entity.User;
import com.service.exception.ServiceException;

import java.util.Map;

/**
 * Created by invincible_ g_d on 12/14/15.
 */
public interface UserService {

    User add(User user)throws ServiceException;
//    User addAll(User user)throws ServiceException;

    Map<String, String> getEmailForEachUser()throws ServiceException, DAOException;
}
