package pl.ciesla.ryd.lib.service

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import pl.ciesla.ryd.lib.exception.EntityNotFoundException
import pl.ciesla.ryd.lib.mapper.RYDMapper
import pl.ciesla.ryd.lib.model.RYDEntity
import pl.ciesla.ryd.lib.web.dto.RYDDTO

abstract class RYDService<ENTITY : RYDEntity, DTO : RYDDTO>(
    open val repository: JpaRepository<ENTITY, Long>,
    open val mapper: RYDMapper<ENTITY, DTO>
) {

    fun findAll(): List<DTO> = mapper.entityToDTOList(repository.findAll())

    fun getById(id: Long): DTO {
        var entity = repository.findByIdOrNull(id) ?: throw EntityNotFoundException()
        return mapper.entityToDTO(entity)
    }

    fun create(dto: DTO): DTO {
        var entity = repository.save(mapper.dtoToEntity(dto))
        return mapper.entityToDTO(entity)
    }

    fun update(id: Long, dto: DTO): DTO {
        if (!repository.existsById(id)) {
            throw EntityNotFoundException()
        }
        dto.id = id
        var entity = repository.save(mapper.dtoToEntity(dto))
        return mapper.entityToDTO(entity)
    }

    fun delete(id: Long) {
        if (!repository.existsById(id)) {
            throw EntityNotFoundException()
        }
        repository.deleteById(id)
    }
}