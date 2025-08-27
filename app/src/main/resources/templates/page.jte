@import gg.jte.Content
@import hexlet.code.dto.BasePage

@param Content content
@param BasePage page = null

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Анализатор страниц</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>
    <body>
        @if(page != null && page.getFlash() != null)
            <div class="alert ${page.getAlertType()}" role="alert">
                <p>${page.getFlash()}</p>
            </div>
        @endif
        ${content}
    </body>
</html>