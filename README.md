## Project structure

### App

Contains everything related to app

#### Feature Todo List

Inside of app and contains:

#### data

* **Entity:** this is the object received from API
* **Local:** this object extends from RealmObject and is necessary to create the database
* **Remote:** interface to API
* **Repository:** contains an interface to be implemented by usecase
  * RepositoryImp class to manage responses from server
  * LocalRepository class to manage data to save in local database
  * **mapper:**
	* EntityToDomainMapper class: mapper that transforms object from entity to domain (model) and vice versa
	* LocalToDomainMapper class: mapper that transforms objects from local to domain (modal) and vice versa

#### domain

* **model:** bussiness logic
* **usecase:** actions that the user can trigger

#### presentation

* **di:** Dependency injection package, use this to inject presenters, adapters, at UI level
* **ui:** Contains activities, fragments, adapters, presenters

##### di

* dependency injection package, contains module to handle connection to services and repository

### Core

this module contain all the base classes for app

