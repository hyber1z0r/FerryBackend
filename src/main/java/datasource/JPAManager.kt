package datasource

import interfaces.IJPAManager
import models.Person
import javax.persistence.EntityManager
import javax.persistence.Persistence


class JPAManager : IJPAManager {
    val em: EntityManager

    constructor(persistencUnit: String) {
       this.em = Persistence.createEntityManagerFactory(persistencUnit).createEntityManager()
    }

    override fun create(p: Person) {
        em.transaction.begin()
        em.persist(p)
        em.transaction.commit()
    }
}