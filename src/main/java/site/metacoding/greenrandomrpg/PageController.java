package site.metacoding.greenrandomrpg;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/battlepage")
    public String battlepage() {
        return "battlePage";
    }

    @GetMapping("/store")
    public String store(Model model) {
        model.addAttribute("user", true);
        model.addAttribute("store", true);
        return "store";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("main", true);
        // model.addAttribute("user", true);
        return "main";
    }

    @GetMapping("/ready")
    public String ready(Model model) {
        model.addAttribute("user", true);
        model.addAttribute("ready", true);
        return "readyBattlePage";
    }

}
