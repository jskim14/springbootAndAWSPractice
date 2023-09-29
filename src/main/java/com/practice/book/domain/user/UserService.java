package com.practice.book.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long withdrawal(Long id) {
        User byId = userRepository.findById(id).orElseThrow();
        userRepository.delete(byId);
        return id;
    }

}
