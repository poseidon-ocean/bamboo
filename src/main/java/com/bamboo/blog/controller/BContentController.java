package com.bamboo.blog.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bamboo.blog.domain.BlogContent;
import com.bamboo.blog.service.BContentService;
import com.bamboo.common.controller.BaseController;
import com.bamboo.common.utils.PageUtils;
import com.bamboo.common.utils.Query;
import com.bamboo.common.utils.R;

/**
 * 文章内容
 * 
 */
@Controller
@RequestMapping("/blog/bContent")
public class BContentController extends BaseController {
	
	@Autowired
	BContentService bContentService;

	@GetMapping()
	@RequiresPermissions("blog:bContent:bContent")
	String bContent() {
		return "blog/bContent/bContent";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("blog:bContent:bContent")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<BlogContent> bContentList = bContentService.list(query);
		int total = bContentService.count(query);
		PageUtils pageUtils = new PageUtils(bContentList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("blog:bContent:add")
	String add() {
		return "blog/bContent/add";
	}

	@GetMapping("/edit/{cid}")
	@RequiresPermissions("blog:bContent:edit")
	String edit(@PathVariable("cid") Long cid, Model model) {
		BlogContent bContentDO = bContentService.get(cid);
		model.addAttribute("bContent", bContentDO);
		return "blog/bContent/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@RequiresPermissions("blog:bContent:add")
	@PostMapping("/save")
	public R save(BlogContent bContent) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (bContent.getAllowComment() == null) {
			bContent.setAllowComment(0);
		}
		if (bContent.getAllowFeed() == null) {
			bContent.setAllowFeed(0);
		}
		if(null==bContent.getType()) {
			bContent.setType("article");
		}
		bContent.setGtmCreate(new Date());
		bContent.setGtmModified(new Date());
		int count;
		if (bContent.getId() == null || bContent.getId().equals("")) {
			count = bContentService.save(bContent);
		} else {
			count = bContentService.update(bContent);
		}
		if (count > 0) {
			return R.ok().put("cid", bContent.getId());
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@RequiresPermissions("blog:bContent:edit")
	@RequestMapping("/update")
	public R update(@RequestBody BlogContent bContent) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		bContent.setGtmCreate(new Date());
		bContentService.update(bContent);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequiresPermissions("blog:bContent:remove")
	@PostMapping("/remove")
	@ResponseBody
	public R remove(Long id) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (bContentService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@RequiresPermissions("blog:bContent:batchRemove")
	@PostMapping("/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Long[] cids) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		bContentService.batchRemove(cids);
		return R.ok();
	}
}
