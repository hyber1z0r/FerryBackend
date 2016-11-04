package entities

import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="ENTITY_TYPE")
abstract class TravelingEntity {
    @Id
    @Column(name = "TRAVELING_ENTITY_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long = 0

    @ManyToOne(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.PERSIST))
    var travelingEntityType: TravelingEntityType? = null

    @ManyToMany(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.PERSIST))
    @JoinTable(name = "TRAVELING_ENTITY_RESERVATION",
            joinColumns = arrayOf(JoinColumn(name = "TE_ID", referencedColumnName = "TRAVELING_ENTITY_ID")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "R_ID", referencedColumnName = "RESERVATION_ID")))
    private var reservations: MutableList<Reservation>? = null

    fun addReservation(reservation: Reservation) {
        if (this.reservations?.contains(reservation)!!) return
        this.reservations?.add(reservation)
        reservation.addTravelingEntity(this)
    }

    init {
        this.reservations = mutableListOf()
    }
}