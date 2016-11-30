package entities

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "RESERVATION")
class Reservation : Serializable {
    @Id
    @Column(name = "RESERVATION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long = 0

    @ManyToOne(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.PERSIST))
    @JoinColumn(name = "PERSON_ID")
    var person: Person? = null

    @Column(name = "HAS_ARRIVED")
    var isHasArrived: Boolean = false

    @ManyToOne(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.PERSIST))
    @JoinColumn(name = "DEPARTURE_ID")
    var departure: Departure? = null

    @ManyToMany(mappedBy = "reservations", fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.PERSIST))
    private var travelingEntities: MutableList<TravelingEntity>? = null

    init {
        this.isHasArrived = false
        this.travelingEntities = mutableListOf()
    }

    fun addTravelingEntity(travelingEntity: TravelingEntity) {
        if (this.travelingEntities?.contains(travelingEntity)!!) return
        this.travelingEntities?.add(travelingEntity)
        travelingEntity.addReservation(this)
    }
}
