Project structure

App: Contain everything related to app
Core: this module contain all the base classes for app

<b>Feature Todo List:</b>
	Inside of app and contains
	data:
		entity: this is the object received from API
		local: this object extends from RealmObject and is necessary to create the database
		remote: interface to API
		repository:
			contains an interface to be implemented by usecase
			RepositoryImp class to manage responses from server
			LocalRepository class to manage data to save in local database
			mapper:
				EntityToDomainMapper class: mapper that transforms object from entity to domain (model) and vice versa
				LocalToDomainMapper class: mapper that transforms objects from local to domain (modal) and vice versa
	domain:
		model:
		usecase:
	presentation:
		di: dependency injection package, use this to inject presenters, adapters, at UI level
		ui: contains activities, fragments, adapters, presenters
	di: dependency injection package, contains module to handle connection to services and repository

