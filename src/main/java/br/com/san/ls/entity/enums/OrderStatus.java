package br.com.san.ls.entity.enums;

public enum OrderStatus {

	REQUESTED("Solicitado"),
	FINISHED("Finalizado"),
	RETURNED("Devolvido");
	
	
	private final String status; 
	
	OrderStatus(String status){
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
