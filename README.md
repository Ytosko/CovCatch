# CovCatch

On 1930s a new virus turned into determined which become a raspatory infection of domesticated chickens which became caused by resulting from infectious bronchitis virus (IBV). however on the quit of 2019 this virus become placed at human body and observed that it's far getting greater dangerous when it's far affecting increasingly human beings whose have become nearby to an affected person. As of give up of July there are greater than 19,468,083 lively cases of Corona patients and overall dying of more than 5,137,264 and the actual purpose of this large amount of patient is their unconsciousness. WHO and plenty of other health organizations said the principle weapon for stopping this pandemic is social-distancing and focus. however we must agree that humans have a few ought to conditions after they have to pass outside for works or enterprise due to the fact each person has a own family and they should run their family. however is they get a few stuff that help them to locate if any of corona nice affected person came close by in closing 14 days or now not in order that they get aware and test themselves for corona which also can save them and their family. As we recognize Corona virus is a highly infectious virus and it has a large opportunity that if someone come close by a corona high-quality patient through 6 ft the person will have a excessive risk of getting suffering from this virus. Now as you can see that every device has a Bluetooth hardware and our main focus of this project is to use that hardware which will give us access to trace all the nearby devices carried by a person now as we know covid-19 can be spread by getting in contact so we tried to build a system which can detect that contact and take actions to prevent someone who is already Covid positive to spread the virus across the people. the main methodologies of a project is to share a specialized key between the devices so every device get know who is got in contact with them and when they got contacted and if someone found covid-19 positive the system will notify everyone who is got contacted with that user in previous 14 days. This machine makes use of Firebase as important API to attain a UID for a person. we all understand if we use Firebase Authentication system it'll provide us a UID with the account. Now our goal is to change these UID with everyone who are getting nearer with BLE and these exchanged UID will be uploaded to Firebase Realtime Database with the date below the precise node created with the consumer’s UID where the exchanged UID can be saved. 

## Screenshots

* Login page with blueprint
<img src="https://user-images.githubusercontent.com/43275869/234958857-7b5bafd3-7ba3-4b82-b9b2-6041b9282f2e.png" width="600">


#
* Infograph

