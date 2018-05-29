package jp.te4a.spring.boot.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.te4a.spring.boot.bean.UserBean;
import jp.te4a.spring.boot.form.UserForm;
import jp.te4a.spring.boot.repository.UserRepository;

@Service
public class UserService {

	  @Autowired
	  UserRepository userRepository;
	  
	  public UserForm create(UserForm userForm) {
//		  bookForm.setId(bookRepository.getBookId());
		  
		  // パスワードエンコード
		  userForm.setPassword(new Pbkdf2PasswordEncoder().encode(userForm.getPassword()));
		  
		  UserBean userBean = new UserBean();
		  BeanUtils.copyProperties(userForm, userBean);
//		  bookRepository.create(bookBean);
		  userRepository.save(userBean);
		  return userForm;
	  }
}
