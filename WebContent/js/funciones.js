/**
 * 
 */

function entraFoco(elemento){
    elemento.style.backgroundColor = "lightblue";
}

function saleFoco(elemento){
    elemento.style.backgroundColor = "";
}
function mostrar(elemento){
    elemento.style.display = "block";
}

function noMostrar(elemento){
    elemento.style.display = "none";
}

function estiloCorrecto (elemento){
    elemento.style.backgroundColor = "";
    elemento.style.borderColor = "green";
}

function estiloIncorrecto (elemento) {
    elemento.style.backgroundColor = "lightyellow";
    elemento.style.borderColor = "red";
}





 function validarStringInside(elemento,min,max,capaerror){ //para los campos tipo string de este formulario
        if(!validarString(elemento.value,min,max)){
            estiloIncorrecto(elemento);
            mostrar(capaerror);
        }else{
           estiloCorrecto(elemento);
           noMostrar(capaerror);
        }
    }

function validarNumeroInside(elemento,min,max,capaerror) {
	if(!validarNumero(elemento.value,min,max)) {
		estiloIncorrecto(elemento);
		mostrar(capaerror);
	} else {
		estiloCorrecto(elemento);
		noMostrar(capaerror);
	}

}

function revisarEmail(elemento,min,max,capaerror){
          if(!esEmail(elemento) || !validarString(elemento.value, min, max)){
            estiloIncorrecto(elemento);
            mostrar(capaerror);
          }
          else{
            estiloCorrecto(elemento);
            noMostrar(capaerror);
          }
      }

