package br.com.analiseskillus.relatorioskillusportal.dto;

public class VendaCiclo {
	
	private String valorTotal;
	
	private String tipo;
	
	
	public VendaCiclo() {
		
	}
	
	public VendaCiclo(String valorTotal, String tipo) {
		this.valorTotal = valorTotal;
		this.tipo = tipo;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
