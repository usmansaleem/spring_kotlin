package info.usmans.springboot

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

/**
 * Default entry point for SpringBoot applications
 */
@SpringBootApplication
open class Application {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }
    }

    private val log = LoggerFactory.getLogger(Application::class.java)

    @Bean
    open fun init(repository: TenantRepository) = CommandLineRunner {
        // save a couple of customers
        repository.save(Tenant("Jack", 300.0))
        repository.save(Tenant("Chloe", 350.0))

        // fetch all customers
        log.info("Tenants found with findAll():")
        log.info("-------------------------------")
        for (customer in repository.findAll()) {
            log.info(customer.toString())
        }
        log.info("")

    }
}
