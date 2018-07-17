package com.authenteq.model;

import com.google.gson.annotations.SerializedName;



/**
 * The Class Validator.
 */
public class Validator {

	/** The public key. */
	@SerializedName("pub_key")
	private ValidatorPubKey pubKey;
	
	/** The signature. */
	@SerializedName("power")
	private Integer power;

	/**
	 * Gets the pubKey.
	 *
	 * @return the pubKey
	 */
	public ValidatorPubKey getPubKey() {
		return pubKey;
	}
	
	/**
	 * Sets the pubKey.
	 *
	 * @param pubKey the new pubKey
	 */
	public void setPubKey(ValidatorPubKey pubKey) {
		this.pubKey = pubKey;
	}
	
	/**
	 * Gets the power.
	 *
	 * @return the power
	 */
	public Integer getPower() {
		return power;
	}
	
	/**
	 * Sets the power.
	 *
	 * @param power the new power
	 */
	public void setPower(Integer power) {
		this.power = power;
	}
	
	
}
