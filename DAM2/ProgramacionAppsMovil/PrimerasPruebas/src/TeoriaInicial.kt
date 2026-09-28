//fun main(args: Array<String>) {
//    Este se utiliza para recibir parametros al ejecutarse por consola pero
//    no la vamos a utilizar.
//}
//
//fun main() : Unit {
//    Unit es como el void de java, para indicar que no devuelve
//    no hace falta ponerlo ya que es implicito.
//}

fun main() {
    val a : Int // Constantes de java, se indica el tipo a la dercha
    var b : String// Variables de java

    val c = 1 //Se asigna el valor y se le pone el tipo automáticamente
    val d : Int = 1 //Es el equivalente de anterior

    //Para las  variables y constantes se tiene que indicar el tipo o darle un
    //Si no vas a modificar una variable en el programa es recomendable ponerlo como val
    // valor ya que sino kotlin no sabe qe va a guardar.
    //Las variables son case sensitive.

    var j = c.toString() // No existe casteo como java entre parentesis () sino que se hace cosaACastear.toTipoCasteamos()

    print(c) // sout de java, tiene que estar inicializado los variables.
    println(c) // Añade un salto de línea al mensaje que saca por terminal

    var respuesta = readln() // Scanner de kotlin, nos da un string de lo escrito por consola
    print("Mensaje: $respuesta") //Si pones el dolar lo resuelve por el valor de una variable, este y el siguiente se les llaman templates
    print("Longitud de mensaje : ${respuesta.length}") //Si queremos hacer algo con las variables hay que ponerlo entre llaves sino va a sacar por pantalla length
    print("Mensaje: "+respuesta) // Se puede poner como lo hacemos en java pero Kotlin dice que es ineficiente

    val mensaje2 = """
        Podemos añadir varias líneas
        con 3 comillas
        te manteiene los saltos de línea
    """
    val mensaje3 = """
        Con trimIndent()
        te quita la tabulación del mensaje
        pero te respeta los saltos de línea
    """.trimIndent()

    //Los operadores aritméticos, lógicos y de asignación son iguales que en java.
    // + - * / % ++ --
    // En ++ y -- importa el orden
    // ++a incrementa y luego muestra o guarda el valor y a++ muestra o guarda el valor de a y luego incrementa el valor
    // == >= <= !=
    // += -= /= *= %=


    // las contrabarras existen en kotlin como para los saltos de línea, tabulaciones y demás

    // Los condicionales de if if-else else if son iguales que java
    // Pero se pueden añadir a variables, dependiendo del valor se guarda una cosa u otra como si fuese un operador ternario.

    val op = 1
    val saludo = if (op == 1){
        print("hola")
    } else {
        print("adios")
    }

    //El switch se hace con when

    when (op){
        1 -> println("hola")
        2 -> println("adios")
        3,4 -> println("varios valores")
        in 6..10 -> println("en un rango")
        else -> print("como el default de java")
    }

    //solo existe el for each, el while es igual que en java

    for(i in 1..10){
        print("hola") //Recorrer varios valores, listas y demás
    }






}