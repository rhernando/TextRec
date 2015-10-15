/**
 * TODO: Put the licenses here
 */

package com.stratio.rdf.spark

import org.scalatest.{FlatSpec, Matchers}

import com.stratio.rdf.SparkTextContext

class ParseRdfRddTest extends FlatSpec with Matchers with SparkTextContext {

  val lines = "<http://dbpedia.org/resource/Albedo> <http://purl.org/dc/terms/subject> <http://dbpedia" +
    ".org/resource/Category:Radiation> .\n<http://dbpedia.org/resource/Anarchism> <http://purl.org/dc/terms/subject> " +
    "<http://dbpedia.org/resource/Category:Anarchism> .\n<http://dbpedia.org/resource/Anarchism> <http://purl" +
    ".org/dc/terms/subject> <http://dbpedia.org/resource/Category:Political_culture> .\n<http://dbpedia" +
    ".org/resource/Anarchism> <http://purl.org/dc/terms/subject> <http://dbpedia" +
    ".org/resource/Category:Political_ideologies> .\n<http://dbpedia.org/resource/Anarchism> <http://purl" +
    ".org/dc/terms/subject> <http://dbpedia.org/resource/Category:Social_theories> .\n<http://dbpedia" +
    ".org/resource/Anarchism> <http://purl.org/dc/terms/subject> <http://dbpedia.org/resource/Category:Anti-fascism>" +
    " .\n<http://dbpedia.org/resource/Anarchism> <http://purl.org/dc/terms/subject> <http://dbpedia" +
    ".org/resource/Category:Anti-capitalism> .\n<http://dbpedia.org/resource/Anarchism> <http://purl" +
    ".org/dc/terms/subject> <http://dbpedia.org/resource/Category:Far-left_politics> .\n<http://dbpedia" +
    ".org/resource/Autism> <http://purl.org/dc/terms/subject> <http://dbpedia.org/resource/Category:Autism> " +
    ".\n<http://dbpedia.org/resource/Autism> <http://purl.org/dc/terms/subject> <http://dbpedia" +
    ".org/resource/Category:Communication_disorders> .\n<http://dbpedia.org/resource/Autism> <http://purl" +
    ".org/dc/terms/subject> <http://dbpedia.org/resource/Category:Mental_and_behavioural_disorders> " +
    ".\n<http://dbpedia.org/resource/Autism> <http://purl.org/dc/terms/subject> <http://dbpedia" +
    ".org/resource/Category:Neurological_disorders> .\n<http://dbpedia.org/resource/Autism> <http://purl" +
    ".org/dc/terms/subject> <http://dbpedia.org/resource/Category:Neurological_disorders_in_children> " +
    ".\n<http://dbpedia.org/resource/Autism> <http://purl.org/dc/terms/subject> <http://dbpedia" +
    ".org/resource/Category:Pervasive_developmental_disorders> .\n<http://dbpedia.org/resource/Autism> <http://purl" +
    ".org/dc/terms/subject> <http://dbpedia.org/resource/Category:Psychiatric_diagnosis> .\n<http://dbpedia" +
    ".org/resource/A> <http://purl.org/dc/terms/subject> <http://dbpedia" +
    ".org/resource/Category:ISO_basic_Latin_letters> .\n<http://dbpedia.org/resource/A> <http://purl" +
    ".org/dc/terms/subject> <http://dbpedia.org/resource/Category:Vowel_letters> .\n<http://dbpedia" +
    ".org/resource/Achilles> <http://purl.org/dc/terms/subject> <http://dbpedia" +
    ".org/resource/Category:Characters_in_the_Iliad> .\n<http://dbpedia.org/resource/Ian_%22Molly%22_Meldrum> " +
    "<http://www.w3.org/2000/01/rdf-schema#label> \"Ian \\\"Molly\\\" M\n<http://dbpedia" +
    ".org/resource/List_of_Sites_of_Special_Scientific_Interest_in_Berwickshire_and_Roxburgh> <http://www.w3" +
    ".org/2000/01/rdf-schema#label> \"List of Sites of Special Scientific Interest in Berwickshire and Roxburgh\"@en ."

  "A RDD[String]" should "be converted to a RDD[Rdfentity]" in {
    val rddText = sc.parallelize(lines.split("\n"))
    import RdfRDD._
    val ents = rddText.entities

    ents.count() shouldBe lines.split("\n").length
  }

  "it" should "group entities in relations" in {
    import RdfRDD._
    import RdfEntityRDD._

    val entRels = sc.parallelize(lines.split("\n")).entities.entityRelations

    entRels.count() shouldBe 7
  }

  "it" should "become a Graph" in {
    import RdfRDD._
    import RdfEntityRDD._

    val graph = sc.parallelize(lines.split("\n")).entities.graphMode

    graph.outDegrees.count() shouldBe 7
  }
}
