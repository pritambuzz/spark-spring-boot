package com.accrete.ai.products.dataset;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;

public class GenerateProductDataset {
	@Autowired
    private static SparkSession sparkSession;
	
	public static boolean generateSparkDataset() {
		boolean blnGenerateDatsetStatus = false;
		try {
			Dataset<Row> PurchaseTableV5 = sparkSession.read()
		  	          .format("jdbc")
		  	          .option("url", "jdbc:mysql://169.6.169.254:3306/")
		  	          .option("dbtable", "distillerdb.purchasetablev5_test1")
		  	          .option("user", "root")
		  	          .option("password", "Accrete@12")
		  	          .option("ignoreLeadingWhiteSpace","true")
		  	          .option("ignoreTrailingWhiteSpace","true")
		  	          .load();
			PurchaseTableV5.createGlobalTempView("PurchaseTableV5");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return blnGenerateDatsetStatus;
	}
}
