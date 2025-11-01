# \# 🎬 VideoClub Online

# \### Proyecto del equipo: \*\*Andrés Fernández Pereira y Eduardo Pérez García\*\*

# \*\*Equipo\_ANDROIDE\*\*

# 

# ---

# 

# \## 🧩 1 - Toolbar

# 

# La \*\*Toolbar\*\* es el componente superior de la aplicación \*\*VideoClub Online\*\*, desarrollada con \*\*Jetpack Compose\*\*.  

# Cumple la función de ofrecer una navegación rápida entre las principales secciones de la app.

# 

# \### 🔧 Características:

# \- Diseñada con \*Jetpack Compose\* utilizando \*\*Box\*\* y \*\*Row\*\* para la disposición de los elementos.  

# \- Presenta un \*\*degradado de color (de azul oscuro a violeta)\*\* que da un aspecto moderno y visualmente atractivo.  

# \- Incluye cinco iconos interactivos:

# &nbsp; - 🏠 \*\*Inicio:\*\* lleva al usuario a la pantalla principal.  

# &nbsp; - 🎥 \*\*Cámara:\*\* permite acceder a la sección de cámara y QR.  

# &nbsp; - 👤 \*\*Perfil:\*\* abre la información del usuario.  

# &nbsp; - 🚪 \*\*Salir:\*\* cierra sesión o retorna a la pantalla de inicio.  

# &nbsp; - 🔙 \*\*Volver:\*\* retrocede a la pantalla anterior.  

# \- Cada icono usa un color \*\*amarillo dorado (#FFC107)\*\* que contrasta con el fondo degradado.  

# \- Se fusiona visualmente con la zona del reloj (ambas comparten el mismo fondo degradado).  

# \- Optimizada para usarse dentro de un \*\*Scaffold\*\*, permaneciendo fija en la parte superior de la interfaz.

# 

# ---

# 

# \## 🎞️ 2 - Funciones principales

# 

# \### 🎥 Catálogo de películas

# \- Películas agrupadas por categoría (Drama, Comedia, Acción, etc.).  

# \- Cada grupo tiene un título visible.  

# \- Visualización de todas las películas disponibles para alquilar.  

# \- Filtros por \*\*género, año, popularidad y novedades\*\*.  

# \- Búsqueda rápida por título o actor.  

# 

# \### 📽️ Alquiler de películas

# \- Selección de una película para alquilar.  

# \- Confirmación del alquiler con duración predeterminada (por ejemplo, 48 horas).  

# \- Visualización del estado del alquiler (\*activo, vencido\*).  

# 

# \### 🔁 Devolución de películas

# \- Opción para \*\*marcar una película como devuelta\*\*.  

# \- Registro automático de la devolución para liberar la película.  

# \- Permite nuevos alquileres tras la devolución.  

# 

# \### 👤 Gestión de usuario

# \- Registro e inicio de sesión.  

# \- Historial de alquileres (actuales y pasados).  

# \- Información personal y configuración de preferencias.  

# \- Perfil del usuario con opción de modificar datos.

# 

# ---

# 

# \## 🧭 3 - Navegación y uso de la app

# 

# \### 🚀 Inicio

# \- Al abrir la app, se muestra el \*\*catálogo principal\*\* de películas.  

# \- El usuario puede usar \*\*buscador o filtros\*\* para encontrar películas.  

# 

# \### 🎬 Alquilar una película

# 1\. Selecciona una película.  

# 2\. Pulsa el botón \*\*“Alquilar”\*\*.  

# 3\. Se muestra un resumen y la duración del alquiler.  

# 4\. Confirma la operación.  

# 5\. La película se agrega a la lista de alquileres activos.

# 

# \### 🎞️ Ver películas alquiladas

# \- Desde el menú principal, accede a \*\*“Mis alquileres”\*\*.  

# \- Muestra todas las películas activas con la fecha de vencimiento.  

# \- Desde aquí se puede \*\*reproducir o devolver\*\* la película.

# 

# \### 🔄 Devolver una película

# 1\. Selecciona la película en “Mis alquileres”.  

