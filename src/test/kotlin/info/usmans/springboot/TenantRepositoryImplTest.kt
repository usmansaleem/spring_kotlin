package info.usmans.springboot

import info.usmans.springboot.model.Tenant
import info.usmans.springboot.repositories.RentReceiptRepository
import info.usmans.springboot.repositories.TenantRepository
import info.usmans.springboot.util.createRentReceiptForTenant
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*


/**
 * Created by Usman Saleem on 27/11/2016.
 */

@RunWith(SpringRunner::class)
@DataJpaTest
open class TenantRepositoryImplTest {

    @Autowired
    lateinit var tenantRepository: TenantRepository

    @Autowired
    lateinit var rentReceiptRepository: RentReceiptRepository

    @Test fun testFindTenantsWithRentReceiptInGivenHour() {
        val tenant1 = Tenant(name = "test", weeklyRentAmount = 300.0, currentRentCreditAmount = 50.0)
        val tenant2 = Tenant(name = "test", weeklyRentAmount = 300.0, currentRentCreditAmount = 50.0)
        tenantRepository.save(tenant1)
        tenantRepository.save(tenant2)

        //2 hours ago ...
        val rentReceipt = createRentReceiptForTenant(tenant1, 275.0)
        val cal = Calendar.getInstance()
        cal.time = rentReceipt.createdOn
        cal.add(Calendar.HOUR, -2)
        rentReceipt.createdOn = cal.time;
        rentReceiptRepository.save(rentReceipt)
        tenantRepository.save(rentReceipt.tenant)

        // now
        val rentReceipt2 = createRentReceiptForTenant(tenant2, 275.0)
        rentReceiptRepository.save(rentReceipt2)
        tenantRepository.save(rentReceipt2.tenant)


        val tenantList = tenantRepository.findTenantsWithRentReceiptCreatedInLastHours(1)


        Assert.assertEquals(1, tenantList.size)
        Assert.assertEquals(tenant2.id, tenantList.iterator().next().id)
    }

    @Test fun testFindRentReceiptForGivenTenant() {
        val tenant1 = Tenant(name = "test", weeklyRentAmount = 300.0, currentRentCreditAmount = 50.0)
        val tenant2 = Tenant(name = "test", weeklyRentAmount = 300.0, currentRentCreditAmount = 50.0)
        tenantRepository.save(tenant1)
        tenantRepository.save(tenant2)

        //2 hours ago ...
        val rentReceipt = createRentReceiptForTenant(tenant1, 275.0)
        val cal = Calendar.getInstance()
        cal.time = rentReceipt.createdOn
        cal.add(Calendar.HOUR, -2)
        rentReceipt.createdOn = cal.time;
        rentReceiptRepository.save(rentReceipt)
        tenantRepository.save(rentReceipt.tenant)

        // now
        val rentReceipt2 = createRentReceiptForTenant(tenant2, 300.0)
        rentReceiptRepository.save(rentReceipt2)
        tenantRepository.save(rentReceipt2.tenant)

        // now
        val rentReceipt3 = createRentReceiptForTenant(tenant2, 350.0)
        rentReceiptRepository.save(rentReceipt3)
        tenantRepository.save(rentReceipt2.tenant)

        val tenantList = rentReceiptRepository.findRentReceiptsForTenant(tenant2.id)


        Assert.assertEquals(2, tenantList.size)
        tenantList.forEach { Assert.assertEquals(tenant2.id, it.tenant.id) }
    }

}
