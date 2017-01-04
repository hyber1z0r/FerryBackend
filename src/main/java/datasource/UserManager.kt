package datasource

import dtos.*
import entities.Departure
import entities.Route
import entities.TravellingEntity
import interfaces.UserInterface
import java.sql.Date
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

    override fun getDepartures(date: LocalDate, route: RouteIdentifier): List<DepartureDetail> {
        val query = em.createNamedQuery("Departure.search")
        query.setParameter("date", Date.valueOf(date))
        query.setParameter("routeId", route.routeId)
        val departures = query.resultList as List<Departure>
        return departures.map(Departure::toDTO)
    }

    override fun getDeparture(p0: Long): DepartureIdentifier {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun createReservation(p0: PersonDetail?, p1: Long, p2: MutableList<TravellingEntity>?): ReservationIdentifier {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}