### env prepare
```
    docker-compose -f docker-compose.yml up -d 
```

### login on mysql as root and grant schema 'order' PRIVILEGES to order 'test'
```
create schema order;
create user test@'%' identified by 'thoughtworks';
grant all privileges on *.* to test@'%';
flush privileges;

``` 
### db-migration
#### reference flyway : create new sql file and put into classpath: db/migration.
exec OrderServiceApplication$Main method

### Swagger UI http://localhost:8082/swagger-ui/index.html
### Swagger UI http://localhost:8082/swagger-ui/index.html

