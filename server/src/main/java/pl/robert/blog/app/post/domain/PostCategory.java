package pl.robert.blog.app.post.domain;

import lombok.Getter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
enum PostCategory {

    PROGRAMMING("Programming"),
    TRAVELS("Travels"),
    SPORT("Sport");

    String type;
}
