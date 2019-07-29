package com.${service_name}.repository;

import com.${service_name}.entity.${entity_name};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ${entity_name}Repository extends JpaRepository<${entity_name}, Long>, JpaSpecificationExecutor<${entity_name}> {

}
