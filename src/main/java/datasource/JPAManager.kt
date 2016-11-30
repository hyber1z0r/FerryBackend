package datasource

import javax.persistence.EntityManager
import javax.persistence.Persistence

class JPAManager private constructor(persistencUnit: String) {
    val em: EntityManager

    init {
        this.em = Persistence.createEntityManagerFactory(persistencUnit).createEntityManager()
    }

    private object Holder { val INSTANCE = JPAManager("FerryUnit") }

    companion object {
        val instance: JPAManager by lazy { Holder.INSTANCE }
    }
}