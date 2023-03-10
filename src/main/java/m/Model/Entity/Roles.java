package m.Model.Entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rolesId")
    private int roleId;
    @Column(name = "rolesName")
    @Enumerated(EnumType.STRING)
    private ERole roleName;
}
