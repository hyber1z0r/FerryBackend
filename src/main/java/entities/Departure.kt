package entities

import dtos.DepartureDetail
import java.io.Serializable
import java.sql.Date
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "DEPARTURE")
@NamedQueries(NamedQuery(name = "Departure.search", query = "SELECT d FROM Departure d WHERE d.departureDate = :date AND d.route.id = :routeId"))
class Departure : Serializable {
    @Id
    @Column(name = "DEPARTURE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long = 0

    @Column(name = "DEPARTURE_TIME", nullable = false)
    var departureTime: Timestamp? = null

    @Column(name = "DEPARTURE_DATE", nullable = false)
    var departureDate: Date? = null

    @OneToMany(mappedBy = "departure", cascade = arrayOf(CascadeType.PERSIST))
    var reservations: MutableList<Reservation>? = null

    @ManyToOne(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.PERSIST))
    @JoinColumn(name = "FERRY_CONFIG_ID")
    var ferryConfig: FerryConfig? = null

    @ManyToOne(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.PERSIST))
    @JoinColumn(name = "ROUTE_ID")
    var route: Route? = null

    constructor() {
    }

    constructor(departureTime: LocalTime, departureDate: LocalDate) {
        this.departureTime = Timestamp.valueOf(LocalDateTime.of(departureDate, departureTime))
        this.departureDate = Date.valueOf(departureDate)
        this.reservations = ArrayList()
    }

    fun addReservation(reservation: Reservation) {
        this.reservations?.add(reservation)
        if (reservation.departure !== this) {
            reservation.departure = this
        }
    }

    fun toDTO(): DepartureDetail = DepartureDetail(this.departureTime?.toLocalDateTime(), this.route?.toDTO(), this.id)
}
