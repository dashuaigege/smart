package com.smart.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.smart.domain.User;

@RunWith(SpringJUnit4ClassRunner.class) // @RunWith就是一个运行器,使用了Spring的SpringJUnit4ClassRunner，以便在测试开始的时候自动创建Spring的应用上下文。
@ContextConfiguration("classpath*:smart-context.xml")
public class UserServiceTest {
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Test
	public void testHashMatchUser() {
		// System.out.println("Test...hashMatchUser!");
		boolean b1 = userService.hasMatchUser("admin", "123456");
		boolean b2 = userService.hasMatchUser("admin", "4321");
		assertTrue(b1);
		assertTrue(!b2);
	}

	@Test
	public void testFindUserByUserName() {
		User user = userService.findUserByUserName("admin");
		assertEquals(user.getUserName(), "admin");
	}

	@Test
	public void testAddLoginLog() {
		User user = userService.findUserByUserName("admin");
		userService.loginSuccess(user);
	}
}
