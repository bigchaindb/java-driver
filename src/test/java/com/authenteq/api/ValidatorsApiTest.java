package com.authenteq.api;

import org.junit.Test;

import java.io.IOException;


/**
 * The Class ValidatorsApiTest.
 */
public class ValidatorsApiTest extends AbstractApiTest
{

	/**
	 * Test get validators.
	 */
	@Test
	public void testGetValidators() {
		try {
			ValidatorsApi.getValidators();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
