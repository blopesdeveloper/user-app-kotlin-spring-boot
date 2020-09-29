package com.blopesdeveloper.user.app.domain.exception

import java.lang.RuntimeException

class AlreadyExistsException(val newMessage: String): RuntimeException()