package com.project.busanHere2.controller;

import com.project.busanHere2.domain.review.ReviewRequest;
import com.project.busanHere2.domain.review.ReviewResponse;
import com.project.busanHere2.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final ReviewService reviewService;

    @GetMapping("/post/write.do")
    public String openPostWrite(@RequestParam(value = "boardId", required = false) final Long boardId, Model model) {
        if (boardId != null) {
            ReviewResponse post = reviewService.findById(boardId);  // TODO: 2023-04-04 004
            model.addAttribute("post", post);
        }
        return "post/reviewWrite1";
    }

    @PostMapping("/post/save.do")
    public String savePost(ReviewRequest reviewRequest) {
        log.info("1 =================");
        reviewService.save(reviewRequest);
        log.info("2 =================");
        return "redirect:/post/list.do";    // "redirect:review/list";
    }

    @GetMapping("/post/list.do")
    public String openPostList(Model model) {
        List<ReviewResponse> posts = reviewService.findAll();
        model.addAttribute("posts", posts);
        return "post/list1";
    }

    @GetMapping("/post/view.do")
    public String openPostView(@RequestParam Long boardId, Model model) {
        ReviewResponse post = reviewService.findById(boardId);
        model.addAttribute("post", post);
        return "post/view1";
    }

    @PostMapping("/post/update.do")
    public String updatePost(ReviewRequest reviewRequest) {
        reviewService.update(reviewRequest);
        return "redirect:/post/list.do";
    }

    @PostMapping("/post/delete.do")
    public String deletePost(@RequestParam Long boardId) {
        reviewService.delete(boardId);
        return "redirect:/post/list.do";
    }

    @PostMapping("/post/test01")
    public String test01(@RequestParam Long boardId) {
        System.out.println("boardId = " + boardId);
        return "redirect:/";
    }

    @GetMapping("/fragmentTest")
    public String fragmentTest() {
        return "template/fragments/fragmentMain";
    }

    @GetMapping("/layoutTest")
    public String layout() {
        return "template/layout/layoutMainTest";
    }

    @GetMapping("layoutExtendTest")
    public String layoutExtendsTest() {
        return "template/layoutExtend/layoutExtendMainTest";
    }

    @GetMapping("layoutExtendTest2")
    public String layoutExtendsTest2() {
        return "template/layoutExtend/layoutExtendMainTest2";
    }

    @GetMapping("layoutExtendTest3")
    public String layoutExtendsTest3() {
        return "template/layoutExtend/layoutExtendMainTest3";
    }

    @GetMapping("/layoutTest/list")
    public String reviews(Model model) {
        List<ReviewResponse> posts = reviewService.findAll();
        model.addAttribute("posts", posts);
        ReviewResponse reviewResponse = posts.get(0);
        model.addAttribute("reviewResponse", reviewResponse);
        return "template/layoutExtend/layoutExtendMainTest4";
    }
}
