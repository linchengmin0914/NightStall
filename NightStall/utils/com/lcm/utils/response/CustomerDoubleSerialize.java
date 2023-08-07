package com.lcm.utils.response;

import java.io.IOException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class CustomerDoubleSerialize  extends JsonSerializer<Double> {
	private DecimalFormat df = new DecimalFormat("0.00");

	@Override
	public void serialize(Double arg0, JsonGenerator arg1,
			SerializerProvider arg2) throws IOException,
			JsonProcessingException {
		System.out.println("=================" + arg0);
		if(arg0 != null && !arg0.equals("-")) {
			arg1.writeString(df.format(arg0));
		} else {
			arg1.writeString(arg0.toString());
		}
		
	}

}
