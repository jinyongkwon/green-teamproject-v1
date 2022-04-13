package site.metacoding.greenrandomrpg.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.greenrandomrpg.domain.monster.Monster;
import site.metacoding.greenrandomrpg.domain.rpg.Rpg;
import site.metacoding.greenrandomrpg.service.MonsterService;
import site.metacoding.greenrandomrpg.service.RpgService;
import site.metacoding.greenrandomrpg.web.api.ResponseDto;
import site.metacoding.greenrandomrpg.web.api.dto.UpdateDto;

@Controller
@RequiredArgsConstructor
public class RpgController {
    private final RpgService rpgService;
    private final MonsterService monsterService;

    @GetMapping({ "/battle/{id}" })
    public String rpgStart(Model model) {

        List<Rpg> rstate = rpgService.알피지상태();
        model.addAttribute("rlist", rstate);
        List<Monster> mstate = monsterService.몬스터상태();
        model.addAttribute("mlist", mstate);
        return "/battle";
    }

    @PutMapping({ "/battle/update/{id}" })
    public @ResponseBody ResponseDto<?> rpgOver(@PathVariable Integer id, @RequestBody UpdateDto updateDto,
            Model model) {
        System.out.println("들어옴");
        Rpg rpgEntity = rpgService.업데이트(id, updateDto);
        model.addAttribute("user", rpgEntity);
        return new ResponseDto<>(1, "성공", null);
    }

}