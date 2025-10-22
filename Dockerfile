FROM apache/spark:3.4.0

# Copy core jar into Spark’s default jars dir
COPY spark-core/target/spark-core-1.0.0.jar /opt/spark/jars/

# Copy main app jar
COPY spark-app/target/spark-app-1.0.0.jar /opt/spark/app.jar
