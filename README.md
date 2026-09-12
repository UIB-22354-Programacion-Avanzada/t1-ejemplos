# t1-ejemplos — Tema 1: POO, Principios SOLID y Diseño

Código de los ejemplos del **Tema 1** de *Programación Avanzada (22354)*, Grado en Ingeniería
Telemática, UIB-EPS. Todo el código que aparece en la
[página del tema](https://uib-22354-programacion-avanzada.github.io/website/es/contenido/tema1.html)
está aquí, compilable y con sus pruebas, para que puedas ejecutarlo, modificarlo y experimentar.

Es un proyecto **Maven** para **Java 25** con pruebas en **JUnit 5**, y viene preparado para
trabajar en **GitHub Codespaces** sin instalar nada en tu ordenador.

## Qué contiene

```text
t1-ejemplos/
├── .devcontainer/devcontainer.json   ← configuración del Codespace (JDK 25 + Maven)
├── .github/workflows/pruebas.yml     ← ejecuta las pruebas en cada push
├── pom.xml                           ← proyecto Maven
├── servicios.txt                     ← configuración de ejemplo para el monitor
└── src/
    ├── main/java/es/uib/prgava/tema1/
    │   ├── poo/         ← ejemplos breves de la sección 1.1 (DispositivoRed, Enrutador, Punto, ...)
    │   ├── monitor/     ← el ejemplo motivador: monitor de servicios de red (secciones 1.1–1.3)
    │   └── genericos/   ← ejemplos breves de la sección 1.3 (Par, Copias, Composicion, ...)
    └── test/java/es/uib/prgava/tema1/monitor/   ← pruebas JUnit 5 de la sección 1.4
```

Cuando una clase aparece varias veces en el tema (por ejemplo `Monitor` o `Servicio`, que
evolucionan con los principios SOLID), aquí está su **versión final**.

## 1. Crear tu copia del repositorio

Este repositorio es una **plantilla**. No trabajes directamente sobre él: crea tu propia copia.

1. Pulsa el botón verde **Use this template** → **Create a new repository**.
2. Como propietario elige **tu cuenta personal** de GitHub, dale un nombre (por ejemplo
   `t1-ejemplos`) y créalo. Puede ser público o privado.

A partir de aquí, todo se hace en tu copia.

## 2. Abrir un Codespace

1. En tu repositorio, pulsa **Code** → pestaña **Codespaces** → **Create codespace on main**.
2. La primera vez tarda unos minutos: GitHub construye un contenedor con JDK 25, Maven y las
   extensiones de Java de VS Code. Las siguientes veces se abre en segundos.
3. Cuando termine verás VS Code en el navegador. En el terminal integrado debe aparecer la
   salida de `java -version` con `openjdk version "25..."`. Si no ves el terminal, ábrelo con
   **Terminal → New Terminal** (o `Ctrl+ñ`).

Comprueba que todo funciona ejecutando las pruebas:

```bash
mvn test
```

Debe terminar con `Tests run: 21, Failures: 0, Errors: 0` y `BUILD SUCCESS`.

> **Importante:** los Codespaces consumen horas de tu cuota mensual gratuita mientras están
> encendidos. Cuando termines de trabajar, deténlo: **Code → Codespaces → ⋯ → Stop codespace**,
> o desde [github.com/codespaces](https://github.com/codespaces). Un Codespace detenido no
> consume horas y conserva tus ficheros; si no lo usas durante 30 días, GitHub lo borra, pero
> lo que hayas subido con `git push` sigue en tu repositorio.

## 3. Ejecutar los ejemplos

Hay tres formas, y conviene conocer las tres.

**Desde el editor.** Abre una clase con `main` (por ejemplo
`src/main/java/es/uib/prgava/tema1/poo/DemoDispositivos.java`). Encima del método `main`
aparece el enlace **Run | Debug**; pulsa *Run* y la salida se muestra en el terminal.
Los programas que reciben argumentos (`Principal` y `MonitorV0` necesitan la ruta de
`servicios.txt`) se ejecutan mejor desde el terminal.

**Desde el terminal, con Maven:**

```bash
mvn -q compile                                                     # compila a target/classes
java -cp target/classes es.uib.prgava.tema1.poo.DemoDispositivos
java -cp target/classes es.uib.prgava.tema1.monitor.Principal servicios.txt
java -cp target/classes es.uib.prgava.tema1.monitor.DemoInformes
java -cp target/classes es.uib.prgava.tema1.genericos.Composicion
```

**Las pruebas.** `mvn test` ejecuta todas; `mvn -Dtest=MonitorTest test` solo una clase.
También puedes usar el icono de matraz (*Testing*) de la barra lateral de VS Code, que muestra
las pruebas en árbol y permite ejecutar una sola con un clic.

## 4. Modificar el código

Trabaja como en cualquier proyecto: edita, guarda (`Ctrl+S`), vuelve a ejecutar. Algunas
sugerencias para empezar, de menor a mayor dificultad:

- En `DemoDispositivos`, añade un tercer dispositivo y comprueba el contador estático.
- Añade una nueva política de alerta en `monitor/` (por ejemplo, «tres fallos en las últimas
  cinco comprobaciones») y úsala en `Principal`. No deberías tocar `Monitor`.
- Escribe una prueba para esa política en `src/test/java/.../monitor/`, siguiendo el estilo de
  `FallosConsecutivosTest`, y ejecútala.
- Añade un nuevo tipo de servicio a la interfaz sellada `Servicio` y observa qué deja de
  compilar y por qué.

Cuando quieras guardar tu trabajo en GitHub, usa la vista **Source Control** de VS Code (icono
de ramas en la barra lateral): escribe un mensaje, pulsa **Commit** y después **Sync Changes**.
O desde el terminal:

```bash
git add -A
git commit -m "Nueva política de alerta"
git push
```

Cada `push` ejecuta automáticamente las pruebas en GitHub Actions; verás el resultado en la
pestaña **Actions** de tu repositorio y como una marca ✓ o ✗ junto al último *commit*.

## 5. Pasar el desarrollo a IntelliJ IDEA (opcional)

Si prefieres trabajar en tu ordenador con un IDE Java completo, puedes seguir usando el mismo
repositorio desde **IntelliJ IDEA Community** (gratuito). El proyecto es Maven estándar, así
que IntelliJ lo entiende sin ninguna configuración adicional.

1. Instala [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/) y
   [Git](https://git-scm.com/downloads). No hace falta instalar Maven: IntelliJ incluye uno.
2. En la pantalla de bienvenida, **Clone Repository** (o **File → New → Project from Version
   Control**), pega la URL de tu repositorio (`https://github.com/TU-USUARIO/t1-ejemplos.git`)
   y elige una carpeta local. IntelliJ te pedirá iniciar sesión en GitHub la primera vez.
3. Al abrirse, IntelliJ detecta el `pom.xml`, lo importa como proyecto Maven y descarga las
   dependencias (JUnit). Espera a que termine la indexación (barra inferior).
4. **JDK 25.** Si no lo tienes instalado, IntelliJ lo descarga por ti: **File → Project
   Structure → Project → SDK → Add SDK → Download JDK…**, elige la versión **25** (distribución
   *Eclipse Temurin*) y acepta. Comprueba que en esa misma pantalla *Language level* queda en
   25 (lo toma del `pom.xml`).
5. Ejecuta cualquier clase con `main` con el triángulo verde del margen, y las pruebas con el
   mismo triángulo junto a la clase o al método de prueba, o con clic derecho sobre
   `src/test/java` → **Run 'All Tests'**. Para `Principal`, añade `servicios.txt` como argumento
   en **Run → Edit Configurations → Program arguments**.
6. Los *commits* y *push* se hacen desde el menú **Git** (o `Ctrl+K` para *commit*,
   `Ctrl+Shift+K` para *push*).

Puedes alternar entre el Codespace y IntelliJ sin problema: los dos trabajan contra el mismo
repositorio de GitHub. Solo recuerda hacer **push** antes de cambiar de entorno y **pull**
(*Git → Update Project* en IntelliJ; *Sync Changes* en VS Code) al llegar al otro, para no
generar conflictos.

Si prefieres VS Code en tu ordenador en lugar de IntelliJ, también sirve: instala JDK 25 y
Git, abre la carpeta clonada y acepta la instalación del *Extension Pack for Java* que VS Code
propone. Y si tienes Docker, VS Code puede abrir la carpeta dentro del mismo contenedor del
Codespace (**Reopen in Container**), con lo que no necesitas instalar Java.

## Problemas frecuentes

- **`mvn` no se encuentra** en el Codespace: el contenedor no terminó de construirse. Ejecuta
  **Codespaces: Rebuild Container** desde la paleta de órdenes (`F1`).
- **`java -version` muestra otra versión**: mismo remedio; revisa el registro con
  **Codespaces: View Creation Log**.
- **Las pruebas pasan en local pero fallan en Actions** (o al revés): casi siempre es un fichero
  que no has subido. `git status` te dirá cuál.
- **IntelliJ marca errores en `record`, `sealed` o `switch` con patrones**: el SDK del proyecto
  o el *language level* no es 25. Revisa **File → Project Structure → Project**.

## Licencia

El código de este repositorio se publica bajo licencia [MIT](LICENSE) — copyright © 2026
Alejandro Mesejo. Puedes usarlo, copiarlo y modificarlo libremente, dentro y fuera de la
asignatura, conservando el aviso de copyright.

Esa licencia cubre el **material de partida**: los esqueletos, las clases ya escritas y las
pruebas. Cuando crees tu copia con **Use this template**, el fichero `LICENSE` viaja con ella,
pero **las modificaciones que escribas son tuyas** y puedes licenciarlas como quieras. Si haces
público tu repositorio y quieres dejarlo claro, añade tu propio nombre al aviso de copyright o
una nota al principio de este README.

Las explicaciones del tema y el resto del material docente están en el
[sitio web de la asignatura](https://uib-22354-programacion-avanzada.github.io/website/) y se
publican bajo licencia
[CC BY-SA 4.0](https://creativecommons.org/licenses/by-sa/4.0/deed.es).
