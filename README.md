Project structure

App: Contain everything related to app
Core: this module contain all the base classes for app

<b>Feature Todo List:</b><br>
	Inside of app and contains<br>
	data:<br>
		entity: this is the object received from API<br>
		local: this object extends from RealmObject and is necessary to create the database<br>
		remote: interface to API<br>
		repository:<br>
			contains an interface to be implemented by usecase<br>
			RepositoryImp class to manage responses from server<br>
			LocalRepository class to manage data to save in local database<br>
			mapper:<br>
				EntityToDomainMapper class: mapper that transforms object from entity to domain (model) and vice versa<br>
				LocalToDomainMapper class: mapper that transforms objects from local to domain (modal) and vice versa<br>
	domain:<br>
		model:<br>
		usecase:<br>
	presentation:<br>
		di: dependency injection package, use this to inject presenters, adapters, at UI level<br>
		ui: contains activities, fragments, adapters, presenters<br>
	di: dependency injection package, contains module to handle connection to services and repository<br>

