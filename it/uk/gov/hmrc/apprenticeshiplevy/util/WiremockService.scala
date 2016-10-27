package uk.gov.hmrc.apprenticeshiplevy.util

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock._
import com.github.tomakehurst.wiremock.core.WireMockConfiguration._
import org.scalatest.Informer
import com.github.tomakehurst.wiremock.common._

trait WiremockService extends IntegrationTestConfig with StandardOutInformer {
  lazy val notifier = new WiremockTestInformerNotifier(info, verboseWiremockOutput)

  info(s"Configuring wire mock server to listen on ${stubHost}:${stubPort} using responses configured in ${stubConfigPath}")
  lazy val wireMockServer = new WireMockServer(wireMockConfig.notifier(notifier).usingFilesUnderDirectory(stubConfigPath).port(stubPort).bindAddress(stubHost))

  def start() = {
    wireMockServer.start()
  }

  def stop() = {
    wireMockServer.stop()
  }
}

object WiremockService extends WiremockService