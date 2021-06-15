package domain;

import exceptions.DomainException;
import util.Validator;

public class Articulo {
	private int codArt;
	private String nombreArt;
	private String descripcion; // puede ser nulo
	private Double precioVenta;
	private int stock;
	private String imagen;
	
	// Constructores
	
	Articulo() {
	}
	
	public Articulo(int codArt) {
		setCodArt(codArt);
	}
	
	
	public Articulo(String nombreArt, String descripcion, Double precioVenta, int stock, String imagen) {
		setNombreArt(nombreArt);
		setDescripcion(descripcion);
		setPrecioVenta(precioVenta);
		setStock(stock);
		setImagen(imagen);
	}
	
	public Articulo(int codArt, String nombreArt, String descripcion, Double precioVenta, int stock, String imagen) {
		setCodArt(codArt);
		setNombreArt(nombreArt);
		setDescripcion(descripcion);
		setPrecioVenta(precioVenta);
		setStock(stock);
		setImagen(imagen);
	}
	

	Articulo(String nombreArt, Double precioVenta, int stock) {
		this.nombreArt = nombreArt;
		this.precioVenta = precioVenta;
		this.stock = stock;
	}

	public int getCodArt() {
		return codArt;
	}

	public void setCodArt(int codArt) {
		if (codArt>=0 && codArt<999999) {
			this.codArt=codArt;
		} else {
	    	 throw new DomainException("La longitud del codigo del articulo es incorrecta");
	     }
	}

	public String getNombreArt() {
		return nombreArt;
	}

	public void setNombreArt(String nombreArt) {
		if (Validator.length(nombreArt, 1, 50)) {
			this.nombreArt = nombreArt.trim();
		} else {
			throw new DomainException("La longitud del nombre del articulo no es correcta.");
		}
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		if (descripcion==null || descripcion.trim().length()==0) {
			this.descripcion = null;
		} else {
			if (Validator.length(descripcion, 1, 100)) {
				this.descripcion = descripcion.trim();
			} else {
				throw new DomainException("La longitud de la descripción del articulo no es correcta.");
			}
		}
	}

	public Double getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Double precioVenta) {
		if (precioVenta!=null){
			if (precioVenta>=0) {
				if(Validator.lengthDecimal(precioVenta, 9,2)) {
			    	 this.precioVenta = precioVenta;
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		if (stock>=0 && stock<999999) {
			this.stock=stock;
		} else {
	    	 throw new DomainException("La cantidad del stock no es correcta");
	     }
	}
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		if (Validator.length(imagen, 1, 100)) {
			this.imagen = imagen;
		} else {
			throw new DomainException("La longitud de la ruta de la imagen no es correcta.");
		}
	}

	@Override
	public String toString() {
		return "Articulo [codArt=" + codArt + ", nombreArt=" + nombreArt + ", descripcion=" + descripcion
				+ ", precioVenta=" + precioVenta + ", stock=" + stock + ", imagen=" + imagen + "]";
	}
	
	//toString

	
	
	

	
	
	
	
	
		
}
