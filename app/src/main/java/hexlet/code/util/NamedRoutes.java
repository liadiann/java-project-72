package hexlet.code.util;

public class NamedRoutes {
    public static String urlsPath() {
        return "/urls";
    }

    public static String buildUrlPath() {
        return "/";
    }

    //Это нужно, чтобы не преобразовывать типы снаружи
    public static String urlPath(Long id) {
        return urlPath(String.valueOf(id));
    }

    public static String urlPath(String id) {
        return "/urls/" + id;
    }
}
