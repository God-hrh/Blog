package com.hrh.blog.web.blog;

import com.hrh.blog.pojo.Tag;
import com.hrh.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


/**
 * @author Heerh
 * @version 1.0
 * @date 2020/6/27 0:31
 */
@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;
    //查询tags
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }
    //新增tags页面
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }
    //新增tags提交逻辑
    @PostMapping("/tags")
    public String addtagpost(@Valid Tag tag, BindingResult result, RedirectAttributes attributes){

        //后端的重复名称逻辑校验
        if (tagService.findTagByName(tag.getName())!=null){
            result.rejectValue("name","nameError","已存在该标签");
        }
        //后端类型名称的非空校验
        if (result.hasErrors()){
            return "admin/tags-input";
        }
        Tag t = tagService.saveTag(tag);
        //判断是否保存成功
        if (t==null){
            //没保存成功
            attributes.addFlashAttribute("message","添加失败");
        }else {
            //保存成功
            attributes.addFlashAttribute("message","添加成功");
        }
        return "redirect:/admin/tags";
    }
    //修改tags页面
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag", tagService.findTag(id));
        return "admin/tags-input";
    }
    //修改类型提交逻辑
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
        Tag tag1 = tagService.findTagByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name","nameError","不能添加重复的分类");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        Tag t = tagService.updateTag(id,tag);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags";
    }
    //删除类型逻辑
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }
}
