package com.project.busanHere2.controller;

import com.project.busanHere2.domain.member.MemberDTO;
import com.project.busanHere2.domain.review.ReviewResponse;
import com.project.busanHere2.domain.shop.ShopForm;
import com.project.busanHere2.service.MemberService;
import com.project.busanHere2.service.ReviewService;
import com.project.busanHere2.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class MainController {

    @Autowired
    private final ShopService shopService;
    private final MemberService memberService;

    @GetMapping
    public String index(@SessionAttribute(name = "loginMember", required = false) MemberDTO loginMember,
                            Model model) {
        model.addAttribute("loginMember", loginMember);
        List<ShopForm> allShops = shopService.findAllShops();
        return "main/index";
    }

    @GetMapping("/shops")
    @ResponseBody
    public List<ShopForm> readShops(@RequestParam(required = false) String category) {
        List<ShopForm> shops = shopService.readShops(category);
        return shops;
    }
}
