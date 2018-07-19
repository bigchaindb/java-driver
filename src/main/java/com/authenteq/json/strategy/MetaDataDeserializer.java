package com.authenteq.json.strategy;

import com.authenteq.model.MetaData;
import com.authenteq.model.MetaDatas;
import com.authenteq.util.JsonUtils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * The Class AssetsDeserializer.
 */
public class MetaDataDeserializer implements JsonDeserializer<MetaDatas> {

	/* (non-Javadoc)
	 * @see com.google.gson.JsonDeserializer#deserialize(com.google.gson.JsonElement, java.lang.reflect.Type, com.google.gson.JsonDeserializationContext)
	 */
	@Override
	public MetaDatas deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {

		MetaDatas metaDatas = new MetaDatas();
		for( JsonElement jElement: json.getAsJsonArray() ) {
			metaDatas.addMetaData(JsonUtils.fromJson(jElement.getAsJsonObject().toString(), MetaData.class));
		}
		return metaDatas;
	}
	
}
