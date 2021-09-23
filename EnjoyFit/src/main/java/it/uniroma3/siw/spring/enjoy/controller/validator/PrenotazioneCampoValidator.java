package it.uniroma3.siw.spring.enjoy.controller.validator;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.enjoy.model.PrenotazioneCampo;
import it.uniroma3.siw.spring.enjoy.service.PrenotazioneCampoService;


@Component
public class PrenotazioneCampoValidator implements Validator {
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)

	@Autowired
	private PrenotazioneCampoService prenotazioneService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return PrenotazioneCampo.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		PrenotazioneCampo prenotazione = (PrenotazioneCampo) o;
		LocalDate data = prenotazione.getData();
		
		//controlla che la data scelta sia uguale o maggiore di quella di oggi
		if (data.isBefore(LocalDate.now())) {
			errors.rejectValue("data", "indietro");
		}
		
		//controlla che l'orario scelto sia valido
		LocalTime ora = LocalTime.now();
		LocalTime oraPren = LocalTime.parse(prenotazione.getOrario().getOraMax());
		if((data.isEqual(LocalDate.now()) && (ora.isAfter(oraPren)))) {
			errors.rejectValue("orario", "indietro");
		}
	
	}

}
