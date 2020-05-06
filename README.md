![logo](src/main/resources/img/icon.png)

![MIT License](https://codeclimate.com/github/JonSn0w/Hyde/badges/gpa.svg) ![MIT License](https://david-dm.org/tterb/Hyde.svg) ![MIT License](https://badge.fury.io/gh/tterb%2FHyde.svg) ![MIT License](https://img.shields.io/apm/l/atomic-design-ui.svg?)

# Description

**Sportify** is a JavaFX desktop application that collects and presents information on the top European football leagues using JPA Persistence API for database management.

Installation

---

**Requirements**

- [JDK 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
- [Apache Maven 3.6.0](https://maven.apache.org/download.cgi)
- [MySQL 8.0](https://dev.mysql.com/downloads/installer/)
- [Git 2.26](<https://git-scm.com/downloads](https://git-scm.com/downloads)>)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) (recommend )
- other dependencies you can see in [pom.xml](https://github.com/attiasas/FootballAssociationSystem/blob/master/pom.xml)

---

**How To Install**

You can modify or contribute to this project by following the steps below:

**1. Clone the repository**

- [Clone](https://help.github.com/en/github/creating-cloning-and-archiving-repositories/cloning-a-repository) or download to a location on your machine.

      ```bash
      # Clone the repository
      $> git clone https://github.com/attiasas/sportify.git

      # Navigate to the directory
      $> cd sportify
      ```

  **2. Database connection**

- Create an empty base
  `sql mysql> CREATE DATABASE IF NOT EXISTS sportify; mysql> USE sportify;`
- [Connecting it to IntelliJ IDEA](https://www.jetbrains.com/help/idea/working-with-the-database-tool-window.html#create_data_source)

- Set up a connection for JPA

  ```bash
  # Navigate to the directory
  $> cd sportify

  # Open config file
  $> nano src/main/resources/config.properties
  ```

````

```properties
# Edit with the correct values
      ...
db.database=sportify
db.port=3306
db.user=root
db.password=
      ...
````

**3. Assembly and launch of the project**

```bash
# Navigate to the directory
$> cd sportify

# compile and run
$> mvn package && java -jar target/sportify-0.0.1-SNAPSHOT.jar
```

**_Note to IntelliJ Users_**

This project uses the lombok library in some of the model classes to cut down on boiler plate code. IntelliJ requires that you enable annotation processing to prevent it showing errors from unimplemented methods. You can find this in _Settings->Build, Execution, Deployment->Compiler->Annotation Processors_ - Or simply search for **_"enable annotation processing"_** in the settings search bar.

**Run** the main application using the appropriate process for your set-up by IntelliJ Run/Debug.

Test Instructions

---

- Various testing methods such as:
  - Unit testing
  - Integration Testing
  - Functional Testing

* Run tests with Maven from the command line.

  ```bash
  $> mvn verify
  ```

Aditional Links

---

- [System Demo](https://sportify.com/) - todo
- [Trace Google Sheet](https://docs.google.com/spreadsheets/u/1/d/17n6JLLVUFWz8y_0te5-axufSroGrtFTEjjgtUX7Dgyw/edit?usp=drive_web&ouid=104494091826522493400)
- [Trello Board](https://trello.com/b/H4fnsVwZ/v3)

**Design Patterns and Techniques**

- Singleton
- Façade
- Dependency Injection

---

**_Team Members_**:

| Name             | Username                                    | Contact Info            |
| ---------------- | ------------------------------------------- | ----------------------- |
| _Amir Gabbay_    | [AmirGabayy](https://github.com/AmirGabayy) | gabayam@post.bgu.ac.il  |
| _Amit Damri_     | [amitdamri](https://github.com/amitdamri)   | amitdamr@post.bgu.ac.il |
| _Avihai Serfati_ | [serfati](https://github.com/serfati)       | serfata@post.bgu.ac.il  |
| _Asaf Attias_    | [attiasas93](https://github.com/attiasas93) | assafattias93@gmail.com |
| _Dvir Simchon_   | [dvirsimhon](https://github.com/dvirsimhon) | dvirsim@post.bgu.ac.il  |

> Author _serfati_

## License

This program is free software: you can redistribute it and/or modify it under the terms of the **MIT LICENSE** as published by the Free Software Foundation.