package com.tgroup;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TblogApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void alwaysTrue() {
		TestCase.assertTrue(true);
	}
}
