package com.practice.book.domain.posts;

import com.practice.book.config.auth.dto.SessionUser;
import com.practice.book.domain.user.Role;
import com.practice.book.web.dto.PostResponseDto;
import com.practice.book.web.dto.PostsDeleteRequestDto;
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
    public Posts save(PostsSaveRequestsDto postsSaveRequestsDto) {
        return postsRepository.save(postsSaveRequestsDto.toEntity());
    }

    @Transactional
    public Posts update(Long id, PostsUpdateRequestDto postsUpdateRequestDto, SessionUser user) {
        Posts entity = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id)
        );
        userCheck(user, postsUpdateRequestDto.getAuthor());
        return entity.update(postsUpdateRequestDto);
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

    @Transactional
    public void delete(Long id, PostsDeleteRequestDto postsDeleteRequestDto, SessionUser user) {
        Posts entity = postsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id)
        );
        userCheck(user, postsDeleteRequestDto.getAuthor());
        postsRepository.delete(entity);
    }

    public void userCheck(SessionUser user, String author) {
        if(!(user.getRole().equals(Role.ADMIN) || user.getName().equals(author))) {
            throw new IllegalArgumentException("글 작성자가 아닙니다.");
        }
    }
}
