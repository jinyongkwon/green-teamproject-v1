package site.metacoding.greenrandomrpg.domain.rpg;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer attack;

    @Column(nullable = false)
    private Integer maxHp;

    @Column(nullable = false)
    private Integer hp;

    @Column(nullable = false)
    private Integer html;

    @Column(nullable = false)
    private Integer java;

    @Column(nullable = false)
    private Integer jsp;

    @Column(nullable = false)
    private Integer spring;

    @Column(nullable = true)
    private LocalDateTime freeTime;

    public Timestamp getFormatFreeTime() {
        Timestamp time = new Timestamp(0);
        if (freeTime != null) {
            time = Timestamp.valueOf(freeTime); // getTime을 쓰기위해 TimeStamp타입으로 변환
        }
        return time;
    }
}
