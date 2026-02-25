package api.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import models.request.project.post.ProjectRequestModel;
import models.response.project.get.Entity;
import models.response.project.get.ProjectGetResponseModel;

import static api.specs.QASESpec.REQ_SPEC;
import static api.specs.QASESpec.responseWithStatusCode;
import static io.restassured.RestAssured.given;

public class ProjectSteps {

    static String path = "/project";

    @Step("Создать проект")
    public static ValidatableResponse createProject(ProjectRequestModel projectRequest, Integer statusCode) {
        return given()
                .spec(REQ_SPEC)
                .body(projectRequest)
                .post(path)
                .then()
                .spec(responseWithStatusCode(statusCode));
    }

    @Step("Удалить проект с кодом {0}")
    public static ValidatableResponse deleteProject(String projectCode, Integer statusCode) {
        return given()
                .spec(REQ_SPEC)
                .delete(path + "/" + projectCode.toUpperCase())
                .then()
                .spec(responseWithStatusCode(statusCode));

    }

    @Step("Отправка GET-запроса на получение списка проектов")
    public static ValidatableResponse getProjects() {
        return given()
                .spec(REQ_SPEC)
                .get(path)
                .then()
                .spec(responseWithStatusCode(200));
    }

    @Step("Удалить все проекты")
    public static void deleteAllProjects() {
        ProjectGetResponseModel response = getProjects()
                .extract()
                .as(ProjectGetResponseModel.class);

        if (response.getResult().getTotal() > 0) {
            response.getResult().getEntities().stream()
                    .map(Entity::getCode)
                    .forEach(code -> deleteProject(code, 200));
        }
    }
}
