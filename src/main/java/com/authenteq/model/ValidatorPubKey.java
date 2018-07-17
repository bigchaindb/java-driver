package com.authenteq.model;

import com.google.gson.annotations.SerializedName;




/**
 * The Class ValidatorPubKey.
 */
public class ValidatorPubKey {
	
	/** The data. */
	@SerializedName("data")
	private String data;
	
	/** The type. */
	@SerializedName("type")
	private String type;

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * Sets the data for pub key.
	 *
	 * @param data the new data for pub key
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type for pub key.
	 *
	 * @param type the new type for pub key
	 */
	public void setType(String type) {
		this.type = type;
	}

}
