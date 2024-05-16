package com.longfish.orca;

import com.longfish.orca.properties.JwtProperties;
import com.longfish.orca.properties.ProjectProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrcaSpringbootApplicationTests {

	@Autowired
	private ProjectProperties projectProperties;

	@Autowired
	private JwtProperties jwtProperties;

	@Test
	void testLoadsProp() {
		System.out.println(projectProperties);
		System.out.println(jwtProperties);
	}

}
