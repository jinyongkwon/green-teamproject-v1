package site.metacoding.greenrandomrpg.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RpgMainController {

    @GetMapping({ "/" })
    public String main(Model model) {
        return "/main";
    }
}