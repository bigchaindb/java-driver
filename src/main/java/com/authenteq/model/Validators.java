package com.authenteq.model;

import java.util.ArrayList;
import java.util.List;



/**
 * The Class Validators.
 */
public class Validators {

	/** The validators. */
	private List<Validator> validators = new ArrayList<Validator>();

	/**
	 * Gets the validators.
	 *
	 * @return the validators
	 */
	public List<Validator> getValidators() {
		return validators;
	}

	/**
	 * Sets the validators.
	 *
	 * @param validators the new validators
	 */
	public void setValidators(List<Validator> validators) {
		this.validators = validators;
	}

	/**
	 * Adds the validator.
	 *
	 * @param validator the validator
	 */
	public void addValidator(Validator validator) {
		this.validators.add(validator);
	}
}
