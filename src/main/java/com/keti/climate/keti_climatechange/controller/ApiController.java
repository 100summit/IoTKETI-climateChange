package com.keti.climate.keti_climatechange.controller;

import com.keti.climate.keti_climatechange.service.ApiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ApiController extends BaseController {

    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private ApiService apiService;


    @GetMapping("/api/getDeviceStatus/{agvNm}")
    public Mono getDeviceStatus(@PathVariable String agvNm) { return apiService.getDeviceStatus(agvNm); }

    @GetMapping("/api/getAgv/{agvId}")
    public Mono getAgv(@PathVariable String agvId) {
        return apiService.getAgv(agvId);
    }

    @GetMapping("/api/getAgvDetection/{agvId}")
    public Mono getAgvDetection(@PathVariable String agvId) {
        return apiService.getAgvDetection(agvId);
    }

    //메인화면 deployed , ruunig stopped 조회
    @GetMapping("/api/mlModelStatusList/{agvNm}")
    public Mono getmlModelStatusList(@PathVariable String agvNm) {
        return apiService.getmlModelStatusList(agvNm);
    }

    /**
     * 배포된 최신 ML 모델 조회 (배포상태)
     * @return
     */
    @GetMapping("/api/mlModelNewDeployInfo/la/{agvNm}")
    public Mono getDeploymlModelNewList(@PathVariable String agvNm) {
        return apiService.mlModelNewDeployInfo(agvNm);
    }


    @GetMapping("/api/reportLine/{agvNm}")
    public Mono getReportLine(@PathVariable String agvNm) {
        return apiService.getReportLine(agvNm);
    }

    @GetMapping("/api/reportBar/{agvNm}")
    public Mono getReportBar(@PathVariable String agvNm) {
        return apiService.getReportBar(agvNm);
    }


    @GetMapping("/api/getAgvMlModel/{agvId}")
    public Mono getAgvMlModel(@PathVariable String agvId) {
        return apiService.getAgvMlModel(agvId);
    }

    @GetMapping("/api/getAgvTraining/{agvId}")
    public Mono getAgvTraining(HttpServletRequest request, @PathVariable String agvId) {
        return apiService.getAgvTraining(agvId, parseQueryParam(request));
    }

    @GetMapping("/api/getAgvModels/{agvNm}")
    public Mono getAgvModels(@PathVariable String agvNm) {
        return apiService.getAgvModels(agvNm);
    }

    @GetMapping("/api/getModels")
    public Mono getModels() {
        return apiService.getModels();
    }

    @GetMapping("/api/getAgvModelsForRi/{agvNm}")
    public Mono getAgvModelsForRi(@PathVariable String agvNm) {
        return apiService.getAgvModelsForRi(agvNm);
    }

    @GetMapping("/api/getModelInfo/{modelId}")
    public Mono getModelInfo(@PathVariable String modelId) {
        return apiService.getModelInfo(modelId);
    }

    @PostMapping("/api/insertModel")
    @ResponseBody
    public Mono insertModel(@RequestBody String data) {
        return apiService.insertModel(getInsertModelBody(data));
    }

    @PutMapping("/api/updateModel/{rn}")
    @ResponseBody
    public Mono updateModel(@PathVariable String rn , @RequestBody String data) {
        return apiService.updateModel(rn , getUpdateModelBody(data));
    }



    @PostMapping("/api/deployModel/{agvNm}")
    @ResponseBody
    public Mono deployModel(@PathVariable String agvNm , @RequestBody String data) {
        return apiService.deployModel(agvNm , getDeployModelBody(data));
    }

    @GetMapping("/api/getModelGroup/{agvNm}")
    public Mono getModelGroup(@PathVariable String agvNm) {
        return apiService.getModelGroup(agvNm);
    }

    @PutMapping("/api/putModelGroup/{agvNm}")
    public Mono putModelGroup(@PathVariable String agvNm, @RequestBody String data) {
        return apiService.putModelGroup(agvNm, getPutModelGroupBody(data));
    }

    @GetMapping("/api/getAgvCheck")
    public Mono getAgvCheck(@RequestParam(value = "resource", required = false) String resource) {
        return apiService.getAgvCheck(resource);
    }

    @GetMapping("/api/getAgvHistory/{agvNm}/{cra}/{crb}")
    public Mono getAgvHistory(@PathVariable String agvNm , @PathVariable String cra , @PathVariable String crb) {
        return apiService.getAgvHistory(agvNm , cra , crb);
    }

    @PutMapping("/api/chgStatus/{ri}")
    public Mono chgStatus(@PathVariable String ri, @RequestBody String data) {
        return apiService.putModelChgStatus(ri, getPutModelChgStatusBody(data));
    }

    @PutMapping("/api/chgInsOus/{ri}")
    public Mono chgInsOus(@PathVariable String ri, @RequestBody String data) {
        return apiService.putModelChgInsOus(ri, getPutModelChgInsOusBody(data));
    }




}
