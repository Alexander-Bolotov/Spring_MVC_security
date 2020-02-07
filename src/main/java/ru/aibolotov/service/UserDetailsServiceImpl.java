package ru.aibolotov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aibolotov.dao.UserDao;
import ru.aibolotov.dao.UserDaoImpl;
import ru.aibolotov.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private Assembler assembler;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        User user = userDao.getUser(username);
        if (user == null)
            throw new UsernameNotFoundException("user not found");

        return assembler.buildUserFromUserEntity(user);
    }
}
