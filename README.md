# Dynamic Linking

1.To import this library, Add the following line to your project's *build.gradle* at the end of repositories.
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
	}

```
2.To import this library, Add the following line to your app level *build.gradle* file.
```
implementation 'com.github.tejaSomanchi:dynamicLinking:1.0.2

```

3.Create a Domain Link in your linked firebase account to use Dynamic Linking


4.To receive Dynamic Links, add the following intent-filter to your *AndroidManifest.xml* file

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

5.To create a short dynamic link that opens on your app, call the *createShortLink* method as shown
```
createShortLink(link: String, domain: String, packageName: String?,listener: onGetDataListener)

```
Note: onGetDataListener is already available in library and can be imported

6.To share the dynamic link using other apps, call the *shareLink* method as shown

```
shareLink(myDynamicLink: Uri?, linkMessage: String, context: Context)

```
## Example
```
createShortLink("https://todotask.page.link/splash", "https://todotask.page.link", BuildConfig.APPLICATION_ID, new onGetDataListener() {
    @Override
    public void onFailure(@NotNull Exception e) {
	Log.d("createShortLink", "onFailure: "+e);
    }

    @Override
    public void onSuccess(Uri link) {
	shareLink(link,"Get this amazing app: ",MainActivity.this);
    }
});

```
7.To receive the deep link, call the *getDynamicLink* method as shown
```
getDynamicLink(intent: Intent,listener: onGetDataListener)

```
Note: onGetDataListener is already available in library and can be imported

## Example
```
getDynamicLink(getIntent(), new onGetDataListener() {
    @Override
    public void onSuccess(@Nullable Uri uri) {
	Log.d("deeplink", "onSuccess: "+uri);
    }

    @Override
    public void onFailure(@NotNull Exception e) {

    }
});
```
