package com.authenteq.api;

import com.authenteq.constants.BigchainDbApi;
import com.authenteq.model.BigChainDBGlobals;
import com.authenteq.model.MetaDatas;
import com.authenteq.util.JsonUtils;
import com.authenteq.util.NetworkUtils;
import okhttp3.Response;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * The Class MetaDataApi.
 */
public class MetaDataApi {
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger( MetaDataApi.class );
	/**
	 * Gets the metadata.
	 *
	 * @param searchKey the search key
	 * @return the metadata
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static MetaDatas getMetaData(String searchKey) throws IOException {
		log.debug( "getMetaData Call :" + searchKey );
		Response response = NetworkUtils.sendGetRequest(BigChainDBGlobals.getBaseUrl() + BigchainDbApi.METADATA + "/?search="+ searchKey);
		String body = response.body().string();
		response.close();
		return JsonUtils.fromJson(body, MetaDatas.class);
	}
	
	/**
	 * Gets the metadata with limit.
	 *
	 * @param searchKey the search key
	 * @param limit the limit
	 * @return the metadata with limit
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static MetaDatas getMetaDataWithLimit(String searchKey, String limit) throws IOException {
		log.debug( "getMetaDataWithLimit Call :" + searchKey + " limit " + limit );
		Response response = NetworkUtils.sendGetRequest(BigChainDBGlobals.getBaseUrl() + BigchainDbApi.METADATA + "/?search="+ searchKey+ "&limit=" + limit);
		String body = response.body().string();
		response.close();
		return JsonUtils.fromJson(body, MetaDatas.class);
	}
}
