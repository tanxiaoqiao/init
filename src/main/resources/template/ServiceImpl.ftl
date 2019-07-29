package com.${service_name}.service.impl;

import com.${service_name}.entity.${entity_name};
import com.${service_name}.repository.${entity_name}Repository;
import com.${service_name}.service.${entity_name}Service;
import com.${service_name}.util.JpaUtils;
import com.${service_name}.model.${entity_name}Dto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ${entity_name}ServiceImpl implements ${entity_name}Service {

    @Autowired
    ${entity_name}Repository ${entity_name_2}Rep;

    @Override
    public void save(${entity_name} entity) {
        ${entity_name_2}Rep.save(entity);
    }

    @Override
    public void delete(${entity_name} entity) {
        ${entity_name_2}Rep.delete(entity);
    }

    @Override
    public void deleteById(Long id) {
        ${entity_name_2}Rep.deleteById(id);
    }

    @Override
    public Optional<${entity_name}> findById(Long id) {
        return ${entity_name_2}Rep.findById(id);
    }

    /**
     * 分页查询
     *
     * @return
     */
    @Override
    public Page<${entity_name}> findPage() {
        Page<${entity_name}> entityPage = ${entity_name_2}Rep.findAll(JpaUtils.getPageRequest());
        return entityPage;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @Override
    public Page<${entity_name}> findPage(Specification<${entity_name}> specification) {
        Page<${entity_name}> entityPage = ${entity_name_2}Rep.findAll(specification, JpaUtils.getPageRequest());
        return entityPage;
    }

    @Override
    public ${entity_name} toEntity(${entity_name}Dto dto) {
        ${entity_name} entity = new ${entity_name}();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    @Override
    public ${entity_name}Dto toDto(${entity_name} entity) {
        ${entity_name}Dto dto = new ${entity_name}Dto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
