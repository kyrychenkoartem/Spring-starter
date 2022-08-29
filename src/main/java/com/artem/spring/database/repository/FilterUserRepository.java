package com.artem.spring.database.repository;

import com.artem.spring.database.entity.Role;
import com.artem.spring.database.entity.User;
import com.artem.spring.dto.PersonalInfo;
import com.artem.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter userFilter);

    List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role);

    void updateCompanyAndRole(List<User> users);

    void updateCompanyAndRoleNamed(List<User> users);
}
