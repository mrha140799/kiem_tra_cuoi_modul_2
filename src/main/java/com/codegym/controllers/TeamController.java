package com.codegym.controllers;

import com.codegym.model.Team;
import com.codegym.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TeamController {
    @Autowired
    private TeamService teamService;
    @GetMapping("/team/list")
    public String listHome(Model model) {
        model.addAttribute("teams", teamService.findAll());
        return "/team/list";
    }
    @GetMapping("/team/create")
    public String createHome(Model model) {
        model.addAttribute("team", new Team());
        return "/team/create";
    }
    @PostMapping("/team/create/done")
    public String createDone(@ModelAttribute("team")Team team, RedirectAttributes redirect) {
        teamService.save(team);
        redirect.addFlashAttribute("message", "Create team successful!");
        return "redirect:/team/create";
    }
    @GetMapping("/team/{id}/edit")
    public String editHome(@PathVariable Long id , Model model) {
        model.addAttribute("team", teamService.findById(id));
        return "/team/edit";
    }
    @PostMapping("/team/edit/done")
    public String editDone(@ModelAttribute("team") Team team, RedirectAttributes redirect) {
        teamService.save(team);
        redirect.addFlashAttribute("message","Edit successful!");
        return "redirect:/team/list";
    }
    @GetMapping("/team/{id}/delete")
    public String deleteDone( @PathVariable Long id,RedirectAttributes redirect) {
        teamService.delete(id);
        redirect.addFlashAttribute("message","Delete successful!");
        return "redirect:/category/list";
    }
}
