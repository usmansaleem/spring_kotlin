package info.usmans.springboot

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Tenant
 * Created by Usman Saleem on 27/11/2016.
 */
@Entity
data class Tenant(var name: String = "",
                  var weeklyRentAmount: Double=0.0,
                  var currentRentPaidToDate: Date = Date(),
                  var currentRentCreditAmount: Double = 0.0,
                  @Id @GeneratedValue(strategy = GenerationType.AUTO)
                  var id: Long = 0)
