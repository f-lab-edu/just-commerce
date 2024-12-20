
rootProject.name = "just-commerce"
include(
    "discovery",
    "gateway",
    "common",
    "user-service",
    "payment-service",
    "item-service",
    "wallet-service"
)

pluginManagement {
    val kotlinVersion: String by settings
    val kotestVersion: String by settings
    val asciidoctorJvmConvertVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)
                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
                "org.asciidoctor.jvm.convert" -> useVersion(asciidoctorJvmConvertVersion)
            }
        }
    }
}
