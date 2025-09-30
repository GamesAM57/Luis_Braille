Algoritmo sin_titulo
	Definir hora Como Entero;
	Escribir "Indica una hora";
	Leer hora;
	si	hora >= 6 y hora <= 11 Entonces
		Escribir "Mañana";
	SiNo
		si	hora >= 12 y hora <= 18 Entonces
			Escribir "Tarde";
		SiNo
			si	hora >= 19 y hora <= 23 Entonces
				Escribir "Noche";
			SiNo
				si	hora >= 0 y hora <= 5 Entonces
					Escribir "Madrugada";
				SiNo
					Escribir  "Hora inválida";
				FinSi
			FinSi
		FinSi
	FinSi
FinAlgoritmo
