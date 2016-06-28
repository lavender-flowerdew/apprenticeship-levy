/*
 * Copyright 2016 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.apprenticeshiplevy.connectors

import org.joda.time.LocalDate
import org.scalatest.{Matchers, WordSpecLike}

class TaxYearTest extends WordSpecLike with Matchers {

  "forDate" should {
    "return previous tax year for April 5th" in {
      TaxYear.forDate(new LocalDate(2017, 4, 5)) shouldBe TaxYear(2016)
    }

    "return current tax year for April 6th" in {
      TaxYear.forDate(new LocalDate(2017, 4, 6)) shouldBe TaxYear(2017)
    }
  }

  "yearsInRange" should {
    "return an empty list if startDate > endDate" in {
      TaxYear.yearsInRange(LocalDate.now, LocalDate.now.minusDays(1)) shouldBe Seq()
    }

    "return a list of one year if startDate == endDate" in {
      val refDate = new LocalDate(2016, 6, 27)
      TaxYear.yearsInRange(refDate, refDate) shouldBe Seq(TaxYear(2016))
    }

    "return a list of three years" in {
      val startDate = new LocalDate(2014, 6, 27)
      val endDate = new LocalDate(2016, 6, 27)
      TaxYear.yearsInRange(startDate, endDate) shouldBe Seq(TaxYear(2014), TaxYear(2015), TaxYear(2016))
    }
  }


}