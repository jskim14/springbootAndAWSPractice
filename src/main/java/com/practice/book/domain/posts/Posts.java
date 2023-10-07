package com.practice.book.domain.posts;

import com.practice.book.domain.BaseTimeEntity;
import com.practice.book.web.dto.PostsUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@Entity // jpa 어노테이션, 주요 어노테이션일수록 클래스와 가깝게 둔다
public class Posts extends BaseTimeEntity {
    @Id //pk 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "TITLE IS NULL")
    @Column(length = 500, nullable = false)
    private String title;

    @NotBlank(message = "CONTENT IS NULL")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 생성자에 선언하면 생성자에 포함된 필드만 빌더에 포함된다.
    public Posts(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts update(PostsUpdateRequestDto postsUpdateRequestDto) {
        this.title = postsUpdateRequestDto.getTitle();
        this.content = postsUpdateRequestDto.getContent();
        return this;
    }
}
