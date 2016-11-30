package datasource

import Sampledata.Inserter
import dtos.RouteSummary
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UserManagerTest {
    private var manager: UserManager? = null

    @Before
    fun setUp() {
        manager = UserManager(JPAManager.instance.em)
        Inserter(JPAManager.instance.em).insert()
    }

    @Test
    fun getAllRouteSummaries() {
        val summaries: List<RouteSummary>? = manager?.allRouteSummaries
        assertTrue(summaries?.size == 2)
    }
}