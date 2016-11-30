package entities

import javax.persistence.*

@Entity
@Table(name = "FERRY")
class Ferry {
    @Id
    @Column(name = "FERRY_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private val id: Long = 0

    @Column(name = "NAME")
    var name: String? = ""

    @OneToMany(mappedBy = "ferry", cascade = arrayOf(CascadeType.PERSIST), fetch = FetchType.EAGER)
    private var ferryConfigs: MutableList<FerryConfig>? = null

    @ManyToMany(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.PERSIST))
    @JoinTable(name = "FERRY_HARBOUR",
            joinColumns = arrayOf(JoinColumn(name = "F_ID", referencedColumnName = "FERRY_ID")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "H_ID", referencedColumnName = "HARBOUR_ID")))
    private var harbours: MutableList<Harbour>? = null

    constructor() {
    }

    constructor(name: String) {
        this.name = name
        this.ferryConfigs = mutableListOf()
        this.harbours = mutableListOf()
    }

    fun addFerryConfig(ferryConfig: FerryConfig) {
        this.ferryConfigs?.add(ferryConfig)
        if (ferryConfig.ferry != this) {
            ferryConfig.ferry = this
        }
    }

    fun addHarbour(harbour: Harbour) {
        if (this.harbours?.contains(harbour)!!) return
        this.harbours?.add(harbour)
        harbour.addFerry(this)
    }
}