package jp.te4a.spring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.te4a.spring.boot.bean.UserBean;

public interface UserRepository extends JpaRepository<UserBean, String>{
}
