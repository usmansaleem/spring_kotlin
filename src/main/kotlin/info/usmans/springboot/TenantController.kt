package info.usmans.springboot

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Tenant REST Controller.
 *
 * Created by Usman Saleem on 27/11/2016.
 */

@RestController
@RequestMapping("/api/v1/tenant")
class TenantController {

    @GetMapping("/{id}")
    fun findById(@PathVariable id:Long) =
            Tenant(name="test" + id, weeklyRentAmount = 350.0, id=id)

}
