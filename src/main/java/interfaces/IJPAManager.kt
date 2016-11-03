package interfaces

import models.Person

interface IJPAManager {
    fun create(p: Person)
}
