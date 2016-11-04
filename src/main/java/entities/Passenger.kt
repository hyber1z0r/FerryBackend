package entities

import javax.persistence.*

@MappedSuperclass
abstract class Passenger : TravelingEntity {

    constructor() {

    }
}