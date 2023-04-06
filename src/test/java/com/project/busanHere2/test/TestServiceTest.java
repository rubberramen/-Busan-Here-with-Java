package com.project.busanHere2.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestServiceTest {

    @Autowired
    TestService testService;

    @Test
    public void findAllShops() throws Exception {
        List<TestResponse> allShops = testService.findAllShops();
        for (TestResponse Shop : allShops) {
            System.out.println("Shop.toString() = " + Shop.toString());
        }
    }
}
