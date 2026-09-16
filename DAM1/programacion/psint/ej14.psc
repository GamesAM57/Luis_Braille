Algoritmo sin_titulo
	Definir num como entero;
	Definir contpar como entero;
	Definir contimp Como Entero;
	Definir  result Como Entero;
	
	contpar = 0;
	contimp = 0;
	
	Mientras num >= 0 Hacer
		Escribir "Introduce un número, introduce negativo para terminar.";
		leer num;
		si num >= 0 Entonces
			si num % 2 = 0 Entonces
				contpar = contpar + 1;
			sino 
				contimp = contimp + 1;
			FinSi
			result = result + num;
		FinSi
	Fin Mientras
	contpar = contimp + contpar;
	Escribir "Resultado: ", result, " Impares: ", contimp, " Pares: ", contpar, " Num tecleado: ", contpar;
FinAlgoritmo
