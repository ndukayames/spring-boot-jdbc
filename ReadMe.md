- Each Tutorial has id, title, description, published status.
- The apis help to create, retrieve, update, delete Tutorials.
- the apis also support custom finder methods such as find by published status or by title.
- These are APIs that we need to provide:

 ### Methods	Urls	Actions
- POST	/api/tutorials	create new Tutorial
- GET	/api/tutorials	retrieve all Tutorials
- GET	/api/tutorials/:id	retrieve a Tutorial by :id
- PUT	/api/tutorials/:id	update a Tutorial by :id
- DELETE	/api/tutorials/:id	delete a Tutorial by :id
- DELETE	/api/tutorials	delete all Tutorials
- GET	/api/tutorials/published	find all published Tutorials
- GET	/api/tutorials?title=[keyword]	find all Tutorials which title contains keyword.

The API contains CRUD operations & finder methods with Spring Data Jdbc.
The database will be MySQL.