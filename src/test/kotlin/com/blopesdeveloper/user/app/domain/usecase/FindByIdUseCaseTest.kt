package com.blopesdeveloper.user.app.domain.usecase

import com.blopesdeveloper.user.app.domain.exception.NotFoundException
import com.blopesdeveloper.user.app.domain.gateway.UserGateway
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class FindByIdUseCaseTest {

    @InjectMockKs
    lateinit var target: FindByIdUseCase

    @MockK
    lateinit var gateway: UserGateway

    init {
        MockKAnnotations.init(this)
    }

    @Test
    fun `given id when call find by id should return user`() {
        //Given
        val id = UUID.randomUUID().toString()

        //When
        every { gateway.findById(id) }.returns(mockk())

        //Then
        val result = target.execute(id)
        assertNotNull(result)
        verify { gateway.findById(eq(id)) }
    }

    @Test
    fun `given id when call find by id not found should return NotFoundException`() {
        //Given
        val id = UUID.randomUUID().toString()

        //When
        every { gateway.findById(id) }.returns(null)

        //Then
        assertThrows<NotFoundException>{ target.execute(id) }

        verify { gateway.findById(eq(id)) }
    }

}