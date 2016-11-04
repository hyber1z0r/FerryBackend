package entities

import javax.persistence.*

@Entity
@Table(name = "PERSON")
class Person {
    @Id
    @Column(name = "PERSON_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long = 0

    @Column(name = "FIRST_NAME")
    var firstName: String? = null
    @Column(name = "LAST_NAME")
    var lastName: String? = null
    @Column(name = "EMAIL", unique = true)
    var email: String? = null

    @OneToMany(mappedBy = "person", cascade = arrayOf(CascadeType.PERSIST))
    private var reservations: MutableList<Reservation>? = null

    constructor() {
    }

    constructor(firstName: String, lastName: String, email: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.reservations = mutableListOf()
    }

    fun getReservations(): MutableList<Reservation>? {
        return reservations
    }

    fun addReservation(reservation: Reservation) {
        this.reservations?.add(reservation)
        if (reservation.person !== this) {
            reservation.person = this
        }
    }
}
