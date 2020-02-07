package ru.aibolotov.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ru.aibolotov.model.Role;

import java.util.ArrayList;
import java.util.Collection;

@Service("assembler")
public class Assembler {

//    private String prefixRole = "ROLE_";
    @Transactional(readOnly = true)
    User buildUserFromUserEntity(ru.aibolotov.model.User user) {
        String userName = user.getName();
        String password = user.getPassword();

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return new User(userName, password, true,true, true, true, authorities);
    }
}
