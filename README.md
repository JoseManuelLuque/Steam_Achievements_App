# Manual Técnico API - Steam Achievements App

### 1. Introducción

#### Objetivo del documento

Este documento tiene como objetivo proporcionar una descripción técnica detallada de la aplicación **Steam Achievements App**, incluyendo su arquitectura, componentes principales y guías para su instalación, despliegue y mantenimiento.

#### Resumen del proyecto

**Steam Achievements App** es una aplicación móvil desarrollada en Kotlin para la plataforma Android. Su propósito es permitir a los usuarios visualizar y gestionar los logros de sus juegos en Steam, obteniendo información directamente desde la Steam Web API.

#### Audiencia objetivo

Este manual está dirigido a desarrolladores, ingenieros de software y personal técnico involucrado en el desarrollo, mantenimiento y despliegue de la aplicación.

### 2. Descripción general del sistema

#### Visión general

La aplicación permite a los usuarios autenticarse utilizando sus credenciales de Steam, acceder a una lista de sus juegos y visualizar los logros asociados a cada uno. Además, ofrece funcionalidades para filtrar logros completados y no completados, y proporciona detalles específicos de cada logro.

#### Arquitectura del sistema

La aplicación sigue una arquitectura basada en MVVM (Model-View-ViewModel), lo que facilita la separación de responsabilidades y mejora la mantenibilidad del código.

#### Tecnologías utilizadas

* **Lenguaje de programación**: Kotlin
* **Frameworks y bibliotecas**:
  * Retrofit: Para la comunicación HTTP con la Steam Web API.
  * Room: Para la gestión de la base de datos local.
  * Jetpack Navigation: Para la navegación entre las diferentes pantallas de la aplicación.
* **Base de datos**: Room (encapsula SQLite)
* **API externa**: Steam Web API
* **Herramientas de desarrollo**: Android Studio, Gradle, Jetpack Compose

### 3. Requisitos del sistema

#### Requisitos funcionales

* Autenticación de usuarios mediante credenciales de Steam.
* Visualización de la lista de juegos del usuario.
* Visualización y filtrado de logros por juego.
* Detalle específico de cada logro.
* Persistencia de datos de usuario y logros en una base de datos local.

#### Requisitos no funcionales

* **Rendimiento**: La aplicación debe responder de manera ágil a las interacciones del usuario, con tiempos de carga mínimos.
* **Usabilidad**: Interfaz intuitiva y fácil de navegar.
* **Confiabilidad**: Manejo adecuado de errores y excepciones, especialmente en operaciones de red.

#### Requisitos de hardware y software

* **Dispositivo**: Smartphone o tablet con Android 7.0 (Nougat) o superior.
* **Conectividad**: Conexión a Internet para interactuar con la Steam Web API.
* **Almacenamiento**: Al menos 50 MB de espacio disponible para la instalación y almacenamiento de datos locales.

### 4. Diseño técnico

#### Modelado de datos

La base de datos local se estructura en las siguientes entidades:

* **User**:
  * `id`: Identificador único.
  * `username`: Nombre de usuario.
  * `steamId`: Identificador de Steam.
  * `apiKey`: Clave de API de Steam.
* **Game**:
  * `id`: Identificador único.
  * `name`: Nombre del juego.
  * `imageUrl`: URL de la imagen del juego.
* **Achievement**:
  * `id`: Identificador único.
  * `gameId`: Identificador del juego asociado.
  * `name`: Nombre del logro.
  * `description`: Descripción del logro.
  * `achieved`: Booleano que indica si el logro ha sido obtenido.

### 5. Detalles de implementación

#### Estructura del código

El proyecto sigue la estructura estándar de una aplicación Android desarrollada en Kotlin:

* `MainActivity.kt`: Punto de entrada de la aplicación.
* `LoginViewModel.kt`: Manejo del inicio de sesión y persistencia.
* `SteamApiService.kt`: Implementación de Retrofit para acceder a Steam Web API.
* `SteamRepository.kt`: Manejo de datos de juegos y logros.

#### Configuraciones

* Uso de `sharedPreferences` para recordar sesión.
* Configuración de Retrofit con URL base `https://api.steampowered.com/`.

### 6. Guía de instalación y despliegue

#### Requisitos previos

* Android Studio instalado.
* API Key de Steam Web API e ID de Steam.

#### Pasos de instalación

1. Clonar el repositorio.
2. Configurar la API Key creando un usario.
3. Ejecutar la aplicación desde Android Studio.

### 7. Pruebas

#### Estrategia de pruebas

* Pruebas unitarias en ViewModels.
* Pruebas de integración con Retrofit.

#### Casos de prueba

* Inicio de sesión con credenciales incorrectas.
* Obtención de juegos sin conexión a Internet.

### 8. Mantenimiento y soporte

#### Resolución de problemas comunes

* **Error de conexión**: Verificar clave de API y conexión a Internet.
* **Datos no actualizados**: Limpiar caché de la aplicación.

#### Plan de mantenimiento

* Actualización periódica de dependencias.
* Revisión de compatibilidad con nuevas versiones de Android.
* Monitoreo de errores y optimización de rendimiento.

### 9. Seguridad

* **Almacenamiento seguro de API Key de forma local**.
* **Cifrado de credenciales de usuario**.
* **Validación de datos de entrada** para prevenir ataques de inyección.

### 10. Anexos

#### Glosario

* **Steam Web API**: Interfaz para obtener datos de Steam.
* **Retrofit**: Gestor de peticiones Http a la API
* **Room Database**: Base de datos local en Android.

#### Referencias

* [Steam Web API](https://developer.valvesoftware.com/wiki/Steam_Web_API)

#### Contacto

* Equipo de desarrollo: jluqgon214@g.educaand.es
