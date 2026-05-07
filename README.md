# Sistema de Control de Versiones Simple (Git Local)

## Versión
**1.0**

## Descripción
Sistema de control de versiones simplificado desarrollado en Java, similar a Git. Permite inicializar repositorios, agregar archivos, crear commits, manejar ramas y comparar versiones.

## Alcance

### ✅ Qué hace
- Inicializar repositorio local
- Agregar archivos (Texto, Código, Imagen, Configuración)
- Crear commits con mensaje y autor
- Ver historial de commits
- Crear y cambiar entre ramas
- Comparar diferencias entre commits
- Estado del repositorio (staging)

### ❌ Qué NO hace
- No tiene interfaz gráfica (solo consola)
- No persiste archivos en disco real
- No es un reemplazo de Git profesional

## Tipos de Archivo

| Tipo | Descripción | Validación |
|------|-------------|------------|
| Texto | Archivos de texto plano | Siempre válido |
| Código | Archivos de código fuente | No vacío |
| Imagen | Archivos de imagen (simulado) | No vacío |
| Configuración | Propiedades, JSON, XML | Formato clave=valor |

## Comandos Disponibles

| Comando | Descripción |
|---------|-------------|
| init | Inicializa un nuevo repositorio |
| add | Agrega un archivo al staging |
| commit | Crea un commit con los archivos agregados |
| log | Muestra el historial de commits |
| branch | Crea una nueva rama |
| checkout | Cambia a una rama existente |
| status | Muestra el estado del repositorio |
| diff | Compara dos commits |

## Documentación Javadoc
[https://mpfx.github.io/ControlVersiones/](https://mpfx.github.io/ControlVersiones/)

## Tecnologías
- Java
- Javadoc
- GitHub Pages

## Modo de uso
**Este proyecto NO es una aplicación visual/gráfica.**
Funciona exclusivamente por consola (CLI - Command Line Interface).

## Propósito
**Educativo / Pedagógico**
- Programación orientada a objetos en Java
- Herencia y clases abstractas
- Interfaces
- Documentación técnica con Javadoc
- Control de versiones con Git y GitHub Pages

## Derechos de autor
**© 2026 ISC Israel de Jesus Mar Parada**
Todos los derechos reservados.
