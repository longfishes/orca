package com.longfish.orca;

import com.longfish.orca.pojo.entity.User;
import com.longfish.orca.properties.JwtProperties;
import com.longfish.orca.properties.ProjectProperties;
import com.longfish.orca.properties.RandomProperties;
import com.longfish.orca.service.IUserService;
import com.longfish.orca.util.CodeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
class OrcaSpringbootApplicationTests {

	@Autowired
	private ProjectProperties projectProperties;

	@Autowired
	private JwtProperties jwtProperties;

	@Autowired
	private RandomProperties randomProperties;

	@Autowired
	private CodeUtil codeUtil;

	@Autowired
	private IUserService userService;

	@Test
	void testMP() {
		userService.save(User.builder()
				.username("sb" + UUID.randomUUID().toString().substring(0, 10))
				.email("sb@qq.com")
				.password("1145")
				.createTime(LocalDateTime.now())
				.build());
		userService.save(User.builder()
				.username("sb2" + UUID.randomUUID().toString().substring(0, 10))
				.email("sb@qq.com").password("1145")
				.info("我是你爹")
				.createTime(LocalDateTime.now())
				.build());
		userService.list().forEach(System.out::println);
	}

	@Test
	void testLoadsProp() {
		System.out.println(projectProperties);
		System.out.println(jwtProperties);
		System.out.println(randomProperties);
	}

	@Test
	void testRand() {
		for (int i = 0; i < 200; i++) {
			System.out.println(codeUtil.getRandomCode());
		}
	}

}
