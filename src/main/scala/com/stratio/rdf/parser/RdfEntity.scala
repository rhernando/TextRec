/**
 * TODO: Put the licenses here
 */

package com.stratio.rdf.parser

case class RdfEntity(objName: String, objType: String, objClass: String) {

  def relationClass: ((String, String), String) = {
    ((objName, objType), objClass)
  }
}

object RdfEntity {

  def toEntity(line: String): Option[RdfEntity] = {
    val fields = line.split("<|>").map(_.trim).filter(_.nonEmpty)

    if (fields.length >= 3) {
      val Array(n, t, o, _*) = fields
      Some(RdfEntity(n, t, o))
    } else {
      println(line)
      fields.foreach(println)
      None
    }
  }
}
