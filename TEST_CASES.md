# Sign In - Test Cases

---

## Positive Test Cases

### Successful login with valid credentials
**Предусловия:**
- Открыта страница логина

**Шаги:**
1. Ввести валидный Email: `hf1bg@virgilian.com`
2. Ввести валидный пароль: `gdft1ywbo123`
3. Нажать кнопку **Sign In**

**Ожидаемый результат:**
- Пользователь успешно авторизован
- Открывается страница **Projects**

---

## Negative Test Cases — Email Field
### Login with empty Email field
**Шаги:**
1. Оставить поле Email пустым
2. Ввести валидный пароль
3. Нажать кнопку **Sign In**

**Ожидаемый результат:**
- Под полем Email отображается сообщение:  
  `This field is required`

---

### Email without domain
**Данные:** `hf1bg@`

**Шаги:**
1. Ввести Email без домена
2. Ввести валидный пароль
3. Нажать кнопку **Sign In**

**Ожидаемый результат:**
- Отображается сообщение ошибки:  
  `Value 'hf1bg@' does not match format email of type string`

---

### Email without username
**Данные:** `@virgilian.com`

**Ожидаемый результат:**
- Отображается сообщение ошибки:  
  `Value '@virgilian.com' does not match format email of type string`

---

### Email without symbol "@"
**Данные:** `hf1bgvirgilian.com`

**Ожидаемый результат:**
- Отображается сообщение ошибки:  
  `Value 'hf1bgvirgilian.com' does not match format email of type string`

---

### Email with leading space
**Данные:** `␣hf1bg@virgilian.com`

**Ожидаемый результат:**
- Отображается сообщение ошибки:  
  `Value ' hf1bg@virgilian.com' does not match format email of type string`

---

### Email with trailing space
**Данные:** `hf1bg@virgilian.com␣`

**Ожидаемый результат:**
- Отображается сообщение ошибки:  
  `Value 'hf1bg@virgilian.com ' does not match format email of type string`

---

## Negative Test Cases — Password Field

### Login with empty Password field
**Шаги:**
1. Ввести валидный Email
2. Оставить поле Password пустым
3. Нажать кнопку **Sign In**

**Ожидаемый результат:**
- Под полем Password отображается сообщение:  
  `This field is required`

---

### Login with invalid password
**Данные:** `wrongPassword`

**Шаги:**
1. Ввести валидный Email
2. Ввести невалидный пароль
3. Нажать кнопку **Sign In**

**Ожидаемый результат:**
- Пользователь перенаправляется на страницу **Password Reset**

---

## Negative Test Cases — Both Fields

### Login with empty Email and Password fields
**Шаги:**
1. Оставить поле Email пустым
2. Оставить поле Password пустым
3. Нажать кнопку **Sign In**

**Ожидаемый результат:**
- Под полем Email отображается сообщение:  
  `This field is required`
- Под полем Password отображается сообщение:  
  `This field is required`