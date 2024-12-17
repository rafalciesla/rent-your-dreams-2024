package pl.ciesla.ryd.lib.web.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.ciesla.ryd.lib.model.RYDEntity
import pl.ciesla.ryd.lib.service.RYDService
import pl.ciesla.ryd.lib.web.dto.RYDDTO

abstract class RYDController<ENTITY : RYDEntity, DTO : RYDDTO, SERVICE : RYDService<ENTITY, DTO>>(
    protected val service: SERVICE
) {

    val logger = KotlinLogging.logger {}

    @GetMapping
    fun findAll(): ResponseEntity<List<DTO>> {
        logger.info { "Finding all entities START" }
        val allEntities = service.findAll()
        logger.info { "Finding all entities END" }
        return ResponseEntity.ok(allEntities)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long): ResponseEntity<DTO> {
        val car = service.getById(id)
        return ResponseEntity.ok(car)
    }

    @PostMapping
    fun create(@RequestBody dto: DTO): ResponseEntity<DTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody dto: DTO): ResponseEntity<DTO> {
        return ResponseEntity.ok(service.update(id, dto))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}