package pl.ciesla.ryd.lib.web.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.ciesla.ryd.lib.mapper.RYDMapper
import pl.ciesla.ryd.lib.model.RYDEntity
import pl.ciesla.ryd.lib.service.RYDService
import pl.ciesla.ryd.lib.web.dto.RYDDTO

abstract class RYDController<ENTITY : RYDEntity, DTO : RYDDTO>(
    protected val service: RYDService<ENTITY, DTO>
) {

    @GetMapping
    fun findAll(): ResponseEntity<List<DTO>> {
        return ResponseEntity(service.findAll(), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long): ResponseEntity<DTO> {
        val car = service.getById(id)
        return ResponseEntity(car, HttpStatus.OK)
    }

    @PostMapping
    fun create(@RequestBody dto: DTO): ResponseEntity<DTO> {
        return ResponseEntity(service.create(dto), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody dto: DTO): ResponseEntity<DTO> {
        return ResponseEntity(service.update(id, dto), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Any> {
        service.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}