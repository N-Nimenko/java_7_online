package homework14.controller;

import homework14.dto.SculptorDto;
import homework14.facade.SculptorFacade;
import homework14.facade.SculptureFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/sculptors")
public class SculptorController {

    @Autowired
    private SculptorFacade sculptorFacade;

    @Autowired
    private SculptureFacade sculptureFacade;

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("sculptors", sculptorFacade.findAll());
        return "pages/sculptors/sculptors_all";
    }

    @GetMapping("/sculptures/{id}")
    public String findAll(Model model, @PathVariable Long id) {
        model.addAttribute("sculptors", sculptorFacade.findBySculpture(id));
        return "pages/sculptors/sculptors_all";
    }

    @GetMapping("/new")
    public String redirectToNewSculptor(Model model) {
        model.addAttribute("sculptor", new SculptorDto());
        return "pages/sculptors/sculptors_new";
    }

    @PostMapping("/new")
    public String newSculptor(@ModelAttribute SculptorDto sculptor) {
        sculptorFacade.create(sculptor);
        return "redirect:/sculptors";
    }

    @GetMapping("/{id}/update")
    public String redirectToUpdateSculptor(Model model, @PathVariable Long id) {
        model.addAttribute("sculptor", sculptorFacade.findById(id));
        return "pages/sculptors/sculptors_update";
    }

    @PostMapping("/{id}/update")
    public String updateSculptors(@ModelAttribute SculptorDto sculptor, @PathVariable Long id) {
        System.out.println("sculptor = " + sculptor);
        sculptorFacade.update(id, sculptor);
        return "redirect:/sculptors";
    }

    @PostMapping("/{id}")
    public String deleteSculptor(@PathVariable("id") Long id) {
        sculptorFacade.delete(id);
        return "redirect:/sculptors";
    }

    @GetMapping("/{id}/delete")
    public String showDeleteSculptorPage(Model model, @PathVariable Long id) {
        SculptorDto sculptor = sculptorFacade.findById(id);
        model.addAttribute("sculptor", sculptor);
        return "pages/sculptors/sculptors_delete";
    }

    @GetMapping("/find")
    public String showFindSculptorPage(Model model) {
        model.addAttribute("sculptor", new SculptorDto());
        return "pages/sculptors/sculptors_find";
    }

    @GetMapping("/findSculptor")
    public String findSculptor(@RequestParam Long id, Model model) {
        SculptorDto sculptor = sculptorFacade.findById(id);
        model.addAttribute("sculptor", sculptor);
        return "pages/sculptors/sculptors_modal";
    }
}
