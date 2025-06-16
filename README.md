# QuanLyThuoc
This is tool for supervising prescription.

**Install dependencies**

1. Use command
   ``` shell
        mvn install:install-file `
          -Dfile="~\dependencies\swing-toast-notifications-1.0.1.jar" `
        -DgroupId="com.example.swing" `
          -DartifactId="swing-toast-notifications" `
        -Dversion="1.0.1" `
        -Dpackaging=jar
    ```
   
    
        
``` bash
   mvn install:install-file \
   -Dfile=/path/to/swing-toast-notifications-1.0.4.jar \
   -DgroupId=com.example.swing \
   -DartifactId=swing-toast-notifications \
   -Dversion=1.0.4 \
   -Dpackaging=jar
   ```
    

2. Then run command "mvn install" to install dependencies.