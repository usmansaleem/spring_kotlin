package info.usmans.springboot

import java.util.*

/**
 * Rent Receipt
 * Created by Usman Saleem on 27/11/2016.
 */
class RentReceipt(var amount: Double=0.0, var tenant: Tenant, var id: Long=0)

/**
 * Creates a rent receipt for a tenant. Returns a pair of RentReceipt and tenant.
 * RestReceipt needs to be persisted in database. Tenant needs to be updated in database.
 */
fun createRentReceiptForTenant(tenant: Tenant, paidAmount: Double): RentReceipt {
    val amountDue = tenant.weeklyRentAmount - tenant.currentRentCreditAmount //300 - 50 (250)
    val currentRentCreditAmount = paidAmount - amountDue //275 - 250 (25)

    //advanced date by 7 days
    val c = Calendar.getInstance()
    c.time = tenant.currentRentPaidToDate
    c.add(Calendar.DATE, 7)

    val updatedTenant = tenant.copy(currentRentPaidToDate = c.time,
            currentRentCreditAmount = currentRentCreditAmount)

    return RentReceipt(amountDue, updatedTenant)
}
