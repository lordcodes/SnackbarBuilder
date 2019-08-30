# SnackbarBuilder

NOTE - Deprecated and no longer actively developed. The library was useful on Android for a while, however, it is not really needed anymore and as such is no longer supported or maintained.

The Android Design Support library introduced the `Snackbar`. SnackbarBuilder provides a builder pattern that not only makes Snackbars easier to create, but it also provides some extra customisations.

One of the main annoyances with the `Snackbar` is that it has a dark background, but takes the default text color from your theme, which is often dark as well. This makes the messages hard to read and requires you to retrieve the `TextView` yourself and change the text color. `SnackbarBuilder` defaults the text color to white and then allows you to choose your own colour if you wish.

## Main Features

- A builder pattern to create Snackbars.
- Customise message and action text color with global theme attributes and on a per-Snackbar basis.
- Dismiss callbacks with separate methods for each type of dismiss event.
- Theme attribute for the default view ID in an activity to attach Snackbars to, so you don't need to provide the parent view each time you wish to show one.
- Set the default duration to use through a theme attribute.
- Append text, which allows you to have multiple text colours.
- Add an icon to the Snackbar if you wish.
- A ToastBuilder to create Toast messages, with a builder pattern and global theme attributes.

## Usage

### Create

It is really easy to build and customise snackbars.

```java
Snackbar snackbar = new SnackbarBuilder(this)
      .message("message")
      .actionText("Action")
      .snackbarCallback(new MySnackbarCallback())
      .build()
      .show();
```

Check out the sample project to see examples of how the library can be used.

### Callback

Simply implement the interface for the callback you are interested in and call the appropriate method on the `SnackbarBuilder`. It is as simple as that!

- Shown = `showCallback(SnackbarShowCallback callback)`
- Dismissed = `dismissCallback(SnackbarDismissCallback callback)`
- Dismissed (action pressed) = `actionDismissCallback(SnackbarActionDismissCallback callback)`
- Dismissed (swiped away) = `swipeDismissCallback(SnackbarSwipeDismissCallback callback)`
- Dismissed (timeout) = `timeoutDismissCallback(SnackbarTimeoutDismissCallback callback)`
- Dismissed (manual dismiss call) = `manualDismissCallback(SnackbarManualDismissCallback callback)`
- Dismissed (another Snackbar shown) = `consecutiveDismissCallback(SnackbarConsecutiveDismissCallback callback)`

```java
  new SnackbarBuilder(this)
      .message("Message")
      .actionText("Action")
      .showCallback(new MyShowCallback())
      .actionDismissCallback(new MyActionDismissCallback())
      .timeoutDismissCallback(new MyTimeoutDismissCallback())
      .build()
      .show();
```

Alternatively, if you would rather handle all the callbacks with a single class, then you can do that too. Simply extend the `SnackbarCallback` class and override only the methods you are interested in. Rather than needing to check the `dismissEvent` integer in the `Snackbar.Callback` class, there is a separate method to override for each dismiss type. There is also a method that accepts the standard `Snackbar.Callback`.


### ToastBuilder

Makes it very easy to create and customise `Toast` messages.

```java
new ToastBuilder(this)
    .message("message")
    .messageTextColor(Color.BLUE)
    .duration(Toast.LENGTH_LONG)
    .gravity(Gravity.TOP)
    .build()
    .show();
```

The builder allows you to change the text color of the displayed message. If you wish to change the background you can provide a custom view for the `Toast`. By providing the ID of a `TextView` within this view, you can set the message on it through the builder method `message(String)`.

### Theme

You can provide defaults to the `SnackbarBuilder` and the `ToastBuilder` through theme attributes.

```xml
<style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
    <item name="colorAccent">@color/colorAccent</item>

    <item name="snackbarBuilderStyle">@style/SampleSnackbarStyle</item>
    <item name="toastBuilderStyle">@style/SampleToastStyle</item>
</style>

<style name="SampleSnackbarStyle">
    <item name="snackbarBuilder_parentViewId">@id/coordinator</item>
    <item name="snackbarBuilder_duration">shortTime</item>
    <item name="snackbarBuilder_messageTextColor">@color/messageText</item>
    <item name="snackbarBuilder_actionTextColor">@color/colorAccent</item>
    <item name="snackbarBuilder_iconMargin">8dp</item>
    <item name="snackbarBuilder_actionAllCaps">false</item>
</style>

<style name="SampleToastStyle">
    <item name="toastBuilder_messageTextColor">@color/snackbarbuilder_default_message</item>
    <item name="toastBuilder_duration">shortTime</item>
</style>
```

There are some base SnackbarBuilder and ToastBuilder styles included in the library. You can use these as a parent style if you would like to. These styles can be useful to se all the theme attributes you have access to.

```xml
<style name="SampleSnackbarStyle" parent="SnackbarBuilder">
    ...
</style>

<style name="SampleToastStyle" parent="ToastBuilder">
    ...
</style>
```

## Author

Andrew Lord [@lordcodes](https://twitter.com/@lordcodes)
