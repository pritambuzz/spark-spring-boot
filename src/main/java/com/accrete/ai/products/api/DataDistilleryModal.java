package com.accrete.ai.products.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataDistilleryModal {
    @Autowired
    private SparkSession sparkSession; 

    public List<String> distillerData(String filterData, boolean flag) {
    	List<String> stringDataset = new ArrayList<>();
		try {
			Dataset<Row> PurchaseTableV5 = sparkSession.read()
		  	          .format("jdbc")
		  	          .option("url", "jdbc:mysql://169.60.169.254:3306/")
		  	          .option("dbtable", "distillerdb.purchasetablev5_test1")
		  	          .option("user", "root")
		  	          .option("password", "Accrete@12")
		  	          .option("ignoreLeadingWhiteSpace","true")
		  	          .option("ignoreTrailingWhiteSpace","true")
		  	          .load();
			PurchaseTableV5.createOrReplaceTempView("PurchaseTableV5");
			PurchaseTableV5.show();
			Dataset<Row> purchaseTable = sparkSession.sql("select * from PurchaseTableV5 limit 50");
			stringDataset = purchaseTable.toJSON().collectAsList();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return stringDataset;
    }
}