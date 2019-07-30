package org.springboot.first;

import org.springframework.core.convert.converter.Converter;

public class CustomConverter implements Converter<String, Object> {

	@Override
	public Object convert(String source) {
		// 如果为null或空字符串，那么返回null
		if (source == null || "".equals(source)) {
			return null;
		}
		
		return null;
	}

}
