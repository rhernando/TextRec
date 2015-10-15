/**
 * TODO: Put the licenses here
 */

package com.stratio.rdf.spark

import scala.language.implicitConversions

import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD

import com.stratio.rdf.parser.RdfEntity

class RdfEntityRDD(self: RDD[RdfEntity]) {

  def entityRelations: RDD[((String, String), List[String])] = {
    self.map(_.relationClass).combineByKey(
      (c: String) => List(c),
      (l: List[String], c: String) => l :+ c,
      (l1: List[String], l2: List[String]) => l1 ++ l2
    )
  }

  def graphMode: Graph[String, String] = {
    import RdfEntityRDD._

    val rels = self.entityRelations.persist()

    val vertices = VertexRDD(rels.
      flatMap({ case (nameRel: (String, String), listObjs: List[String]) =>
      Seq((nameRel._1.hashCode.toLong, nameRel._1)) ++ listObjs.map(lo => (lo.hashCode.toLong, lo))
    }))

    val edges = rels.flatMap({ case (nameRel: (String, String), listObjs: List[String]) =>
      listObjs.map(o => Edge(nameRel._1.hashCode.toLong, o.hashCode.toLong, nameRel._2))
    })

    Graph(vertices, edges)
  }
}

object RdfEntityRDD {

  implicit def rddToRdfFunctions(rdd: RDD[RdfEntity]): RdfEntityRDD = {
    new RdfEntityRDD(rdd)
  }
}
