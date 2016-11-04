package entities

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "TRUCK")
@DiscriminatorValue("TRUCK")
class Truck(weight: Double, regNo: String, length: Double) : Vehicle(weight, regNo, length) {
    constructor() : this(0.0, "", 0.0) {}
}