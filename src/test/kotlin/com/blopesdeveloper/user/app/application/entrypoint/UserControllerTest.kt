package com.blopesdeveloper.user.app.application.entrypoint

import com.blopesdeveloper.user.app.application.entrypoint.entity.TypeApi
import com.blopesdeveloper.user.app.application.entrypoint.entity.UserApi
import com.blopesdeveloper.user.app.application.entrypoint.mapper.UserMapper
import com.blopesdeveloper.user.app.domain.entity.Type
import com.blopesdeveloper.user.app.domain.entity.User
import com.blopesdeveloper.user.app.domain.exception.AlreadyExistsException
import com.blopesdeveloper.user.app.domain.exception.NotFoundException
import com.blopesdeveloper.user.app.domain.usecase.DeleteByIdUseCase
import com.blopesdeveloper.user.app.domain.usecase.FindAllUseCase
import com.blopesdeveloper.user.app.domain.usecase.FindByIdUseCase
import com.blopesdeveloper.user.app.domain.usecase.SaveUseCase
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*
import org.mockito.Mockito.`when` as every

@ExtendWith(SpringExtension::class)
@WebMvcTest(UserController::class)
class UserControllerTest {

    @Autowired
    private lateinit var mockMvcTest: MockMvc

    @MockBean
    private lateinit var findAllUseCase: FindAllUseCase

    @MockBean
    private lateinit var saveUseCase: SaveUseCase

    @MockBean
    private lateinit var findByIdUseCase: FindByIdUseCase

    @MockBean
    private lateinit var deleteByIdUseCase: DeleteByIdUseCase

    @Autowired
    private lateinit var mapper: ObjectMapper

    private val userMapper = Mappers.getMapper(UserMapper::class.java)


    @Test
    fun `given users when call find all should return list of users`() {
        //Given
        val user = User(id = UUID.randomUUID().toString(), email = "admin@admin.com", type = Type.ADMIN, password = UUID.randomUUID().toString())
        val users = Collections.singletonList(user)

        //When
        every(findAllUseCase.execute()).thenReturn(users)

        //Then
        mockMvcTest.perform(get("/user"))
                .andExpect(status().isOk)

    }

    @Test
    fun `given nothing when call find all and there are nothing should return not found `() {
        //When
        every(findAllUseCase.execute()).thenThrow(NotFoundException("test"))

        //Then
        mockMvcTest.perform(get("/user"))
                .andExpect(status().isNotFound)
    }

    @Test
    fun `given user when call save should save and return user`() {
        //Given
        val userApi = UserApi(id = UUID.randomUUID().toString(), email = "admin@admin.com", type = TypeApi.ADMIN, password = UUID.randomUUID().toString())
        val user = userMapper.map(userApi)

        //When
        every(saveUseCase.execute(user)).thenReturn(user)

        //Then
        mockMvcTest.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(userApi)))
                .andExpect(status().isCreated)

    }

    @Test
    fun `given user already exists when call save should return bad request`() {
        //Given
        val userApi = UserApi(id = UUID.randomUUID().toString(), email = "admin@admin.com", type = TypeApi.ADMIN, password = UUID.randomUUID().toString())
        val user = userMapper.map(userApi)

        //When
        every(saveUseCase.execute(user)).thenThrow(AlreadyExistsException("already exists"))

        //Then
        mockMvcTest.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(userApi)))
                .andExpect(status().isBadRequest)

    }

    @Test
    fun `given id when call find by id should return user`() {
        //Given
        val id = UUID.randomUUID().toString()
        val user = User(id = UUID.randomUUID().toString(), email = "admin@admin.com", type = Type.ADMIN, password = UUID.randomUUID().toString())

        //When
        every(findByIdUseCase.execute(id)).thenReturn(user)

        //Then
        mockMvcTest.perform(get("/user/{id}", id))
                .andExpect(status().isOk)
    }

    @Test
    fun `given id when call find by id that not exists should return not found status`() {
        //Given
        val id = UUID.randomUUID().toString()

        //When
        every(findByIdUseCase.execute(id)).thenThrow(NotFoundException(""))

        //Then
        mockMvcTest.perform(get("/user/{id}", id))
                .andExpect(status().isNotFound)
    }

    @Test
    fun `given id when call delete by id should return ok`() {
        //Given
        val id = UUID.randomUUID().toString()

        //Then
        mockMvcTest.perform(get("/user/{id}", id))
                .andExpect(status().isOk)
    }

}