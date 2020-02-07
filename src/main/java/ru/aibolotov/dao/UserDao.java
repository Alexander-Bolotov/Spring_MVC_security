package ru.aibolotov.dao;

import ru.aibolotov.model.User;

import java.util.List;

public interface UserDao {

    List<User> getListUsers();

    void addUser(User user);

    User getUserById(Long id);

    void deleteUserById(Long id);

    User getUser(String name);

}
