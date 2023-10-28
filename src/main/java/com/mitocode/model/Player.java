package com.mitocode.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Player {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idName;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToOne
    @JoinColumn( nullable = false, foreignKey = @ForeignKey(name = "FK_SHIRT_PLAYER"))
    private Shirt shirt;

    @ManyToOne
    @JoinColumn( nullable = false, foreignKey = @ForeignKey(name = "FK_POSITION_PLAYER"))
    private Position position;

}
