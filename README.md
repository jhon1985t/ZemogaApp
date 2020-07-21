# ZemogaApp
Jhon Jarol Tabares Orozco

Project for Zemoga App Test Application Android Developer

Share private project on github
Add branch master
Add branch develop
Create README file

Select Architecture

Clean Architecture
 * Create modules for every layer of the app (app, data, domain, usecases)
    -> App = contain the database, server, connections, api calls, local calls, the activities, fragments, viewmodels, Koin DI configuration and use, listen the usecases, data calls, domain objects
    -> Data = contain the data class objects to domain, datasources and repositories
    -> Domain = contain the Models of the objects in data, mappers transform from data objects to domain
    -> Uses Cases = contain the connection between the data repository and the app interaction calls into viewmodel

Solid Principles
 * Design Principles for make the app more readable, and maintanable

Third Libraries
 -> Retrofit = Handle connections with better cache integration and server response, petitions to API
 -> Room = Database local SQL querys, transformation data to better way that SQLite solution
 -> OkHttp = Intercept the calls to server
 -> Mockito = Test Library for mock class, services, etc
