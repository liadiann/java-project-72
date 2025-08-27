@import hexlet.code.dto.UrlPage
@param UrlPage page

@template.page(
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
                <td>${String.valueOf(page.getUrl().getCreatedAt())}</td>
            </tr>
        </table>
    `
)