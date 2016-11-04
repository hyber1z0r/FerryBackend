package interfaces

import entities.Person

interface IJPAManager {
    fun create(p: Person)
}
