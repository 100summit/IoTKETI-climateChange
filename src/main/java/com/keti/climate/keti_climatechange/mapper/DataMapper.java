package com.keti.climate.keti_climatechange.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataMapper {

    List<Map<String, Object>> getSites();

    Map<String, Object> getSite(String siteId);

    int updateSite(Map<String, Object> map);

    int insertSite(Map<String, Object> map);

    int deleteSite(String siteId);

    List<Map<String, Object>> getAgvs(String siteId);

    Map<String, Object> getAgv(String agvId);

    int updateAgv(Map<String, Object> map);

    int insertAgv(Map<String, Object> map);

    int deleteAgv(String agvId);

    int insertAgvRunStatus(Map<String, Object> map);

    int agvRunStatusExist(Map<String, Object> map);

    String agvGetStatus(Map<String, Object> map);
}
