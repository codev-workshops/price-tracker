# Regression Checklist

Functional flows to verify after each migration phase.

| # | Flow | Expected Behavior | Status |
|---|------|-------------------|--------|
| 1 | App launches to MainActivity | 4 bottom tabs visible: Home, Favorites, Notifications, More | ⬜ |
| 2 | Login flow | LoginActivity opens, form validates, API call fires | ⬜ |
| 3 | Register flow | RegisterActivity opens, form validates, API call fires | ⬜ |
| 4 | Home screen loads product lists | Top, Recent, Discounted products + Top Apps sections render | ⬜ |
| 5 | Favorites screen loads | Shows auth gate if logged out; product grid if logged in | ⬜ |
| 6 | Notifications screen loads | Shows auth gate if logged out; notification list if logged in | ⬜ |
| 7 | More screen navigates to sub-screens | About, Contact, Rate, Login/Logout, Register items work | ⬜ |
| 8 | Product detail with tabs | Detail and Alarms tabs render via ViewPager | ⬜ |
| 9 | Add product form | URL input, paste-from-clipboard, submit to API | ⬜ |
| 10 | Push notification handling | FCM message received → notification shown → tap opens correct screen | ⬜ |
| 11 | Logout clears session | Logout API call → session dropped → redirect to Home | ⬜ |
| 12 | Error handling (network, auth) | Network errors show toast; 401 redirects to Logout | ⬜ |
