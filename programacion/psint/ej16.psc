Algoritmo sin_titulo
	Definir i, suma Como Entero
	suma = 0;
	
	Para i = 1 Hasta 10 Con Paso 2 Hacer
		si i % 2 <> 0 Entonces
			suma = suma + i;
		FinSi
	FinPara
	Escribir "La suma de los numeros impares entre 0 y 10 es: ", suma;
FinAlgoritmo