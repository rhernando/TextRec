/**
 * TODO: Put the licenses here
 */

package com.stratio.rdf

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.scalatest.{BeforeAndAfterAll, FlatSpec}

import com.stratio.util.ConfUtil

trait SparkTextContext extends BeforeAndAfterAll {
  self: FlatSpec =>

  @transient var sc: SparkContext = _

  lazy val prop = ConfUtil.loadProperties("test")

  override def beforeAll {
    Logger.getRootLogger.setLevel(Level.WARN)

    System.setProperty("spark.cleaner.ttl", "300")
    sc = ConfUtil.getContext(prop)
  }

  override def afterAll {
    System.clearProperty("spark.driver.port")
    System.clearProperty("spark.hostPort")
    sc.stop()
    sc = null
  }
}
