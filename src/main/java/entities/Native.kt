package entities

import javax.persistence.*

@Entity
@Table(name = "NATIVE")
@DiscriminatorValue("NATIVE")
class Native() : Passenger()