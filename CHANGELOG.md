# Changelog

## v0.6.0 (TBC)

### Features

- New constructor that takes a `SnackbarParentFinder`, which is used to look for a parent view to attach to - allowing fallback parent views when one is not found.

### API Changes

 - `SnackbarCombinedCallback` is now final.
 - `SnackbarBuilder.iconMarginStartPixels` -> `iconMarginStart`
 - `SnackbarBuilder.iconMarginStart` -> `iconMarginStartRes`
 - `SnackbarBuilder.iconMarginEndPixels` -> `iconMarginEnd`
 - `SnackbarBuilder.iconMarginEnd` -> `iconMarginEndRes`
 
### Improvements

- Tidied up Gradle files
- Removed logging from `SnackbarCallback`
- Tidied up some JavaDoc comments

### Dependencies

- Updated Gradle plugin to `2.1.2`
- Updated build tools to `23.0.3`

## v0.5.0 (06/03/16)

### Features

- Append messages to the end of the main `Snackbar` messages. Each of these appended messages can have a different colour specified.
- Add an icon to the `Snackbar`.
- Added a `SnackbarWrapper`, to allow you to customise the `Snackbar` after it has been created.

### API Changes

- Builder methods which take a `String` now take a `CharSequence`, so will honour any spans that have been applied already.
- Rather than providing styling through single theme attributes, you assign a whole style to the theme attributes `snackbarBuilderStyle` and `toastBuilderStyle`. This cleans up your theme and is a bit easier as you can extend the built-in `SnackbarBuilder` style.
- Moved the `ToastBuilder` to be within the `snackbarbuilder` package, to ensure the whole library is within a single base package.

### Improvements

- Added JavaDoc comments to the full public API.
- Added more samples.

## v0.4 (12/02/16)

- Added ToastBuilder for display Toast messages
- Added SnackbarCallbackWrapper for more control over your callbacks and to easily wrap existing callbacks
- Specify custom Snackbar duration through a global theme attribute

## v0.3 (10/02/16)

- Updated dependencies
- Fix problems that were caused by using ThemeUtils class

## v0.2 (12/11/15)

- First usable version of the library.
- It is working and is fully covered with tests.
- Contains a builder pattern to create and show Snackbars.
