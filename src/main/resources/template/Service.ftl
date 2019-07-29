package com.${service_name}.service;

import com.${service_name}.entity.${entity_name};
import com.${service_name}.model.${entity_name}Dto;


public interface ${entity_name}Service extends BaseService<${entity_name}> {

    ${entity_name} toEntity(${entity_name}Dto dto);

    ${entity_name}Dto toDto(${entity_name} entity);
}
