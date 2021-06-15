package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que maneja las validaciones de los formatos de los datos de la
 * aplicacion
 * 
 */
public class Validator {

	/**
	 * Metodo que comprueba la cantidad de caracteres de una cadena
	 * 
	 * @param cadena Cadena a comprobar
	 * @param min    Minimo de caracteres
	 * @param max    Maximo de caracteres
	 * @return boolean; true = valido
	 */
	public static boolean length(String cadena, int min, int max) {
		boolean validation = false;
		if (cadena != null) {
			cadena = cadena.trim();
			if (cadena.length() >= min && cadena.length() <= max) {
				validation = true;
			}
		}
		return validation;

	}

	/**
	 * Metodo que comprueba la cantidad de caracteres de una cadena
	 * 
	 * @param cadena Cadena a comprobar
	 * @param min    Minimo de caracteres
	 * @param max    Maximo de caracteres
	 * @return boolean; true = valido
	 */
	public static boolean lengthDecimal(double numero, int precision, int escala) {
		// los ceros a la izquierda ,y a la derecha de la parte decimal, son omitidos
		// en java por el tipo Double.
		String decimal = null;
		if (numero < 0)
			numero *= -1;
		// para la parte entera
		if ((int) numero >= Math.pow(10, precision - escala))
			return false;
		// para los decimales
		decimal = new Double(numero).toString();
		decimal = decimal.substring(decimal.indexOf(".") + 1);

		if (decimal.length() <= escala) {

			return true;
		} else {

			return false;
		}

	}

	/*
	 * public static boolean alphanumeric(String cadena){ boolean validation =
	 * false; if (cadena != null){ String regex = "[a-zA-Z0-9\\s]+"; if
	 * (cadena.matches(regex)){ validation = true; } } return validation; }
	 */

	/**
	 * Comprueba si un email es valido
	 * 
	 * @param email
	 * @param emailMax
	 * @param emailMin
	 * @return boolean; true = email valido
	 */
	public static boolean email(String email, int emailMin, int emailMax) {
		boolean validation = false;
		if (Validator.length(email, emailMin, emailMax)) {
			String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
			if (email.matches(regex)) {
				validation = true;
			}
		}
		return validation;

	}

	public static boolean isValidEmailAddress(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	/**
	 * Comprueba que el telefono solo tiene numeros y espacios, puede tener '+'
	 * delante
	 * 
	 * @param phoneNo
	 * @return boolean
	 */
	public static boolean telephone(String phoneNo, int min, int max) {
		boolean validation = false;
		if (Validator.length(phoneNo, min, max)) {
			phoneNo = phoneNo.trim();
			if (phoneNo.length() > 0) {

				String regex = "^[+]?([0-9]|\\s)+";
				if (phoneNo.matches(regex)) {
					validation = true;
				}

			} else {
				validation = true;
			}
		}
		return validation;
	}

	/**
	 * Comprueba que el DNI es valido
	 * 
	 * @param dni
	 * @return boolean
	 */

	public static boolean DNI(String nif) {
		// si es NIE, eliminar la x,y,z inicial para tratarlo como nif
		if (nif.toUpperCase().startsWith("X") || nif.toUpperCase().startsWith("Y") || nif.toUpperCase().startsWith("Z"))
			nif = nif.substring(1);

		Pattern nifPattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
		Matcher m = nifPattern.matcher(nif);
		if (m.matches()) {
			String letra = m.group(2);
			// Extraer letra del NIF
			String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
			int dni = Integer.parseInt(m.group(1));
			dni = dni % 23;
			String reference = letras.substring(dni, dni + 1);

			if (reference.equalsIgnoreCase(letra)) {
				return true;
			} else {
				return false;
			}
		} else
			return false;
	}

}
