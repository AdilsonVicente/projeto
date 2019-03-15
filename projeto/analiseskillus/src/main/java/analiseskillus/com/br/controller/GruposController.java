package analiseskillus.com.br.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import analiseskillus.com.br.model.Grupo;
import analiseskillus.com.br.service.CadastroGrupoService;
import analiseskillus.com.br.service.exception.NomeGrupoJaCadastroException;

@Controller
@RequestMapping("/grupos")
public class GruposController {
	
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Grupo grupo) {
		return new ModelAndView("/menu/CadastroGrupos");
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Grupo grupo, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(grupo);
		}
		
		try {
			cadastroGrupoService.salvar(grupo);
		} catch (NomeGrupoJaCadastroException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(grupo);
		}
		
		attributes.addFlashAttribute("mensagem", "Grupos salvos com sucesso!");
		return new ModelAndView("redirect:/grupos/novo");
	}
	
}
