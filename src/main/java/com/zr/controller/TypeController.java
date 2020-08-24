package com.zr.controller;

import com.zr.bean.Type;
import com.zr.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("admin/types")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @RequestMapping
    public String list(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        Page<Type> page = typeService.findAll(pageable);
        model.addAttribute("page",page);
        return "admin/types";
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable Long id){
        typeService.delteById(id);
        return "redirect:/admin/types";

    }
    @RequestMapping("toInput/{id}")
    public String toInput(@PathVariable Long id,Model model){
        if(id==-1){
            model.addAttribute("type",new Type());
        }else {
            Type type=typeService.findById(id);
            model.addAttribute("type",type);

        }
        return "admin/types-input";

    }

    @RequestMapping("input")
    public String input(Type type){
        typeService.add(type);
        return "redirect:/admin/types";
    }
}
