package jp.te4a.spring.boot.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import jp.te4a.spring.boot.bean.UserBean;
import lombok.Data;

@Data
public class LoginUserDetails extends User {

	private final UserBean user;

    public LoginUserDetails(UserBean userBean) {
        super(userBean.getUsername(), userBean.getPassword(),
        		AuthorityUtils.createAuthorityList("ROLE_USER"));
        this.user = userBean;
    }
}
