package com.project.busanHere2.controller;

import com.project.busanHere2.domain.member.MemberDTO;
import com.project.busanHere2.domain.member.MemberForm;
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
    public String create(@Valid MemberForm form, BindingResult bindingResult, Model model) {
        log.info("POST : /new");

        if (bindingResult.hasErrors()) {
            return "members/createMemberForm";
        }
        try {
            MemberForm memberForm = new MemberForm();
            memberForm.setting(form.getName(), form.getPw(), form.getNickName(), form.getSex());
            memberService.join(memberForm);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "members/createMemberForm";
        }

        return "redirect:/";
    }

    @GetMapping("/login") // TODO: 2023-02-17 017
    public String loginForm(Model model) {
        log.info("POST : loginForm()");
        model.addAttribute("memberForm", new MemberForm());
        return "members/loginForm";
    }

    @PostMapping("/login")
    public String login(MemberForm memberForm, HttpServletRequest request, RedirectAttributes rttr) {
//        log.info("{} {}", "hello", nickName);
        HttpSession session = request.getSession();


        MemberDTO loginMember = memberService.login(memberForm.getNickName(), memberForm.getPw());
        System.out.println("loginMember = " + loginMember);

        String failMessage = "아이디 혹은 비밀번호가 잘못 되었습니다.";
        if (loginMember == null) {
            rttr.addFlashAttribute("loginFail", failMessage);
            return "redirect:/login";
        }

        session.setAttribute("loginMember", loginMember);
        return "redirect:/";
    }

    @GetMapping(value = "/logout")
    public String logoutGET(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }
}
