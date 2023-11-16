package com.example.banking.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.Type;

import jakarta.persistence.*;
import org.hibernate.type.descriptor.jdbc.VarbinaryJdbcType;

@Entity
@Table(name = "user_images")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "original_file_name")
    private String originalFileName;
    @Column(name = "size")
    private Long size;

    @Lob
    @JdbcType(VarbinaryJdbcType.class)
    private byte[] bytes;

    @ManyToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    private User user;
    @Column(name = "content_type")
    private String contentType;
    @Column(name = "activate")
    private Boolean activate;
}
