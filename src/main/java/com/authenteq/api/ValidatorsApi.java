package com.authenteq.api;

import com.authenteq.constants.BigchainDbApi;
import com.authenteq.model.BigChainDBGlobals;
import com.authenteq.model.Validators;
import com.authenteq.util.JsonUtils;
import com.authenteq.util.NetworkUtils;
import okhttp3.Response;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * The Class ValidatorsApi.
 */
public class ValidatorsApi {
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger( ValidatorsApi.class );

	/**
	 * Gets the the local validators set of a given node.
	 *
	 * @return the validators
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Validators getValidators() throws IOException {
		log.debug( "getValidators Call" );
		Response response = NetworkUtils.sendGetRequest(BigChainDBGlobals.getBaseUrl() + BigchainDbApi.VALIDATORS);
		String body = response.body().string();
		response.close();
		return JsonUtils.fromJson(body, Validators.class);
	}

}
