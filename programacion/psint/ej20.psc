Algoritmo sin_titulo
	Definir num1 Como entero;
	Definir num2 Como entero;
	Escribir "Introduce un número";
	Leer num1;
	Mientras num1 <= 140 Hacer
		Mientras num1 >= 0 Hacer
			Escribir "Introduce otro número, negativo para salir";
			leer num1;
			si num1 > num2 entonces
				num2 = num1;
			FinSi
		FinMientras
	FinMientras
	Escribir "Num mayor es ", num2;
FinAlgoritmo
