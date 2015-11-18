package br.nassau.projeto.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.nassau.projeto.dao.DaoUsuario;
import br.nassau.projeto.dao.DaoUsuarioImpl;
import br.nassau.projeto.model.Usuario;

/**
 * Servlet implementation class EfetuaLoginServlet
 */
@WebServlet("/EfetuaLoginAdministradorServlet")
public class EfetuaLoginAdministradorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private DaoUsuario daoUsuario;
	private Usuario us;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		daoUsuario = new DaoUsuarioImpl();
	}
	
    public EfetuaLoginAdministradorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		
		Integer id = Integer.valueOf(request.getParameter("id"));
		
		boolean aut = false;
		
		try {
			aut = daoUsuario.autenticaAdministrador(email, senha);
			
			if(aut){
				us = daoUsuario.get(id);
				HttpSession session = request.getSession();
				session.setAttribute("usRs", us);
				
				response.sendRedirect("indexAdminPainel.jsp");
			}else{
				response.sendRedirect("indexAdmin.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
