package io.shaka.http

import org.scalatest.{BeforeAndAfterEach, FunSuite}
import io.shaka.http.Request.GET
import io.shaka.http.Status.OK


class HttpsSpec extends FunSuite with BeforeAndAfterEach {
  var server: TestHttpServer = _

  test("does HTTPS"){
    val https = io.shaka.http.Http.https("src/test/certs/keystore-testing.jks", "password")

    val expected = "helloworld"
    val response = https(GET(server.url + expected))
    assert(response.status === OK)
    assert(response.entityAsString === expected)
  }

  override protected def beforeEach(){server = TestHttpServer.startHttps("src/test/certs/keystore-testing.jks", "password")}
  override protected def afterEach(){server.stop()}
}