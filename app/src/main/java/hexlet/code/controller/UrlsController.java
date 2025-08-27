package hexlet.code.controller;

import hexlet.code.dto.BuildUrlPage;
import hexlet.code.dto.UrlPage;
import hexlet.code.dto.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.repository.UrlRepository;
import hexlet.code.util.NamedRoutes;
import hexlet.code.util.UrlParser;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.sql.SQLException;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlsController {
    public static void build(Context ctx) {
        var page = new BuildUrlPage();
        ctx.render("urls/build.jte", model("page", page));
    }

    public static void create(Context ctx) throws SQLException {
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
        var page = new UrlsPage(urls);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setAlertType(ctx.consumeSessionAttribute("alert"));
        ctx.render("urls/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var url = UrlRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Url not found"));
        var page = new UrlPage(url);
        ctx.render("urls/show.jte", model("page", page));
    }
}
