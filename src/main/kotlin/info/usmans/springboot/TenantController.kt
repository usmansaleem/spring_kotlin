package info.usmans.springboot

import org.springframework.web.bind.annotation.*

/**
 * Tenant REST Controller.
 *
 * Created by Usman Saleem on 27/11/2016.
 */

@RestController
@RequestMapping("/api/v1/tenant")
class TenantController(val repository: TenantRepository) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id:Long) =
            repository.findOne(id)

    @GetMapping
    fun findAll() = repository.findAll()

    @PostMapping
    fun addTenant(@RequestBody tenant: Tenant ) =
        repository.save(Tenant(tenant.name, tenant.weeklyRentAmount))

}


