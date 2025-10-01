Algoritmo sin_titulo
	Definir i, suma Como Entero
	suma = 0;
	
	Para i = 0 Hasta 9 Hacer
		si i % 2 <> 0 Entonces
			suma = suma + i;
		FinSi
	FinPara
	Escribir "La suma de los numeros impares entre 0 y 9 es: ", suma;
FinAlgoritmo
