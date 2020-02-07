package ru.aibolotov.service;

import ru.aibolotov.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getListRoles();

    boolean roleIsExist(String role);

    Role getRoleByName(String roleName);
}
