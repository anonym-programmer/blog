package pl.robert.blog.app.post.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 40, nullable = false)
    String title;

    @Column(length = 1000000, nullable = false)
    String content;

    @Enumerated(EnumType.STRING)
    @Column(length = 11, nullable = false)
    PostCategory category;

    @Column(length = 6, nullable = false)
    final String author = "Robert";

    @DateTimeFormat(pattern = "EEE, dd-MM-yyyy")
    @Column(length = 15, nullable = false)
    final LocalDateTime date = LocalDateTime.now();

    Post(String title, String content, PostCategory category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }
}
