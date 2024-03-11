package util

import java.time.LocalDate
import java.util.*

object DataReader {
    private val scanner = Scanner(System.`in`)

    fun leerEntero(): Int? {
        return scanner.nextInt()
    }

    fun leerLinea(): String? {
        return scanner.nextLine()
    }

    fun leerFecha(): LocalDate? {
        Printer.imprimir("Ingrese la fecha (formato: yyyy-mm-dd):")
        val fechaStr = scanner.nextLine()
        return try {
            LocalDate.parse(fechaStr)
        } catch (e: Exception) {
            null
        }
    }
}

object Printer {
    fun imprimirMenu() {
        println("------ MENÚ ------")
        println("1. Agregar habitación")
        println("2. Eliminar habitación")
        println("3. Hacer reserva")
        println("4. Cancelar reserva")
        println("5. Agregar cliente")
        println("6. Eliminar cliente")
        println("7. Listar habitaciones")
        println("8. Listar clientes")
        println("9. Salir")
        println("------------------")
        print("Seleccione una opción: ")
    }

    fun imprimir(mensaje: String) {
        println(mensaje)
    }
}
