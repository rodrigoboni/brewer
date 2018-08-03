package com.algaworks.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.brewer.model.Cerveja;

@Controller
public class CervejasController {

	@RequestMapping("/cervejas/novo")
	public String novo() {
		return "cerveja/CadastroCerveja";
	}

	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Cerveja cerveja, BindingResult bindingResult, Model model,
			RedirectAttributes attributes) {
		
		if (bindingResult.hasErrors()) {
			// como o return indica ao servlet para requisitar a view ao viewresolver,
			// os attr definidos no model serão encaminhados ao template engine
			// este é o comportamento padrão, foward
			model.addAttribute("mensagem", "Erro no formulário");
			return "cerveja/CadastroCerveja";
		}

		System.out.println(">>> cadastrar " + cerveja);
		
		// como será feito um redirect é necessário usar o obj redirectattr,
		// para que os atributos fiquem "vivos" em uma sessão temp até a próxima requisição, após o browser
		// requisitar a página indicada
		attributes.addFlashAttribute("mensagem", "Cadastro ok!");
		
		// redirect faz response com status 302 (found) + attr location, p/ browser fazer nova req para a url informada
		// assim no caso deste método chama o método get para mostrar o formulário novamente
		return "redirect:/cervejas/novo";
	}
}
