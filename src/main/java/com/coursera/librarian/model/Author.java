package com.coursera.librarian.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Author implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_author_gen", sequenceName = "seq_author", allocationSize = 1)
    @GeneratedValue(generator = "seq_author_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "AUTHOR_NAME",length = 100)
    private String name;


}
