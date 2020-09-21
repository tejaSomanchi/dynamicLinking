# Dynamic Linking

1.To import this library, Add the following line to your app level build.gradle file.
```
implementation 'com.github.PrasadVennamAppy:AppyhighUtils:{latestVersion}

```

2.Create a Domain Link in your linked firebase account to use Dynamic Linking


3.To receive Dynamic Links, add the following intent-filter to your *AndroidManifest.xml* file

```
<intent-filter>
    <action android:name="android.intent.action.VIEW"/>
    <category android:name="android.intent.category.DEFAULT"/>
    <category android:name="android.intent.category.BROWSABLE"/>
    <data
        android:host="your_domainLink"
        android:scheme="https"/>
</intent-filter>
```

4.To create and share dynamic links that opens the link on your app, call the *createShortLink* method as shown
```
createShortLink(link: String, domain: String, packageName: String?, linkMessage: String, context: Context)

```

5.To receive the deep link, call the *getDynamicLink* method as shown
```
getDynamicLink(intent: Intent):Uri?

```
 return type : Uri