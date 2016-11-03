package models

import javax.persistence.*
import java.io.Serializable

@Entity
@Table(name = "DEPARTURES")
class Departure : Serializable {
    @Id
    @Column(name = "DEPARTURE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long = 0

}
