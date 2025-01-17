package pl.ciesla.ryd.users.core.domain.exception

import org.springframework.http.HttpStatus
import pl.ciesla.ryd.lib.exception.RydException

class UserAlreadyExistsException(email: String)
    : RydException("User with email [$email] already exists", HttpStatus.CONFLICT)