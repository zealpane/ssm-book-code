package ref.proxy.jdk;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeviceServiceImpl implements DeviceService {

	@Override
	public void online() {
		log.info("设备在线了");
	}

	@Override
	public void offline() {
		log.info("设备离线了");
	}

}
