/**
 * 
 */

function validarString(cadena,min, max){
	var res= false;
	if(min == 0 && max != null){
		if((isNaN(cadena) && cadena.length <= max) || cadena == "")
			res = true;		
	}

	if(min > 0 && max == null){
		if(isNaN(cadena) && cadena.length >= min)
			res = true;
	}

	if(min == 0 && max == null){
		if(isNaN(cadena) || cadena == "")
			res = true;
	}

	if(min > 0 && max != null){
		if(isNaN(cadena) && cadena.length >= min && cadena.length <= max)
			res = true;
	}

	return res;
}
function esVacio(cadena){
	if(cadena.length==0){
		return true;
	}
	else{
		return false;
	}
}
function validarNumero(cadena,min,max){
	numero = parseInt(cadena);
	if(!isNaN(cadena) && numero >= min && numero <= max){
		return true;
	}else{
		return false;
	}
}

function validarCP(cadena){
	var numero = parseInt(cadena);
	var min = 1000;
	var max = 52999;
	if(cadena.length==5 && !isNaN(cadena) && numero>= min && numero <= max){
		return true;
	}
	else{ 
		return false;
	}
}

function validarTelefono(cadena) {
    digito=(cadena[0]);
    numero = parseInt (cadena);
    if(!isNaN(cadena) && cadena.length==9 && digito==6 || digito==7 || digito==9) {
        return true;
    } else {
        return false;
    }
}


function validarDNI(dni) {
    var numero;
	var lett; 
	var letra;
    var expresion_regular_dni = /^[XYZ]?\d{5,8}[A-Z]$/;
    dni = dni.toUpperCase();
    if(expresion_regular_dni.test(dni) === true){
        numero = dni.substr(0,dni.length-1);
        numero = numero.replace('X', 0);
        numero = numero.replace('Y', 1);
        numero = numero.replace('Z', 2);
        lett = dni.substr(dni.length-1, 1);
        numero = numero % 23;
        letra = 'TRWAGMYFPDXBNJZSQVHLCKET';
        letra = letra.substring(numero, numero+1);
        if (letra != lett) {
            //alert('Dni erroneo, la letra del NIF no se corresponde');
            return false;
        }else{
            //alert('Dni correcto');
            return true;
        }
    }else{
        //alert('Dni erroneo, formato no válido');
        return false;
    }
}

function esEmail(elemento){
    if(elemento.value!=""){
        var email = elemento.value;
        if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/.test(email)){
            return true;
        }
        else {
            return false;
        }
    }
    else{
	        return false;
    }
}

function validarFecha(fecha){
    var RegExPattern = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;
    if ((fecha.match(RegExPattern)) && (fecha!='')) {
        var array = fecha.split("/");
        var dia = array[0];
        var mes = array[1];
        var ano = array[2];
        if(mes<13 && mes>0){
			var validar = new Date(ano,mes, '0'); // al pasarle un 0 como parametro del día, te selecciona el último día del mes en cuestión
            if((dia)>(validar.getDate())){ // aquí te compara el dia que has metido por teclado con el último día del mes en cuestión
               return false;
            }else{
				var fechaN = new Date();
				fechaN.setDate(dia);
				fechaN.setMonth(mes-1);
				fechaN.setFullYear(ano);
				
				var hoy = new Date();
				hoy.getDate();
				hoy.getMonth()+1;
				hoy.getFullYear();
			
				if(fechaN>hoy){
					return true;
				}else{
					return false;
				}
			}
        }else{
			return false;
        }

    } else {
        return false;
    }
}

