package site.metacoding.greenrandomrpg.web.dto.question;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.greenrandomrpg.domain.question.Question;
import site.metacoding.greenrandomrpg.domain.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionWriteReqDto {
    private String title;
    private String content;
    private MultipartFile file;

    public Question toEntity(String file, User principal) {
        Question post = new Question();
        post.setTitle(title);
        post.setContent(content);
        post.setFile(file);
        post.setUser(principal);
        return post;
    }
}
