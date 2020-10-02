package com.blopesdeveloper.user.app.container

import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.junit.jupiter.Container

@RunWith(SpringRunner::class)
@SpringBootTest
@ContextConfiguration(initializers = [ContainerTestBase.Initializer::class])
@AutoConfigureMockMvc
class ContainerTestBase {

    companion object{
        @Container
        val mongoDBContainer = MongoDBContainer()
    }

    internal class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
            mongoDBContainer.start()

            TestPropertyValues.of(
                    "spring.data.mongodb.host=${mongoDBContainer.containerIpAddress}",
                    "spring.data.mongodb.port=${mongoDBContainer.firstMappedPort}"
            ).applyTo(configurableApplicationContext.environment)
        }
    }

 /*   @DynamicPropertySource
    fun definepropertySource(registry: DynamicPropertyRegistry){
        registry.add("spring.data.mongodb.host", mongoDBContainer::getHost)
        registry.add("spring.data.mongodb.port", mongoDBContainer::getPortBindings)
    }*/
}