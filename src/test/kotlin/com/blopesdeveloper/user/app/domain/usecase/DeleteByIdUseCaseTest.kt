package com.blopesdeveloper.user.app.domain.usecase

import com.blopesdeveloper.user.app.domain.exception.NotFoundException
import com.blopesdeveloper.user.app.domain.gateway.UserGateway
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

import java.util.*

class DeleteByIdUseCaseTest {

    @InjectMockKs
    lateinit var target: DeleteByIdUseCase

    @MockK
    lateinit var gateway: UserGateway

    init {
        MockKAnnotations.init(this)
    }

    @Test
    fun `given id when call delete by id should delete user`(){
        //Given
        val id = UUID.randomUUID().toString()

        //When
        every { gateway.deleteById(eq(id)) }.returns(Unit)

        //Then
        target.execute(id)
        verify { gateway.deleteById(eq(id)) }
    }

    @Test
    fun `given id when call delete not found should throw notFoundException`(){
        //Given
        val id = UUID.randomUUID().toString()

        //When
        every { gateway.deleteById(eq(id)) }.throws(NotFoundException("test"))

        //Then
        assertThrows<NotFoundException> { target.execute(id)}

    }

}