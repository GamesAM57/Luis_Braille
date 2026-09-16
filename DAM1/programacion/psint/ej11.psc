Algoritmo sin_titulo
	Definir num Como Entero
	Definir contador Como Entero
	contador <- -1
	Escribir 'Tiene que introducir un numero, si indicas un positivo sigue adelante, sino termina.'
	Repetir
		Escribir 'Indica un número'
		Leer num
		contador <- contador+1
	Hasta Que num<1
	Escribir 'has intruducido ', contador, ' numero positivo'
FinAlgoritmo