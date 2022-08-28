package com.artem.spring.database.repository;

import com.artem.spring.database.entity.User;
import com.artem.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter userFilter);

}
