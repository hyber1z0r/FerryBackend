import datasource.JPAManager
import models.Person
import models.Reservation

fun main(args: Array<String>) {
    val manager = JPAManager("FerryUnit")
    val person = Person("Leon", "Hansen", "leon@hansen.dk")
    val res = Reservation()
    person.addReservation(res)
    manager.create(person)
}
