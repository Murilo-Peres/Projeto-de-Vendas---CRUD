package org.libertas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.libertas.vendas.Retorno;
import org.libertas.vendas.VendasDAO;

import com.google.gson.Gson;

/**
 * Servlet implementation class Vendas
 */
public class Vendas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Vendas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
//		response.setContentType("text/html; chçarset=UTF-8");
//		response.setHeader("Cache-control", "no-cache, no-store");
//		response.setHeader("Pragma", "no-cache");
//		response.setHeader("Expires", "-1");
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		response.setHeader("Access-Control-Allow-Methods", "*");
//		response.setHeader("Access-Control-Allow-Headers", "*");
//		response.setHeader("Access-Control-Max-Age", "0");
//		response.addHeader("Access-Control-Allow-Credentials", "true");
//		response.addHeader("Content-Type", "application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		//out.println("Executando método GET");
		VendasDAO venDAO = new VendasDAO();
		List<org.libertas.vendas.Vendas> lista = venDAO.listar();
		Gson gson = new Gson();
		out.println(gson.toJson(lista));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		//out.println("Executando método POST");
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String body = sb.toString();
			
			Gson gson = new Gson();
			org.libertas.vendas.Vendas venda  = gson.fromJson(body, org.libertas.vendas.Vendas.class);
			
			VendasDAO venDAO = new VendasDAO();
			venDAO.inserir(venda);
			Retorno r = new Retorno(true, "Registro inserido com sucesso!");
			out.print(gson.toJson(r));
		} catch (Exception e) {
			Gson gson = new Gson();
			e.printStackTrace();
			Retorno r = new Retorno(false, e.getMessage());
			out.print(gson.toJson(r));
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//out.println("Executando método PUT");
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String body = sb.toString();
			
			Retorno r = new Retorno(true, "Registro alterado com sucesso!");
			Gson gson = new Gson();
			org.libertas.vendas.Vendas venda  = gson.fromJson(body, org.libertas.vendas.Vendas.class);
			
			VendasDAO venDAO = new VendasDAO();
			venDAO.alterar(venda);
		} catch (Exception e) {
			e.printStackTrace();
			Gson gson = new Gson();
			Retorno r = new Retorno(false, e.getMessage());
			out.print(gson.toJson(r));
		}
	}
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		//out.println("Executando método DELETE");
		try {
			String id = request.getRequestURI();
			id = id.substring(id.lastIndexOf("/")+1);
			
			VendasDAO venDAO = new VendasDAO();
			org.libertas.vendas.Vendas venda = new org.libertas.vendas.Vendas();
			venda.setId_venda(Integer.parseInt(id));
			venDAO.excluir(venda);
			
			Retorno r = new Retorno(true, "Registro excluido com sucesso!");
			
			Gson gson = new Gson();
			out.print(gson.toJson(r));
		} catch (Exception e) {
			e.printStackTrace();
			Gson gson = new Gson();
			Retorno r = new Retorno(false, e.getMessage());
			out.print(gson.toJson(r));
		}
	}

}


