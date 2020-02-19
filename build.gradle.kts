import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.run.BootRun

apply {
  plugin("idea")
  plugin("kotlin")
  plugin("kotlin-spring")
  plugin("org.springframework.boot")
  plugin("io.spring.dependency-management")
  plugin("com.github.ben-manes.versions")

  from("$rootDir/gradle/libraries.gradle.kts")
}
val libraries = extra["libraries"] as Map<*, *>

buildscript {
  repositories {
    maven { setUrl("https://plugins.gradle.org/m2/") }
  }
  project.apply {
    from("$rootDir/gradle/gradlePlugins.gradle.kts")
  }
  val gradlePlugins = extra["gradlePlugins"] as Map<*, *>
  dependencies {
    gradlePlugins.mapValues { classpath(it.value as String) }
  }
}

configure<JavaPluginConvention> {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
  jcenter()
}

dependencies {
  "compile"(libraries["kotlin-stdlib-jre8"] as String)
  "compile"(libraries["kotlin-reflect"] as String)
  "compile"(libraries["jackson-module-kotlin"] as String)
  "compile"(libraries["reactor-kotlin-extensions"] as String)

  "compile"(libraries["spring-boot-starter-webflux"] as String)
  "compile"(libraries["spring-boot-starter-thymeleaf"] as String)
//  "compile"(libraries["spring-boot-starter-security"] as String)
  "compile"(libraries["spring-boot-starter-actuator"] as String)
//  "compile"(libraries["spring-boot-starter-data-mongodb-reactive"] as String)

  "compile"(libraries["spring-boot-devtools"] as String)
  "compileOnly"(libraries["spring-context-indexer"] as String)
}

tasks {
  withType<KotlinCompile> {
    kotlinOptions {
      freeCompilerArgs = listOf("-Xjsr305=strict")
      jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
  }

  withType<BootRun> {
    systemProperties = System.getProperties().mapKeys { it.key.toString() }.toMap()
  }
}
