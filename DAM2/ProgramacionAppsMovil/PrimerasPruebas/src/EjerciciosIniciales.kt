fun main() {
    ejercicio01()
    ejercicio02()
    ejercicio03()
    ejercicio04()
    ejercicio05()
    ejercicio06()
}

fun ejercicio01() {
    val n1 = 3
    val n2 = 2

    println("Suma: ${n1+n2}")
    println("Resta: ${n1-n2}")
    println("Multipicacion: ${n1*n2}")
    println("Division: ${n1/n2}")
    println("Modulo: ${n1%n2}")
}

fun ejercicio02() {
    val nombre = "Liam"
    println("Bienvenido, $nombre")
}

fun ejercicio03() {
    print("Indica tu nombre: ")
    val nombre = readln()
    println("Bienvenido, $nombre")
}

fun ejercicio04() {
    print("Indica el primer número: ")
    val num1 = readln().toInt()
    print("Indica el segundo número: ")
    val num2 = readln().toInt()

    if (num1 < num2){
        println("$num1 es menor que $num2")
    } else if (num1 > num2) {
        println("$num1 es mayor que $num2")
    } else {
        println("Los numeros son iguales.")
    }
}

fun ejercicio05() {
    print("Indica un numero: ")
    val num = readln().toInt()

    if(num%2 == 0){
        print("El numero $num es divisible entre 2")
    } else {
        print("El numero $num no es divisible entre 2")
    }
}

fun ejercicio06() {
    val pregunta = """
        ¿Cuál es la capital de Colombia?
        a. La Paz
        b. Buenos Aires
        c. La Habana
        d. Bogotá
        Respuesta: 
    """.trimIndent()

    print(pregunta)
    var respuesta = readln()

    while (respuesta != "d"){
        print(pregunta)
        respuesta = readln()
    }
    println("¡Felicitaciones!")
}