package com.retails.asales.validators;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
@FacesValidator(value = "passwordValidator")
public class PasswordValidator implements Validator, Serializable {

	@Override
	public void validate(FacesContext fc, UIComponent uic, Object propertyValue) throws ValidatorException {
		// your validation here

		Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}");
		Matcher matcher = pattern.matcher(propertyValue.toString());
		boolean matches = matcher.matches();
		if (!matches) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"atleast 8 character, containing minimum one digit, character and special character", null);
			throw new ValidatorException(message);
		}

	}

}