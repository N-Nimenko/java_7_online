package homework14.controller;

import homework14.dto.SculptorDto;
import homework14.dto.SculptureDto;
import homework14.facade.SculptorFacade;
import homework14.facade.SculptureFacade;
import homework14.service.SculptureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Collection;

@Controller
@RequestMapping("/sculptures")
public class SculptureController {

    @Autowired
    private SculptureFacade sculptureFacade;

    @Autowired
    private SculptorFacade sculptorFacade;

    @Autowired
    private SculptureService sculptureService;

    @GetMapping
    public String findAll(Model model) {
        Collection<SculptureDto> dtos = sculptureFacade.findAll();
        model.addAttribute("sculptures", dtos);
        return "pages/sculptures/sculptures_all";
    }

    @GetMapping("/sculptors/{id}")
    public String findAll(Model model, @PathVariable Long id) {
        model.addAttribute("sculptures", sculptureFacade.findBySculptor(id));
        return "pages/sculptures/sculptures_all";
    }

    @GetMapping("/new")
    public String redirectToNewSculpture(Model model) {
        model.addAttribute("sculpture", new SculptureDto());
        return "pages/sculptures/sculptures_new";
    }

    @PostMapping("/new")
    public String newSculpture(@ModelAttribute SculptureDto sculpture) {
        sculptureFacade.create(sculpture);
        return "redirect:/sculptures";
    }

    @GetMapping("/{id}/update")
    public String redirectToUpdateSculpture(Model model, @PathVariable Long id) {
        model.addAttribute("sculpture", sculptureFacade.findById(id));
        return "pages/sculptures/sculptures_update";
    }

    @PostMapping("/{id}/update")
    public String updateSculpture(@ModelAttribute SculptureDto sculpture, @PathVariable Long id) {
        System.out.println("sculpture = " + sculpture);
        sculptureFacade.update(id, sculpture);
        return "redirect:/sculptures";
    }

    @PostMapping("/{id}")
    public String deleteSculpture(@PathVariable("id") Long id) {
        sculptureFacade.delete(id);
        return "redirect:/sculptures";
    }

    @GetMapping("/{id}/delete")
    public String showDeleteSculpturePage(Model model, @PathVariable Long id) {
        SculptureDto sculpture = sculptureFacade.findById(id);
        model.addAttribute("sculpture", sculpture);
        return "pages/sculptures/sculptures_delete";
    }
}       


