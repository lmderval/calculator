
plugins {
    id("calculator.java-library-conventions")
}

dependencies {
    implementation(project(":complex"))
    implementation(project(":token"))
    implementation(project(":ast"))
}
