@import hexlet.code.dto.UrlsPage
@import hexlet.code.util.NamedRoutes

@param UrlsPage page

@template.page(
    page=page,
    content=@`
        <table>
            <caption>
                Сайты
            </caption>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Имя</th>
                    <th>Последняя проверка</th>
                    <th>Код ответа</th>
                </tr>
            </thead>
            <tbody>
                @for(var url : page.getUrls())
                    <tr>
                        <td>${url.getId()}</td>
                        <td><a href="${NamedRoutes.urlPath(url.getId())}">${url.getName()}</a></td>
                        <td></td>
                        <td></td>
                    </tr>
                @endfor
            </tbody>
        </table>
    `
)