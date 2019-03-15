package analiseskillus.com.br.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import analiseskillus.com.br.model.Permissao;
import analiseskillus.com.br.service.CadastroPermissaoService;
import analiseskillus.com.br.service.exception.NomePermissaoJaCadastraException;

@Controller
@RequestMapping("/permissoes")
public class PermissoesController {

	@Autowired
	private CadastroPermissaoService cadastroPermissaoService;
	
	@RequestMapping("/nova")
	public ModelAndView nova(Permissao permissao) {
		return new ModelAndView("/menu/CadastroPermissoes");
	}
	
	public ModelAndView cadastrar(@Valid Permissao permissao, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return nova(permissao);
		}
		
		try {
			cadastroPermissaoService.salvar(permissao);
		}catch(NomePermissaoJaCadastraException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return nova(permissao);
		}
		
		attributes.addFlashAttribute("mensagem", "Permissões salvas com sucesso!");
		return new ModelAndView("redirect:/permissoes/nova");
	}
}
