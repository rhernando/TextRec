/**
 * TODO: Put the licenses here
 */

package com.stratio.rdf.parser

import org.scalatest._

class ParseRdfLineTest extends FlatSpec with Matchers {
  val line = "<http://dbpedia.org/resource/Albedo> <http://purl.org/dc/terms/subject> <http://dbpedia.org/resource/Category:Climate_forcing> ."

  "A RdfEntity" should "be created from a text line" in {
    val rdfEntity = RdfEntity.toEntity(line).get

    rdfEntity.objName should be ("http://dbpedia.org/resource/Albedo")
    rdfEntity.objType should be ("http://purl.org/dc/terms/subject")
    rdfEntity.objClass should be ("http://dbpedia.org/resource/Category:Climate_forcing")

  }


}
