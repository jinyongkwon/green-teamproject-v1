package site.metacoding.greenrandomrpg.service.question;

import java.util.List;
import java.util.Optional;

import javax.persistence.Id;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.greenrandomrpg.domain.comment.Comment;
import site.metacoding.greenrandomrpg.domain.comment.CommentRepository;
import site.metacoding.greenrandomrpg.domain.question.Question;
import site.metacoding.greenrandomrpg.domain.question.QuestionRepository;
import site.metacoding.greenrandomrpg.domain.user.User;
import site.metacoding.greenrandomrpg.util.FileUpload;
import site.metacoding.greenrandomrpg.web.dto.question.QuestionWriteReqDto;

@RequiredArgsConstructor
@Service
public class QuestionService {

    @Value("${file.path}")
    private String uploadFolder;

    private final QuestionRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void 질문등록하기(QuestionWriteReqDto questionWriteReqDto, User principal) {
        String file = null;
        if (!questionWriteReqDto.getFile().isEmpty()) {
            file = FileUpload.write(uploadFolder, questionWriteReqDto.getFile());
        }
        Question questionEntity = questionWriteReqDto.toEntity(file, principal);
        postRepository.save(questionEntity);
    }

    public List<Question> 질문목록가져오기(Integer userId, User principal) {
        List<Question> questions = null;
        if (principal.isManager()) {
            questions = postRepository.findAll();
        } else {
            questions = postRepository.findByUserId(userId);
        }
        return questions;
    }

    public Question 질문가져오기(Integer id) {
        Optional<Question> questionOp = postRepository.findById(id);
        if (questionOp.isPresent()) {
            Question questionEntity = questionOp.get();
            return questionEntity;
        } else {
            throw new RuntimeException("질문을 찾을수가 없습니다.");
        }
    }

    public void 답글달기(Comment comment, Integer id) {
        Optional<Question> questionOp = postRepository.findById(id);
        if (questionOp.isPresent()) {
            Question questionEntity = questionOp.get();
            comment.setQuestion(questionEntity);
            commentRepository.save(comment);
        } else {
            throw new RuntimeException("질문을 찾을수가 없습니다.");
        }
    }
}
