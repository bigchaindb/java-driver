package com.authenteq.json.strategy;

import com.authenteq.model.Validator;
import com.authenteq.model.Validators;
import com.authenteq.util.JsonUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Iterator;



/**
 * The Class ValidatorDeserializer.
 */
public class ValidatorDeserializer implements JsonDeserializer<Validators> {

	/* (non-Javadoc)
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public Validators deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		
		Validators validators = new Validators();
		Iterator<JsonElement> jsonIter = json.getAsJsonArray().iterator();
		while(jsonIter.hasNext()) {
			JsonElement jElement = jsonIter.next();
			validators.addValidator(JsonUtils.fromJson(jElement.getAsJsonObject().toString(), Validator.class));
		}
		return validators;
	}
	
}
