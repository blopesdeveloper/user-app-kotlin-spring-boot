package com.blopesdeveloper.user.app.domain.exception

import java.lang.RuntimeException

class NoContentException(val newMessage: String): RuntimeException()