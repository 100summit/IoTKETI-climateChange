package com.keti.climate.keti_climatechange.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Map;

@Service
public class ApiService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private WebClient webClient;

    String baseUrl ="";
    String url ="";


    public Mono getDeviceStatus(String agvNm) {
        return webClient.get()
                .uri("/{agvNm}/deviceStatus/la",agvNm)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(Map.class))
                .flatMap(map -> ((Map) map.get("m2m:cin")).isEmpty() ? null : Mono.just(map.get("m2m:cin")));
    }

    public Mono getAgv(String agvId) {

        return webClient.get()
                .uri("/{agvId}", agvId)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(Map.class))
                .flatMap(map -> Mono.just(map.get("m2m:ae")));

    }

    public Mono getAgvDetection(String agvId) {
        return webClient.get()
                .uri("/{agvId}/detection/la", agvId)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(Map.class))
                .flatMap(map -> Mono.just(map.get("m2m:cin")));
    }


    public Mono getAgvMlModel(String agvId) {
        return webClient.get()
                .uri("/{agvId}/mlModels/la", agvId)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(Map.class))
                .flatMap(map -> ((Map) map.get("m2m:cin")).isEmpty() ? Mono.just(new ArrayList()) : Mono.just(map.get("m2m:cin")));
    }

    public Mono getAgvTraining(String agvId, Map<String, Object> parameterMap) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/{agvId}/trainingData")
                        .queryParam("rcn", 8)
                        .queryParam("ty", 4)
                        .queryParam("cra", parameterMap.get("cra"))
                        .queryParam("crb", parameterMap.get("crb"))
                        .build(agvId))
                .retrieve()
                .bodyToMono(Map.class)
                .flatMap(map -> ((Map) map.get("m2m:cnt")).isEmpty() ? Mono.just(new ArrayList()) : Mono.just(((Map) map.get("m2m:cnt")).get("m2m:cin")));
    }

    public Mono getAgvModels(String agvNm) {
        return webClient.get()
                .uri("/{agvNm}/modelGroup/fopt",agvNm)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(Map.class))
                .flatMap(map -> ((Map) map.get("m2m:agr")).isEmpty() ? Mono.just(new ArrayList()) : Mono.just(((Map) map.get("m2m:agr")).get("m2m:rsp")));
    }


    public Mono getModels() {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/radarEyeModels")
                        .queryParam("rcn", 8)
                        .queryParam("ty", 107)
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .flatMap(map -> ((Map) map.get("m2m:mrp")).isEmpty() ? Mono.just(new ArrayList()) : Mono.just(((Map) map.get("m2m:mrp")).get("m2m:mmd")));

    }

    public Mono getModelsDetail() {

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/radarEyeModels")
                        .queryParam("rcn", 8)
                        .queryParam("ty", 107)
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .flatMap(map -> ((Map) map.get("m2m:mrp")).isEmpty() ? Mono.just(new ArrayList()) : Mono.just(((Map) map.get("m2m:mrp")).get("m2m:mmd")));

    }


    public Mono getModelInfo(String modelId) {
        return webClient.mutate()
                .baseUrl(baseUrl)
                .build()
                .get()
                .uri("/"+modelId)
                .retrieve()
                .bodyToMono(Map.class);
                //.flatMap(map -> Mono.just(map.get("m2m:mmd")));
    }



    public Mono insertModel(JSONObject parameterJson) {

        return webClient.mutate()
                .build()
                .post()
                .uri("/radarEyeModels")
                .header("Content-Type", "application/json; ty=107")
                .body(BodyInserters.fromValue(parameterJson))
                .retrieve()
                .bodyToMono(Map.class)
                .flatMap(map -> Mono.just(map.get("m2m:mmd")));

    }

    public Mono deployModel(String agvNm , JSONObject parameterJson) {

        return webClient.mutate()
                .build()
                .post()
                .uri("/{agvNm}/mlModelList" , agvNm)
                .header("Content-Type", "application/json; ty=109")
                .body(BodyInserters.fromValue(parameterJson))
                .retrieve()
                .bodyToMono(Map.class)
                .flatMap(map -> Mono.just(map.get("m2m:dpm")));
    }

    public Mono getModelGroup(String agvNm) {
        return webClient.get()
                .uri("/{agvNm}/modelGroup", agvNm)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(Map.class))
                .flatMap(map -> Mono.just(map.get("m2m:grp")));
    }

    public Mono putModelGroup(String agvNm, JSONObject parameterJson) {
        return webClient.mutate()
                .build()
                .put()
                .uri("/{agvNm}/modelGroup",agvNm)
                .header("Content-Type", "application/json")
                .body(BodyInserters.fromValue(parameterJson))
                .retrieve()
                .bodyToMono(Map.class);
    }

    public Mono getAgvCheck(String resource) {

        return webClient.mutate()
                .baseUrl(url)
                .build()
                .get()
                .uri("/"+resource)
                .retrieve()
                .bodyToMono(Map.class)
                .flatMap(map -> Mono.just(map.get("m2m:ae")));
    }

    /**
     * 모델상태 조회
     * @return
     */
    public Mono getmlModelStatusList(String agvNm) {
        return webClient.get()
                .uri("/{agvNm}//mlModelList",agvNm)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(Map.class))
                .flatMap(map -> Mono.just(map.get("m2m:mdp")));
    }

    public Mono mlModelNewDeployInfo(String agvNm) {
        return webClient.get()
                .uri("/{agvNm}/mlModelList/la",agvNm)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(Map.class))
                .flatMap(map -> Mono.just(map.get("m2m:dpm")));
    }

    public Mono getReportLine(String agvNm) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/"+agvNm+"/report/counting")
                        .queryParam("ty", 4)
                        .queryParam("rcn", 8)
                        .queryParam("lim", 5)
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .flatMap(map -> Mono.just(map.get("m2m:cnt")));
    }

    public Mono updateModel(String rn , JSONObject parameterJson) {

        logger.info("data:"+BodyInserters.fromValue(parameterJson));

        return webClient.mutate()
                .build()
                .put()
                .uri("/radarEyeModels/{rn}",rn)
                .header("Content-Type", "application/json")
                .body(BodyInserters.fromValue(parameterJson))
                .retrieve()
                .bodyToMono(Map.class)
                .flatMap(map -> Mono.just(map.get("m2m:mmd")));

    }


    public Mono getReportBar(String agvNm) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/"+agvNm+"/report/material/la")
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .flatMap(map -> Mono.just(map.get("m2m:cin")));
    }

    public Mono getAgvHistory(String agvNm, String cra , String crb) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/"+agvNm+"/report")
                        .queryParam("rcn", 8)
                        .queryParam("ty", 4)
                        .queryParam("cra", cra)
                        .queryParam("crb", crb)
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .flatMap(map -> ((Map) map.get("m2m:cnt")).isEmpty() ? Mono.just(new ArrayList()) : Mono.just(((Map) map.get("m2m:cnt")).get("m2m:cin")));
    }

    public Mono getAgvModelsForRi(String agvNm) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/"+agvNm+"/mlModelList")
                        .queryParam("ty", 109)
                        .queryParam("rcn", 8)
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .flatMap(map -> ((Map) map.get("m2m:mdp")).isEmpty() ? Mono.just(new ArrayList()) : Mono.just(((Map) map.get("m2m:mdp")).get("m2m:dpm")));

    }

    public Mono putModelChgStatus(String ri , JSONObject putModelChgStatusBody) {

        return webClient.mutate()
                .baseUrl(url)
                .build()
                .put()
                .uri("/"+ri)
                .header("Content-Type", "application/json")
                .body(BodyInserters.fromValue(putModelChgStatusBody))
                .retrieve()
                .bodyToMono(Map.class);

    }


    public Mono putModelChgInsOus(String ri, JSONObject putModelChgInsOusBody) {
        return webClient.mutate()
                .baseUrl(url)
                .build()
                .put()
                .uri("/"+ri)
                .header("Content-Type", "application/json")
                .body(BodyInserters.fromValue(putModelChgInsOusBody))
                .retrieve()
                .bodyToMono(Map.class);

    }
}
