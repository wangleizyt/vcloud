package cn.com.vcloud.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.vcloud.admin.core.common.SysLog;
import cn.com.vcloud.admin.model.entity.SysRole;
import cn.com.vcloud.admin.service.RoleService;
import cn.com.vcloud.core.response.Result;
import cn.com.vcloud.core.response.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @className: RoleController
 * @description: 角色管理类
 * @author zhanghaifeng
 * @dateTime 2019年3月20日 
 */
@Api(value="角色管理",tags={"角色管理"})
@RestController
@RequestMapping("/v1/system/role")
@Validated
public class RoleController {

	@Resource
    private RoleService roleService;
	
    @PreAuthorize("hasAuthority('system:role:list')")
	@ApiOperation(value = "获取所有角色信息", notes = "角色信息")
    @ApiImplicitParam(name = "user", value = "信息", required = true)
	@SysLog(module = "角色", action = "列表")
    @GetMapping
    public Result list() {
       
        final List<SysRole> list = this.roleService.findAllRoleWithPermission();
        
        return ResultGenerator.genOkResult(list);
    }
}
