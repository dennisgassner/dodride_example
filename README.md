# dodride_example
This project's weird name stands for DOmain DRIven DEsign Example and is quite meaningful because this project is a spring boot application designed using the principles of domain driven design.
The actual purpose is to learn and try domain driven design using spring boot. The application's fictional purpose is a flight booking system that offers REST Services to receive a list of all bookable flights, to calculate a bookable configuration consisting of a choosen flight and a list of booked seats, and to actual book this configuration.

This project also got an simple **[ui](https://github.com/dennisgassner/flight_booking_ui)**

## Business rules and specials
Initially there are two flights. Each flight got a departure and destination, an aircraft and within it some seats.
A booking referes to a flight and got a list of seats that are booked.
Not every seat costs the same: Business class costs mostly the double of an economy seat. And an economy seat that provides extra leg room also costs a bit more. In addition to that a flight basically costs more the less seats are available left which is implemented using domain events to inform the flight about new bookings.
PolygotPersistence is implied by the persistence of flights and bookings inside the default H2 memory and the additional mocked external service to a crm-system storing customers informations.
