package entities

import javax.persistence.*

@Entity
@Table(name = "FERRY_CONFIG")
class FerryConfig {
    @Id
    @Column(name = "FERRY_CONFIG_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long = 0

    @Column(name = "PEOPLE_CAPACITY")
    var peopleCapacity: Int? = null

    @Column(name = "VEHICLE_CAPACITY")
    var vehicleCapacity: Int? = null

    @Column(name = "WEIGHT_CAPACITY")
    var weightCapacity: Double? = null

    @OneToMany(mappedBy = "ferryConfig", cascade = arrayOf(CascadeType.PERSIST))
    private var departures: MutableList<Departure>? = null

    @ManyToOne(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.PERSIST))
    @JoinColumn(name = "FERRY_ID")
    var ferry: Ferry? = null

    constructor() {}

    constructor(peopleCapacity: Int, vehicleCapacity: Int, weightCapacity: Double) {
        this.peopleCapacity = peopleCapacity
        this.vehicleCapacity = vehicleCapacity
        this.weightCapacity = weightCapacity
        this.departures = mutableListOf()
    }

    fun addDeparture(departure: Departure) {
        this.departures?.add(departure)
        if (departure.ferryConfig != this) {
            departure.ferryConfig = this
        }
    }
}