package com.pivinadanang.controller.admin;

import com.pivinadanang.dto.CategoryDTO;
import com.pivinadanang.dto.PostDTO;
import com.pivinadanang.services.ICategoryService;
import com.pivinadanang.services.IPostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("admin/posts")
public class PostController {


    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IPostService postService;

    @ModelAttribute("categories")
    public List<CategoryDTO> getCategories(){
        return categoryService.getIdAndNameCategory();
    }

    @GetMapping("add")
    public String add (Model model) {
        PostDTO post = new PostDTO();
        model.addAttribute("post",post);
        return "admin/posts/addOrUpdate";
    }

    @GetMapping ("edit/{id}")
    public ModelAndView edit (ModelMap model, @PathVariable("id") Long id){
        PostDTO post = postService.findByPostId(id);
        post.setIsEdit(true);
        model.addAttribute("post", post);
        return new ModelAndView("admin/posts/addOrUpdate", model);
    }
    @PostMapping("addOrUpdate")
    public String addOrUpdate (@Valid @ModelAttribute("product")PostDTO postDTO ,@RequestParam("thumbnailFile") MultipartFile thumbnailFile,
                                BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()){
            return "admin/posts/addOrUpdate";
        }
        if(postDTO.getId()== null){
            if (thumbnailFile == null || thumbnailFile.isEmpty()) {
                result.rejectValue("thumbnailFile", "file.required", "Please select a thumbnail file");
                return "admin/posts/addOrUpdate";
            }
            if (postDTO.getCategoryId() == null) {
                result.rejectValue("categoryId", "categoryId.requiredle", "Please select a categoryId ");
                return "admin/posts/addOrUpdate";
            }
        }else {
            if (postDTO.getCategoryId() == null) {
                result.rejectValue("categoryId", "categoryId.requiredle", "Please select a categoryId ");
                return "admin/posts/addOrUpdate";
            }
        }
        return "redirect:/admin/posts/list";
    }

    @GetMapping ("list")
    public String listPosts (ModelMap model, @RequestParam(value = "title", required = false) String title,
                                @RequestParam ("page") Optional<Integer> page,
                                @RequestParam ("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Pageable pageable = PageRequest.of(currentPage -1, pageSize, Sort.by("title"));
        Page<PostDTO> resultPage = null;

        if(StringUtils.hasText(title)){
            resultPage = postService.findByNameContainsIgnoreCase(title, pageable);
            model.addAttribute("title",title);
        }else {
            resultPage = postService.findAllPostsPaginged(pageable);
        }

        int totalPages = resultPage.getTotalPages();
        if(totalPages > 0) {
            int start = Math.max(1, currentPage-2);
            int end = Math.min(currentPage + 2, totalPages);

            if(totalPages > 5) {
                if(end == totalPages) start = end -5;
                else if (start == 1) end = start + 5;
            }
            List<Integer> pageNumbers = IntStream.rangeClosed(start, end)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }


        model.addAttribute("postPage", resultPage);
        return "/admin/posts/list";
    }



    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        postService.deletePostById(id);
        redirectAttributes.addFlashAttribute("message", "post is deleted!");
        redirectAttributes.addFlashAttribute("alert","success");
        return "redirect:/admin/products/list";
    }

}
