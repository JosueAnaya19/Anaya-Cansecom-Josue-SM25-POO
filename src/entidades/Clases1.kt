package entidades

import java.time.LocalDate

data class Habitacion(
    val numero: Int,
    val tipo: TipoHabitacion,
    val capacidad: Int,
    val precioPorNoche: Double
) {
    enum class TipoHabitacion {
        INDIVIDUAL,
        DOBLE,
        SUITE
    }
}

data class Reserva(
    val habitacion: Habitacion,
    val cliente: Cliente,
    val fechaInicio: LocalDate,
    val fechaFin: LocalDate
)

data class Cliente(
    val nombre: String,
    val direccion: String,
    val numeroTelefono: String
)
