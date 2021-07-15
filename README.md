# People API - java

### Sistema de gerenciamento de pessoas em API REST com Spring

# Endpoints
Base Url: https://api-people-java.herokuapp.com/ 

Create Person
-
* Criar usuário(pessoa)
```http
 POST /api/v1/people/register
```

List All 
-
* Lista todos os usuários
```http
 GET /api/v1/people/listAll
```

List By ID 
-
* Retorna os usuários referente ao ID
```http
 GET /api/v1/people/findById/{id}
```

Update 
-
* Atualiza o usuário referente ao ID
```http
 PUT /api/v1/people/updateById/{id}
```

Delete 
-
* Deleta o usuário referente ao ID
```http
 DELETE /api/v1/people/deleteById/{id}
```
