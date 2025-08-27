@import hexlet.code.dto.BuildUrlPage
@param BuildUrlPage page

@template.page(
    page=page,
    content=@`
        <h1>Анализатор страниц</h1>
        <h3>Бесплатно проверяйте сайты на SEO пригодность</h3>
        <form action="/urls" method="post">
            <div>
                <label>
                    <input type="text" name="url" required placeholder="Ссылка" title="Заполните это поле">
                </label>
            </div>
            <input type="submit" value="Проверить" class="btn btn-primary">
            <p>Пример: https://www.example.com</p>
        </form>
    `
)