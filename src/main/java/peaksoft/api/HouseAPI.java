package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Comment;
import peaksoft.model.House;
import peaksoft.service.CommentService;
import peaksoft.service.HouseService;

@Controller
@RequestMapping
public class HouseAPI {

    private final HouseService houseService;
    private final CommentService commentService;

    @Autowired
    public HouseAPI(HouseService houseService, CommentService commentService) {
        this.houseService = houseService;
        this.commentService = commentService;
    }

    @GetMapping("/allHouses")
    public String getAllHouse(Model model) {
        model.addAttribute("allHouse", houseService.getAllHouse());
        return "/mainPage";
    }

    @GetMapping("/getHouse/{houseId}")
    public String getHouseById(@PathVariable("houseId") Long id, Model model) {
        model.addAttribute("house", houseService.getHouseById(id));
        model.addAttribute("comments", commentService.getAllComments(id));
        return "/innerPage";
    }

    @GetMapping("/new")
    public String newHouse(Model model) {
        model.addAttribute("newHouse", new House());
        return "/newHouse";
    }

    @PostMapping("/save")
    public String saveHouse(@ModelAttribute("newHouse") House house) {
        houseService.saveHouse(house);
        return "redirect:/allHouses";
    }

    @PostMapping("/delete/{id}")
    public String deleteHouseById(@PathVariable Long id) {
        houseService.deleteById(id);
        return "redirect:/allHouses";
    }

    @GetMapping("/{id}/newComment")
    public String newComment(Model model) {
        model.addAttribute("comment", new Comment());
        return "/saveComment";
    }

    @PostMapping("/{id}/saveComment")
    public String saveComment(@ModelAttribute("comment") Comment comment, @PathVariable Long id) {
        commentService.saveComment(id, comment);
        return "redirect:/getHouse/{houseId}";
    }

}
