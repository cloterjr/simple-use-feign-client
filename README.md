# simple-use-feign-client
Example on how to simple implement the use of feign client

to simulate install the json-server, see the link bellow.
https://github.com/typicode/json-server

run the command bellow
json-server --watch --port 3000 --delay 420000 ./src/main/resources/db/db.json

The --delay parameter is related to the timeout of the api response, to simulate a slow api response. In this case is configured to 7 minutes.

To call the methos just open the url bellow in the browser after running the spring boot api.
http://localhost:8080/users/1