![image](https://user-images.githubusercontent.com/43275869/234959171-459e7335-6009-43ca-ab2f-d63ffba6a390.png)  ![image](https://user-images.githubusercontent.com/43275869/234959237-18f053a4-f395-4fa4-864b-37e38c0a3748.png)  ![image](https://user-images.githubusercontent.com/43275869/234959265-6af8882a-21d1-463d-ad78-4c09674fe3b9.png)  ![image](https://user-images.githubusercontent.com/43275869/234959287-1ebef5ea-aef7-4f51-94ca-5cdc83c54ad0.png) 

#
* Home Page / World Stat of Covid 19
<img src="https://user-images.githubusercontent.com/43275869/234960127-5f38d076-531f-4c64-847e-d7e72c3f8834.png" width="300">

#
* Isolation Mode & Covid Result

<img src="https://user-images.githubusercontent.com/43275869/234960684-ec7d5e5a-109b-4ea0-8bb0-f6a08fecb1b1.png" height='300'/> <img src="https://user-images.githubusercontent.com/43275869/234960707-cfc2eaaa-6764-47f1-b6c4-ff60c4c641e6.png" height='300'/>


## Dependencies

    implementation fileTree(dir: "libs", include: ["*.jar"])
    implementation 'androidx.appcompat:appcompat:1.3.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.1'
    implementation 'androidx.cardview:cardview:1.0.0'
    implementation 'androidx.navigation:navigation-fragment:2.3.5'
    implementation 'androidx.navigation:navigation-ui:2.3.5'
    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
    implementation 'com.google.android.gms:play-services-maps:18.0.0'
    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    implementation platform('com.google.firebase:firebase-bom:29.0.0')
    implementation 'com.google.firebase:firebase-analytics'
    implementation 'com.google.android.gms:play-services-nearby:18.0.0'
    implementation 'com.google.firebase:firebase-core:20.0.0'
    implementation 'com.google.firebase:firebase-auth:21.0.1'
    implementation 'com.google.firebase:firebase-database:20.0.2'
    implementation 'com.google.firebase:firebase-messaging:23.0.0'
    implementation 'com.google.firebase:firebase-firestore:24.0.0'
    25
    implementation 'com.google.firebase:firebase-functions:20.0.1'
    testImplementation 'junit:junit:4.12'
    implementation('com.github.ViksaaSkool:AwesomeSplash:v1.0.0') {
    exclude group: 'com.android.support'
    }
    implementation 'com.github.markushi:circlebutton:1.1'
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    testImplementation 'junit:junit:4.12'
    implementation 'com.weiwangcn.betterspinner:library-material:1.1.0'
    implementation 'com.google.android.material:material:1.5.0-beta01'
    implementation 'com.firebaseui:firebase-ui-auth:4.3.1'
    implementation 'com.firebase:firebase-client-android:2.5.2'
    implementation 'com.facebook.android:facebook-androidsdk:
    latest.release'
    //noinspection GradleCompatible
    implementation 'com.android.support:recyclerview-v7:29.0.0'
    //noinspection GradleCompatible
    implementation 'com.android.support:cardview-v7:29.0.0'
    implementation 'com.android.support:multidex:1.0.3'
    // FirebaseUI for Firebase Realtime Database
    implementation 'com.firebaseui:firebase-ui-database:6.0.2'
    // FirebaseUI for Cloud Firestore
    implementation 'com.firebaseui:firebase-ui-firestore:6.0.2'
    // FirebaseUI for Firebase Auth
    implementation 'com.firebaseui:firebase-ui-auth:6.0.2'
    // FirebaseUI for Cloud Storage
    implementation 'com.firebaseui:firebase-ui-storage:6.0.2'
    implementation 'androidx.appcompat:appcompat:1.3.1'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    implementation 'de.hdodenhof:circleimageview:3.1.0'
    implementation 'com.github.blackfizz:eazegraph:1.2.5l@aar'
    implementation 'com.nineoldandroids:library:2.4.0'
    implementation 'com.android.volley:volley:1.2.1'
    implementation 'com.leo.simplearcloader:simplearcloader:1.0.+'
    implementation 'com.google.android.gms:play-services-location:18.0.0'
    implementation 'net.igenius:customcheckbox:1.3'
    implementation 'com.google.android.flexbox:flexbox:3.0.0'
    implementation 'net.danlew:android.joda:2.10.12.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
	

## User permission



    <uses-permission android:name="android.permission.BLUETOOTH_ADVERTISE"/>
    <uses-permission android:name="android.permission.BLUETOOTH_CONNECT"/>
    <uses-permission android:name="android.permission.BLUETOOTH_SCAN" />
    <uses-permission android:name="android.permission.VIBRATE" />
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
    <uses-permission android:name="android.permission.EXPAND_STATUS_BAR"/>
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.READ_CALL_LOG" />
    <uses-permission android:name="android.permission.WRITE_CALL_LOG" />
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>
    <uses-permission
    android:name="android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS"/>
    <uses-permission android:name="android.permission.WAKE_LOCK" />
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
    <uses-permission android:name="android.permission.SEND_SMS" />
    <uses-permission
    android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission
    android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-feature
    android:name="android.hardware.microphone"
    android:required="false" />
    <uses-permission android:name="android.permission.RECORD_AUDIO" />
    <uses-permission
    android:name="android.permission.MODIFY_AUDIO_SETTINGS" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission
    android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.READ_CONTACTS" />
    <uses-permission android:name="android.permission.RECEIVE_SMS" />
    <uses-permission android:name="android.permission.READ_SMS" />
    <uses-permission android:name="android.permission.SEND_SMS" />
    <uses-permission
    android:name="android.permission.BLUETOOTH"
    android:required="true" />
    <uses-permission
    android:name="android.permission.BLUETOOTH_ADMIN"
    android:required="true" />
    <uses-feature
    android:name="android.hardware.bluetooth_le"
    android:required="true" />
    <uses-permission
    android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission
    android:name="android.permission.ACCESS_BACKGROUND_LOCATION" />
	


