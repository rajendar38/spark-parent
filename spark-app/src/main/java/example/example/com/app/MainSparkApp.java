package com.example.app;

import com.example.core.WordUtil;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;
import java.util.List;

public class MainSparkApp {
    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("SimpleWordCountApp")
                .getOrCreate();

        JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());

        List<String> words = WordUtil.getWords();
        JavaRDD<String> rdd = sc.parallelize(words);

        rdd.mapToPair(w -> new Tuple2<>(w, 1))
           .reduceByKey(Integer::sum)
           .collect()
           .forEach(t -> System.out.println(t._1 + " -> " + t._2));
        try {
            Thread.sleep(10 * 60 * 1000); // Sleep for 10 minutes (600,000 ms)
        } catch (InterruptedException e) {
            e.printStackTrace(); // or log the interruption
        }

        spark.stop();
    }
}

