package com.practice.book.web.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloResponseDtoTest {

    @Test
    public void 롬복테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto helloResponseDto = new HelloResponseDto(name, amount);

        //then
        assertThat(helloResponseDto.getName()).isEqualTo(name);
        assertThat(helloResponseDto.getAmount()).isEqualTo(amount);
    }

    @Test
    void getName() {
    }

    @Test
    void getAmount() {
    }
}