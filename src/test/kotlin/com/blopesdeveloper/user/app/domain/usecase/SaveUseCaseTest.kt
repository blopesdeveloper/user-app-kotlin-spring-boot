package com.blopesdeveloper.user.app.domain.usecase

import com.blopesdeveloper.user.app.domain.entity.User
import com.blopesdeveloper.user.app.domain.exception.AlreadyExistsException
import com.blopesdeveloper.user.app.domain.gateway.UserGateway
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SaveUseCaseTest {

    @InjectMockKs
    lateinit var target: SaveUseCase

    @MockK
    lateinit var userGateway: UserGateway

    init{
        MockKAnnotations.init(this)
    }

    @Test
    fun `given user when call save should save user by gateway`(){
        // Given
        val user = mockk<User>()

        //When
        every { userGateway.save(eq(user))}.returns(user)

        //Then
        val result = target.execute(user)
        assertEquals(result, user)
        verify { userGateway.save(eq(user)) }

    }

    @Test
    fun `given user when call and already exist should return AlreadyExistsException`(){
        //Given
        val user = mockk<User>()

        //When
        every { userGateway.save(user) }.throws(AlreadyExistsException("there is an user with this id"))

        //Then
        assertThrows<AlreadyExistsException>{ target.execute(user) }

    }

}