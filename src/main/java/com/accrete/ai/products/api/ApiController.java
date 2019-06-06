package com.accrete.ai.products.api;

import java.util.List;

import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Owner
 */
@RequestMapping("api/v1/datadistillery")
@Controller
public class ApiController {
	
	@Autowired
    DataDistilleryModal dataDistilleryModal = new DataDistilleryModal();
     
    @RequestMapping("distillerData")
    public ResponseEntity<List<String>> distillerData(String filterData, boolean flag) throws AnalysisException {
        return new ResponseEntity<>(dataDistilleryModal.distillerData(filterData, flag), HttpStatus.OK);
    }    
}