import io.gatling.core.Predef._
import io.gatling.core.body.Body
import io.gatling.core.session.Expression
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class GatlingNeo4JTest extends Simulation {

  val traversalQuery: Body with (Expression[String]) = StringBody("{ \"statements\" : [ { \"statement\" : \"MATCH (n:Person) WHERE id(n) = {0} RETURN n LIMIT 25\" }]}".format(223))

  // TODO convert from HTTP API to Bolt API to closer match the behaviour of the application calls

  val httpProtocolBuilder: HttpProtocolBuilder = http
    .baseUrl("http://localhost:7474") // Here is the root for all relative URLs
    .authorizationHeader("Basic bmVvNGo6UEBzc3cwcmQ")
    .acceptHeader("application/json") // Here are the common headers
    .contentTypeHeader("application/json")
    .header("X-Stream", "true")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")


  val scenarioBuilder: ScenarioBuilder = scenario("Execute Cypher Query")
    .exec(http("request_10")
      .post("/db/data/transaction/commit")
        .body(traversalQuery)
        .asJson
        .check(status.is(200))
        // TODO Check the response payload contains the node(s) selected
    )

  setUp(
    scenarioBuilder.inject(atOnceUsers(1000))
    //scenarioBuilder.inject(rampUsers(10000) over (10 seconds))
  ).protocols(httpProtocolBuilder)
}