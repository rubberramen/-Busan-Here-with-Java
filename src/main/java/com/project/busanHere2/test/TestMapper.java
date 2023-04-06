package com.project.busanHere2.test;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {

    List<TestResponse> findAllShops();
}
