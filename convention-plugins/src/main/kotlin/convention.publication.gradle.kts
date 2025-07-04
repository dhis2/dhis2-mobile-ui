plugins {
    `maven-publish`
    signing
}

val signingPrivateKey: String? = System.getenv("SIGNING_PRIVATE_KEY")
val signingPassword: String? = System.getenv("SIGNING_PASSWORD")

val dokkaHtml = tasks.findByName("dokkaHtml")!!

val dokkaHtmlJar = tasks.register<Jar>("dokkaHtmlJar") {
    dependsOn(dokkaHtml)
    from(dokkaHtml.outputs)
    archiveClassifier.set("javadoc")
}

publishing {

    // Configure all publications
    publications.withType<MavenPublication> {
        artifact(dokkaHtmlJar)

        // Provide artifacts information requited by Maven Central
        pom {
            name.set("DHIS2 Mobile Design system")
            description.set(Props.DESCRIPTION)
            url.set(Props.REPOSITORY_URL)

            licenses {
                license {
                    name.set(Props.LICENSE_NAME)
                    url.set(Props.LICENSE_URL)
                }
            }
            organization {
                name.set(Props.ORGANIZATION_NAME)
                url.set(Props.ORGANIZATION_URL)
            }
            developers {
                developer {
                    name.set(Props.AUTHOR_NAME)
                    email.set(Props.AUTHOR_EMAIL)
                    organization.set(Props.ORGANIZATION_NAME)
                    organizationUrl.set(Props.ORGANIZATION_URL)
                }
            }
            scm {
                url.set("https://github.com/dhis2/dhis2-mobile-ui")
            }
            issueManagement {
                system.set(Props.REPOSITORY_SYSTEM)
                url.set(Props.REPOSITORY_URL)
            }
        }
    }
}

// Signing artifacts. Signing.* extra properties values will be used
signing {
    setRequired({
        !(version.toString().endsWith("-SNAPSHOT") || version.toString().endsWith("-SNAPSHOTLOCAL"))
    })
    useInMemoryPgpKeys(signingPrivateKey, signingPassword)
    sign(publishing.publications)
}

// Fix Gradle warning about signing tasks using publishing task outputs without explicit dependencies
// https://github.com/gradle/gradle/issues/26091
tasks.withType<AbstractPublishToMaven>().configureEach {
    val signingTasks = tasks.withType<Sign>()
    mustRunAfter(signingTasks)
}
