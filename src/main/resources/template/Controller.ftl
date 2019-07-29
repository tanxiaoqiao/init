package com.${service_name}.controller;

import com.${service_name}.annotation.JpaPage;
import com.${service_name}.entity.${entity_name};
import com.${service_name}.service.${entity_name}Service;
import com.${service_name}.util.Pagination;
import com.${service_name}.constant.ErrorEnum;
import com.${service_name}.model.${entity_name}Dto;
import com.${service_name}.util.ResponseObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Api(tags = "${entity_name}")
public class ${entity_name}Controller {

    @Autowired
    ${entity_name}Service ${entity_name_2}Service;


    @GetMapping("/api/${entity_name_2}")
    @JpaPage
    @ApiOperation(value = "分页查询")
    public ResponseObject<Pagination<${entity_name}>> findPage() {
        Page<${entity_name}> pageList = ${entity_name_2}Service.findPage();
        Pagination<${entity_name}> pagination = new Pagination<${entity_name}>((int) pageList.getTotalElements(), pageList.getContent());
        return ResponseObject.success(pagination);
    }

    @ApiOperation(value = "通过ID查找")
    @GetMapping("/api/${entity_name_2}/{id}")
    public ResponseObject findOne(@PathVariable("id") Long id) {
        Optional<${entity_name}> entity = ${entity_name_2}Service.findById(id);
        if (entity.isPresent()) {
            return ResponseObject.success(${entity_name_2}Service.toDto(entity.get()));
        }
        return ResponseObject.fail(ErrorEnum.NOT_FOUND);
    }

    @PostMapping("/api/${entity_name_2}")
    @ApiOperation(value = "保存实体")
    public ResponseObject save(@ApiParam @Validated @RequestBody ${entity_name}Dto dto) {
        ${entity_name} entity = ${entity_name_2}Service.toEntity(dto);

        ${entity_name_2}Service.save(entity);
        return ResponseObject.success("OK");
    }


    @PatchMapping("/api/${entity_name_2}")
    @ApiOperation(value = "部分更新")
    public ResponseObject patchUpdate(@ApiParam @RequestBody ${entity_name}Dto dto) {
        Optional<${entity_name}> op = ${entity_name_2}Service.findById(dto.getId());
        if (op.isPresent()) {
            ${entity_name} entity = op.get();
            BeanUtils.copyProperties(dto, entity);
            ${entity_name_2}Service.save(entity);
            return ResponseObject.success("OK");
        }
        return ResponseObject.fail(ErrorEnum.UPDATE_ERROR);
    }

    @ApiOperation(value = "删除单个实体")
    @DeleteMapping("/api/${entity_name_2}/{id}")
    public ResponseObject<String> delete(@PathVariable("id") Long id) {
        ${entity_name_2}Service.deleteById(id);
        return ResponseObject.success("OK");
    }
}
