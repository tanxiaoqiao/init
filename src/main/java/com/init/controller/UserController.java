package com.init.controller;

import com.init.annotation.JpaPage;
import com.init.entity.User;
import com.init.service.UserService;
import com.init.utils.Pagination;
import com.init.constant.ErrorEnum;
import com.init.model.UserDto;
import com.init.utils.ResponseObject;
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
@Api(tags = "User")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("/api/user")
    @JpaPage
    @ApiOperation(value = "分页查询")
    public ResponseObject<Pagination<User>> findPage() {
        Page<User> pageList = userService.findPage();
        Pagination<User> pagination = new Pagination<User>((int) pageList.getTotalElements(), pageList.getContent());
        return ResponseObject.success(pagination);
    }

    @ApiOperation(value = "通过ID查找")
    @GetMapping("/api/user/{id}")
    public ResponseObject findOne(@PathVariable("id") Long id) {
        Optional<User> entity = userService.findById(id);
        if (entity.isPresent()) {
            return ResponseObject.success(userService.toDto(entity.get()));
        }
        return ResponseObject.fail(ErrorEnum.PARAMS_ERROR);
    }

    @PostMapping("/api/user")
    @ApiOperation(value = "保存实体")
    public ResponseObject save(@ApiParam @Validated @RequestBody UserDto dto) {
        User entity = userService.toEntity(dto);

        userService.save(entity);
        return ResponseObject.success("OK");
    }


    @PatchMapping("/api/user")
    @ApiOperation(value = "部分更新")
    public ResponseObject patchUpdate(@ApiParam @RequestBody UserDto dto) {
        Optional<User> op = userService.findById(dto.getId());
        if (op.isPresent()) {
            User entity = op.get();
            BeanUtils.copyProperties(dto, entity);
            userService.save(entity);
            return ResponseObject.success("OK");
        }
        return ResponseObject.fail(ErrorEnum.SERVER_ERROR);
    }

    @ApiOperation(value = "删除单个实体")
    @DeleteMapping("/api/user/{id}")
    public ResponseObject<String> delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseObject.success("OK");
    }
}
