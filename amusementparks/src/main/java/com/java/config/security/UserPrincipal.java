package bap.training.config.security;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bap.training.common.entity.Roles;
import bap.training.common.entity.Users;
import bap.training.enums.UserEnum;
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

    private boolean tfaChecked;

    private List<Roles> roles;

    private UserEnum.Status status;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Integer id, String password, UserEnum.Status status, List<Roles> roles,
            Collection<? extends GrantedAuthority> authorities) {
        this.userId = id;
        this.password = password;
        this.status = status;
        this.roles = roles;
        this.authorities = authorities;
    }

    public UserPrincipal(Integer id, String password, UserEnum.Status status, List<Roles> roles) {
        this.userId = id;
        this.password = password;
        this.status = status;
        this.roles = roles;

    }

    public static UserPrincipal create(Users user, Collection<? extends GrantedAuthority> authorities)
            throws UsernameNotFoundException, Exception {
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("The user is empty");
        }

        return new UserPrincipal(user.getId(), user.getPassword(), user.getUserStatus().getStatusName(),
                user.getRoles(), authorities);
    }

    public static UserPrincipal create(Users user) throws UsernameNotFoundException, Exception {
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("The user is empty");
        }

        return new UserPrincipal(user.getId(), user.getPassword(), user.getUserStatus().getStatusName(),
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

        return UserEnum.Status.ACTIVED.equals(status);
    }
}
