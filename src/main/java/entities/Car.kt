package entities

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "CAR")
@DiscriminatorValue("CAR")
class Car(weight: Double, regNo: String, length: Double) : Vehicle(weight, regNo, length) {
    constructor() : this(0.0, "", 0.0) {}
}