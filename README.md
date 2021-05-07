# Sumativa3-back-Herrera
Recomendaciones de uso (README):
Como primer punto, es primordial cambiar las credenciales de la base de datos creada (user y password), esta base de datos utiliza el nombre “Herrera”.

Segundo, una vez desplegado el proyecto como spring boot app, es necesario poblar la base de datos. Para esto se debe partir por los usuarios, ya que sin un usuario autentificado no se puede acceder al sitio web. 
El primer usuario creado será idenficado automáticamente como rol administrador (ROL_ADMIN), y por lo tanto tendrá credenciales de para poder agregar y modificar usuarios; agregar, eliminar y crear productos y agregar categorías, no obstante, no podrá agregar productos al carrito de compras si no es directamente desde editar.
 
Los siguientes usuarios creados serán identificados como rol usuarios (ROL_USER) y por lo tanto podrá insertar y eliminar productos al carrito además de modificar directamente el contenido de este.  


Transversalmente se podrá utilizar la función de búsqueda ubicada en la esquina derecha del navbar
 

Por último, para el correcto funcionamiento se recomienda seguir el siguiente orden de población de la base de datos (desde cuenta admin):

1.	Usuario.
a.	Asociado a cada usuario se crea un carro de compra con su ID única
2.	Categoría.
3.	Producto.
¡Y que comiencen tus compras!
