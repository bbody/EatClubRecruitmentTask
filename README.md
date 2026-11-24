# EatClub Recruitment Task

## Tech stack
- Java
- Spring Boot
- Maven
 Retrofit and OkHttp
- Jackson
- JUnit 5

## How to run the application

```bash
mvn spring-boot:run
```

The application should be available at `http://localhost:8080`

## Tests

```bash
mvn test
```

## Task1
Without a time of day:
```bash
curl "http://localhost:8080/deals"
```
### Test cases

[See the output](./TASK1_TESTS.MD).

### Assumptions

- The spec for the response wants things as strings, like the [provided API](https://eccdn.com.au/misc/challengedata.json), internally I represented as strong types
- Some deals had open and close on it (I presumed for lunc/dinner specials), the field on the output is called *restaurantOpen* and *restaurantClose*, so I ignored the deal time.

## Task2

```bash
curl "http://localhost:8080/peak_times"
```

### Output

```json
{
  "peakTimeStart": "6:00PM",
  "peakTimeEnd": "9:00PM"
}
```

### Assumptions

- I did not handle peaks going over midnight e.g. 10PM-1AM
- Not an efficient implementation O(n) calculation
