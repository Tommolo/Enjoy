package it.uniroma3.siw.spring.enjoy.controller.validator;

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
	
	

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prezzo", "required");
	
		TipologiaCampo tipoCampo = (TipologiaCampo) o;
		//controlla che i prenotanti siano maggiori di 0
		if(tipoCampo.getNumeroPersone() <= 0) {
			errors.rejectValue("numeroPersone", "negativo");
		}
		

		if (!errors.hasErrors()) {
			if (this.collezioneService.alreadyExists((TipologiaCampo)o)) {
				errors.reject("collezione.duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return TipologiaCampo.class.equals(clazz);
	}

}
