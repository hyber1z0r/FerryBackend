package entities

import javax.persistence.*
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "DEPARTURE")
class Departure : Serializable {
    @Id
    @Column(name = "DEPARTURE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long = 0

    @Column(name = "DEPARTURE_TIME")
    var departureTime: LocalTime? = null

    @Column(name = "DEPARTURE_DATE")
    var departureDate: LocalDate? = null

    @OneToMany(mappedBy = "departure", cascade = arrayOf(CascadeType.PERSIST))
    private var reservations: MutableList<Reservation>? = null

    @ManyToOne(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.PERSIST))
    @JoinColumn(name = "FERRY_CONFIG_ID")
    var ferryConfig: FerryConfig? = null

    @ManyToOne(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.PERSIST))
    @JoinColumn(name = "ROUTE_ID")
    var route: Route? = null

    constructor() {}

    constructor(departureTime: LocalTime, departureDate: LocalDate) {
        this.departureTime = departureTime
        this.departureDate = departureDate
        this.reservations = mutableListOf()
    }

    fun addReservation(reservation: Reservation) {
        this.reservations?.add(reservation)
        if (reservation.departure !== this) {
            reservation.departure = this
        }
    }
}
