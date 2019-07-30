package org.lombok.demo;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Slf4jTest {

	@Test
	public void test() {
		log.debug("这里输出调试信息");
	}
}
