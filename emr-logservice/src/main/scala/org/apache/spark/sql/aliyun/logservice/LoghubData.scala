/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.spark.sql.aliyun.logservice

import org.apache.spark.internal.Logging

abstract class LoghubData()
  extends Serializable {
  def getContent: Array[Byte]
  def toArray: Array[String]
}

class SchemaLoghubData(content: Array[(String, String)])
  extends LoghubData with Logging with Serializable {

  override def toArray: Array[String] = {
    content.map(_._2)
  }

  override def getContent: Array[Byte] = throw new UnsupportedOperationException
}

class RawLoghubData(project: String, store: String, shardId: Int, dataTime: java.sql.Timestamp,
    topic: String, source: String, content: Array[Byte])
  extends LoghubData {

  override def getContent: Array[Byte] = content

  override def toArray: Array[String] = throw new UnsupportedOperationException
}