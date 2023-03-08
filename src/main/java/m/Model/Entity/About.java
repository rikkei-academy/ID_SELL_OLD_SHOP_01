package m.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "about")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class About {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aboutId")
    private int aboutId;
    @Column(name = "aboutImage")
    private String aboutImage;
    @Column(name = "aboutDescription")
    private String aboutDescription;
    @Column(name = "aboutAddress")
    private String aboutAddress;
    @Column(name = "phoneNumber")
    private int phoneNumber;
    @Column(name = "emailAbout")
    private String emailAbout;
   @Column(name = "aboutStatus")
    private boolean aboutStatus;
}
