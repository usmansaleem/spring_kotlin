package info.usmans.springboot.repositories

import info.usmans.springboot.model.RentReceipt
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

/**
 * Rent Receipt Repository
 * Created by Usman Saleem on 27/11/2016.
 */

interface RentReceiptRepository : CrudRepository<RentReceipt, Long> {
    @Query("select r from RentReceipt r where r.tenant.id = :tenantId")
    fun findRentReceiptsForTenant(@Param("tenantId") tenantId: Long): List<RentReceipt>
}