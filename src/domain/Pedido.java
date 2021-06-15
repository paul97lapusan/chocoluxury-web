package domain;

import java.sql.Date;

import exceptions.DomainException;
import util.Validator;

public class Pedido {
	private int codPed;
	private Date fechaPed;
	private Cliente cliente; 
	private EstadoPedido estado;
	private int domicilio; //0=a recoger en tienda, 1=pedido a domicilio
	private Double importe;
	// constructores
	
	public Pedido() {		
	}
	
	public Pedido(int codped) {
		this.codPed=codped;
	}
	
	public Pedido (int codped, Date fechaped, Cliente cliente, EstadoPedido estado, int domicilio, Double importe) {
		setCodped(codped);
		setFechaped(fechaped);
		setCliente(cliente);
		setEstado(estado);
		setDomicilio(domicilio);
		setImporte(importe);
	}
	
	public Pedido (Date fechaped, Cliente cliente, EstadoPedido estado, int domicilio, Double importe) {
		setFechaped(fechaped);
		setCliente(cliente);
		setEstado(estado);
		setDomicilio(domicilio);
		setImporte(importe);
	}
	
	public Pedido (Cliente cliente) {
		setCliente(cliente);
	}
	
	// getters y setters
	
	public int getCodped() {
		return codPed;
	}

	public void setCodped(int codped) {
		this.codPed = codped;
	}

	public Date getFechaped() {
		return fechaPed;
	}

	public void setFechaped(Date fechaped) {
		this.fechaPed = fechaped;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		if (cliente!=null) {
			this.cliente=cliente;
			} else {
				throw new DomainException("No hay cliente");
		}
	}

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		if(estado!=null) {
			this.estado=estado;
		} else {
			throw new DomainException("El estado es obligatorio.");
		}
	}
	
	public Double getImporte() {
		return importe;
	}
	
	public void setImporte(Double importe) {
		if (importe!=null){
			if (importe>=0) {
				if(Validator.lengthDecimal(importe, 9,2)) {
			    	 this.importe = importe;
			     } else {
			    	 throw new DomainException("La longitud del precio de venta no es correcta");
			     }
			} else {
				throw new DomainException("El precio de venta no puede ser negativo");
			}
		} else {
			throw new DomainException("El precio de venta es obligatorio");
		}
	}

	public int getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(int domicilio) {
		if (domicilio == 0 || domicilio == 1) {
			this.domicilio = domicilio;
		} else {
			throw new DomainException("Seleccione 0 o 1 para determinar si el pedido es a domicilio");
		}
	}

	@Override
	public String toString() {
		return "Pedido [codPed=" + codPed + ", fechaPed=" + fechaPed + ", cliente=" + cliente + ", estado=" + estado
				+ ", domicilio=" + domicilio + ", importe=" + importe + "]";
	}
	
	//toString

	
	

	
	
	
}
