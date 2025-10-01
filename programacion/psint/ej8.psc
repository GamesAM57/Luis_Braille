Algoritmo sin_titulo
	Definir edad Como Entero;
	Definir estu Como Caracter;
	Definir precio Como Real;
	
	Escribir "Indica tu edad";
	leer edad;
	si edad < 12 Entonces
		precio = 0;
	SiNo
		Escribir "Indica el precio base";
		leer precio;
		si edad >= 12 y edad <= 17 Entonces
			precio = precio/2;
		SiNo
			Escribir "Indica si o no si eres estudiante";
			leer estu;
			si estu == "si" Entonces
				precio = precio - (precio / 4);
			FinSi
			si edad >= 65 Entonces
				precio = precio - (precio / 10);
			FinSi
		FinSi
	FinSi
	Escribir "Tienes que pagar ", precio, " euros.";
FinAlgoritmo
