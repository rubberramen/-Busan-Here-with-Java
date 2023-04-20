package com.project.busanHere2.controller;

import com.project.busanHere2.common.MessageDto;
import com.project.busanHere2.domain.review.ReviewRequest;
import com.project.busanHere2.domain.review.ReviewResponse;
import com.project.busanHere2.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final ReviewService reviewService;

    @GetMapping("/post/write.do")
    public String openPostWrite(@RequestParam(value = "boardId", required = false) final Long boardId,
                                Model model) {
        if (boardId != null) {
            ReviewResponse post = reviewService.findById(boardId);  // TODO: 2023-04-04 004
            model.addAttribute("post", post);
        }
        return "post/reviewWrite1";
    }

//    @PostMapping("/post/save.do")
    public String savePost(ReviewRequest reviewRequest) {
        log.info("1 =================");
        reviewService.save(reviewRequest);
        log.info("2 =================");
        return "redirect:/post/list.do";    // "redirect:review/list";
    }

    // 신규 게시글 생성
    @PostMapping("/post/save.do")
    public String savePost(final ReviewRequest params, Model model) {
        reviewService.save(params);
        MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list.do",
                RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
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

//    @PostMapping("/post/update.do")
    public String updatePost(ReviewRequest reviewRequest) {
        reviewService.update(reviewRequest);
        return "redirect:/post/list.do";
    }

    // 기존 게시글 수정
    @PostMapping("/post/update.do")
    public String updatePost(final ReviewRequest params, Model model) {
        reviewService.update(params);
        MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/post/list.do",
                RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

//    @PostMapping("/post/delete.do")
    public String deletePost(@RequestParam Long boardId) {
        reviewService.delete(boardId);
        return "redirect:/post/list.do";
    }

    // 게시글 삭제
    @PostMapping("/post/delete.do")
    public String deletePost(@RequestParam final Long boardId, Model model) {
        reviewService.delete(boardId);
        MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/post/list.do",
                RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

    // 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
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
