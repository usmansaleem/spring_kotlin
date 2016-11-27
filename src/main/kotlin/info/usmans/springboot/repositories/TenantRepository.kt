package info.usmans.springboot.repositories

import info.usmans.springboot.model.Tenant
import org.springframework.data.repository.CrudRepository

/**
 * Customer Repository
 * Created by Usman Saleem on 27/11/2016.
 */

interface TenantRepository : CrudRepository<Tenant, Long> {
    fun findTenantsWithRentReceiptCreatedInLastHours(hours:Int): List<Tenant>
}
