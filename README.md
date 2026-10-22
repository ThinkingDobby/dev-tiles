**Android 기기의 빠른 설정창에 개발자용 타일을 추가하는 앱**

<img width="360" alt="스크린샷 2026-10-23 오전 7 42 34" src="https://github.com/user-attachments/assets/2ec3992a-35cd-4b18-add9-024ac7cf8324" />

<br>
<br>

**설치**

```bash
./gradlew installDebug
adb shell pm grant com.example.devtiles android.permission.WRITE_SECURE_SETTINGS
```
