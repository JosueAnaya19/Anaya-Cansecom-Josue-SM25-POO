package controladores

import entidades.*
import util.*
import java.time.LocalDate

class HotelController {
    private val hotel = Hotel()

    fun agregarHabitacion(numero: Int, tipo: Habitacion.TipoHabitacion, capacidad: Int, precioPorNoche: Double) {
        hotel.agregarHabitacion(Habitacion(numero, tipo, capacidad, precioPorNoche))
    }

    fun eliminarHabitacion(numero: Int) {
        hotel.eliminarHabitacion(numero)
    }

    fun agregarCliente(nombre: String, direccion: String, numeroTelefono: String) {
        hotel.agregarCliente(Cliente(nombre, direccion, numeroTelefono))
    }

    fun eliminarCliente(nombre: String) {
        hotel.eliminarCliente(nombre)
    }

    fun hacerReserva(numeroHabitacion: Int, nombreCliente: String, fechaInicio: LocalDate, fechaFin: LocalDate) {
        val habitacion = hotel.buscarHabitacion(numeroHabitacion)
        val cliente = hotel.buscarCliente(nombreCliente)
        if (habitacion != null && cliente != null) {
            hotel.hacerReserva(Reserva(habitacion, cliente, fechaInicio, fechaFin))
        } else {
            Printer.imprimir("Habitación o cliente no encontrado.")
        }
    }

    fun cancelarReserva(numeroHabitacion: Int) {
        val reserva = hotel.buscarReservaPorHabitacion(numeroHabitacion)
        if (reserva != null) {
            hotel.cancelarReserva(reserva)
        } else {
            Printer.imprimir("No se encontró reserva para la habitación $numeroHabitacion.")
        }
    }

    fun listarHabitaciones() {
        hotel.listarHabitaciones()
    }

    fun listarClientes() {
        hotel.listarClientes()
    }
}

class UIController {
    private val hotelController = HotelController()

    fun iniciar() {
        while (true) {
            Printer.imprimirMenu()
            val opcion = DataReader.leerEntero()
            when (opcion) {
                1 -> agregarHabitacion()
                2 -> eliminarHabitacion()
                3 -> hacerReserva()
                4 -> cancelarReserva()
                5 -> agregarCliente()
                6 -> eliminarCliente()
                7 -> listarHabitaciones()
                8 -> listarClientes()
                9 -> break
                else -> Printer.imprimir("Opción inválida.")
            }
        }
    }

    private fun agregarHabitacion() {
        Printer.imprimir("Ingrese el número de la habitación:")
        val numero = DataReader.leerEntero() ?: return
        hotelController.agregarHabitacion(numero, Habitacion.TipoHabitacion.INDIVIDUAL, 1, 50.0)
    }

    private fun eliminarHabitacion() {
        Printer.imprimir("Ingrese el número de la habitación a eliminar:")
        val numero = DataReader.leerEntero() ?: return
        hotelController.eliminarHabitacion(numero)
    }

    private fun hacerReserva() {
        Printer.imprimir("Ingrese el número de la habitación:")
        val numeroHabitacion = DataReader.leerEntero() ?: return

        Printer.imprimir("Ingrese el nombre del cliente:")
        val nombreCliente = DataReader.leerLinea() ?: return

        val fechaInicio = DataReader.leerFecha()
        val fechaFin = fechaInicio?.plusDays(3)

        if (fechaInicio != null && fechaFin != null) {
            hotelController.hacerReserva(numeroHabitacion, nombreCliente, fechaInicio, fechaFin)
        } else {
            Printer.imprimir("Fecha inválida.")
        }
    }

    private fun cancelarReserva() {
        Printer.imprimir("Ingrese el número de la habitación para cancelar la reserva:")
        val numeroHabitacion = DataReader.leerEntero() ?: return
        hotelController.cancelarReserva(numeroHabitacion)
    }

    private fun agregarCliente() {
        Printer.imprimir("Ingrese el nombre del cliente:")
        val nombre = DataReader.leerLinea() ?: return
        hotelController.agregarCliente(nombre, "", "")
    }

    private fun eliminarCliente() {
        Printer.imprimir("Ingrese el nombre del cliente a eliminar:")
        val nombre = DataReader.leerLinea() ?: return
        hotelController.eliminarCliente(nombre)
    }

    private fun listarHabitaciones() {
        hotelController.listarHabitaciones()
    }

    private fun listarClientes() {
        hotelController.listarClientes()
    }
}
