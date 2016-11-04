import datasource.JPAManager
import entities.*
import java.time.LocalDate
import java.time.LocalTime

fun main(args: Array<String>) {
    val manager = JPAManager("FerryUnit")
    val person = Person("Leon", "Hansen", "leon@hansen.dk")
    val dep = Departure(LocalTime.now(), LocalDate.now())
    val res = Reservation()
    val ferryconf = FerryConfig(50, 10, 2000.0)
    val ferry = Ferry("Margrethe")
    val harbour = Harbour("Leons Havn")
    val harbour2 = Harbour("Margrethes Havn")
    val route1 = Route(30)
    val route2 = Route(30)
    val expensive = Price(3000.0)
    val cheap = Price(1500.0)
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

    harbour.addFerry(ferry)
    ferry.addFerryConfig(ferryconf)
    ferryconf.addDeparture(dep)
    dep.addReservation(res)
    person.addReservation(res)
    manager.create(person)
}
