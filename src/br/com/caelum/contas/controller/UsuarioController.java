package br.com.caelum.contas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.contas.dao.UsuarioDAO;
import br.com.caelum.contas.modelo.Usuario;

@Controller
public class UsuarioController {

	@RequestMapping("/efetuaLogin")
	public String login(Usuario usuario, HttpSession session) {

		if (new UsuarioDAO().existeUsuario(usuario)) {
			session.setAttribute("usuarioLogado", usuario); // criar sessao q sera utilizada na autorização das páginas

			return "redirect: listaContas";
		}

		return "usuario/loginForm";

	}

	@RequestMapping("/loginForm")
	public String form() {

		return "usuario/loginForm";
	}

	@RequestMapping("/logout")
	public String logout(Usuario usuario, HttpSession session) {

		session.invalidate();

		return "usuario/loginForm";
	}

}
