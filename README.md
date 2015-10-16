# TextRec
spark + orientdb + rdf data

## Description

At this point the program reads some files downloades from http://wiki.dbpedia.org/Downloads2015-04#titles, 
transform data to a graph (spark-graphX) and store this graph into orientDb

These files contains triples of the form
```
$object rdf:type $class.
```

## Orientdb
*DB version*: 2.1.4 

*link*: http://orientdb.com/wp-content/uploads/2014/05/linux.png

### Notes
In order to make it work with a non-root user, we should create a user an add permissions (read/write). 
We may need to update these permissions to allow this user to access to the cluster meta-info

## Orientdb connector

The *jar* is generated from https://github.com/rhernando/spark-orientdb-connector (forked from https://github.com/metreta/spark-orientdb-connector)

To make it work, we have to include orientdb dependencies in *build.sbt* (with the same version of our orientdb instance )




## Run

```
./bin/spark-submit --class com.stratio.rdf.CreateRDFGraph --name RDFOrient --jars /home/rhernando/proy/sandbox/TextRec/target/scala-2.10/rdforient.jar /home/rhernando/proy/sandbox/TextRec/target/scala-2.10/rdforient.jar dev /home/rhernando/proy/sandbox/lang/*
```
