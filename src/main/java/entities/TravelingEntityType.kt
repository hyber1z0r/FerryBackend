package entities

import entities.Price
import javax.persistence.*

@Entity
@Table(name = "TRAVELING_ENTITY_TYPE")
class TravelingEntityType {
    @Id
    @Column(name = "TRAVELING_ENTITY_TYPE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long = 0

    @OneToMany(mappedBy = "travelingEntityType", fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.PERSIST))
    private val prices: MutableList<Price>

    @OneToMany(mappedBy = "travelingEntityType", fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.PERSIST))
    private val travelingEntities: MutableList<TravelingEntity>

    constructor() {
        this.prices = mutableListOf()
        this.travelingEntities = mutableListOf()
    }

    fun addPrice(price: Price) {
        this.prices.add(price)
        if (price.travelingEntityType != this) {
            price.travelingEntityType = this
        }
    }

    fun addTravelingEntity(travelingEntity: TravelingEntity) {
        this.travelingEntities.add(travelingEntity)
        if (travelingEntity.travelingEntityType != this) {
            travelingEntity.travelingEntityType = this
        }
    }
}