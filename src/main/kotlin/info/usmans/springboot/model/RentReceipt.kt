package info.usmans.springboot.model

import java.util.*
import javax.persistence.*

/**
 * Rent Receipt
 * Created by Usman Saleem on 27/11/2016.
 */
@Entity
class RentReceipt(var amount: Double=0.0,
                  var createdOn: Date = Date(),
                  @Id @GeneratedValue(strategy = GenerationType.AUTO)
                  var id: Long=0) {
    @ManyToOne
    @JoinColumn(name="TENANT_ID")
    lateinit var tenant: Tenant
}


