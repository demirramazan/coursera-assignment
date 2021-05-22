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
@Table(name = "publisher")
public class Publisher implements Serializable {
    @Id
    @SequenceGenerator(name = "seq_publish_gen", sequenceName = "seq_publisher", allocationSize = 1)
    @GeneratedValue(generator = "seq_publish_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "PUBLISHER_NAME", length = 100, nullable = false)
    private String name;
}
