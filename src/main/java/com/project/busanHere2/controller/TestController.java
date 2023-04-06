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
        return "review/reviewWrite1";
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
        return "review/list1";
    }

    @GetMapping("/post/view.do")
    public String openPostView(@RequestParam Long boardId, Model model) {
        ReviewResponse post = reviewService.findById(boardId);
        model.addAttribute("post", post);
        return "review/view1";
    }

    @PostMapping("/post/update.do")
    public String updatePost(ReviewRequest reviewRequest) {
        reviewService.update(reviewRequest);
        return "redirect:/post/list.do";
    }
}
