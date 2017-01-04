package entities

import dtos.RouteSummary
import javax.persistence.*

@Entity
@Table(name = "ROUTE")
@NamedQueries(NamedQuery(name = "Route.findAll", query = "SELECT r FROM Route r"))
class Route {
    @Id
    @Column(name = "ROUTE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long = 0

    @Column(name = "TRAVEL_TIME")
    var travelTime: Double = 0.0

    @ManyToOne(cascade = arrayOf(CascadeType.PERSIST), fetch = FetchType.EAGER)
    var origin: Harbour? = null

    @ManyToOne(cascade = arrayOf(CascadeType.PERSIST), fetch = FetchType.EAGER)
    var destination: Harbour? = null

    @OneToMany(mappedBy = "route", cascade = arrayOf(CascadeType.PERSIST), fetch = FetchType.EAGER)
    var departures: MutableList<Departure>? = null

    @OneToMany(mappedBy = "route", cascade = arrayOf(CascadeType.PERSIST), fetch = FetchType.EAGER)
    var prices: MutableList<Price>? = null

    constructor(travelTime: Double, departures: MutableList<Departure> = mutableListOf(),
                prices: MutableList<Price> = mutableListOf()) {
        this.travelTime = travelTime
        this.departures = departures
        this.prices = prices
    }

    constructor() {
    }

    fun addDeparture(departure: Departure) {
        this.departures?.add(departure)
        if (departure.route != this) {
            departure.route = this
        }
    }

    fun addPrice(price: Price) {
        this.prices?.add(price)
        if (price.route != this) {
            price.route = this
        }
    }

    fun toDTO() = RouteSummary(travelTime, origin?.name, destination?.name, id)
}