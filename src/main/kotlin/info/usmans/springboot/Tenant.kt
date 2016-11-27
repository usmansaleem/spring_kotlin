package info.usmans.springboot

import java.util.*

/**
 * Tenant
 * Created by Usman Saleem on 27/11/2016.
 */

data class Tenant(var name: String = "",
                  var weeklyRentAmount: Double=0.0,
                  var currentRentPaidToDate: Date = Date(),
                  var currentRentCreditAmount: Double = 0.0,
                  var id: Long = 0)
