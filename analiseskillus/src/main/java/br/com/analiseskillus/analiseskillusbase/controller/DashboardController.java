package br.com.analiseskillus.analiseskillusbase.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.analiseskillus.analiseskillusbase.dto.UsuarioTeste;
import br.com.analiseskillus.analiseskillusbase.repository.Usuarios;
import br.com.analiseskillus.relatorioskillusportal.dto.VendaCiclo;
import br.com.analiseskillus.relatorioskillusportal.repository.RelatoriosPortal;

@Controller
public class DashboardController {

	private Usuarios usuarios;
	
	private RelatoriosPortal relatoriosPortal;
	
	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("index");
		
		return mv;
	}
	
	@GetMapping("/total")
	public @ResponseBody List<VendaCiclo> totalVendaCiclo() {
		return relatoriosPortal.totalPorCiclo();
	}
	
	@GetMapping("usuarioTeste")
	public @ResponseBody List<UsuarioTeste> totalUsuarioTeste() {
		return usuarios.listarUsuario();
	}
}
