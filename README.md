<p align="center">
  <img src="./assets/logo.svg" alt="Ghossh logo" width="48" height="48" />
</p>

<h1 align="center">Ghossh</h1>

<p align="center">
 A modern, native Android SSH client powered by libghostty
</p>

<p align="center">
  <a href="https://github.com/ljsydpwym/ghossh/releases/latest">Download Latest</a> ·
  <a href="https://github.com/ljsydpwym/ghossh/releases/">Changelog</a>
</p>

---

<table align="center" cellpadding="10">
  <tr>
    <td><img src="./assets/sample-3.jpg" alt="Ghossh sample 3" height="420" /></td>
    <td><img src="./assets/sample-2.jpg" alt="Ghossh sample 2" height="420" /></td>
    <td><img src="./assets/sample-1.jpg" alt="Ghossh sample 1" height="420" /></td>
    <td><img src="./assets/sample-4.jpg" alt="Ghossh sample 4" height="420" /></td>
</table>

Ghossh is a native Android SSH client powered by libghostty, with a terminal-first Compose UI and support for both standard SSH and Tailscale SSH workflows.

This project is based on [Chuchu](https://github.com/jossephus/chuchu) by [jossephus](https://github.com/jossephus) — the original Android SSH client for Android. Ghossh continues development with its own direction, improvements, and features.

### Features
- SSH (password + key) and Tailscale SSH authentication
- Kitty image protocol support for inline images
- 400+ themes from the official Ghostty repository
- Configurable accessory keys
- Full terminal renderer with resize, scrollback, focus, modifier keys, and mouse support
- Clickable links in terminal output

## Status

Ghossh is in active development. I daily drive it and fix issues as I find them. Found a bug? Open an issue or send a PR.

### Getting Started

Download the latest APK from the [Releases](https://github.com/ljsydpwym/ghossh/releases) page and side-load it.

> **Note:** I don't have a Play Store account, so APK releases are the only distribution method for now.

## Stack

- **Kotlin + Jetpack Compose** — Android UI
- **Zig** — native build orchestration and JNI bridge
- **Ghostty VT** — terminal emulation engine
- **libssh2 + OpenSSL** — SSH transport
- **Room** — local database

## Development

### Prerequisites

With Nix:

```sh
nix develop
```

Without Nix:

- Android Studio (SDK, NDK, JDK 17+)
- Zig 0.15.2

### Build

```sh
# 1. Build the native JNI library
zig build jni -Dtarget=aarch64-linux-android

# 2. Build the APK
cd android && ./gradlew assembleDebug
```

The JNI build copies `libchuchu_jni.so` into `android/app/src/main/jniLibs/arm64-v8a/`.

### Install on device

```sh
cd android && ./gradlew installDebug
```

Or use the Makefile shortcut:

```sh
make build   # builds native lib
make app     # builds + installs + launches
```
