package domain;

import exceptions.DomainException;
import util.Validator;

public class HistorialVentas {
	private Articulo articulo;
	private int mes;
	private Double cantidadAcumulada;
	
	// Constructores
	
	HistorialVentas() {
	}
	
	HistorialVentas (Articulo articulo, int mes, Double cantidadAcumulada) {
		this.articulo = articulo;
		this.mes = mes;
		this.cantidadAcumulada = cantidadAcumulada;
	}
	
	// Getters y Setters

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		if(articulo!=null) {
			this.articulo = articulo;
		} else {
			throw new DomainException("El articulo  es obligatorio.");
		}
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		if (mes>0 && mes<13) {
			this.mes = mes;
		} else {
			throw new DomainException("El mes es incorrecto.");
		}
	}

	public Double getCantidadAcumulada() {
		return cantidadAcumulada;
	}

	public void setCantidadAcumulada(Double cantidadAcumulada) {
		if (cantidadAcumulada!=null){
			if (cantidadAcumulada>=0) {
				if(Validator.lengthDecimal(cantidadAcumulada, 9,2)) {
			    	 this.cantidadAcumulada = cantidadAcumulada;
			     } else {
			    	 throw new DomainException("La longitud de la cantidad acumulada no es correcta");
			     }
			} else {
				throw new DomainException("La cantidad acumulada no puede ser negativa");
			}
		} else {
			throw new DomainException("La cantidad acumulada es obligatoria");
		}
	}
	
	//toString

	@Override
	public String toString() {
		return "HistorialVentas [articulo=" + articulo + ", mes=" + mes + ", cantidadAcumulada=" + cantidadAcumulada
				+ "]";
	}
	
	
	
	

}
