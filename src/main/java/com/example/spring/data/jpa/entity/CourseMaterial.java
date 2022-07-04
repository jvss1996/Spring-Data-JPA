package com.example.spring.data.jpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "course")
public class CourseMaterial {

    @Id
    @SequenceGenerator(name = "course_material_sequence", sequenceName = "course_material_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_material_sequence")
    private Long courseMaterialId;
    private String url;

    @OneToOne(cascade = CascadeType.ALL,    // Sync between parent and child stays
            fetch = FetchType.LAZY,         // LAZY = fetch when needed, EAGER = fetch immediately
            optional = false                // course material is required when saving a course. By default, it is false
    )
    @JoinColumn(name = "course_id", referencedColumnName = "courseId")
    private Course course;
}
