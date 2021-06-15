package domain;

import util.Validator;
import exceptions.DomainException;

public class Cliente {

	private int codCli;
	private String nombre;
	private String telefono;// puede ser nulo
	private String direccion; // puede ser nulo
	private String email;
	private String clave;
	
	// Constructores

	public Cliente() {
	}

	public Cliente(int codcli) {
		this.codCli = codcli;
	}

	public Cliente(String nombre, String telefono, String direccion, String email, String clave) {
		setNombre(nombre);
		setTelefono(telefono);
		setDireccion(direccion);
		setEmail(email);
		setClave(clave);
	}
	
	public Cliente(int codcli, String nombre, String telefono, String direccion, String email, String clave) {
		setCodcli(codcli);
		setNombre(nombre);
		setTelefono(telefono);
		setDireccion(direccion);
		setEmail(email);
		setClave(clave);
	}

	public Cliente(Cliente cliente) {
		setCodcli(cliente.codCli);
		setNombre(cliente.nombre);
		setTelefono(cliente.telefono);
		setDireccion(cliente.direccion);
		setEmail(cliente.email);
		setClave(cliente.clave);
	}
	
	// Getters y Setters

	public int getCodCli() {
		return codCli;
	}

	public void setCodcli(int codcli) {
		if (codcli>=0 && codcli<999999) {
			this.codCli=codcli;
		} else {
	    	 throw new DomainException("La longitud del codigo del cliente es incorrecta");
	     }
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (Validator.length(nombre, 1, 30)) {
			this.nombre = nombre.trim();
		} else {
			throw new DomainException("La longitud del nombre del cliente no es correcta (max 30).");
		}
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null || telefono.trim().length() == 0) {
			this.telefono = null;
		} else {
			if (Validator.telephone(telefono, 1, 9)) {
				this.telefono = telefono.trim();
			} else {
				throw new DomainException("El telf no es correcto.");
			}
		}
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		if (direccion == null || direccion.trim().length() == 0) {
			this.direccion = null;
		} else {
			if (Validator.length(direccion, 1, 50)) {
				this.direccion = direccion.trim();
			} else {
				throw new DomainException("La longitud de la dirección del cliente no es correcta (max 50).");
			}
		}
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (Validator.isValidEmailAddress(email) && Validator.length(email, 1, 50)) {
			this.email = email;
		} else {
			throw new DomainException("El email no es correcto.");
		}
	}
	
	public String getClave () {
		return clave;
	}
	
	public void setClave(String clave) {
		if (Validator.length(clave, 8, 20)) {
			this.clave = clave;
		} else {
			throw new DomainException("La longitud de la clave no es correcta, entre 8 y 20.");
		}
	}
	
	// toString

	@Override
	public String toString() {
		return "Cliente [codcli=" + codCli + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion="
				+ direccion + ", email=" + email + ", clave=" + clave + "]";
	}

	
	
	


}
