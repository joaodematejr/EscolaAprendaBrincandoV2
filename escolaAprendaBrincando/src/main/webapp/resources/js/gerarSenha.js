function dec2hex(numero) {
	var base = 16;
	var digito = new Array();
	var i = 0;

	while (numero != 0) {
		i++;
		digito[i] = numero % base;
		numero = Math.floor(numero / base);
	}
	value = "";
	while (i >= 1) {
		switch (digito[i]) {
		case 10: {
			value += "A";
			break
		}
		case 11: {
			value += "B";
			break
		}
		case 12: {
			value += "C";
			break
		}
		case 13: {
			value += "D";
			break
		}
		case 14: {
			value += "E";
			break
		}
		case 15: {
			value += "F";
			break
		}
		default: {
			value += digito[i];
			break
		}
		}
		i--;
	}
	return value;
}

function GerarSenha() {
	document.forms[0].senha.value = "";
	tamanho = document.forms[0].digitos.value;

	// validar o campo *dígitos*
	if (tamanho < 1 || isNaN(tamanho)) {
		alert("Escolha um valor numérico válido para esse campo");
		document.forms[0].digitos.focus();
		document.forms[0].digitos.select();
		return;
	}

	// ajusta o tamanho (em pixels) do campo de acordo com o número de dígitos
	document.forms[0].senha.style.width = (tamanho * 9) + "px";

	// códigos ASCII decimais
	min = 32;
	max = 126;

	for (i = 1; i <= tamanho; i++) {
		caracter = min + Math.floor((Math.random() * (max - min))); // 32 a 126
		caracter = "%" + dec2hex(caracter);
		caracter = unescape(caracter);
		document.forms[0].senha.value += caracter;
		document.getElementById("aguarde").innerHTML = "aguarde...";
	}
	document.getElementById("aguarde").innerHTML = "";
}