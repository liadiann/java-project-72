package hexlet.code.controller;

import hexlet.code.dto.BuildUrlPage;
import hexlet.code.dto.UrlPage;
import hexlet.code.dto.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import hexlet.code.util.UrlParser;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import kong.unirest.core.Unirest;
import org.jsoup.Jsoup;

import java.sql.SQLException;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlsController {
    public static void build(Context ctx) {
        var page = new BuildUrlPage();
        ctx.render("urls/build.jte", model("page", page));
    }

    public static void create(Context ctx) {
        var originalUrl = ctx.formParam("url");
        try {
            var normalizedUrl = UrlParser.getNormalizedUrl(originalUrl);
            if (UrlRepository.findByName(normalizedUrl).isPresent()) {
                ctx.sessionAttribute("flash", "Страница уже существует");
                ctx.sessionAttribute("alert", "alert-info");
                ctx.redirect(NamedRoutes.urlsPath());
            } else {
                var url = new Url(normalizedUrl);
                UrlRepository.save(url);
                ctx.sessionAttribute("flash", "Страница успешно добавлена");
                ctx.sessionAttribute("alert", "alert-success");
                ctx.redirect(NamedRoutes.urlsPath());
            }
        } catch (Exception e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.sessionAttribute("alert", "alert-danger");
            var page = new BuildUrlPage();
            page.setFlash(ctx.consumeSessionAttribute("flash"));
            page.setAlertType(ctx.consumeSessionAttribute("alert"));
            ctx.render("urls/build.jte", model("page", page));
        }
    }

    public static void index(Context ctx) throws SQLException {
        var urls = UrlRepository.getEntities();
        var checks = UrlCheckRepository.findChecksWithLastCreatedAt();
        var page = new UrlsPage(urls, checks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setAlertType(ctx.consumeSessionAttribute("alert"));
        ctx.render("urls/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Integer.class).get();
        var url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Url not found"));
        var urlChecks = UrlCheckRepository.findUrlChecksForUrlId(id);
        var page = new UrlPage(url, urlChecks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setAlertType(ctx.consumeSessionAttribute("alert"));
        ctx.render("urls/show.jte", model("page", page));
    }

    public static void check(Context ctx) throws SQLException {
        var urlId = ctx.pathParamAsClass("id", Integer.class).get();
        var url = UrlRepository.find(urlId)
                .orElseThrow(() -> new NotFoundResponse("Url not found"));
        var check = new UrlCheck(urlId);
        var response = Unirest.get(url.getName()).asString();
        check.setStatusCode(response.getStatus());
        var html = response.getBody();
        var document = Jsoup.parse(html);
        check.setTitle(document.title());
        var elementH1 = document.selectFirst("h1");
        var h1 = elementH1 == null ? null : elementH1.text();
        check.setH1(h1);
        var elementDescription = document.selectFirst("meta[name=description]");
        var description = elementDescription == null ? null : elementDescription.attr("content");
        check.setDescription(description);
        UrlCheckRepository.save(check);
        ctx.sessionAttribute("flash", "Страница успешно проверена");
        ctx.sessionAttribute("alert", "alert-success");
        ctx.redirect(NamedRoutes.urlPath(urlId));
    }
}
