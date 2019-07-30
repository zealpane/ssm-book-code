package ref.proxy;

import org.junit.Test;

import ref.proxy.jdk.DeviceProxy;
import ref.proxy.jdk.DeviceService;
import ref.proxy.jdk.DeviceServiceImpl;

public class JdkReflectTest {

	@Test
	public void newProxy() {
		
		DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl();
		DeviceProxy h = new DeviceProxy();
		DeviceService dService = (DeviceService) h.newProxy(deviceServiceImpl);
		dService.online();
		System.out.println();
		dService.offline();
	}
}
