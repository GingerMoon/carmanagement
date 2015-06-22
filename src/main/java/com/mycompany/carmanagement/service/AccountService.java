package com.mycompany.carmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.mycompany.carmanagement.domain.Account;
import com.mycompany.carmanagement.respository.AccountRepository;


@Service
@Transactional(readOnly = true)
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;

	@Transactional(readOnly = false)
	public Account save(Account account) {
		return this.accountRepository.save(account);
	}

//	public Account login(String username, String password) throws AuthenticationException {
//		Account account = this.accountRepository.findByUsername(username);
//		if (account != null) {
//			BCryptPasswordEncoder encod = new BCryptPasswordEncoder();
//			String pwd = encod.encode(password);
//			String pwdDB = encod.encode(account.getPassword());
////			String pwd = DigestUtils.sha256Hex(password + "{" + username + "}");
//			if (0 != pwd.compareToIgnoreCase(pwdDB)) {
//				//throw new AuthenticationException("Wrong username/password combination.", "invalid.password");
//			}
//		} else {
////			throw new AuthenticationException("Wrong username/password combination.", "invalid.username");
//		}
//
//		return account;
//	}

	public Account getAccount(String username) {
		return this.accountRepository.findByUsername(username);
	}
}
