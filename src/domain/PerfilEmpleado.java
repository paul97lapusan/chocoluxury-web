package domain;

import util.Validator;
import exceptions.DomainException;

public class PerfilEmpleado {
	private int idPerfil;
	private String perfil;
	
	// Constructores

	public PerfilEmpleado() {
	}
	
	public PerfilEmpleado(int idPerfil) {
		this.idPerfil= idPerfil;
	}

	public PerfilEmpleado(int idPerfil, String perfil) {
		this.idPerfil = idPerfil;
		this.perfil = perfil;
	}
	
	// Getters y Setters

	public int getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		if (idPerfil>99 || idPerfil<1) {
			throw new DomainException("El num de ID del perfil no es correcto.");
		} else {
			this.idPerfil=idPerfil;
		}
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		if (Validator.length(perfil, 1, 15)) {
			this.perfil = perfil.trim();
		} else {
			throw new DomainException("La longitud del nombre del perfil no es correcta.");
		}
	}
	
	// toString

	@Override
	public String toString() {
		return "PerfilEmpleado [idPerfil=" + idPerfil + ", perfil=" + perfil + "]";
	}
	
	

	
	


}
