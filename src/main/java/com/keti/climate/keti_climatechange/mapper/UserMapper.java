package com.keti.climate.keti_climatechange.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {

    Map<String, Object> getUserById(Map<String, Object> map);

    Map<String, Object> selectUser(Map<String, Object> map);

    int insertUser(Map<String, Object> map);

    int updateUser(Map<String, Object> map);

}
