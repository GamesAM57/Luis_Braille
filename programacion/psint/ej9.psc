Algoritmo sin_titulo
	Definir num Como Entero;
	Escribir "Indica un n�mero entero";
	Leer num;
	Si num < 0 Entonces
		si num % 2 = 0 Entonces
			Escribir "N�mero negativo par";
		SiNo
			Escribir  "N�mero negativo impar"
		FinSi
	SiNo
		si num % 2 = 0 Entonces
			Escribir "N�mero positivo par";
		SiNo
			Escribir  "N�mero positivo impar"
		FinSi
	FinSi
FinAlgoritmo
