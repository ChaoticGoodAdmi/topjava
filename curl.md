#### get all meals
curl --location --request GET "http://localhost:8080/topjava/rest/meals/" 
#### get a meal with id
curl --location --request GET "http://localhost:8080/topjava/rest/meals/100002" 
#### get meals filtered between dates and times
curl --location --request GET "http://localhost:8080/topjava/rest/meals/filter?startDate=2020-01-30&endDate=2020-01-30&startTime=10%3A00&endTime=17%3A00" 
#### create a new meal
curl --location --request POST "http://localhost:8080/topjava/rest/meals/" --header "Content-Type: application/json" --data-raw "{"dateTime": "2020-11-20T10:00:00","description": "Завтрак","calories": 500}"
#### delete a meal with id
curl --location --request DELETE "http://localhost:8080/topjava/rest/meals/100003"
#### update a meal with id
curl --location --request PUT "http://localhost:8080/topjava/rest/meals/100002" --header "Content-Type: application/json" --data-raw "{"dateTime":"2020-01-30T07:00","description":"Обновлено","calories":1000}"
#### get all users
curl --location --request GET "http://localhost:8080/topjava/rest/admin/users"
#### get a user with id
curl --location --request GET "http://localhost:8080/topjava/rest/admin/users/100000"