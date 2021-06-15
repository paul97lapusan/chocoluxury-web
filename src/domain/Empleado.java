package domain;

import util.Validator;
import exceptions.DomainException;

public class Empleado {
	
	private int codEmp;
	private String dni;
	private String clave;
	private String nombre;
	private String telefono;// puede ser nulo
	private PerfilEmpleado perfil;
	private String email;
	
	// Constructores

	public Empleado() {
	}

	public Empleado(int codemp) {
		this.codEmp = codemp;
	}

	public Empleado(String dni, String email, String clave, String nombre, String telefono, PerfilEmpleado perfil) {
		setDni(dni);
		setClave(clave);
		setNombre(nombre);
		setTelefono(telefono);
		setPerfil(perfil);
		setEmail(email);
	}
	
	public Empleado(int codemp, String dni, String email, String clave, String nombre, String telefono, PerfilEmpleado perfil) {
		setCodemp(codemp);
		setDni(dni);
		setClave(clave);
		setNombre(nombre);
		setTelefono(telefono);
		setPerfil(perfil);
		setEmail(email);
	}
	

	public Empleado(Empleado empleado) {
		setCodemp(empleado.codEmp);
		setDni(empleado.dni);
		setEmail(empleado.email);
		setClave(empleado.clave);
		setNombre(empleado.nombre);
		setTelefono(empleado.telefono);
		setPerfil(empleado.perfil);
	}
	
	// Getters y Setters

	public int getCodemp() {
		return codEmp;
	}

	public void setCodemp(int codemp) {
		if (codemp>=0 && codemp<999999) {
			this.codEmp=codemp;
		} else {
	    	 throw new DomainException("La longitud del codigo del empleado es incorrecta");
	     }
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		if (Validator.DNI(dni)) {
			this.dni = dni;
		} else {
			throw new DomainException("El DNI no es correcto.");
		}
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		if (Validator.length(clave, 8, 20)) {
			this.clave = clave;
		} else {
			throw new DomainException("La longitud de la clave no es correcta, entre 8 y 20.");
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (Validator.length(nombre, 1, 30)) {
			this.nombre = nombre.trim();
		} else {
			throw new DomainException("La longitud del nombre del empleado no es correcta.");
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
				throw new DomainException("El telfn no es correcto.");
			}
		}
	}

	public PerfilEmpleado getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEmpleado perfil) {
		if(perfil!=null) {
			this.perfil=perfil;
		} else {
			throw new DomainException("El perfil es obligatorio.");
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (Validator.isValidEmailAddress(email) && Validator.length(email, 1, 50)) {
			this.email = email.trim();
		} else {
			throw new DomainException("El email no es correcto.");
		}
	}
	
	// toString

	@Override
	public String toString() {
		return "Empleado [codemp=" + codEmp + ", dni=" + dni + ", clave=" + clave + ", nombre=" + nombre + ", telefono="
				+ telefono + ", perfil=" + perfil + ", email=" + email + "]";
	}
	


}
