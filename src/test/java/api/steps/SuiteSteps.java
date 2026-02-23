package api.steps;

import io.qameta.allure.Step;
import models.request.suite.post.SuiteRequestModel;
import models.response.suite.delete.SuiteDeleteResponseModel;
import models.response.suite.get.SuiteGetSuitesResponseModel;
import models.response.suite.post.SuiteCreateResponseModel;

import static api.specs.QASESpec.REQ_SPEC;
import static api.specs.QASESpec.responseWithStatusCode;
import static io.restassured.RestAssured.given;

public class SuiteSteps {

    @Step("Создать сьюту в проекте {projectCode}")
    public static SuiteCreateResponseModel createSuite(String projectCode, SuiteRequestModel suiteRequest) {
        return given()
                .spec(REQ_SPEC)
                .body(suiteRequest)
                .post("/suite/{code}", projectCode.toUpperCase())
                .then()
                .spec(responseWithStatusCode(200))
                .extract()
                .as(SuiteCreateResponseModel.class);
    }

    @Step("Получить список сьют проекта {projectCode}")
    public static SuiteGetSuitesResponseModel getSuites(String projectCode) {
        return given()
                .spec(REQ_SPEC)
                .get("/suite/{code}", projectCode.toUpperCase())
                .then()
                .spec(responseWithStatusCode(200))
                .extract()
                .as(SuiteGetSuitesResponseModel.class);
    }

    @Step("Удалить сьюту {suiteId} из проекта {projectCode}")
    public static SuiteDeleteResponseModel deleteSuite(String projectCode, Integer suiteId) {
        return given()
                .spec(REQ_SPEC)
                .delete("/suite/{code}/{id}", projectCode.toUpperCase(), suiteId)
                .then()
                .spec(responseWithStatusCode(200))
                .extract()
                .as(SuiteDeleteResponseModel.class);
    }
}
