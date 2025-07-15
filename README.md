# 📧 Email Writer AI – Chrome Extension with Gmail + Gemini + Spring Boot

**Email Writer AI** is a Chrome extension that integrates directly into Gmail and uses **Google's Gemini API** (via a Spring Boot backend) to generate context-aware replies with a single click. No prompt needed — just click **AI Reply**, and it crafts the email for you.

---

## 🌟 Features

- 🔗 Seamless Gmail Integration
- 🧠 Auto-reads email content for context
- 🖱️ One-click **AI Reply** — no typing required
- 🤖 Drafts responses using **Gemini API**
- 🔐 Secure Spring Boot backend for API communication

---

## Working

The **AI Reply** button appears directly inside Gmail’s reply box. When clicked, it automatically reads the email thread and uses Gemini to draft a complete, relevant reply.

---

## 🧠 How It Works

1. Open Gmail and select a conversation.
2. Click the ✨ `AI Reply` button that appears in the reply box.
3. The extension reads the email thread.
4. Sends the content to your Spring Boot backend.
5. The backend uses Gemini API to generate a reply.
6. The reply is auto-inserted into the Gmail compose area — ready to send.

---

## 🛠️ Tech Stack

| Part          | Tech                     |
|---------------|--------------------------|
| Extension UI  | HTML, CSS, JavaScript    |
| Backend       | Java, Spring Boot        |
| AI API        | Gemini API (REST-based)  |
| Gmail Access  | DOM Manipulation         |

---

