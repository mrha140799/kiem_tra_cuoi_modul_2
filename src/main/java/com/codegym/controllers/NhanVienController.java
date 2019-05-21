package com.codegym.controllers;

import com.codegym.model.NhanVien;
import com.codegym.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("/nhanvien/create")
    public ModelAndView createPage(){
        return new ModelAndView("nhanvien/create", "nhanvien",new NhanVien());
    }



    @PostMapping(value = "/nhanvien/create/done", params = "add")
    public String createAdd(@Validated @ModelAttribute("nhanvien")NhanVien nhanVien, BindingResult bindingResult, RedirectAttributes redirect ) {
        new NhanVien().validate(nhanVien,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "nhanvien/create";
        }else {
            nhanVienService.save(nhanVien);
            redirect.addFlashAttribute("message", "create successful");
            return "redirect:/nhanvien/create";
        }
    }
    @PostMapping(value = "/nhanvien/create/done", params = "cancle")
    public String createCancle() {
        return "redirect:/";
    }




    @GetMapping("/")
    public ModelAndView listPage(@PageableDefault(size = 2)Pageable pageable){
        return new ModelAndView("nhanvien/list","listNV",nhanVienService.findAll(pageable));
    }
    @GetMapping("/nhanvien/{id}/edit")
    public ModelAndView editPage(@PathVariable Long id){
        return new ModelAndView("nhanvien/edit","nhanvien",nhanVienService.findeById(id));
    }
    @PostMapping(value = "/nhanvien/edit/done", params = "edit")
    public String editAdd(@ModelAttribute("nhanvien")NhanVien nhanVien, RedirectAttributes redirect) {
        if (nhanVien.getHoTen().equals("")){
            redirect.addFlashAttribute( "message","Edit false!");
        }else {
            nhanVienService.save(nhanVien);
            redirect.addFlashAttribute("message","Edit successful!");
        }
        return "redirect:/";
    }
    @PostMapping(value = "/nhanvien/edit/done", params = "cancle")
    public String editCancle() {
        return "redirect:/";
    }
    @GetMapping("/nhanvien/{id}/delete")
    public String deleteComplete(@PathVariable Long id) {
        nhanVienService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/nhanvien/search")
    public ModelAndView searchNhanVien(@RequestParam("strName") String name , @PageableDefault(size = 2) Pageable pageable){
        List<NhanVien> nhanViens = nhanVienService.searchNhanVien(name);
        Page<NhanVien> pageList =new PageImpl<>(nhanViens,pageable,nhanViens.size());
        ModelAndView modelAndView = new ModelAndView("nhanvien/list", "listNV" , pageList);
        return modelAndView;

    }
}
