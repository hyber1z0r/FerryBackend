package models

import javax.persistence.*
import java.io.Serializable

@Entity
@Table(name = "RESERVATIONS")
class Reservation : Serializable {
    @Id
    @Column(name = "RESERVATION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID")
    var person: Person? = null

    @Column(name = "HAS_ARRIVED")
    var isHasArrived: Boolean = false

    init {
        this.isHasArrived = false
    }
}
