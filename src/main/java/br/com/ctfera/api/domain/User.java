package br.com.ctfera.api.domain;

import jakarta.persistence.*;
import lombok.*;

@Data //Substitui, apesar de menos performático, as anotações Getter, Setter, EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(unique = true)
    private String email;
    private String password;

}
