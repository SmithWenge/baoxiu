#Manual

### MySQL Config
```
CREATE SCHEMA `baoxiu` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE USER 'baoxiu'@'localhost' IDENTIFIED BY 'baoxiu';
GRANT ALL PRIVILEGES ON baoxiu.* TO 'baoxiu'@'localhost' WITH GRANT OPTION;
```
+++*