<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Colorful JSP Page</title>
    <style>
        body {
            background-color: #f0f8ff; /* Light blue background */
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #ff6347; /* Tomato color */
            padding: 20px;
            text-align: center;
            color: white;
            font-size: 2em;
        }

        .content {
            display: flex;
            justify-content: space-around;
            padding: 20px;
        }

        .section {
            width: 30%;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .section-1 {
            background-color: #98fb98; /* Pale green */
        }

        .section-2 {
            background-color: #ffd700; /* Gold */
        }

        .section-3 {
            background-color: #add8e6; /* Light blue */
        }

        footer {
            background-color: #4682b4; /* Steel blue */
            color: white;
            text-align: center;
            padding: 15px;
            position: fixed;
            width: 100%;
            bottom: 0;
        }

        h2 {
            color: #333;
        }

        p {
            font-size: 1.1em;
            color: #666;
        }

    </style>
</head>
<body>

    <header>
        Welcome to My Colorful JSP Page
    </header>

    <div class="content">
        <div class="section section-1">
            <h2>Section 1</h2>
            <p>This section is styled with a light green background.</p>
        </div>
        <div class="section section-2">
            <h2>Section 2</h2>
            <p>This section is styled with a bright yellow (gold) background.</p>
        </div>
        <div class="section section-3">
            <h2>Section 3</h2>
            <p>This section is styled with a light blue background.</p>
        </div>
    </div>

    <footer>
        &copy; 2025 Colorful JSP Page
    </footer>

</body>
</html>
