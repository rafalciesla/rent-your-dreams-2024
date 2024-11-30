package pl.ciesla.ryd.lib.exception

import org.springframework.http.HttpStatus

class EntityNotFoundException: RYDException("Entity not found", HttpStatus.NOT_FOUND)