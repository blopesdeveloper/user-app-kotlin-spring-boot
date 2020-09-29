package com.blopesdeveloper.user.app.domain.usecase
import com.blopesdeveloper.user.app.domain.entity.User
import com.blopesdeveloper.user.app.domain.exception.NotFoundException
import com.blopesdeveloper.user.app.domain.gateway.UserGateway
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FindAllUseCaseTest {

    @InjectMockKs
    lateinit var target: FindAllUseCase

    @MockK
    lateinit var gateway: UserGateway

    init{
        MockKAnnotations.init(this)
    }

    @Test
    fun `given nothing when call find all should return a list of users`(){
        //When
        every { gateway.findAll() }.returns(listOf(mockk()))

        //Then
        assertNotNull(target.execute())
    }

    @Test
    fun `given nothing when call find all and don't have User should return no content exception`(){
        //When
        every { gateway.findAll() }.returns(ArrayList())

        //Then
        assertThrows<NotFoundException> { target.execute() }
    }

}