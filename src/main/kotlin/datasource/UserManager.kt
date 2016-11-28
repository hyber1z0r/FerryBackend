package datasource

import dtos.*
import entities.Person
import entities.Route
import entities.TravellingEntity
import interfaces.UserInterface
import java.time.LocalDate
import javax.persistence.EntityManager

class UserManager(em: EntityManager) : UserInterface {
    private val em: EntityManager

    init {
        this.em = em
    }

    override fun getAllRouteSummaries(): List<RouteSummary> {
        val routes = em.createNamedQuery("Route.findAll").resultList as List<Route>
        return routes.map(Route::toDTO)
    }

    override fun getTravellingEntities(): MutableList<TravellingEntity> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDepartures(p0: LocalDate?, p1: RouteIdentifier?): MutableList<DepartureIdentifier> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDeparture(p0: Long): DepartureIdentifier {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createReservation(p0: PersonDetail?, p1: Long, p2: MutableList<TravellingEntity>?): ReservationIdentifier {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun createPerson(p: Person) {
        em.transaction.begin()
        em.persist(p)
        em.transaction.commit()
    }


}