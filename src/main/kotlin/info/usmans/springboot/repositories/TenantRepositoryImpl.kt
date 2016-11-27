package info.usmans.springboot.repositories

import info.usmans.springboot.model.Tenant
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.TemporalType


/**
 * TenantRepositoryImpl - For a bit complex queries
 *
 * Created by Usman Saleem on 27/11/2016.
 */

class TenantRepositoryImpl {
    @PersistenceContext
    private val em: EntityManager? = null

    fun findTenantsWithRentReceiptCreatedInLastHours(hours:Int): List<Tenant> {
        //TODO: Check for negative hours ... or use absolute?
        val absHours = Math.abs(hours)
        val c= Calendar.getInstance()
        c.add(Calendar.HOUR, -absHours)
        val pastHours=c.time

        val query = "select t from RentReceipt r, Tenant t where r.createdOn >= :pastHours AND t=r.tenant"
        val q = em?.createQuery(query)
        q?.setParameter("pastHours", pastHours, TemporalType.TIMESTAMP)
        return q?.resultList as List<Tenant>
    }
}