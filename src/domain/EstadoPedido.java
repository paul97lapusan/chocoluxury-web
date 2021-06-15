package domain;

import exceptions.DomainException;
import util.Validator;

public class EstadoPedido {
	private int idEstado;
	private String estado;
	
	// Constructores

	public EstadoPedido() {
	}
	
	public EstadoPedido (int idEstado) {
		this.idEstado = idEstado;
	}

	public EstadoPedido (int idEstado, String estado) {
		this.idEstado = idEstado;
		this.estado = estado;
	}
	
	// Getters y Setters
	
	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		if (idEstado>99 || idEstado<1) {
			throw new DomainException("El num de ID del estado del pedido no es correcto.");
		} else {
			this.idEstado=idEstado;
		}
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		if (Validator.length(estado, 1, 20)) {
			this.estado = estado.trim();
		} else {
			throw new DomainException("La longitud del nombre del estado del pedido no es correcta.");
		}
	}
	
	// toString

	@Override
	public String toString() {
		return "EstadoPedido [idEstado=" + idEstado + ", estado=" + estado + "]";
	}
	
	
	
	
}
