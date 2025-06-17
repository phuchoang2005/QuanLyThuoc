# QuanLyThuoc
This is tool for supervising prescription.

**Install dependencies**

1. Use command
   ``` shell
        mvn install:install-file `
          -Dfile="dependencies\swing-toast-notifications-1.0.4.jar" `
        -DgroupId="com.example.swing" `
          -DartifactId="swing-toast-notifications" `
        -Dversion="1.0.4" `
        -Dpackaging=jar
    ```
   
    
        
``` bash
   mvn install:install-file \
   -Dfile=dependencies/swing-toast-notifications-1.0.4.jar \
   -DgroupId="com.example.swing" \
   -DartifactId="swing-toast-notifications" \
   -Dversion="1.0.4" \
   -Dpackaging=jar
   ```
    

2. Then run command "mvn install" to install dependencies.
3. All paths have been moved to **IconPathUtil.java**. We should consistently reference them from there.
4. All resources are located in the **resources** folder. Since paths are set to start from this folder by default, we should refer to subdirectories directly—for example: /images/icon.

