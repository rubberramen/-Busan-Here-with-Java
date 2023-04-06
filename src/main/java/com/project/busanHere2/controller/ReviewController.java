package com.project.busanHere2.controller;

import com.project.busanHere2.domain.review.ReviewRequest;
import com.project.busanHere2.domain.review.ReviewResponse;
import com.project.busanHere2.domain.shop.ShopForm;
import com.project.busanHere2.service.ReviewService;
import com.project.busanHere2.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;
    private final ShopService shopService;

    @GetMapping("/new")
    public String reviewWriteForm(@RequestParam(value = "boardId", required = false) final Long boardId, Model model) {
        if (boardId != null) {
            ReviewResponse post = reviewService.findById(boardId);
            model.addAttribute("post", post);
        }
        List<ShopForm> allShops = shopService.findAllShops();
        model.addAttribute("allShops", allShops);
        return "review/reviewWrite";
    }

    @PostMapping("/new")
    public String saveReview(ReviewRequest reviewRequest) {
        log.info("포스트1  zzzzzzzzz");
        reviewService.save(reviewRequest);
        log.info("포스트2  zzzzzzzzz");
        return "redirect:/review/list";
    }

    @GetMapping("/list")
    public String reviewList(Model model) {
        List<ReviewResponse> posts = reviewService.findAll();
        model.addAttribute("posts", posts);
        return "review/reviewList";
    }

    @GetMapping("/{reviewId}")
    public String reviewDetail(@PathVariable Long reviewId, Model model) {
        ReviewResponse post = reviewService.findById(reviewId);
        model.addAttribute("post", post);

        return "review/reviewDetail";
    }

    @PostMapping("/update")
    public String updatePost(ReviewRequest reviewRequest) {
        reviewService.update(reviewRequest);
        Long boardId = reviewRequest.getBoardId();
        String s = String.valueOf(boardId);
        return "redirect:/review/" + s;
    }
}
