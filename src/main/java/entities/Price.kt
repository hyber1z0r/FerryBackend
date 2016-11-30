package entities

import javax.persistence.*

@Entity
@Table(name = "PRICE")
class Price {
    @Id
    @Column(name = "PRICE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long = 0

    @Column(name = "AMOUNT")
    var amount: Double? = 0.0

    @ManyToOne(cascade = arrayOf(CascadeType.PERSIST), fetch = FetchType.EAGER)
    @JoinColumn(name = "ROUTE_ID")
    var route: Route? = null

    @ManyToOne(cascade = arrayOf(CascadeType.PERSIST), fetch = FetchType.EAGER)
    @JoinColumn(name = "TRAVELING_ENTITIY_TYPE_ID")
    var travelingEntityType: TravelingEntityType? = null

    constructor(amount: Double) {
        this.amount = amount
    }

    constructor() {

    }
}