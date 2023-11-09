package com.keti.climate.keti_climatechange.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {

    protected Map<String, Object> getRequestBody(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            map = new ObjectMapper().readValue(request.getAttribute("requestBody").toString(), Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return map;
    }

    protected Map<String, Object> parseQueryParam(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Enumeration<String> enumeration = request.getParameterNames();

        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getParameter(key);
            map.put(key, value);
        }

        return map;
    }

    protected JSONObject getInsertModelBody(String data) {

        JSONObject body = new JSONObject();
        JSONObject con = new JSONObject();

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(data);
            JSONObject jsonData = (JSONObject) obj;

            //System.out.println("lbl:"+ (String) jsonData.get("outputLabels"));
            String lbl = (String) jsonData.get("outputLabels");
            String[] lblArr = lbl.split(",");

            con.put("lbl", lblArr);
            con.put("vr", (String) jsonData.get("version"));
            con.put("nm", (String) jsonData.get("model_name"));
            con.put("plf", (String) jsonData.get("platform"));
            con.put("mlt", jsonData.get("mlType"));
            con.put("dc", (String) jsonData.get("description"));
            con.put("ips", (String) jsonData.get("inputSample"));
            con.put("ous", "");
            con.put("mmu", (String) jsonData.get("mlModelUrl"));
            con.put("mmd", (String) jsonData.get("model"));

            body.put("m2m:mmd", con);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }

    protected JSONObject getUpdateModelBody(String data) {
        JSONObject body = new JSONObject();
        JSONObject cin = new JSONObject();

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(data);
            JSONObject jsonData = (JSONObject) obj;
            String lbl = (String) jsonData.get("outputLabels");
            String[] lblArr = lbl.split(",");

            //cin.put("nm", (String) jsonData.get("model_name"));
            cin.put("lbl", lblArr);
            cin.put("vr", (String) jsonData.get("version"));
            cin.put("nm", (String) jsonData.get("model_name"));
            cin.put("plf", (String) jsonData.get("platform"));
            cin.put("mlt", jsonData.get("mlType"));
            cin.put("dc", (String) jsonData.get("description"));
            cin.put("ips", (String) jsonData.get("inputSample"));
            cin.put("ous", "");

            body.put("m2m:mmd", cin);



        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;

    }

    protected JSONObject getDeployModelBody(String data) {

        JSONObject body = new JSONObject();
        JSONObject cin = new JSONObject();

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(data);
            JSONObject jsonData = (JSONObject) obj;

            cin.put("moid", (String) jsonData.get("modelId"));
            cin.put("inr", (String) jsonData.get("inTxt"));
            cin.put("our", (String) jsonData.get("outTxt"));
            body.put("m2m:dpm", cin);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }

    protected JSONObject getPutModelGroupBody(String data) {

        JSONObject body = new JSONObject();
        JSONObject grp = new JSONObject();

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(data);
            JSONObject jsonData = (JSONObject) obj;

            grp.put("mid", (JSONArray) jsonData.get("mids"));
            body.put("m2m:grp", grp);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }

    protected JSONObject getPutModelChgStatusBody(String data) {

        JSONObject body = new JSONObject();
        JSONObject grp = new JSONObject();

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(data);
            JSONObject jsonData = (JSONObject) obj;

            grp.put("mcmd", jsonData.get("mcmd"));
            body.put("m2m:dpm", grp);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }


    protected JSONObject getPutModelChgInsOusBody(String data) {

        JSONObject body = new JSONObject();
        JSONObject grp = new JSONObject();

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(data);
            JSONObject jsonData = (JSONObject) obj;
            grp.put("inr", jsonData.get("inr"));
            grp.put("our", jsonData.get("our"));
            body.put("m2m:dpm", grp);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return body;
    }



}
