<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Car Showcase</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f8ff;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #333;
            color: white;
            padding: 20px;
            text-align: center;
        }

        .car-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            margin: 20px;
        }

        .car-item {
            width: 30%;
            text-align: center;
            margin: 10px;
            border: 1px solid #ddd;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 10px;
            background-color: #fff;
        }

        .car-item img {
            width: 100%;
            height: auto;
            border-radius: 10px;
        }

        .car-item h3 {
            color: #333;
            margin-top: 10px;
        }

        footer {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 15px;
            position: fixed;
            width: 100%;
            bottom: 0;
        }
    </style>
</head>
<body>

    <header>
        <h1>Car Showcase</h1>
        <p>Explore the best cars in the world!</p>
    </header>

    <div class="car-container">
        <div class="car-item">
            <img src="car1.jpg" alt="Car 1">
            
            <h3>Ferrari</h3>
        </div>

        <div class="car-item">
            <img src="https://example.com/car2.jpg" alt="Car 2">
            <h3>Lamborghini</h3>
        </div>

        <div class="car-item">
            <img src="https://example.com/car3.jpg" alt="Car 3">
            <h3>Mercedes-Benz</h3>
        </div>

        <div class="car-item">
            <img src="https://example.com/car4.jpg" alt="Car 4">
            <h3>Porsche</h3>
        </div>

        <div class="car-item">
            <img src="https://example.com/car5.jpg" alt="Car 5">
            <h3>Audi</h3>
        </div>

        <div class="car-item">
            <img src="https://example.com/car6.jpg" alt="Car 6">
            <h3>BMW</h3>
        </div>
    </div>

    <footer>
        &copy; 2025 Car Showcase
    </footer>

</body>
</html>
