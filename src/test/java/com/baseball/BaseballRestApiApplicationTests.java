package com.baseball;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

//JUnit tests to test the Spring Boot application. Currently, all tests pass
@SpringBootTest
class BaseballRestApiApplicationTests {

	@Autowired
	ApplicationContext appContext;
	
	@Test
	void contextLoads() {
		assertNotNull(appContext);
	}

}
