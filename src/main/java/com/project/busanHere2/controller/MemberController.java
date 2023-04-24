package com.project.busanHere2.controller;

import com.project.busanHere2.common.MessageDto;
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
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private final MemberService memberService;

    @GetMapping("/new")
    public String addMember(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/new")
    public String addMember(@Valid MemberForm form, BindingResult bindingResult, Model model) {
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

    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }
}
