package info.usmans.springboot.util

import info.usmans.springboot.model.RentReceipt
import info.usmans.springboot.model.Tenant
import java.util.*

/**
 * Created by Usman Saleem on 27/11/2016.
 */

/**
 * Creates a rent receipt for a tenant.
 * RestReceipt and enclosing Tenant needs to be updated in database.
 */
fun createRentReceiptForTenant(tenant: Tenant, paidAmount: Double): RentReceipt {
    val workingTenant = tenant.copy()
    var adjustedPaidAmount = paidAmount
    var credit: Double

    do {
        credit = workingTenant.weeklyRentAmount - workingTenant.currentRentCreditAmount - adjustedPaidAmount
        workingTenant.currentRentCreditAmount = credit.unaryMinus()
        if(workingTenant.currentRentCreditAmount == -0.0) {
            workingTenant.currentRentCreditAmount = 0.0
        }
        adjustedPaidAmount = 0.0

        val c = Calendar.getInstance()
        c.time = workingTenant.currentRentPaidToDate
        c.add(Calendar.DATE, 7)
        workingTenant.currentRentPaidToDate = c.time
    } while (workingTenant.currentRentCreditAmount > 0.0 && workingTenant.currentRentCreditAmount >= workingTenant.weeklyRentAmount)

     val rentReceipt = RentReceipt(paidAmount)
    rentReceipt.tenant = workingTenant

    return rentReceipt
}