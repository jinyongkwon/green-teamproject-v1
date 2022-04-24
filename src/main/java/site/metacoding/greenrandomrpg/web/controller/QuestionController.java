package site.metacoding.greenrandomrpg.web.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.greenrandomrpg.config.auth.LoginUser;
import site.metacoding.greenrandomrpg.domain.comment.Comment;
import site.metacoding.greenrandomrpg.domain.question.Question;
import site.metacoding.greenrandomrpg.domain.user.User;
import site.metacoding.greenrandomrpg.service.question.QuestionService;
import site.metacoding.greenrandomrpg.web.dto.question.QuestionWriteReqDto;

@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;

    // 질문게시글 등록
    @PostMapping("/s/question/write")
    public String questionWrite(QuestionWriteReqDto postWriteReqDto, @AuthenticationPrincipal LoginUser loginUser) {
        User principal = loginUser.getUser();
        questionService.질문등록하기(postWriteReqDto, principal);
        return "redirect:/s/question/list/" + principal.getId();
    }

    // 질문게시판 쓰기폼
    @GetMapping("/s/question/writeForm")
    public String questionWriteForm() {
        return "question/writeForm";
    }

    // 질문게시판 목록
    @GetMapping("/s/question/list/{userId}")
    public String questionList(@PathVariable Integer userId, Model model) {
        List<Question> questions = questionService.질문목록가져오기(userId);
        model.addAttribute("questions", questions);
        return "question/list";
    }

    // 질문게시판 상세보기
    @GetMapping("/s/question/{id}")
    public String questionDetailForm(@PathVariable Integer id, Model model) {
        Question question = questionService.질문가져오기(id);
        model.addAttribute("question", question);
        return "question/detail";
    }

    // 질문게시판 이미지보기
    @GetMapping("/s/question/checkImg/{id}")
    public String questioncheckImg(@PathVariable Integer id, Model model) {
        Question question = questionService.질문가져오기(id);
        model.addAttribute("question", question);
        return "question/img";
    }

}
