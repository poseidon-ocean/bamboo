package com.bamboo.common.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bamboo.common.domain.Dict;
import com.bamboo.common.service.SysDictService;
import com.bamboo.common.utils.PageUtils;
import com.bamboo.common.utils.Query;
import com.bamboo.common.utils.R;

/**
 * 字典表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-29 18:28:07
 */
 
@Controller
@RequestMapping("/common/sysDict")
public class DictController extends BaseController{
	@Autowired
	private SysDictService sysDictService;
	
	@GetMapping()
	@RequiresPermissions("common:sysDict:sysDict")
	String SysDict(){
	    return "common/sysDict/sysDict";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("common:sysDict:sysDict")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<Dict> sysDictList = sysDictService.list(query);
		int total = sysDictService.count(query);
		PageUtils pageUtils = new PageUtils(sysDictList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("common:sysDict:add")
	String add(){
	    return "common/sysDict/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("common:sysDict:edit")
	String edit(@PathVariable("id") Long id,Model model){
		Dict sysDict = sysDictService.get(id);
		model.addAttribute("sysDict", sysDict);
	    return "common/sysDict/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("common:sysDict:add")
	public R save( Dict sysDict){
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if(sysDictService.save(sysDict)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("common:sysDict:edit")
	public R update( Dict sysDict){
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		sysDictService.update(sysDict);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("common:sysDict:remove")
	public R remove( Long id){
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if(sysDictService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:sysDict:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		sysDictService.batchRemove(ids);
		return R.ok();
	}
	
	@GetMapping( "/type")
	@ResponseBody
	public List<Dict> listType(){
		return sysDictService.listType();
	};
	//类别已经指定增加
	@GetMapping("/add/{type}/{description}")
	@RequiresPermissions("common:sysDict:add")
	String addD( Model model,@PathVariable("type") String type, @PathVariable("description") String description ){
		model.addAttribute("type", type);
		model.addAttribute("description",description);
	    return "common/sysDict/add";
	}
	
}
