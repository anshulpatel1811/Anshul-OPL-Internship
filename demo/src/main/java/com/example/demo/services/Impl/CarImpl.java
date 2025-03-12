package com.example.demo.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Car;

@Component
public class CarImpl {

	static List<Car> cars = new ArrayList<>();

	static {

		cars.add(new Car(123, "BMW", "Black", "12.z"));
		cars.add(new Car(321, "Audi", "Blue", "17"));
		cars.add(new Car(456, "Ferrari", "Red", "67"));
		cars.add(new Car(654, "Jeep", "White", "fz"));
		cars.add(new Car(990, "Alfa Romeo", "Green", "ict"));
		cars.add(new Car(987, "Cadillac", "Yellow", "c2"));
	}

	public List<Car> getAll(Car car) {
		return cars;
	}

	public Car getById(Integer id) {
		Car car = cars.stream().filter(c -> c.getCarId().equals(id)).findFirst().get();

		return car;
	}

	public List<Car> carUpdate(Integer id, String carName) {

		Car car = getById(id);
		car.setCarName(carName);
		return cars;
	}

	public List<Car> deleteById(Integer id) {

		Car car = cars.stream().filter(c -> c.getCarId().equals(id)).findFirst().get();

		cars.remove(car);

		return cars;
	}

}
