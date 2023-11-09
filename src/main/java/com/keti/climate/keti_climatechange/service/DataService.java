package com.keti.climate.keti_climatechange.service;

import com.keti.climate.keti_climatechange.mapper.DataMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DataService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private DataMapper dataMapper;

    public List<Map<String, Object>> getSites() {
        return dataMapper.getSites();
    }

    public Map<String, Object> getSite(String siteId) {
        return dataMapper.getSite(siteId);
    }

    public int updateSite(Map<String, Object> map) {
        return dataMapper.updateSite(map);
    }

    public int insertAgvRunStatus (Map<String, Object> map) {
        return dataMapper.insertAgvRunStatus(map);
    }


    public int insertSite(Map<String, Object> map) {
        return dataMapper.insertSite(map);
    }

    public int deleteSite(String siteId) {
        return dataMapper.deleteSite(siteId);
    }

    public List<Map<String, Object>> getAgvs(String siteId) {
        return dataMapper.getAgvs(siteId);
    }

    public Map<String, Object> getAgv(String agvId) {
        return dataMapper.getAgv(agvId);
    }

    public int updateAgv(Map<String, Object> map) {
        return dataMapper.updateAgv(map);
    }

    public int insertAgv(Map<String, Object> map) {
        return dataMapper.insertAgv(map);
    }

    public int deleteAgv(String agvId) {
        return dataMapper.deleteAgv(agvId);
    }

    public int agvRunStatusExist(Map<String, Object> map) {
        return dataMapper.agvRunStatusExist(map);
    }

    public String agvGetStatus(Map<String, Object> map) {
        return dataMapper.agvGetStatus(map);
    }
}
