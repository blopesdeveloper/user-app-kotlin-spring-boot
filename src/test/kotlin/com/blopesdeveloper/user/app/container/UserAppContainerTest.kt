package com.blopesdeveloper.user.app.container


import com.blopesdeveloper.user.app.application.entrypoint.entity.TypeApi
import com.blopesdeveloper.user.app.application.entrypoint.entity.UserApi
import com.blopesdeveloper.user.app.application.gateway.UserRepository
import com.blopesdeveloper.user.app.container.ContainerTestBase
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

class UserAppContainerTest: ContainerTestBase() {

    @Autowired
    lateinit var  mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var userRepository: UserRepository


    @Test
    fun `given userAPi when call save should save user on db`(){
        //given
        val userApi = UserApi(id = UUID.randomUUID().toString(), email = "admin@admin.com", type = TypeApi.ADMIN, password = UUID.randomUUID().toString())

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userApi)))
                .andExpect(MockMvcResultMatchers.status().isCreated)

        //then
        val result = userRepository.findById(userApi.id).get()
        assertNotNull(result)
    }

}