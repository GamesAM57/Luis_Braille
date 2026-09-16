Algoritmo sin_titulo
	Definir num1 Como Real;
	Definir num2 Como Real;
	Definir op como cadena;
	Escribir "Indica el primer número de la operación.";
	Leer num1;
	Escribir "Indica el segundo número de la operación.";
	Leer num2;
	Escribir "Indica una de las siguientes opciones: sumar, restar, multiplicar";
	Leer op;
	Segun op Hacer
		"sumar":
			num1 = num1 + num2;
		"restar":
			num1 = num1 - num2;
		"multiplicar":
			num1 = num1 * num2;
		De Otro Modo:
			Escribir "No has elegido una opción correcta.";
	Fin Segun
	Escribir "El resultado de ", op, " es: ", num1;
FinAlgoritmo
