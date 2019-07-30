package ref.proxy.cglib;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl {

	public void sayHi() {
		log.info("hi");
	}
	
	public void sayHello() {
		log.info("hello");
	}
}
