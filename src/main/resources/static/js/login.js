$(document).ready(function() {
    

    $("#formlogin").validate({
        rules: {
            username: { required: true, minlength: 2},
            password: { required: true, minlength: 4, maxlength: 15},
        },
        messages: {
        	username: "Debe introducir un nombre con un minimo de dos caracteres.",
        	password: "Debe introducir un password con un minimo de cuatro caracteres.",
            //message : "El campo Mensaje es obligatorio.",
        },
        
    });
    
   $("#formNewuser").validate({
        rules: {
            name: { required: true, minlength: 2},
            email: { required:true, email: true},
            password: { required: true, minlength: 4, maxlength: 15},
        },
        messages: {
        	name: "Debe introducir un nombre con un minimo de dos caracteres.",
        	password: "Debe introducir un password con un minimo de cuatro caracteres.",
        	email: "Debe ingresar un correo válido.",
            //message : "El campo Mensaje es obligatorio.",
        },
        
    });
    

   $("#formSearchf").validate({
       rules: {
           title: { required: true, minlength: 1},
       },
       messages: {
    	   title: "Debe introducir un nombre.",
           //message : "El campo Mensaje es obligatorio.",
       },
       
   });
    
   
   $("#formEditfilm").validate({
       rules: {
           urlt: { required: true, url: true},
       },
       messages: {
    	   urlt: "Debe introducir un url.",
           //message : "El campo Mensaje es obligatorio.",
       },
       
   });
   
   
   
   $("#formEdituser").validate({
       rules: {
           name: { required: true, minlength: 2},
           email: { required:true, email: true},
       },
       messages: {
       	name: "Debe introducir un nombre con un minimo de dos caracteres.",
       	email: "Debe ingresar un correo válido.",
           //message : "El campo Mensaje es obligatorio.",
       },
       
   });
   
   
   
   $("#formMuser").validate({
       rules: {
           user: { required: true, minlength: 1},
       },
       messages: {
    	   user: "Debe introducir un nombre.",
           //message : "El campo Mensaje es obligatorio.",
       },
       
   });
   
   $("#formeMfilm").validate({
       rules: {
           title: { required: true, minlength: 1},
       },
       messages: {
    	   title: "Debe introducir un nombre.",
           //message : "El campo Mensaje es obligatorio.",
       },
       
   });
    
});