package entities

import javax.persistence.*

@Entity
@Table(name = "ROUTE")
class Route {
    @Id
    @Column(name = "ROUTE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long = 0

    @Column(name = "TRAVEL_TIME")
    var travelTime: Int = 0

    @ManyToOne(cascade = arrayOf(CascadeType.PERSIST), fetch = FetchType.LAZY)
    var origin: Harbour? = null

    @ManyToOne(cascade = arrayOf(CascadeType.PERSIST), fetch = FetchType.LAZY)
    var destination: Harbour? = null

    @OneToMany(mappedBy = "route", cascade = arrayOf(CascadeType.PERSIST), fetch = FetchType.LAZY)
    private var departures: MutableList<Departure>? = null

    @OneToMany(mappedBy = "route", cascade = arrayOf(CascadeType.PERSIST), fetch = FetchType.LAZY)
    private var prices: MutableList<Price>? = null

    constructor(travelTime: Int) {
        this.travelTime = travelTime
        this.departures = mutableListOf()
        this.prices = mutableListOf()
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

}