# 2\. Pulsa \*\*“Devolver película”\*\*.  

# 3\. El sistema actualiza el estado y libera la película.  

# 4\. La devolución queda registrada en el historial.

# 

# ---

# 

# \## 📸 4 - Cámara y QR

# 

# \- La app incluye acceso rápido a la \*\*cámara\*\* desde la Toolbar.  

# \- Permite realizar fotos o escanear \*\*códigos QR\*\*.  

# \- Dispone de un diseño limpio con botones grandes y colores del tema Material 3.  

# \- Los botones principales:

# &nbsp; - 📷 \*\*Hacer foto\*\*

# &nbsp; - 🔲 \*\*QR\*\*

# 

# ---

# 

# \## 🌈 5 - Diseño general

# 

# \- Uso de \*\*colores temáticos de MaterialTheme.colorScheme\*\*.  

# \- \*\*Scroll vertical y horizontal\*\* adaptado a cada pantalla.  

# \- Diferentes \*\*paletas de color por pantalla\*\* para mantener variedad visual.  

# \- Compatible con \*\*Material Design 3\*\*, usando tipografía y esquemas dinámicos.  

# 

# ---

# 

# \## 🖼️ 6 - Pantallas incluidas

# 

# \- 🏠 \*\*Pantalla Login\*\*  

# \- ➕ \*\*Pantalla Crear Usuario\*\*  

# \- 🔑 \*\*Pantalla Recuperar Contraseña\*\*  

# \- 🎬 \*\*Pantalla Home (Catálogo principal)\*\*  

# \- 📄 \*\*Pantalla Descripción de película\*\*  

# \- 🔁 \*\*Pantalla Devolver película\*\*  

# \- 👤 \*\*Pantalla Perfil Usuario\*\*  

# \- ✏️ \*\*Pantalla Modificar Usuario\*\*  

# \- 📸 \*\*Pantalla Cámara de fotos\*\*  

# \- 🔳 \*\*Pantalla QR\*\*  

# 

# ---

# 

# \## 🧩 7 - Resumen

# 

# La app \*\*VideoClub Online\*\* moderniza la experiencia tradicional del videoclub:  

# permite buscar, alquilar y devolver películas fácilmente desde un dispositivo Android.  

# Su diseño visual con \*\*Jetpack Compose\*\*, colores dinámicos y navegación fluida ofrece una experiencia intuitiva, atractiva y moderna para el usuario.

# 

# ---

# 

# \## 🧰 8 - Tecnologías utilizadas

# 

# | Tecnología | Descripción |

# |-------------|-------------|

# | 🧠 \*\*Kotlin\*\* | Lenguaje principal de desarrollo. |

# | 🎨 \*\*Jetpack Compose\*\* | Framework declarativo de UI para Android. |

# | 🧱 \*\*Material Design 3\*\* | Sistema de diseño moderno y adaptable usado para tipografía, colores y componentes. |

# | 💾 \*\*JSON / XML\*\* | Almacenamiento y manejo de datos locales. |

# | 📂 \*\*Scaffold\*\* | Estructura base para la disposición de pantallas (TopBar, BottomBar, contenido). |

# | 🧩 \*\*Componentes personalizados\*\* | Toolbar, botones, formularios, vistas de cámara, etc. |

# | 📱 \*\*Android Studio Giraffe / Koala\*\* | Entorno de desarrollo integrado (IDE). |

# 

# ---

# 

# \## 📸 9 - Capturas de pantalla

# 

# \_Aquí puedes añadir tus imágenes cuando las exportes desde Android Studio:\_

# 

# ```

# 📁 /screenshots

# &nbsp;  ├── toolbar.png

# &nbsp;  ├── catalogo\_peliculas.png

# &nbsp;  ├── camara.png

# &nbsp;  ├── alquiler\_pelicula.png

# &nbsp;  └── perfil\_usuario.png

# ```

# 

# Ejemplo de inserción en Markdown:

# 

# ```markdown

# !\[Pantalla principal](screenshots/catalogo\_peliculas.png)

# ```



