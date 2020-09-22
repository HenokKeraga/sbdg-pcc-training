**SDBG - THIS SET OF APPLICATIONS Demonstrates Spring Boot Data Gemfire**

The code is provided without warranty and is not expected to provide any real world usage or architectural designs
. It merely demonstrates the annotations and repository interfaces that allows for using Spring with Gemfire.

The code has two specific Spring Boot Applications applications which may be run locally out of the box or installed
 into PCF with Tanzu Gemfire (PCC) 
 
 Each Application has a Swagger Interface that can be accessed at the context root by attaching /swagger-ui.html as
  the path.
  
**FAKE LOADER** 

This application will allow you to generate fake `Person` objects into the `Person Repository` and test your gemfire
 installation using `gfsh`
 
**CACHE CLIENT**
This is a Gemfire Client that will operate against Gemfire (locally, or in the cloud). It has a swagger interface
 that will allow various `CRUD` operations as a demonstration of `SBDG` out of the box.
 
Please feel free to contact me anytime vit GIT and/or connect with me at linked in via [this link here](https://www
.linkedin.com/in/terry-trippany/) and send me a message!

Thank you,
**_Terry Trippany (Trip)_**