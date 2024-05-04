Puedes usar el siguiente script de Groovy para instalar plugins en el arranque de Jenkins. 
Este script se puede colocar en el directorio `init.groovy.d` de tu instalación de Jenkins.

```groovy
import jenkins.model.*
import java.util.logging.Logger

def logger = Logger.getLogger("")
def installed = false
def initialized = false

def pluginManager = Jenkins.instance.pluginManager

def plugins = ['plugin1', 'plugin2']  // Reemplaza 'plugin1', 'plugin2' con los nombres de tus plugins

plugins.each { plugin ->
    def installedPlugin = pluginManager.getPlugin(plugin)

    if (!installedPlugin) {
        logger.info("Instalando el plugin '${plugin}'")

        if (!initialized) {
            pluginManager.doCheckUpdatesServer()
            initialized = true
        }

        def pluginInstallStatus = pluginManager.install(plugin, null, true)

        if (pluginInstallStatus.get()) {
            installed = true
        }
    }
}

if (installed) {
    logger.info("Los plugins se han instalado, se reiniciará Jenkins")
    Jenkins.instance.restart()
} else {
    logger.info("No se instalaron nuevos plugins")
}
```

Por favor, asegúrate de reemplazar `'plugin1', 'plugin2'` con los nombres de los plugins que deseas instalar. Este script verificará si los plugins ya están instalados. Si no lo están, los instalará y reiniciará Jenkins. Si ya están instalados, no hará nada. 

**Nota:** Este script requiere que Jenkins se ejecute con permisos de administrador ya que necesita reiniciar Jenkins después de la instalación de los plugins. Por favor, úsalo con precaución.


Puedes usar el siguiente script de Groovy para configurar un job en Jenkins. Este script se puede ejecutar desde la consola de scripts de Jenkins.

import jenkins.model.*
import hudson.model.*
import hudson.tasks.*

def jobName = 'mi-job'  // Reemplaza 'mi-job' con el nombre de tu job
def jenkinsInstance = Jenkins.getInstance()

// Verifica si el job ya existe
if (jenkinsInstance.getItem(jobName) == null) {
    // Crea un nuevo job
    def job = jenkinsInstance.createProject(FreeStyleProject, jobName)

    // Configura el repositorio de Git
    def gitScm = new hudson.plugins.git.GitSCM('https://github.com/usuario/repositorio.git')  // Reemplaza con la URL de tu repositorio
    job.setScm(gitScm)

    // Añade un paso de construcción de shell
    job.getBuildersList().add(new hudson.tasks.Shell('echo Hola, mundo'))

    // Guarda el job
    job.save()
} else {
    println("El job '${jobName}' ya existe")
}

Por favor, asegúrate de reemplazar 'mi-job' con el nombre del job que deseas crear y 'https://github.com/usuario/repositorio.git' con la URL de tu repositorio de Git. Este script creará un nuevo job si no existe, configurará el repositorio de Git y añadirá un paso de construcción de shell.

Nota: Este script requiere que Jenkins se ejecute con permisos de administrador ya que necesita crear y configurar jobs. Por favor, úsalo con precaución.

