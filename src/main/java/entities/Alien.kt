package entities

import javax.persistence.*

@Entity
@DiscriminatorValue("ALIEN")
class Alien() : Passenger()