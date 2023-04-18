package com.project.busanHere2.controller;

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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.tags.EditorAwareTag;

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

        return "redirect:/members/login";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        log.info("GET : loginForm()");
        model.addAttribute("memberForm", new MemberForm());
        return "members/loginForm";
    }

    @PostMapping("/login")   // TODO: 2023-04-14 014 작동하도록 
    public String login(MemberForm memberForm, HttpServletRequest request, RedirectAttributes rttr, Model model) {
        log.info("/members/login  <- post");
        HttpSession session = request.getSession();


        if (memberForm.getNickName().equals("") && memberForm.getPw().equals("")) {
            String failMessage = "닉네임과 비밀번호를 입력하지 않으셨습니다.";
            rttr.addFlashAttribute("loginFail", failMessage);
            return "redirect:/members/login";
        }

        MemberDTO loginMember = memberService.login(memberForm.getNickName(), memberForm.getPw());
        System.out.println("loginMember = " + loginMember);

        if (loginMember == null) {
            String failMessage = "닉네임 혹은 비밀번호가 잘못 되었습니다.";
            model.addAttribute("loginFail", failMessage);
            return "members/loginForm";
        }

        session.setAttribute("loginMember", loginMember);
//        return "redirect:/";
//        return "redirect:/members/index";  // TODO: 2023-04-18 018

        memberService.indexGet(loginMember, model);
        return "redirect:/";
    }

//    @GetMapping("/index")
//    public String indexGET(@SessionAttribute(name = "loginMember", required = false)MemberDTO loginMember, Model model) {
//        log.info("Controller indexGET");
//
//        model.addAttribute("loginMember", loginMember);
//
//        return "main/index";
//    }

    @GetMapping(value = "/logout")
    public String logoutGET(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }
}
