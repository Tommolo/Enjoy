package it.uniroma3.siw.spring.rava.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.enjoy.model.Cliente;

@Component
public class ClienteValidator implements Validator {
	
	final Integer MAX_NAME_LENGTH = 20;
    final Integer MIN_NAME_LENGTH = 2;
    
    @Override
    public void validate(Object o, Errors errors) {
        Cliente user = (Cliente) o;
        String nome = user.getNome().trim();
        String cognome = user.getCognome().trim();
        String email = user.getEmail().trim();
        String telefono = user.getEmail().trim();

        if (nome.isEmpty())
            errors.rejectValue("nome", "required");
        else if (nome.length() < MIN_NAME_LENGTH || nome.length() > MAX_NAME_LENGTH)
            errors.rejectValue("nome", "size");

        if (cognome.isEmpty())
            errors.rejectValue("cognome", "required");
        else if (cognome.length() < MIN_NAME_LENGTH || cognome.length() > MAX_NAME_LENGTH)
            errors.rejectValue("cognome", "size");
        
        if(email.isEmpty()) 
        	errors.rejectValue("email", "required");
        
        if(telefono.isEmpty())
        	errors.rejectValue("telefono", "required");
        
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Cliente.class.equals(clazz);
    }

}
