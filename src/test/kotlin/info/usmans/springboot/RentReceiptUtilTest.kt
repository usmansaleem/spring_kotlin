package info.usmans.springboot

import info.usmans.springboot.model.Tenant
import info.usmans.springboot.util.createRentReceiptForTenant
import org.junit.Assert
import org.junit.Test
import java.util.*
import org.hamcrest.CoreMatchers.`is` as Is

/**
 * Created by Usman Saleem on 27/11/2016.
 */
class RentReceiptUtilTest {
    @Test fun testCreateRentReceiptForTenantFullPayment() {
        val tenant = Tenant(name="test", weeklyRentAmount = 300.0)
        val rentReceipt = createRentReceiptForTenant(tenant, 300.0)
        Assert.assertThat(rentReceipt.amount, Is(300.0))
        Assert.assertThat(rentReceipt.tenant.currentRentCreditAmount, Is(0.0))
    }

    @Test fun testCreateRentReceiptDateAdvanced() {
        val tenant = Tenant(name="test", weeklyRentAmount = 300.0)
        val rentReceipt = createRentReceiptForTenant(tenant, 300.0)
        val expectedCal = Calendar.getInstance()
        expectedCal.add(Calendar.DATE, 7)


        val actualCal = Calendar.getInstance()
        actualCal.time = rentReceipt.tenant.currentRentPaidToDate

        Assert.assertThat(actualCal.get(Calendar.YEAR), Is(expectedCal.get(Calendar.YEAR)))
        Assert.assertThat(actualCal.get(Calendar.MONTH), Is(expectedCal.get(Calendar.MONTH)))
        Assert.assertThat(actualCal.get(Calendar.DAY_OF_MONTH), Is(expectedCal.get(Calendar.DAY_OF_MONTH)))
    }

    @Test fun testCreateRentReceiptForTenantRentCredit() {
        val tenant = Tenant(name="test", weeklyRentAmount = 300.0, currentRentCreditAmount = 50.0)
        val rentReceipt = createRentReceiptForTenant(tenant, 275.0)
        Assert.assertThat(rentReceipt.amount, Is(275.0))
        Assert.assertThat(rentReceipt.tenant.currentRentCreditAmount, Is(25.0))
    }

    @Test fun testCreateRentTwoWeeksWithCredit() {
        val tenant = Tenant(name="test", weeklyRentAmount = 300.0, currentRentCreditAmount = 0.0)
        val rentReceipt = createRentReceiptForTenant(tenant, 650.0)
        Assert.assertThat(rentReceipt.amount, Is(650.0))
        Assert.assertThat(rentReceipt.tenant.currentRentCreditAmount, Is(50.0))

        val expectedCal = Calendar.getInstance()
        expectedCal.add(Calendar.DATE, 14)

        val actualCal = Calendar.getInstance()
        actualCal.time = rentReceipt.tenant.currentRentPaidToDate

        Assert.assertThat(actualCal.get(Calendar.YEAR), Is(expectedCal.get(Calendar.YEAR)))
        Assert.assertThat(actualCal.get(Calendar.MONTH), Is(expectedCal.get(Calendar.MONTH)))
        Assert.assertThat(actualCal.get(Calendar.DAY_OF_MONTH), Is(expectedCal.get(Calendar.DAY_OF_MONTH)))

    }

    @Test fun testCreateRentThreeWeeksWithZeroCredit() {
        val tenant = Tenant(name="test", weeklyRentAmount = 300.0, currentRentCreditAmount = 50.0)
        val rentReceipt = createRentReceiptForTenant(tenant, 850.0)
        Assert.assertThat(rentReceipt.amount, Is(850.0))
        Assert.assertThat(rentReceipt.tenant.currentRentCreditAmount, Is(0.0))

        val expectedCal = Calendar.getInstance()
        expectedCal.add(Calendar.DATE, 21)

        val actualCal = Calendar.getInstance()
        actualCal.time = rentReceipt.tenant.currentRentPaidToDate

        Assert.assertThat(actualCal.get(Calendar.YEAR), Is(expectedCal.get(Calendar.YEAR)))
        Assert.assertThat(actualCal.get(Calendar.MONTH), Is(expectedCal.get(Calendar.MONTH)))
        Assert.assertThat(actualCal.get(Calendar.DAY_OF_MONTH), Is(expectedCal.get(Calendar.DAY_OF_MONTH)))
    }
}