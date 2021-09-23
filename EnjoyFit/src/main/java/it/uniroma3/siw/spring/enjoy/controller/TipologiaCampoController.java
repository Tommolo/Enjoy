package it.uniroma3.siw.spring.enjoy.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.spring.enjoy.controller.validator.TipologiaCampoValidator;
import it.uniroma3.siw.spring.enjoy.misc.FileUploadUtil;
import it.uniroma3.siw.spring.enjoy.model.TipologiaCampo;
import it.uniroma3.siw.spring.enjoy.service.TipologiaCampoService;





@Controller
public class TipologiaCampoController {
	
	@Autowired
	private TipologiaCampoService tipologiaService;
	
    @Autowired
    private TipologiaCampoValidator tipologiaValidator;


  
    
    /**
     * azione dell'amministratore
     * Crea un nuovo oggetto tipoCampo e reindirizza alla pagina tipoCampoForm
     * @param model
     * @return string
     */
    @RequestMapping(value="/admin/tipoCampo", method = RequestMethod.GET)
    public String addtipoCampo(Model model) {
    	model.addAttribute("tipoEsame", new TipologiaCampo());
        return "tipoCampoForm";
    }

    /**
     * un utente vuole visualizzare la pagina di una specifica tipologia di campo
     * @param id
     * @param model
     * @return string
     */
    @RequestMapping(value = "/tipoCampo/{id}", method = RequestMethod.GET)
    public String getTipoCampo(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("tipoCampo", this.tipologiaService.tipoCampoPerId(id));
    	return "tipoCampo";
    }

    /**
     * un utente vuole visualizzare la lista di tutte le tipologie di campi salvate
     * @param model
     * @return string
     */
    @RequestMapping(value = "/tipoCampi", method = RequestMethod.GET)
    public String getTipoCampi(Model model) {
    		model.addAttribute("tipoCampi", this.tipologiaService.tuttiOrdinati());
    		return "tipoCampi";
    }
    
    /**
     * un amministratore vuole cancellare una tipologia di campi salvata
     * @param id
     * @param model
     * @return string
     */
    @RequestMapping(value = "/admin/tipoCampo/{id}", method = RequestMethod.GET)
    public String deleteTipoCampo(@PathVariable("id") Long id, Model model) {
    	this.tipologiaService.cancella(id);
    	model.addAttribute("tipoCampi", this.tipologiaService.tuttiOrdinati());
    	return "tipoEsami";
    }
    
    @RequestMapping(value = "/admin/tipoCampo", method = RequestMethod.POST)
    public String addTipoCampo(@ModelAttribute("tipoCampo") TipologiaCampo tipoCampo, 
    									Model model, BindingResult bindingResult, @RequestParam("image") MultipartFile multipartFile) throws IOException {
    	this.tipologiaValidator.validate(tipoCampo, bindingResult);
        if (!bindingResult.hasErrors()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            tipoCampo.setFoto(fileName);
        	this.tipologiaService.inserisci(tipoCampo);
            String uploadDir = "uploadable/tipoCampi/" + tipoCampo.getId();
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
            model.addAttribute("tipoCampi", this.tipologiaService.tuttiOrdinati());
            return "tipoCampi";
        }
        model.addAttribute("tipoCampi", this.tipologiaService.tuttiOrdinati());
        return "tipoCampoForm";
    }


}
