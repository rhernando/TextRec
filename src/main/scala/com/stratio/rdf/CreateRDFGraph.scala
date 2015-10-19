/**
 * TODO: Put the licenses here
 */

package com.stratio.rdf

import com.metreta.spark.orientdb.connector.api.OrientDBConnector

import com.stratio.rdf.spark.{RdfEntityRDD, RdfRDD}
import com.stratio.util.ConfUtil
import com.stratio.util.ConfUtil._
import com.metreta.spark.orientdb.connector._
import RdfRDD._
import RdfEntityRDD._

object CreateRDFGraph {

  def main(args: Array[String]) {
    val prop = loadProperties(args(0))
    val filesPath = args(1)

    println("Creating context")

    val sc = ConfUtil.getContext(prop)

    val graph = sc.textFile(filesPath).entities.graphMode

    implicit val _: OrientDBConnector = OrientDBConnector(sc.getConf)
    graph.saveGraphToOrient()
  }
}
