/**
 * TODO: Put the licenses here
 */

package com.stratio.util

import java.net.URL
import java.util.Properties
import scala.io.Source

import org.apache.spark.{SparkConf, SparkContext}

object ConfUtil {

  def loadProperties(env: String): Properties = {
    val prop = new Properties()
    val resource: URL = getClass.getResource(s"/$env/config.properties")
    prop.load(Source.fromURL(resource).reader())
    prop
  }

  def getContext(props: Properties): SparkContext = {
    new SparkContext(new SparkConf().setMaster(props.getProperty("master"))
      .setAppName(props.getProperty("title"))
      .set("spark.orientdb.clustermode", props.getProperty("orientdb.mode"))
      .set("spark.orientdb.connection.nodes", props.getProperty("orientdb.nodes"))
      .set("spark.orientdb.protocol", props.getProperty("orientdb.protocol"))
      .set("spark.orientdb.dbname", props.getProperty("orientdb.dbname"))
      .set("spark.orientdb.port", props.getProperty("orientdb.port"))
      .set("spark.orientdb.user", props.getProperty("orientdb.user"))
      .set("spark.orientdb.password", props.getProperty("orientdb.password")))
  }
}
