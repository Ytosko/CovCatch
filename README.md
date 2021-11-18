# CovCatch
This file describes a project for tracking Corona virus affected humans and alerting its users if they were given contacted with any affected one in past several days. As we all understand we are passing by a totally crucial scenario wherein the principal reason of getting affected by Corona virus is get in touch with a affected character and it is also proved that if an character comes within 6 ft of an affected person also increase the opportunity of having suffering from Corona virus for that man or woman very high. So here we're, if we are able to build a machine that can music anybody who come inside 6 feet and if any of them were given affected by Corona virus, we can get an alert without knowing their identity is sufficient for us to get aware at the right time. So, now let us appearance on what technologies and APIs we are going to use for this undertaking. we're inclined to build this system with the assist of Firebase, Bluetooth low energy or famously called as BLE and a few primary Android SDK gear. In our venture person can without problems mark them function covid-19 in order to be used by a toggle transfer button and if the user transfer the button on the software will update database with that user’s firebase auth UID that he examined corona fine and every person will constantly scan database if any of the UID the were given contacted within last 14 days has a status of Corona effective the software will notify that someone got Corona fantastic that were given nearby with the user in remaining 14 days. We measured those 14 days by counting the day of switching the switch button on as one and we will continuously add a string which contains the quantity of the day of switching the button on and by means of this on the 14th day the fee will be 14 and all and sundry who got contacted with that user will get notified that they were given connected to a consumer who's Covid fantastic. Now permits have a look on the detail descriptions of the device in this paper. 

### Keywords : Corona, Firebase, Bluetooth Low Energy, BLE, Firebase Auth UID, Android SDK

## Overview

On 1930s a new virus turned into determined which become a raspatory infection of domesticated chickens which became caused by resulting from infectious bronchitis virus (IBV). however on the quit of 2019 this virus become placed at human body and observed that it's far getting greater dangerous when it's far affecting increasingly human beings whose have become nearby to an affected person. As of give up of July there are greater than 19,468,083 lively cases of Corona patients and overall dying of more than 5,137,264 and the actual purpose of this large amount of patient is their unconsciousness. WHO and plenty of other health organizations said the principle weapon for stopping this pandemic is social-distancing and focus. however we must agree that humans have a few ought to conditions after they have to pass outside for works or enterprise due to the fact each person has a own family and they should run their family. however is they get a few stuff that help them to locate if any of corona nice affected person came close by in closing 14 days or now not in order that they get aware and test themselves for corona which also can save them and their family. As we recognize Corona virus is a highly infectious virus and it has a large opportunity that if someone come close by a corona high-quality patient through 6 ft the person will have a excessive risk of getting suffering from this virus. Now as you can see that every device has a Bluetooth hardware and our main focus of this project is to use that hardware which will give us access to trace all the nearby devices carried by a person now as we know covid-19 can be spread by getting in contact so we tried to build a system which can detect that contact and take actions to prevent someone who is already Covid positive to spread the virus across the people. the main methodologies of a project is to share a specialized key between the devices so every device get know who is got in contact with them and when they got contacted and if someone found covid-19 positive the system will notify everyone who is got contacted with that user in previous 14 days. This machine makes use of Firebase as important API to attain a UID for a person. we all understand if we use Firebase Authentication system it'll provide us a UID with the account. Now our goal is to change these UID with everyone who are getting nearer with BLE and these exchanged UID will be uploaded to Firebase Realtime Database with the date below the precise node created with the consumer’s UID where the exchanged UID can be saved. we will additionally be running some other provider in historical past in order to suit the UIDs which might be stored below the person with the UIDs that are stored within the node “date” and if healthy observed the machine will alert the consumer to be aware and check for Corona virus as quickly as feasible. 2 Now as per requirement we need just to services of a mobile devices which is Internet connection and Bluetooth Beacon services which will enable us to collect the data using Bluetooth devices and then upload the data via Internet to the firebase database. As we build software we also have taken care of the user privacy as if someone who is Covid positive got contacted with someone who is not and they were nearby for very few several times the user will only get a notification to check for covid-19 test himself but he won't never notified about the user who got affected with quit positive or you don't have any rights to see the user details of anyone who got contact with him or anything else. Now we will also improve the security of the access point of this software, in software we used firebase authentication system which is one of the best authentication systems out there. We used Java for implementing the software and as of our project we tried to provide every service that can be helpful for covid-19. As we know that every people around the world uses mobile phone to get connected with the social media but there are many fake sites there are many fake news platforms so the real number of active cases or dates during covid-19 pandemic cannot be known from any kind of new source because there are many differences in those platforms. In our project the background services which can be used to track the locations or contract racing but in front of a user there will be a interface which provides them a worldwide covid-19 stat and also there is a dedicated button which is used to get the covid-19 result country-specific where we used REST API and RSS feed entry which is used to get the data from API endpoint and then show the data by using JSON parser. At the point of development, we think that we can build a special mode in our project which can be called isolation mode and the mode will be used for those users who are tested positive in covid-19. by this feature a user can toggle isolation mode on and after turning the mode on, there will be interface which will show them that the day that is being passed from the day when they started their isolation and as we know the isolation is 14 days long and there will be a complete pack of 14 days passing indicator and a special quote on Saturday which will motivate if user to cope up with isolation. I think ok in this pandemic this feature can motivate a user with a special touch of caring from that software as the software provides the dates that are being passing through the isolation the user will also have a craving of passing that 14 days and every morning of that 14 days will be started with a new quote of the day which is already implemented in our software. Now we will go through the paper and read every detail of our project so that we can totally understand how is the project and its characteristics, features and special functions that can be useful for its user and serve its purposes.


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



    <uses-permission android:name="android.permission.BLUETOOTH_ADVERTISE"
    />
    <uses-permission android:name="android.permission.BLUETOOTH_CONNECT"
    />
    <uses-permission android:name="android.permission.BLUETOOTH_SCAN" />
    <uses-permission android:name="android.permission.VIBRATE" />
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"
    />
    <uses-permission android:name="android.permission.EXPAND_STATUS_BAR"
    />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"
    />
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"
    />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.READ_CALL_LOG" />
    <uses-permission android:name="android.permission.WRITE_CALL_LOG" />
    <uses-permission android:name="android.permission.FOREGROUND_SERVICE"
    />
    <uses-permission
    android:name="android.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS"
    />
    <uses-permission android:name="android.permission.WAKE_LOCK" />
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"
    />
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
	


