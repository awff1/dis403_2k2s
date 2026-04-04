<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Тест</title>
</head>
<body>

<h1>Main page</h1>

<h2></h2>

username :

<script>
    // 1. Берем токен, который ты сохранил при логине
    let token = localStorage.getItem('jwt_token');

    if (token) {
        try {
            // 2. Расшифровываем полезную нагрузку (payload) JWT токена
            // Токен состоит из: header.payload.signature
            const base64Url = token.split('.')[1];
            const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            const payload = JSON.parse(window.atob(base64));

            // 3. Показываем имя (обычно это поле 'sub')
            document.getElementById('user_display').innerText = payload.sub;

            console.log("Юзер из токена:", payload.sub);
        } catch (e) {
            console.error("Ошибка парсинга токена", e);
            window.location.href = '/login';
        }
    } else {
        // Если токена нет в памяти — на логин
        window.location.href = '/login';
    }
</script>

</body>
</html>