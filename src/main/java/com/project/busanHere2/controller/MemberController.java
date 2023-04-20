package com.project.busanHere2.controller;

import com.project.busanHere2.common.MessageDto;
import com.project.busanHere2.domain.member.MemberDTO;
import com.project.busanHere2.domain.member.MemberForm;
import com.project.busanHere2.domain.member.MemberLoginForm;
import com.project.busanHere2.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private final MemberService memberService;

    @GetMapping("/new")
    public String createForm(Model model) {
        log.info("GET : /new");
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public String create1(@Valid MemberForm form, BindingResult bindingResult, Model model) {
        log.info("POST : /new");
        MessageDto message = new MessageDto("회원 가입이 완료되었습니다.", "/members/login/",
                RequestMethod.GET, null);
        if (bindingResult.hasErrors()) {
            return "members/createMemberForm";
        }
        try {
            memberService.join(form);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "members/createMemberForm";
        }
        return showMessageAndRedirect(message, model);
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        log.info("GET : loginForm()");
        model.addAttribute("memberLoginForm", new MemberLoginForm());
        return "members/loginForm";
    }

    @PostMapping("/login")
    public String login3(@Valid MemberLoginForm memberForm, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes rttr, Model model) {
        log.info("/members/login  <- post33333");

        if (bindingResult.hasErrors()) {
            return "members/loginForm";
        }

        HttpSession session = request.getSession();

        MessageDto message = new MessageDto("로그인이 완료되었습니다.", "/",
                RequestMethod.GET, null);


        MemberDTO loginMember = memberService.login(memberForm.getNickName(), memberForm.getPw());
        System.out.println("loginMember = " + loginMember);

        if (loginMember == null) {
            String failMessage = "닉네임 혹은 비밀번호가 잘못 되었습니다.";
            model.addAttribute("loginFail", failMessage);
            return "members/loginForm";
        }

        session.setAttribute("loginMember", loginMember);
        memberService.indexGet(loginMember, model);
        return showMessageAndRedirect(message, model);
    }

    @GetMapping(value = "/logout")
    public String logoutGET(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }

    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }
}
