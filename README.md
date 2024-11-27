# API 명세서

---

|기능|Method|URL|Request|Response|상태코드|
|:---:|:---:|:---:|:---:|:---:|:---:|
|환전 요청 수행|POST|/api/currency|-|{"message" : "요청에 성공하였습니다."}|상태코드 : 201|
|환전 요청 조회|GET|/api/currency/{userId}|{"userId" : 1}|{"message" : "요청에 성공하였습니다."}|상태코드 : 200|
|환전 요청 취소|PATCH|/api/currency/{userId}/{currencyId}|{"userId" : 1, "currencyId" : 1}|{"message" : "해당 요청을 취소하였습니다."}|상태코드 : 200|
|환전 요청 삭제|DELETE|/api/currency/{userId}|{"userId" : 1}|{"message" : "모든 요청이 삭제되었습니다."}|상태코드 : 200|

---

# ERD

---

![ERD 스크린샷](https://github.com/Mini9709/sample_user_currency/blob/main/ERD-user_currency.png)
