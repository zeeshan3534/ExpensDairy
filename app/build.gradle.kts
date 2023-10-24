plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.example.expenseapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.expenseapplication"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures{
        viewBinding = true
//        navi
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    //Firebase Dependenceies
    implementation("com.google.firebase:firebase-auth-ktx:22.1.2")
    implementation("com.google.firebase:firebase-database-ktx:20.2.2")
//    implementation ("com.google.firebase:firebase-analytics")
    implementation(platform("com.google.firebase:firebase-bom:32.3.1"))
    implementation("com.google.firebase:firebase-firestore-ktx")

    //Pin dependence
    implementation("com.hanks:passcodeview:0.1.2")
    implementation ("com.github.khaled2252:pin-view:1.0.0")
    implementation ("com.keijumt.passwordview:passwordview:1.0.0")
    implementation ("com.andrognito.pinlockview:pinlockview:2.1.0")
    implementation ("com.github.GoodieBag:Pinview:v1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
//    implementation ("com.andrognito.pinlockview:pinlockview:2.1.0")

    //Lottie animation
    implementation ("com.airbnb.android:lottie:5.2.0")



    //Data Store dependency
    implementation ("androidx.datastore:datastore-preferences:1.0.0")


    //Google Material
    implementation("com.google.android.material:material:1.10.0")
    

    //Chart view

    implementation ("com.diogobernardino:williamchart:3.10.1")


    //Firebase messaging
    implementation("com.google.firebase:firebase-messaging:23.3.0")


    //Stripe Dependency
    implementation("com.stripe:stripe-java:22.2.0")
    implementation("com.stripe:stripe-android:17.2.0")
    implementation("com.android.volley:volley:1.2.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")



}