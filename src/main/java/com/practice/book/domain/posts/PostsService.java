package com.practice.book.domain.posts;

import com.practice.book.web.dto.PostResponseDto;
import com.practice.book.web.dto.PostsListReponseDto;
import com.practice.book.web.dto.PostsSaveRequestsDto;
import com.practice.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestsDto postsSaveRequestsDto) {
        return postsRepository.save(postsSaveRequestsDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto postsUpdateRequestDto) {
        Posts entity = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id)
        );

        entity.update(postsUpdateRequestDto.getTitle(), postsUpdateRequestDto.getContent());
        return id;
    }

    public PostResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id)
        );
        return new PostResponseDto(entity);
    }

    public List<PostsListReponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListReponseDto::new)
                .collect(Collectors.toList());
    }
}
