package br.com.caelum.contas.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {

	@RequestMapping("/adicionaConta")
	public String adiciona(Conta conta) {// action
		ContaDAO dao = new ContaDAO();

		dao.adiciona(conta);

		return "conta/conta-adicionada";

	}

	@RequestMapping("/form")
	public String form() {// action
		return "conta/formulario";
	}

	@RequestMapping("/listaContas")
	public ModelAndView lista() {

		ContaDAO dao = new ContaDAO();

		List<Conta> contas = dao.lista();

		ModelAndView mv = new ModelAndView("conta/lista"); // view q sera passada as contas

		mv.addObject("todasContas", contas); // todasContas com todas as contas p/ view

		return mv;
	}

	@RequestMapping("/removeConta")
	public String remove(Conta conta) {

		new ContaDAO().remove(conta);

		// redirect: redirecionamento lado do cliente (a url vai ficar com /listaContas)
		// forward: redirecionamento pelo lado do servidor (u url fica /removeConta?id=x
		return "redirect:listaContas";

	}

	@RequestMapping("/mostraConta")
	public ModelAndView mostra(Long id) {

		return new ModelAndView("conta/mostra").addObject("conta", new ContaDAO().buscaPorId(id));

	}

	@RequestMapping("/alteraConta")
	public String altera(Conta conta) {

		new ContaDAO().altera(conta);

		return "redirect:listaContas";
	}

}
