package m.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String token;
    @OneToOne(targetEntity = Users.class, fetch = FetchType.EAGER)

    @JoinColumn(nullable = false, name = "usersId")
    private Users users;
    private Date startDate;
}