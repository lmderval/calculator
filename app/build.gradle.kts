
plugins {
    id("calculator.java-application-conventions")
}

dependencies {
    implementation(project(":complex"))
    implementation(project(":token"))
    implementation(project(":lexer"))
    implementation(project(":ast"))
    implementation(project(":parser"))
}

application {
    // Define the main class for the application.
    mainClass.set("calculator.App")
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
