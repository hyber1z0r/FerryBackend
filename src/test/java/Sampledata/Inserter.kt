package Sampledata

import entities.*
import entities.Ferry
import entities.*
import entities.Price
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.EntityManager

class Inserter(manager: EntityManager) {
    private var em: EntityManager

    init {
        this.em = manager
    }

    fun insert() {
        val person = Person("Leon", "Hansen", "leon@qqq.dk")
        val dep = Departure(LocalTime.now(), LocalDate.now())
        val dep2 = Departure(LocalTime.now(), LocalDate.of(2008, 12, 24))
        val res = Reservation()
        val ferryconf = FerryConfig(50, 10, 2000.0)
        val ferry = Ferry("Margrethe")
        val harbour = Harbour("Leons Havn")
        val harbour2 = Harbour("Margrethes Havn")
        val route1 = Route(30.0)
        val route2 = Route(30.0)
        val expensive = entities.Price(3000.0)
        val cheap = entities.Price(1500.0)
        val travelingEntityType = TravelingEntityType()
        val alien: TravelingEntity = Alien()
        val truck: TravelingEntity = Truck(1000.0, "AB123456", 8.0)

        travelingEntityType.addTravelingEntity(alien)
        travelingEntityType.addTravelingEntity(truck)
        travelingEntityType.addPrice(expensive)
        res.addTravelingEntity(alien)
        res.addTravelingEntity(truck)
        route1.addPrice(expensive)
        route2.addPrice(cheap)

        harbour2.addOriginRoute(route1)
        harbour.addDestinationRoute(route1)

        harbour.addOriginRoute(route2)
        harbour2.addDestinationRoute(route2)

        route1.addDeparture(dep)
        route1.addDeparture(dep2)

        harbour.addFerry(ferry)
        ferry.addFerryConfig(ferryconf)
        ferryconf.addDeparture(dep)
        dep.addReservation(res)
        person.addReservation(res)

        em.transaction.begin()
        em.persist(person)
        em.transaction.commit()
    }

    fun drop(){
        em.transaction.begin()
        em.createNativeQuery("TRUNCATE TABLE ferry_harbour CASCADE").executeUpdate()
        em.createNativeQuery("TRUNCATE TABLE traveling_entity_reservation CASCADE").executeUpdate()
        em.createNativeQuery("TRUNCATE TABLE reservation CASCADE").executeUpdate()
        em.createNativeQuery("TRUNCATE TABLE departure CASCADE").executeUpdate()
        em.createNativeQuery("TRUNCATE TABLE ferry_config CASCADE").executeUpdate()
        em.createNativeQuery("TRUNCATE TABLE ferry CASCADE").executeUpdate()
        em.createNativeQuery("TRUNCATE TABLE price CASCADE").executeUpdate()
        em.createNativeQuery("TRUNCATE TABLE route CASCADE").executeUpdate()
        em.createNativeQuery("TRUNCATE TABLE harbour CASCADE").executeUpdate()
        em.createNativeQuery("TRUNCATE TABLE person CASCADE").executeUpdate()
        em.createNativeQuery("TRUNCATE TABLE truck CASCADE").executeUpdate()
        em.createNativeQuery("TRUNCATE TABLE native CASCADE").executeUpdate()
        em.createNativeQuery("TRUNCATE TABLE heavymachinery CASCADE").executeUpdate()
        em.createNativeQuery("TRUNCATE TABLE car CASCADE").executeUpdate()
        em.createNativeQuery("TRUNCATE TABLE alien CASCADE").executeUpdate()
        em.createNativeQuery("TRUNCATE TABLE travelingentity CASCADE").executeUpdate()
        em.createNativeQuery("TRUNCATE TABLE traveling_entity_type CASCADE").executeUpdate()
        em.transaction.commit()
    }
    //        // FerryConfigs
//        val fc1 = FerryConfig(peopleCapacity = 120, vehicleCapacity = 16, weightCapacity = 100.0)
//        val fc2 = FerryConfig(peopleCapacity = 80, vehicleCapacity = 10, weightCapacity = 80.0)
//        val fc3 = FerryConfig(peopleCapacity = 200, vehicleCapacity = 0, weightCapacity = 150.0)
//
//        // Ferries
//        val f1 = Ferry("Margrethe")
//        f1.addFerryConfig(fc1)
//        val f2 = Ferry("Birgitte")
//        f2.addFerryConfig(fc2)
//        val f3 = Ferry("Rosa")
//        f3.addFerryConfig(fc3)
//
//        // Harbours
//        val h1 = Harbour("Rødby")
//        h1.addFerry(f1)
//        val h2 = Harbour("Puttgarten")
//        h2.addFerry(f1)
//        val h3 = Harbour("Gedser")
//        h3.addFerry(f2)
//        h3.addFerry(f3)
//        val h4 = Harbour("Rostock")
//        h4.addFerry(f2)
//        h4.addFerry(f3)
//
//        // Routes
//        // Det tager 2 timer at sejle fra Rødby til Puttgarten
//        val r1 = Route(120.0)
//        r1.addPrice(Price(230.0))
//        h1.addOriginRoute(r1)
//        h2.addDestinationRoute(r1)
//
//        // Og ruten eksisterer også den anden vej hjem
//        val r2 = Route(120.0)
//        r2.addPrice(Price(200.0))
//        h2.addOriginRoute(r2)
//        h1.addDestinationRoute(r2)
//
//        // 1 time fra Gedser til Rostock
//        val r3 = Route(60.0)
//        r3.addPrice(Price(120.0))
//        h3.addOriginRoute(r3)
//        h4.addDestinationRoute(r3)
//
//        // Og ruten eksisterer også hjem
//        val r4 = Route(60.0)
//        r4.addPrice(Price(120.0))
//        h4.addOriginRoute(r4)
//        h3.addDestinationRoute(r4)
//
//        // Departures
//        val d1 = Departure(LocalTime.of(9, 0), LocalDate.now())
//        fc1.addDeparture(d1)
//
//        val d2 = Departure(LocalTime.NOON, LocalDate.now())
//        fc1.addDeparture(d2)
//
//        val d3 = Departure(LocalTime.of(15, 0), LocalDate.of(2016, Month.DECEMBER, 24))
//        fc3.addDeparture(d3)
//
//        val d4 = Departure(LocalTime.of(19, 30), LocalDate.of(2016, Month.DECEMBER, 24))
//        fc3.addDeparture(d4)
}