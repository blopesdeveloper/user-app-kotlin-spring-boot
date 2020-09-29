package com.blopesdeveloper.user.app.application.gateway

import com.blopesdeveloper.user.app.application.gateway.entity.TypeDB
import com.blopesdeveloper.user.app.application.gateway.entity.UserDB
import com.blopesdeveloper.user.app.domain.entity.Type
import com.blopesdeveloper.user.app.domain.entity.User
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.util.*

class UserGatewayTest {

    @InjectMockKs
    private lateinit var target: UserGatewayImpl

    @MockK
    private lateinit var userRepository: UserRepository


    init {
        MockKAnnotations.init(this)
    }

    @Test
    fun `given id when call find by id should return user`() {
        //Given
        val randomPassword = UUID.randomUUID().toString()
        val randomId = UUID.randomUUID().toString()
        val userDB = UserDB(id = randomId, email = "admin@admin.com", type = TypeDB.ADMIN, password = randomPassword)
        val user = User(id = randomId, email = "admin@admin.com", type = Type.ADMIN, password = randomPassword)

        //When
        every { userRepository.findById(randomId) }.returns(Optional.of(userDB))

        //Then
        val result = target.findById(randomId)

        assertEquals(result, user)

        verify { userRepository.findById(eq(randomId)) }
    }

    @Test
    fun `given id when call find by id and not found item should return null`() {
        //Given
        val randomId = UUID.randomUUID().toString()

        //When
        every { userRepository.findById(randomId) }.returns(Optional.ofNullable(null))

        //Then
        val result = target.findById(randomId)

        assertNull(result)

        verify { userRepository.findById(eq(randomId)) }
    }

    @Test
    fun `given user when call find all should return list of users`() {
        //Given
        val randomPassword = UUID.randomUUID().toString()
        val randomId = UUID.randomUUID().toString()
        val userDB = UserDB(id = randomId, email = "admin@admin.com", type = TypeDB.ADMIN, password = randomPassword)
        val user = User(id = randomId, email = "admin@admin.com", type = Type.ADMIN, password = randomPassword)

        //When
        every { userRepository.findAll() }.returns(Collections.singletonList(userDB))

        //Then
        val result = target.findAll()

        assertEquals(result[0], user)
        verify { userRepository.findAll() }
    }

    @Test
    fun `given nothing when call find all and there is nothing should return empty list`() {

        //When
        every { userRepository.findAll() }.returns(Collections.emptyList())

        //Then
        val result = target.findAll()

        assertEquals(result.size, 0)
        verify { userRepository.findAll() }
    }

    @Test
    fun `given id when call delete by id should call delete`() {
        //Given
        val randomId = UUID.randomUUID().toString()

        //When
        every { userRepository.deleteById(randomId) }.returns(Unit)

        //Then
        target.deleteById(randomId)

        verify { userRepository.deleteById(eq(randomId)) }
    }

    @Test
    fun `given user when call save should return saved user`() {
        //Given
        val randomPassword = UUID.randomUUID().toString()
        val randomId = UUID.randomUUID().toString()
        val userDB = UserDB(id = randomId, email = "admin@admin.com", type = TypeDB.ADMIN, password = randomPassword)
        val user = User(id = randomId, email = "admin@admin.com", type = Type.ADMIN, password = randomPassword)

        //When
        every { userRepository.save(userDB) }.returns(userDB)

        //Then
        val result = target.save(user)

        assertEquals(result, user)
        verify { userRepository.save(eq(userDB)) }
    }


}