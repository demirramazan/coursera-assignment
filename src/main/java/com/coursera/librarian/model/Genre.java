package com.coursera.librarian.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "genre")
public class Genre implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_genre_gen", sequenceName = "seq_genre",
            allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "seq_genre_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "GENRE_NAME", length = 50, nullable = false)
    private String name;
}
