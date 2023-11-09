package com.keti.climate.keti_climatechange.controller;

import com.keti.climate.keti_climatechange.service.DataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DataController extends BaseController{

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private DataService dataService;

    @GetMapping("/data/sites")
    @ResponseBody
    public List<Map<String, Object>> getSites() {

        List<Map<String, Object>> list = new ArrayList<>();

        list = dataService.getSites();

        return list;

    }

    @GetMapping("/data/site/{siteId}")
    @ResponseBody
    public Map<String, Object> getSite(@PathVariable String siteId) {

        Map<String, Object> map = new HashMap<>();
        map = dataService.getSite(siteId);

        return map;
    }

    @PutMapping("/data/site/update")
    @ResponseBody
    public void updateSite(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = getRequestBody(request);
        int result = dataService.updateSite(map);
        if (result == 1) {
            response.setStatus(HttpStatus.OK.value());
        }
    }

    @PostMapping("/data/agvStatus/insert")
    @ResponseBody
    public void insertAgvRunStatus(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = getRequestBody(request);
        int result = dataService.insertAgvRunStatus(map);
        if (result == 1) {
            response.setStatus(HttpStatus.OK.value());
        }
    }




    @PostMapping("/data/agvGetStatus")
    @ResponseBody
    public Map<String, Object> agvGetStatus(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = getRequestBody(request);
        Map<String, Object> rtnMap = new HashMap<>();
        String result = dataService.agvGetStatus(map);
        rtnMap.put("status",result);

        return rtnMap;
    }

    @PostMapping("/data/agvStatus/exist")
    @ResponseBody
    public Map<String, Object> agvRunStatusExist(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = getRequestBody(request);
        Map<String, Object> rtnMap = new HashMap<>();
        int result = dataService.agvRunStatusExist(map);
        rtnMap.put("exist",result);

        return rtnMap;
    }




    @PostMapping("/data/site/insert")
    @ResponseBody
    public void insertSite(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = getRequestBody(request);
        int result = dataService.insertSite(map);
        if (result == 1) {
            response.setStatus(HttpStatus.OK.value());
        }

    }

    @DeleteMapping("/data/site/delete/{siteId}")
    @ResponseBody
    public void deleteSite(@PathVariable String siteId, HttpServletResponse response) {
        int result = dataService.deleteSite(siteId);
        if (result == 1) {
            response.setStatus(HttpStatus.OK.value());
        }
    }


    @GetMapping("/data/agvs")
    @ResponseBody
    public List<Map<String, Object>> getAgvs(@RequestParam(value="siteId", required = false) String siteId) {

        List<Map<String, Object>> list = new ArrayList<>();

        list = dataService.getAgvs(siteId);

        return list;
    }


    @GetMapping("/data/agv/{agvId}")
    @ResponseBody
    public Map<String, Object> getAgv(@PathVariable String agvId) {

        Map<String, Object> map = new HashMap<>();
        map = dataService.getAgv(agvId);

        return map;
    }

    @PutMapping("/data/agv/update")
    @ResponseBody
    public void updateAgv(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = getRequestBody(request);
        int result = dataService.updateAgv(map);
        if (result == 1) {
            response.setStatus(HttpStatus.OK.value());
        }
    }

    @PostMapping("/data/agv/insert")
    @ResponseBody
    public void insertAgv(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = getRequestBody(request);
        int result = dataService.insertAgv(map);
        if (result == 1) {
            response.setStatus(HttpStatus.OK.value());
        }
    }


    @DeleteMapping("/data/agv/delete/{agvId}")
    @ResponseBody
    public void deleteAgv(@PathVariable String agvId, HttpServletResponse response) {
        int result = dataService.deleteAgv(agvId);
        if (result == 1) {
            response.setStatus(HttpStatus.OK.value());
        }
    }

}
