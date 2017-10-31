package com.retails.asales.validators;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@ManagedBean
@RequestScoped
@FacesValidator(value = "confirmPasswordValidator")
public class ConfirmPasswordValidator implements Validator, Serializable {

	@Override
	public void validate(FacesContext fc, UIComponent uic, Object propertyValue) throws ValidatorException {
		// your validation here

		String param = (String) uic.getAttributes().get("pwd");
		if (!param.equals(propertyValue.toString())) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Password and Confirm Password should be same ", null);
			throw new ValidatorException(message);
		}

	}

}