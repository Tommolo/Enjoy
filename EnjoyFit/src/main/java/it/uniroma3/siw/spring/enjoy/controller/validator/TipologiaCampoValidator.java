package it.uniroma3.siw.spring.enjoy.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.enjoy.model.TipologiaCampo;
import it.uniroma3.siw.spring.enjoy.service.TipologiaCampoService;



@Component 
public class TipologiaCampoValidator implements Validator {
	
	@Autowired
	private TipologiaCampoService collezioneService;
	
	private static final Logger logger = LoggerFactory.getLogger(PrenotazioneCampoValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prezzo", "required");


		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.collezioneService.alreadyExists((TipologiaCampo)o)) {
				logger.debug("e' un duplicato");
				errors.reject("collezione.duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return PrenotazioneCampoValidator.class.equals(aClass);
	}

}
