package entities

import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class Vehicle : TravelingEntity {

    @Column(name = "WEIGHT")
    var weight: Double? = 0.0
    @Column(name = "REGNO")
    var regNo: String? = ""
    @Column(name = "LENGTH")
    var length: Double? = 0.0

    constructor() {

    }

    constructor(weight: Double, regNo: String, length: Double) {
        this.weight = weight
        this.regNo = regNo
        this.length = length
    }
}