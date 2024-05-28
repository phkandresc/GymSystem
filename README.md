# Sistema de Gestión de Gimnasio

## Descripción

Este proyecto es un sistema de gestión de gimnasio desarrollado en Java. El sistema permite la gestión de socios, pagos, clases y actividades, equipos y espacios, así como la generación de informes y análisis. Está diseñado para ser utilizado por los administradores del gimnasio para facilitar la operación diaria y mejorar la experiencia de los socios.

## Requisitos

- Java 8 o superior
- Biblioteca de JDBC para la conexión a la base de datos
- Biblioteca de PlantUML para la generación de diagramas (opcional)

## Características

### 1. Gestión de Socios
- Registro de nuevos socios con información personal y de contacto.
- Actualización de datos de socios existentes.
- Control de acceso al gimnasio mediante identificación de socios.
- Listar y buscar la información sobre los socios.

### 2. Gestión de Pagos
- Registro y seguimiento de pagos de membresía.
- Generación de facturas y recibos.
- Alertas automáticas para pagos vencidos.

### 3. Programación de Clases o Actividades
- Creación de clases o actividades con detalle de costos, horario y cupos.
- Creación de horarios de clases grupales, entrenamientos personalizados, etc.
- Reserva de plazas en clases y actividades.
- Notificaciones de cambios en los horarios de clases por WhatsApp/correo.

### 4. Gestión de Equipos y Espacios
- Inventario de equipos y máquinas de entrenamiento de acuerdo a los espacios que dispone el gimnasio.
- Control de mantenimiento y reparaciones.
- Registro de préstamos de equipos a los socios.

### 5. Informes y Análisis
- Análisis de registro de socios por mes.
- Generación de informes sobre la asistencia a clases o actividades.

### 6. Accesibilidad y Usabilidad
- Diseño de interfaces de usuario intuitivas y fáciles de usar.
- Validación de entradas y mensajes adecuados que ayuden a no cometer errores en el uso de la aplicación.

## Estructura del Proyecto

```plaintext
src/
├── main/
│   ├── java/
│   │   ├── modelo/
│   │   │   ├── Admin.java
│   │   │   ├── Clase.java
│   │   │   ├── ControlAcceso.java
│   │   │   ├── Equipo.java
│   │   │   ├── Gimnasio.java
│   │   │   ├── Informe.java
│   │   │   ├── Membresia.java
│   │   │   ├── Persona.java
│   │   │   ├── Reserva.java
│   │   │   ├── Socio.java
│   │   │   ├── TarjetaRFID.java
│   │   │   └── Analisis.java
│   │   ├── servicio/
│   │   │   └── Servicios.java
│   │   └── control/
│   │       └── Controlador.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── modelo/
            └── PruebasUnitarias.java
