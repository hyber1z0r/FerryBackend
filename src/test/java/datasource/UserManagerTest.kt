package datasource

import Sampledata.Inserter
import dtos.RouteSummary
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class UserManagerTest {
    private var manager: UserManager? = null
    private var inserter: Inserter? = null

    constructor() {
        manager = UserManager(JPAManager.instance.em)
        inserter = Inserter(JPAManager.instance.em)
    }

    @Before
    fun setUp() {
        inserter?.insert()
    }

    @After
    fun cleanUp() {
        inserter?.drop()
    }

    @Test
    fun getAllRouteSummaries() {
        val summaries: List<RouteSummary>? = manager?.allRouteSummaries
        assertTrue(summaries?.size == 2)
    }

    @Test
    fun getDepartures() {
        val searchDate = LocalDate.now()
        val summaries = manager?.allRouteSummaries
        val departures = manager?.getDepartures(searchDate, summaries!![0])
        assertTrue("The departures should be 1 but was " + departures?.size, departures?.size == 1)
    }

    @Test
    fun getDeparturesOffByOneUnder() {
        val searchDate = LocalDate.now().minusDays(1)
        val summaries = manager?.allRouteSummaries
        val departures = manager?.getDepartures(searchDate, summaries!![0])
        assertTrue(departures?.size == 0)
    }

    @Test
    fun getDeparturesOffByOneOver() {
        val searchDate = LocalDate.now().plusDays(1)
        val summaries = manager?.allRouteSummaries
        val departures = manager?.getDepartures(searchDate, summaries!![0])
        assertTrue(departures?.size == 0)
    }

    @Test
    fun getDepartureNotFound() {
        val departureId = -1L
        val departure = manager?.getDeparture(departureId)
        assertTrue(departure == null)
    }
}