package com.authenteq.json.strategy;

import com.authenteq.model.MetaData;
import com.authenteq.util.JsonUtils;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class MetaDataSerializer implements JsonSerializer<MetaData>
{
	/**
	 *
	 * @param src object to serialize
	 * @param typeOfSrc type of src
	 * @param context the json context
	 * @return the json object
	 */
	public JsonElement serialize( MetaData src, Type typeOfSrc, JsonSerializationContext context )
	{
		Gson gson = JsonUtils.getGson();
		JsonObject metadata = new JsonObject();
		metadata.add( "metadata", gson.toJsonTree( src.getMetadata(), new TypeToken<Map<String, String>>() { }.getType() ) );
		metadata.add( "id", gson.toJsonTree( src.getId(), new TypeToken<String>() { }.getType() ) );

		return metadata;
	}
}
