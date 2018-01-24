package com.bamboo.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bamboo.common.controller.BaseController;
import com.bamboo.common.domain.Tree;
import com.bamboo.common.utils.R;
import com.bamboo.sys.domain.Dept;
import com.bamboo.sys.service.DeptService;

/**
 * 部门管理
 * 
 */
 
@Controller
@RequestMapping("/sys/dept")
public class DeptController extends BaseController {
	
	private static String  prefix= "sys/dept";
	
	@Autowired
	private DeptService deptService;
	
	@GetMapping()
	@RequiresPermissions("sys:dept:dept")
	String SysDept(){
	    return prefix + "/dept";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:dept:dept")
	public List<Dept> list(){
		//查询列表数据
       // Query query = new Query(params);
		Map<String, Object> query = new HashMap<>();
		List<Dept> sysDeptList = deptService.list(query);
	//	int total = sysDeptService.count(query);
		//PageUtils pageUtils = new PageUtils(sysDeptList, total);
		return sysDeptList;
	}
	
	@GetMapping("/add/{pId}")
	@RequiresPermissions("sys:dept:add")
	String add(@PathVariable("pId") Long pId,Model model){
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "总部门");
		} else {
			model.addAttribute("pName", deptService.queryById(pId).getName());
		}
	    return "sys/dept/deptAdd";
	}

	@GetMapping("/edit/{deptId}")
	@RequiresPermissions("sys:dept:edit")
	String edit(@PathVariable("deptId") Long deptId,Model model){
		Dept dept = deptService.queryById(deptId);
		model.addAttribute("dept", dept);
	    return prefix + "/deptEdit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:dept:add")
	public R save( Dept sysDept){
		if(deptService.insert(sysDept)){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:dept:edit")
	public R update( Dept sysDept){
		if(deptService.update(sysDept)){
			return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("sys:dept:remove")
	public R remove( Long deptId){
		if(deptService.delete(deptId)){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("sys:dept:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] deptIds){
		deptService.batchRemove(deptIds);
		return R.ok();
	}
	
	
	@GetMapping("/tree")
	@ResponseBody
	public Tree<Dept> tree() {
		Tree<Dept> tree = new Tree<Dept>();
		tree = deptService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return prefix + "/deptTree";
	}
	
}
