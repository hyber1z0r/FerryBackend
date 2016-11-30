package entities

import javax.persistence.*

@Entity
@Table(name = "HARBOUR")
class Harbour {
    @Id
    @Column(name = "HARBOUR_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long = 0

    @Column(name = "NAME")
    var name: String? = ""

    @ManyToMany(mappedBy = "harbours", fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.PERSIST))
    private var ferries: MutableList<Ferry>? = null

    @OneToMany(mappedBy = "destination", cascade = arrayOf(CascadeType.PERSIST))
    private var destinationRoutes: MutableList<Route>? = null

    @OneToMany(mappedBy = "origin", cascade = arrayOf(CascadeType.PERSIST))
    private var originRoutes: MutableList<Route>? = null

    constructor() {
    }

    constructor(name: String) {
        this.name = name
        this.ferries = mutableListOf()
        this.destinationRoutes = mutableListOf()
        this.originRoutes = mutableListOf()
    }

    fun addFerry(ferry: Ferry) {
        if (this.ferries?.contains(ferry)!!) return
        this.ferries?.add(ferry)
        ferry.addHarbour(this)
    }

    fun addDestinationRoute(route: Route) {
        this.destinationRoutes?.add(route)
        if (route.destination != this) {
            route.destination = this
        }
    }

    fun addOriginRoute(route: Route) {
        this.originRoutes?.add(route)
        if (route.origin != this) {
            route.origin = this
        }
    }
}