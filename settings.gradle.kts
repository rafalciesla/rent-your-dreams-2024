plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "rent-your-dreams-2024-backend"

include("lib")
include("configserver")
include("cars")
include("users")
