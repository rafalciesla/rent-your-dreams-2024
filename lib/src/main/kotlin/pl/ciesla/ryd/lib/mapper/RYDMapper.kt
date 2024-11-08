package pl.ciesla.ryd.lib.mapper

import pl.ciesla.ryd.lib.model.RYDEntity
import pl.ciesla.ryd.lib.web.dto.RYDDTO

interface RYDMapper<ENTITY : RYDEntity, DTO : RYDDTO> {

    fun dtoToEntity(dto: DTO): ENTITY
    fun entityToDTO(entity: ENTITY): DTO
    fun dtoToEntityList(dtos: List<DTO>): List<ENTITY>
    fun entityToDTOList(entities: List<ENTITY>): List<DTO>
}