Algoritmo sin_titulo
	Definir num Como Entero;
	Escribir "Indica un número entero";
	Leer num;
	Si num < 0 Entonces
		si num % 2 = 0 Entonces
			Escribir "Número negativo par";
		SiNo
			Escribir  "Número negativo impar"
		FinSi
	SiNo
		si num % 2 = 0 Entonces
			Escribir "Número positivo par";
		SiNo
			Escribir  "Número positivo impar"
		FinSi
	FinSi
FinAlgoritmo
