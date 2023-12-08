package org.libertas.vendas;

public class Retorno {
	private boolean sucesso;
	private String mensagem;
	
	public Retorno (boolean sucesso, String mensagem) {
		this.sucesso = sucesso;
		this.mensagem = mensagem;
	}
	
	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public boolean isSucesso() {
		return sucesso;
	}
	public String getMensagem() {
		return mensagem;
	}
	

}

