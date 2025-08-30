@import hexlet.code.dto.UrlPage
@import hexlet.code.util.NamedRoutes
@import hexlet.code.repository.BaseRepository
@param UrlPage page

@template.page(
    page=page,
    content=@`
        <table>
            <caption>
                Сайт: ${page.getUrl().getName()}
            </caption>
            <tr>
                <td>ID</td>
                <td>${page.getUrl().getId()}</td>
            </tr>
            <tr>
                <td>Имя</td>
                <td>${page.getUrl().getName()}</td>
            </tr>
            <tr>
                <td>Дата создания</td>
                <td>${BaseRepository.formatTime(page.getUrl().getCreatedAt())}</td>
            </tr>
        </table>
        <h2>Проверки</h2>
        <form action="${NamedRoutes.checkUrl(page.getUrl().getId())}" method="post">
            <input type="submit" value="Запустить проверку">
        </form>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Код ответа</th>
                    <th>title</th>
                    <th>h1</th>
                    <th>description</th>
                    <th>Дата проверки</th>
                </tr>
            </thead>
            <tbody>
                @if(page.getChecks() != null)
                    @for(var check : page.getChecks())
                        <tr>
                            <td>${check.getId()}</td>
                            <td>${check.getStatusCode()}</td>
                            <td>${check.getTitle()}</td>
                            <td>${check.getH1()}</td>
                            <td>${check.getDescription()}</td>
                            <td>${BaseRepository.formatTime(check.getCreatedAt())}</td>
                        </tr>
                    @endfor
                @endif
            </tbody>
        </table>
    `
)