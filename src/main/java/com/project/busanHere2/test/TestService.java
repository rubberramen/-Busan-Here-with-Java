package com.project.busanHere2.test;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestMapper testMapper;

    public List<TestResponse> findAllShops() {
        return testMapper.findAllShops();
    }

}
