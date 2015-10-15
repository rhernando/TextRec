/**
 * TODO: Put the licenses here
 */

package com.stratio.rdf.spark

import scala.language.implicitConversions

import org.apache.spark.rdd.RDD

import com.stratio.rdf.parser.RdfEntity

class RdfRDD(self: RDD[String]) {

  def entities: RDD[RdfEntity] = {
    self.flatMap(RdfEntity.toEntity)
  }
}

object RdfRDD {

  implicit def rdfFunctions(rdd: RDD[String]): RdfRDD = {
    new RdfRDD(rdd)
  }
}
