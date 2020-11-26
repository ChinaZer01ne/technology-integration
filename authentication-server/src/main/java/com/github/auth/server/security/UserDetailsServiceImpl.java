package com.github.auth.server.security;

import com.github.internal.api.user.PermissionService;
import com.github.internal.api.user.UserService;
import com.github.internal.api.user.dto.PermissionDTO;
import com.github.internal.api.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义用户认证与授权
 *
 * @author peach
 * @since 2020/11/26 16:37
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userService.get(username);
        if (user == null) {
            return User.builder().build();
        }

        // 获取用户授权
        List<PermissionDTO> permissions = permissionService.get(user.getId());
        // 权限集合
        List<GrantedAuthority> grantedAuthorities = permissions.stream().map(permission -> {
            if (permission != null && permission.getEnName() != null) {
                return new SimpleGrantedAuthority(permission.getEnName());
            }
            return null;
        }).collect(Collectors.toList());

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(grantedAuthorities)
                .build();
    }
}