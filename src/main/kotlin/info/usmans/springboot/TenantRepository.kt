package info.usmans.springboot

import org.springframework.data.repository.CrudRepository

/**
 * Customer Repository
 * Created by Usman Saleem on 27/11/2016.
 */

interface TenantRepository : CrudRepository<Tenant, Long>{

}
