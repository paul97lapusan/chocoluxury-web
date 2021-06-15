package domain;

import java.sql.Date;

import exceptions.DomainException;

public class PreparacionPedido {
	private Pedido pedido;
	private Empleado empleado;
	private Date fechaListo; // puede ser nulo
	
	// constructores
	
	PreparacionPedido() {
	}
	
	PreparacionPedido (Pedido pedido, Empleado empleado) {
		this.pedido=pedido;
		this.empleado=empleado;
	}
	
	PreparacionPedido(Pedido pedido, Empleado empleado, Date fechaListo) {
		this.pedido=pedido;
		this.empleado=empleado;
		this.fechaListo=fechaListo;
	}

	// Getters y Setters 
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		if (pedido!=null) {
			this.pedido=pedido;
		} else {
			throw new DomainException("El pedido es obligatorio");
		}
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		if (empleado!=null) {
			this.empleado=empleado;
		} else {
			throw new DomainException("El empleado es obligatorio");
		}
	}

	public Date getFechaListo() {
		return fechaListo;
	}

	public void setFechaListo(Date fechaListo) {
		if(fechaListo==null){
			this.fechaListo=null;
		} else {
			this.fechaListo=fechaListo;
		}
	}
	
	// toString

	@Override
	public String toString() {
		return "PreparacionPedido [pedido=" + pedido + ", empleado=" + empleado + ", fechaListo=" + fechaListo + "]";
	}
	
	

}
