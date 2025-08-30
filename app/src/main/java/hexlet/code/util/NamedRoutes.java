package hexlet.code.util;

public class NamedRoutes {
    public static String urlsPath() {
        return "/urls";
    }

    public static String buildUrlPath() {
        return "/";
    }

    //Это нужно, чтобы не преобразовывать типы снаружи
    public static String urlPath(Integer id) {
        return urlPath(String.valueOf(id));
    }

    public static String urlPath(String id) {
        return "/urls/" + id;
    }

    public static String checkUrl(Integer id) {
        return checkUrl(String.valueOf(id));
    }

    public static String checkUrl(String id) {
        return "/urls/" + id + "/check";
    }
}
