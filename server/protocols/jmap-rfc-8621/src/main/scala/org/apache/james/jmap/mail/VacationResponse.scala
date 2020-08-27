/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/

package org.apache.james.jmap.mail

import java.time.ZonedDateTime

import org.apache.james.jmap.api.vacation.Vacation

import scala.compat.java8.OptionConverters._

case class VacationResponseId()

case class IsEnabled(value: Boolean)
case class FromDate(value: ZonedDateTime)
case class ToDate(value: ZonedDateTime)
case class Subject(value: String)
case class TextBody(value: String)
case class HtmlBody(value: String)

object VacationResponse {
  def asRfc8621(vacation: Vacation) = VacationResponse(
    id = VacationResponseId(),
    isEnabled = IsEnabled(vacation.isEnabled),
    fromDate = vacation.getFromDate.asScala.map(FromDate),
    toDate = vacation.getToDate.asScala.map(ToDate),
    subject = vacation.getSubject.asScala.map(Subject),
    textBody = vacation.getTextBody.asScala.map(TextBody),
    htmlBody = vacation.getHtmlBody.asScala.map(HtmlBody)
  )
}

case class VacationResponse(id: VacationResponseId,
                           isEnabled: IsEnabled,
                           fromDate: Option[FromDate],
                           toDate: Option[ToDate],
                           subject: Option[Subject],
                           textBody: Option[TextBody],
                           htmlBody: Option[HtmlBody])