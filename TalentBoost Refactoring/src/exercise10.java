public handleRequest(Request request, Response response) {
	// ...
	if (recordNotFound(request)) {
		response.writeStatusCode(404);
		return;
	}
	// ...
}

//Коментар:
//Според мен този метод би трябвало да е деклариран като част от класа Response
