package com.zerobase.reservation.controller.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zerobase.reservation.dto.member.SignupDto;
import com.zerobase.reservation.service.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static com.zerobase.reservation.type.Role.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MemberController.class)
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("회원가입")
    @WithMockUser
    void signUp() throws Exception {

        //given
        SignupDto signupDto = getDto();
        String json = objectMapper.writeValueAsString(signupDto);


        //when //then
        mockMvc.perform(post("/members/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andDo(print())
                .andExpect(status().isOk());
    }

    private static SignupDto getDto() {
        return SignupDto.builder()
                .email("zerobase@naver.com")
                .password("123123")
                .nickname("제로베이스")
                .phoneNumber("01000000000")
                .role(USER)
                .build();
    }
}