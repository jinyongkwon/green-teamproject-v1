package site.metacoding.greenrandomrpg.domain.rpg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.greenrandomrpg.user.User1;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Rpg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer atk;

    @Column(nullable = false)
    private Integer maxHp;

    @Column(nullable = false)
    private Integer hp;

    @JoinColumn(name = "userId")
    @OneToOne(fetch = FetchType.LAZY)
    private User1 user;

}