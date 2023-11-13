
<!DOCTYPE html>
<html>
<head>
    <title>Work in Progress</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f1f1f1;
            text-align: center;
        }

        .wip-container {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            max-width: 400px;
            margin: 100px auto;
            padding: 20px;
        }

        h1 {
            color: #333;
        }

        p {
            color: #555;
        }

        img {
            width: 60px;
            height: auto;
        }
    </style>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
<div class="wip-container">
    <img src="${pageContext.request.contextPath}/image/logo.png">
    <h1>Work in Progress</h1>
    <p>We're working on something awesome! Please check back later.</p>
</div>
</body>
</html>
