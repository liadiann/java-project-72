@import hexlet.code.dto.BuildUrlPage
@param BuildUrlPage page

@template.page(
    page=page,
    content=@`
        <div class="container-fluid bg-dark p-5">
            <div class="row">
                <div class="col-md-10 col-lg-8 mx-auto text-white">
                    <h1 class="display-3 mb-0">Анализатор страниц</h1>
                    <p class="lead">Бесплатно проверяйте сайты на SEO пригодность</p>
                    <form action="/urls" method="post" class="rss-form text-body">
                        <div class="row">
                            <div class="col">
                                <div class="form-floating">
                                    <input id="url-input" type="text" name="url" required autofocus placeholder="Ссылка"
                                        autocomplete="off" class="form-control w-100" title="Заполните это поле">
                                    <label for="url-input">Ссылка</label>
                                </div>
                            </div>
                            <div class="col-auto">
                                <button type="submit" class="btn btn-primary btn-lg h-100 px-sm-5">Проверить</button>
                            </div>
                        </div>
                    </form>
                    <p class="mt-2 mb-0 text-muted">Пример: https://www.example.com</p>
                </div>
            </div>
        </div>
    `
)