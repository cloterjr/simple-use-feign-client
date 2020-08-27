# simple-use-feign-client
Example on how to simple implement the use of feign client

####Start node mock-server
1. cd timeout
2. npm install
3. npm run start:dev

#####Configure the timeout on mock server by adjusting the timeout/src/index.ts appFakeLatencyMillis variable on line 7.
```
 const appFakeLatencyMillis = 480000 //8 minutes
```

To call the method just open the url bellow in the browser after running the spring boot api.
http://localhost:8080/users/1

#### To use RestTemplate instead of FeignClient adjust class RestTemplateTimeoutController on spring boot project.
1. Uncomment the code above.
```
//                Fake API with Rest Template
        RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
        String fooResourceUrl = "http://localhost:3000/users";

        final String url = String.format("%s/%s", fooResourceUrl, id);

        try{
            final User user = restTemplate.getForObject(url, User.class);
            return ResponseEntity.ok(user);
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
```

2. Comment the code Above

```
//        try{
//            final User user = restTemplate.getForObject(url, User.class);
//            return ResponseEntity.ok(user);
//        } catch(Exception ex) {
//            System.out.println(ex.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
```

#### Adjust Http Client Timeout on Spring Boot Project

1. RestTemplate
```
restTemplate:
 connectTimeout: 500000 // 8 min and 20 sec
 readTimeout: 500000 // 8 min and 20 sec
```

2. FeignClient
```
feign:
 client:
  config:
   default:
    connectTimeout: 500000 // 8 min and 20 sec
    readTimeout: 500000 // 8 min and 20 sec
```

