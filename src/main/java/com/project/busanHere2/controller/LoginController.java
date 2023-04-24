package com.project.busanHere2.controller;

import com.project.busanHere2.common.MessageDto;
import com.project.busanHere2.domain.member.MemberDTO;
import com.project.busanHere2.domain.member.MemberLoginForm;
import com.project.busanHere2.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/members/login")
    public String login(Model model) {

        model.addAttribute("memberLoginForm", new MemberLoginForm());
        return "members/loginForm";
    }

    @PostMapping("members/login")
    public String login(@Valid MemberLoginForm memberForm, BindingResult bindingResult,
                         HttpServletRequest request, Model model) {

        if (bindingResult.hasErrors()) {
            return "members/loginForm";
        }
        MemberDTO loginMember = memberService.login(memberForm.getNickName(), memberForm.getPw());

        if (loginMember == null) {
            String failMessage = "닉네임 혹은 비밀번호가 잘못 되었습니다.";
            model.addAttribute("loginFail", failMessage);
            return "members/loginForm";
        }

        HttpSession session = request.getSession();

        session.setAttribute("loginMember", loginMember);
        model.addAttribute("loginMember", loginMember);

        MessageDto message = new MessageDto("로그인이 완료되었습니다.", "/",
                RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    @GetMapping(value = "members/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        session.invalidate();

        return "redirect:/";
    }

    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }
}
