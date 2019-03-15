package analiseskillus.com.br.controller;

import java.util.List;

import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import relatorioskillusportal.dto.VendaCiclo;
import relatorioskillusportal.repositorio.RelatoriosPortal;

@Controller
public class DashboardController {

	private RelatoriosPortal relatorios;
	
	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("index");
		
		//mv.addObject("vendaCiclo", relatorios.totalVendaPorCiclo());
		return mv;
	}
	
	@GetMapping("/totalPorCiclo")
	public @ResponseBody List<VendaCiclo> listarTotalVendaPorCiclo(JpaContext coxtext) {
		return relatorios.totalPorCiclo(coxtext);
	}
}
