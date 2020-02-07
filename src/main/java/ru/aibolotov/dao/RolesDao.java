package ru.aibolotov.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.aibolotov.model.Role;

import java.util.List;

@Repository
@Transactional
public interface RolesDao {
    List<Role> getListRoles();

    boolean roleIsExist(String role);

    Role getRoleByName(String roleName);
}
