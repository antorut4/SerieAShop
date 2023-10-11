<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Eliminazione utente</title>
    <link rel="stylesheet" href="deleteUser.css">
</head>
<body>
<div class="container">
    <h1 class="titolo">Eliminazione utente</h1>
    <form action="/delete-user" method="post">
        <input type="text" name="username" class="form-control" placeholder="Username">
        <input type="submit" value="Elimina" class="form-control">
    </form>
</div>
</body>
</html>
