# PUSH START Offroad

PUSH START Offroad v0.1 is an Android ARM64 proof of concept based on Organic
Maps. It deliberately keeps the existing `app.organicmaps` application ID,
Android namespace, JNI names, providers, intents, and SDK package names while
the impact of changing that identity is evaluated.

The v0.1 scope retains the existing offline maps, GPS, navigation, bookmarks,
track recording, and GPX/KML import functionality. The planned product areas
are MAP, RIDE, ROUTES, NAVIGATION, and CONNECT; this version does not introduce
that navigation structure, glove mode, or oversized controls.

The app remains subject to the repository's existing open-source licenses and
notices. Organic Maps and OpenStreetMap attribution and license information
must remain available in redistributed builds.

## Android build

From the `android` directory, build the installable F-Droid Beta APK for
64-bit ARM devices with:

```sh
./gradlew assembleFdroidBeta -Parm64
```

The output is written below `android/app/build/outputs/apk/fdroid/beta/`.

The branch workflow installs the exact SDK, NDK, CMake, and Java versions used
by the project, signs the test APK with the repository's public debug key, and
checks its signature, application ID, and ARM64-only native libraries before
publishing the APK and its SHA-256 checksum. This debug-key signature is for
testing and must be replaced by a PUSH START release key before distribution.
