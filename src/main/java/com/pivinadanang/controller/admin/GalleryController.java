package com.pivinadanang.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin/gallery")
public class GalleryController {
    @GetMapping("")
    public ModelAndView gallery (){
        return new ModelAndView("admin/gallery/gallery");
    }
    @GetMapping("droptoupload")
    public ModelAndView droptoupload (){
        return new ModelAndView("admin/gallery/dropFileToUpload");
    }
}
