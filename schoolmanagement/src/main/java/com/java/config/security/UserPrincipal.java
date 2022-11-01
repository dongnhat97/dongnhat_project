package com.java.config.security;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java.common.entity.Role;
import com.java.common.entity.User;
import com.java.enums.CommonEnum;
import com.java.enums.UserEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * User details.
 */
@Getter
@NoArgsConstructor
public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String name;

    @JsonIgnore
    private String password;

    private List<Role> role;

    private CommonEnum.StatusEnum status;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Integer id, String password, CommonEnum.StatusEnum status, List<Role> role,
                         Collection<? extends GrantedAuthority> authorities) {
        this.userId = id;
        this.password = password;
        this.status = status;
        this.role = role;
        this.authorities = authorities;
    }

    public UserPrincipal(Integer id, String password, CommonEnum.StatusEnum status, List<Role> role) {
        this.userId = id;
        this.password = password;
        this.status = status;
        this.role = role;

    }

    public static com.java.config.security.UserPrincipal create(User user, Collection<? extends GrantedAuthority> authorities)
            throws UsernameNotFoundException, Exception {
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("The user is empty");
        }

        return new com.java.config.security.UserPrincipal(user.getId(), user.getPassword(), user.getStatus(),
                user.getRoles(), authorities);
    }

    public static com.java.config.security.UserPrincipal create(User user) throws UsernameNotFoundException, Exception {
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("The user is empty");
        }

        return new com.java.config.security.UserPrincipal(user.getId(), user.getPassword(), user.getStatus(),
                user.getRoles());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        return CommonEnum.StatusEnum.ACTIVE.equals(status);
    }
}
