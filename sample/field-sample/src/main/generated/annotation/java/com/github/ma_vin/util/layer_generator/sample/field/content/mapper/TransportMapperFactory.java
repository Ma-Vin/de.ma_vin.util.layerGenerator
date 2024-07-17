package com.github.ma_vin.util.layer_generator.sample.field.content.mapper;

public class TransportMapperFactory {

	private TransportMapperFactory() {
	}

	public static CommonTransportMapper createCommonTransportMapper() {
		return new CommonTransportMapper();
	}

}
