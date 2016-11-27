package info.usmans.springboot.rest

import info.usmans.springboot.model.Tenant
import info.usmans.springboot.repositories.RentReceiptRepository
import info.usmans.springboot.repositories.TenantRepository
import info.usmans.springboot.util.createRentReceiptForTenant
import org.springframework.web.bind.annotation.*

/**
 * Tenant REST Controller.
 *
 * Created by Usman Saleem on 27/11/2016.
 */

@RestController
@RequestMapping("/api/v1/tenant")
class TenantController(val tenantRepository: TenantRepository, val rentReceiptRepository: RentReceiptRepository) {

    @GetMapping
    fun findAll() = tenantRepository.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) =
            tenantRepository.findOne(id)

    @PostMapping
    fun addTenant(@RequestBody tenant: TenantRequest) =
            tenantRepository.save(Tenant(tenant.name, tenant.weeklyRentAmount))

    @PostMapping("/{id}/rentreceipts")
    fun createRentReceipt(@PathVariable id: Long, @RequestBody rentReceiptRequest: RentReceiptRequest) {
        val tenant = tenantRepository.findOne(id)
        val rentReceipt = createRentReceiptForTenant(tenant, rentReceiptRequest.amount)

        rentReceiptRepository.save(rentReceipt)
        tenantRepository.save(rentReceipt.tenant)
    }

    @GetMapping("/{id}/rentreceipts")
    fun findRentReceiptsForTenant(@PathVariable id: Long) =
            rentReceiptRepository.findRentReceiptsForTenant(id)

    @GetMapping("/rentreceipts")
    fun findTenant(@RequestParam(value="hours", required = true) hours: Int ) =
            tenantRepository.findTenantsWithRentReceiptCreatedInLastHours(hours)

    data class RentReceiptRequest(val amount: Double = 0.0)
    data class TenantRequest(val name: String = "", val weeklyRentAmount: Double=0.0)
}



