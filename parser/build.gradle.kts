
plugins {
    id("calculator.java-library-conventions")
}

dependencies {
    implementation(project(":token"))
    implementation(project(":ast"))
}
